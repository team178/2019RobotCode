/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class AutoClimb extends Command {
  
  OI oi;
  Climber climber;
  
  private final double POWER = 1.00;
  private double overidePowerFront;
  private double overridePowerBack;
  private boolean finished;
  
  public AutoClimb() {
    requires(Robot.climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.OI;
    climber = Robot.climber;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    overridePowerFront = oi.getLeftStickYAux();
    overridePowerBack = -oi.getRightStickYAux();
    
    //Front climber
    if (Math.abs(overridePowerFront) < 0.1){
      climber.moveFrontMotors(0);
      finished = true;
    } else if (climber.isFrontClimberAtTop()) {
      climber.moveFrontMotors(0);
      finished = true;
    } else {
      climber.moveFrontMotors(POWER);
    }

    //Back climb
    if (Math.abs(overridePowerBack) < 0.2){
      climber.moveBackMotors(0);
      finished = true;
    } else if (climber.isBackClimberAtTop()) {
      climber.moveBackMotors(0);
      finished = true;
    } else {
      climber.moveBackMotors(-POWER);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    climber.moveBackMotors(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    climber.moveBackMotors(0);
  }
}
