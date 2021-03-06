/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import javax.swing.plaf.synth.SynthStyle;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.HatchMechanism;

public class RetractEjector extends Command {
  OI oi; 
  HatchMechanism hatchmechanism; 
  public RetractEjector() {
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
    DoubleSolenoid.Value isRetracted = hatchmechanism.getEjectorSolenoidState();
    System.out.println("Ejector is in?" + isRetracted);
    System.out.println("Try to retract");
    hatchmechanism.setEjector("Reverse");
  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    DoubleSolenoid.Value EjectState = hatchmechanism.getEjectorSolenoidState();

    if (EjectState == DoubleSolenoid.Value.kReverse){
        return true;
    } else {
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
