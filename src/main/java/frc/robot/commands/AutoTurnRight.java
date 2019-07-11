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
import frc.robot.subsystems.DriveTrain;

public class AutoTurnRight extends Command {

  private DriveTrain drivetrain;
  private OI oi;
  private double angle;
  private double speed;

  public AutoTurnRight(double ang, double spd) {
    requires(Robot.drivetrain);
    angle = ang;
    speed = spd;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    drivetrain = Robot.drivetrain;
    oi = Robot.oi;
    drivetrain.resetGyro();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    drivetrain.leftDrive(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (drivetrain.getAngle() < angle) {
      return true;
    }
      return false;
  }
  
  // Called once after isFinished returns true
  @Override
  protected void end() {
    drivetrain.drive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    drivetrain.drive(0, 0);
  }
}
