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

  private String gimpDriveDirection;

  public ClimbDrive(String gimpDriveDirection) {
    // we removed the climber dependency so the default command does not stop
    //requires(Robot.climber);
    this.gimpDriveDirection = gimpDriveDirection.toLowerCase();
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
    double power = oi.getSlider();

    if (gimpDriveDirection.equals("forward")) {
      power *= -1;
    }

    if (gimpDriveDirection.equals("forward") || gimpDriveDirection.equals("backward")) {
      climber.moveBackWheel(power);
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
    climber.moveBackWheel(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    climber.moveBackWheel(0);
  }
}
