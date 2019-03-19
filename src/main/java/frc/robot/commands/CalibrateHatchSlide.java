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

public class CalibrateHatchSlide extends Command {
  
  private static Timer timer = new Timer();
  OI oi;
  HatchMechanism hatchMechanism;
  
  private String state;
  private double calibratedTimeToCenter;
  
  public CalibrateHatchSlide() {
    requires(Robot.hatchMechanism);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    hatchMechanism = Robot.hatchMechanism;
    state = "move left";
    calibratedTimeToCenter = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (state.equals("move left")) {
      hatchMechanism.moveLeadScrew(false, hatchMechanism.LINEAR_CALIBRATION_SPEED);
      if (hatchMechanism.hasReachedLeftBound()) {
        state = "move right";
        timer.start();
      }
    } else if (state.equals("move right")) {
      hatchMechanism.moveLeadScrew(true, hatchMechanism.LINEAR_CALIBRATION_SPEED);
      if (hatchMechanism.hasReachedRightBound()) {
        calibratedTimeToCenter = timer.get() / 2;
        state = "reached right";
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return state.equals("reached right");
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    hatchMechanism.moveLeadScrew(true, 0);
    System.out.println("Approximate time to center mechanism: " + calibratedTimeToCenter + " seconds");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    hatchMechanism.moveLeadScrew(true, 0);
  }
}
