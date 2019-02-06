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


public class RetractHatchMechanism extends Command {
  OI  oi;
  HatchMechanism hatchmechanism;

  public RetractHatchMechanism() {
    //requires(Robot.hatchMechanism);
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
    hatchmechanism.retractPanel();
    hatchmechanism.retractMechanism();
    while (true) {
      if (hatchmechanism.getMechanismSolenoidState() == DoubleSolenoid.Value.kForward) {
        System.out.println("forward");
      } else {
        System.out.println("reverse");
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    /*if (hatchmechanism.getMechanismSolenoidState() == DoubleSolenoid.Value.kReverse) {
      if (hatchmechanism.getPanelSolenoidState() == DoubleSolenoid.Value.kReverse) {
        return true;
      }
    }*/
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
