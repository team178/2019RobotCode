/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.*;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.CargoLauncher;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class ExtendCargoAimer extends Command {
  OI oi;
  CargoLauncher cargolauncher;

  public ExtendCargoAimer() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.cargolauncher);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    cargolauncher = Robot.cargolauncher;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(cargolauncher.getAimSolenoidState() == DoubleSolenoid.Value.kReverse){
      cargolauncher.raiseLauncher();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (cargolauncher.getAimSolenoidState()==DoubleSolenoid.Value.kForward ){
      return true;
    }
    else {
      return false;
    }
  }

  // Called once afte isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
