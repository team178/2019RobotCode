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

public class ManuallyMoveLeadScrew extends Command {

    private boolean movingForward;
    private double factor;
    private HatchMechanism hatchmechanism;

  public ManuallyMoveLeadScrew(boolean fwd, double f) {
   // requires(Robot.linearactuator);
    requires(Robot.hatchMechanism);
    movingForward = fwd;
    factor = f;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    hatchmechanism = Robot.hatchMechanism;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    hatchmechanism.moveLeadScrew(movingForward, factor);
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