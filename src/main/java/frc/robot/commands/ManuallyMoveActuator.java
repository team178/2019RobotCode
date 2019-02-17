/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.HatchMechanism;
import edu.wpi.first.wpilibj.command.Command;

public class ManuallyMoveActuator extends Command {

    private boolean movingForward;
    private double setPoint;
    private HatchMechanism hatchmechanism;

  public ManuallyMoveActuator(boolean forward) {
   // requires(Robot.linearactuator);
    requires(Robot.hatchMechanism);
    movingForward = forward;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    hatchmechanism = Robot.hatchMechanism;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    hatchmechanism.moveActuator(movingForward, 0.004);
    System.out.println(Robot.hatchMechanism.getActuatorPosition());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.hatchMechanism.getActuatorPosition() == this.setPoint)
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