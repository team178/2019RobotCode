/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.LinearActuator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitUntilCommand;

public class MoveActuator extends Command {

    LinearActuator linearactuator;
    double currentPosition;
    boolean movingForward;

  public MoveActuator(boolean forward) {
   // requires(Robot.linearactuator);
    movingForward = forward;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    linearactuator = Robot.linearactuator;
    currentPosition = linearactuator.get();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (movingForward) {
      if (currentPosition < 1 || currentPosition >= 0) {
        currentPosition+=0.004;
        linearactuator.set(currentPosition);
      }
    } else {
      if (currentPosition <= 1 || currentPosition > 0) {
        currentPosition-=0.004;
        linearactuator.set(currentPosition);
      }
    }
    System.out.println(linearactuator.get());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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