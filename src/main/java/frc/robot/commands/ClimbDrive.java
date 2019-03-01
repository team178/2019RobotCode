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

public class ClimbDrive extends Command {

  OI oi;
  Climber climber;
  double leftVal;
  double rightVal;

  boolean forward;

  public ClimbDrive(boolean forward) {
    requires(Robot.climber);
    this.forward = forward;
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
    leftVal = oi.getLeftTriggerMain();
    rightVal = -oi.getRightTriggerMain();
    double totalVal = leftVal + rightVal;

    if (!forward) {
      totalVal *= -1;
    }
    climber.moveBackWheel(totalVal);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
