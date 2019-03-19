/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.HatchMechanism;
import edu.wpi.first.wpilibj.command.Command;

public class ManuallyMoveLeadScrew extends Command {

    private OI oi;
    private HatchMechanism hatchMechanism;

  public ManuallyMoveLeadScrew() {
    requires(Robot.hatchMechanism);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    hatchMechanism = Robot.hatchMechanism;
    oi = Robot.oi;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftVal = -oi.getLeftTriggerAux();
    double rightVal = oi.getRightTriggerAux();
    double totalVal = leftVal + rightVal;

    if (hatchMechanism.hasReachedLeftBound()) {
      if (totalVal < 0) {
        hatchMechanism.moveLeadScrew(true, 0);
      } else {
        hatchMechanism.moveLeadScrew(true, totalVal);
      }
    } else if (hatchMechanism.hasReachedRightBound()) {
      if (totalVal > 0) {
        hatchMechanism.moveLeadScrew(true, 0);
      } else {
        hatchMechanism.moveLeadScrew(true, totalVal);
      }
    } else {
      hatchMechanism.moveLeadScrew(true, totalVal);
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
    hatchMechanism.moveLeadScrew(true, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    hatchMechanism.moveLeadScrew(true, 0);
  }
}