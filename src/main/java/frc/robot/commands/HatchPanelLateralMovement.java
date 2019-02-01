/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.*;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj.command.Command;

public class HatchPanelLateralMovement extends Command {
  OI oi;
  HatchMechanism hatchmechanism;
  private double speed;
  public HatchPanelLateralMovement(double spd) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
   // requires(Robot.hatchMechanism);
    speed = spd;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
   // hatchmechanism = Robot.hatchMechanism;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    hatchmechanism.slideHatch(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    hatchmechanism.slideHatch(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    hatchmechanism.slideHatch(0);
  }
}
