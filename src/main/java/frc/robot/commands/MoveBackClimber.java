/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;

public class MoveBackClimber extends Command {
  OI oi;
  Climber climber;
  private double power;

  public MoveBackClimber() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.climber);
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
    power = oi.getLeftStickYAux();
    if (Math.abs(power) < 0.2){
      climber.moveBackMotors(0);
    } else if (climber.isBackClimberAtTop()) {
      if(power > 0){
        climber.moveBackMotors(0);
      }else if(power < 0){
        climber.moveBackMotors(power);
      }
    } else if (climber.isBackClimberAtBottom()) {
      if(power > 0) {
        climber.moveBackMotors(power);
      } else if(power < 0){
        climber.moveBackMotors(0);
      }
    } else {
      climber.moveBackMotors(power);
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
   // climber.moveBackMotors(0);
   //System.out.println("PushRobotUp is ending");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
   // climber.moveBackMotors(0);
   //System.out.println("PushRobotUp is being interrupted");
  }
}























































































//eclips












































//a