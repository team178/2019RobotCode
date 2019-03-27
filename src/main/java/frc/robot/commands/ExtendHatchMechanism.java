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
import frc.robot.subsystems.Arduino;
import edu.wpi.first.wpilibj.DoubleSolenoid;


public class ExtendHatchMechanism extends Command {
  OI oi;
  HatchMechanism hatchmechanism;
  Arduino lightsArduino;



  public ExtendHatchMechanism() {
    requires(Robot.hatchMechanism);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      oi = Robot.oi;
     hatchmechanism = Robot.hatchMechanism;
     lightsArduino = Robot.lightsArduino;
    }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    lightsArduino.sendMessage("h");
    DoubleSolenoid.Value isExtended = hatchmechanism.getExtenderSolenoidState();
    System.out.println("Extender is out? " + isExtended);
    System.out.println("Try to go out");
    hatchmechanism.setExtender("Forward");

    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() { 
   DoubleSolenoid.Value ExtendState = hatchmechanism.getExtenderSolenoidState();

    if (ExtendState == DoubleSolenoid.Value.kForward) {
      System.out.println("Finished!");
      return true;
    } else {
      System.out.println("Not finished, trying again: " + ExtendState);
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
