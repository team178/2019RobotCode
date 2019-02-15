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

public class XBoxDrive extends Command {

  OI oi;
  DriveTrain drivetrain;
  private double leftY;
  private double rightY;

  public XBoxDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  //left joystick controls left side of tank drive, right joystick controls right side
    leftY = oi.getLeftStickYMain();
    rightY = oi.getRightStickYMain();
    
    if(Math.abs(leftY) > 0.1 || Math.abs(rightY) > 0.1) {//joystick dead zone, where the motors stop giving a crap
      drivetrain.drive(leftY, rightY);
    } else {
      drivetrain.drive(0,0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drivetrain.drive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    drivetrain.drive(0,0);
  }
}
