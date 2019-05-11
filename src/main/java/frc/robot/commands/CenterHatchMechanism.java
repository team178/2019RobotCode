/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.HatchMechanism;

public class CenterHatchMechanism extends Command {

  private static Timer timer = new Timer();
  OI oi;
  HatchMechanism hatchMechanism;

  private static final double timeToCenter = 0.73; //0.73 for alpha bot; 0.69 for beta bot
  private boolean reachedCenter;

  public CenterHatchMechanism() {
    requires(Robot.hatchMechanism);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    hatchMechanism = Robot.hatchMechanism;
    reachedCenter = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    hatchMechanism.leadScrewToLeft();
    timer.start();
    while (timer.get() < timeToCenter) {
      hatchMechanism.moveLeadScrew(true, hatchMechanism.LINEAR_CALIBRATION_SPEED);
    }
    reachedCenter = true;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return reachedCenter;
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
