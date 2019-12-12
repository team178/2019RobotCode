/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class DriveByTime extends Command {

  private DriveTrain driveTrain;
  
  private static Timer timer;
  private double leftPower;
  private double rightPower;
  private double time;

  public DriveByTime(double leftPower, double rightPower, double time) {
    requires(Robot.drivetrain);
    this.leftPower = leftPower;
    this.rightPower = rightPower;
    this.time = time;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    driveTrain = Robot.drivetrain;
    timer = new Timer();
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    driveTrain.drive(-leftPower, -rightPower);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return timer.get() > time;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    driveTrain.drive(0, 0);
    timer.stop();
    timer.reset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}