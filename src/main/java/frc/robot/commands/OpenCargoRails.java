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
import frc.robot.subsystems.CargoLauncher;
import frc.robot.subsystems.Arduino;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class OpenCargoRails extends Command {

  OI oi;
  CargoLauncher cargoLauncher;
  Arduino lightsArduino;

  public OpenCargoRails() {
    requires(Robot.cargolauncher);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    cargoLauncher = Robot.cargolauncher;
    lightsArduino = Robot.lightsArduino;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    lightsArduino.sendMessage("c");
    cargoLauncher.open();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return cargoLauncher.getRailSolenoidState() == DoubleSolenoid.Value.kForward;
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
