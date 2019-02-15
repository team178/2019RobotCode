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

public class ManuallyMoveActuator extends Command {

    LinearActuator linearactuator;
    private double currentPosition;
    private boolean movingForward;
    private double setPoint;

  public MoveActuator(double setPoint, boolean forward) {
   // requires(Robot.linearactuator);
    this.setPoint = setPoint;
    movingForward = forward;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    linearactuator = Robot.linearactuator;
    currentPosition = linearactuator.getPosition();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (movingForward) {
      if (currentPosition < 1 || currentPosition >= 0) {
        currentPosition+=0.004;
        linearactuator.setPosition(currentPosition);
      }
    } else {
      if (currentPosition <= 1 || currentPosition > 0) {
        currentPosition-=0.004;
        linearactuator.setPosition(currentPosition);
      }
    }
    System.out.println(linearactuator.getPosition());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(linearactuator.getPosition() == this.setPoint)
    {
      return true;
    }
    else
    {
      return false;
    }

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