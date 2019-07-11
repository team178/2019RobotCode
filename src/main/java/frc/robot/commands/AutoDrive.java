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

public class AutoDrive extends Command {
  DriveTrain drivetrain;
  OI oi;
  double distance;
  double speed;

  public AutoDrive(double dist, double spd) {
    requires(Robot.drivetrain);
    distance = dist;
    speed = spd;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    drivetrain = Robot.drivetrain;
    oi = Robot.oi;
    drivetrain.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    drivetrain.drive(speed, speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (drivetrain.getRightDistance() < distance) {
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
