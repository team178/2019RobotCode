/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.*;
import frc.robot.*;

public class PIDDriveForward extends Command {
  OI oi;
  DriveTrain drivetrain;
  Robot robot;

  double robotSpeed, distance;
  double currentErrorLeft, currentErrorRight;
  double previousErrorLeft, previousErrorRight;
  boolean resetGyro;
  static int counter;

  //Actually PID codes
  double aP = 0, aI = 0, aD = 0;
  double PRight, PLeft, ILeft, IRight, DLeft, DRight;

  public PIDDriveForward(double dist, double speed, boolean resetG) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    requires(Robot.drivetrain);
    distance = dist;
    robotSpeed = speed;
    resetGyro = resetG;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    drivetrain = Robot.drivetrain;
    previousErrorLeft = robotSpeed;
    previousErrorRight = robotSpeed;
    if(resetGyro)
      drivetrain.resetGyro();
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Get error in variable

    PLeft = currentErrorLeft * aP;
    PRight = currentErrorRight * aP;

    ILeft += (currentErrorLeft * aI);
    IRight += (currentErrorRight * aI);

    DLeft = (currentErrorLeft - previousErrorLeft) * aD;
    DRight = (currentErrorRight - previousErrorRight) * aD;

    double powerLeft = PLeft + ILeft + DLeft;
    double powerRight = PRight + IRight + DRight;

    drivetrain.drive(powerLeft, powerRight);

    currentErrorLeft = previousErrorLeft;
    currentErrorRight = previousErrorRight;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
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
  }
}
