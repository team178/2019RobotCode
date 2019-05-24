/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.*
import frc.robot.*;

public class AutoClimbPrep extends Command {

  private static Timer timer = new Timer();
  OI oi;
  Climber climber;

  //front climber times and speeds for level 2
  private static final double frontRaiseTimeExtend = 0;
  private static final double frontRaiseTimePrep = 0;
  private static final double frontRaiseSpeedPrep = 0;
  
  private int level;
  private boolean override;
  private String levelTwoPhase;
  private boolean levelTwoTopReached;
  private boolean startTimer;

  public AutoClimbPrep(int level) {
    requires(Robot.climber);
    this.level = level;
    override = false;
    levelTwoPhase = "extend front climber arms";
    levelTwoTopReached = false;
    startTimer = true;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    climber = Robot.climber;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    override = oi.getLeftStickYAux() >= 0.1 || oi.getRightStickYAux() >= 0.1;
    if (level == 2) {
      if (levelTwoPhase.equals("extend front climber arms") {
        //extend front climber arms
        if (startTimer) {
          startTimer();
          startTimer = false;
        }
        
        if (timer.seconds() <= frontRaiseTimeExtend) {
          climber.moveFrontMotors(0.9);
        } else {
          timer.stop();
          climber.moveFrontMotors(0);
          
          //change phase when finished
          levelTwoPhase = "reset front climber";
        }
      } else if (levelTwoPhase.equals("reset front climber")) {
         //reset front climber (front to bottom proximity sensor)
        if (!climber.isFrontClimberAtBottom()) {
          climber.moveFrontMotors(0.9);
        } else {
          climber.moveFrontMotors(0);
        }
        
        //reset back climber
        if (!climber.isBackClimberAtTop()) {
          climber.moveBackMotors(0.2);
        } else {
          climber.moveBackMotors(0);
        }
        
        //change phase when finished
        if (climber.isFrontClimberAtBottom && climber.isBackClimberAtTop()) {
          levelTwoPhase = "raise to level 2 height";
          startTimer = true;
        }
      } else if (levelTwoPhase.equals("raise to level 2 height")) {
        //raise front climber to necessary height
        if (startTimer) {
          startTimer();
          startTimer = false;
        }
        
        if (timer.seconds <= frontRaiseTimePrep) {
          climber.moveFrontMotors(frontRaiseSpeedPrep);
        } else {
          timer.stop();
          climber.moveFrontMotors(0);
          levelTwoTopReached = true;
        }
      }
    } else if (level == 3) {
      if (!climber.isFrontClimberAtTop()) {
        climber.moveFrontMotors(0.8);
      }
      if (!climber.isBackClimberAtTop()) {
        climber.moveBackMotors(0.2);
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (level == 2) {
      return override || levelTwoTopReached;
    } else if (level == 3) {
      return override || (climber.isFrontClimberAtTop() && climber.isBackClimberAtTop());
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    climber.moveFrontMotors(0);
    climber.moveBackMotors(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    climber.moveFrontMotors(0);
    climber.moveBackMotors(0);
  }
  
  private void startTimer() {
    timer.reset();
    timer.start();
  }
}
