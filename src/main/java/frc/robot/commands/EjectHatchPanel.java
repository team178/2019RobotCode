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
import frc.robot.subsystems.HatchMechanism;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class EjectHatchPanel extends Command {
  OI oi; 
  HatchMechanism hatchmechanism;

  public EjectHatchPanel() {
    requires(Robot.hatchMechanism);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    hatchmechanism = Robot.hatchMechanism;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    DoubleSolenoid.Value isEjected = hatchmechanism.getEjectorSolenoidState();
    System.out.println("Ejector is out? " + isEjected);
    System.out.println("Try to go forward");
    hatchmechanism.setEjector("Forward");
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    DoubleSolenoid.Value EjectState = hatchmechanism.getEjectorSolenoidState();

    if (EjectState == DoubleSolenoid.Value.kForward) {
      System.out.println("Finished!");
      return true;
    } else {
      System.out.println("Not finished, trying again: " + EjectState);
      return false;
    }

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    hatchmechanism.setEjector("reverse");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    hatchmechanism.setEjector("reverse");
  }
}
