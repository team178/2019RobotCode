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
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class ExtendCargoShooter extends Command {

  OI oi;
  CargoLauncher cargoLauncher;

  public ExtendCargoShooter() {
    requires(Robot.cargolauncher);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    cargoLauncher = Robot.cargolauncher;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    while (cargoLauncher.getRailSolenoidState() != DoubleSolenoid.Value.kForward) {
      cargoLauncher.open();
    }
    cargoLauncher.shoot();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (cargoLauncher.getShootSolenoidState() == DoubleSolenoid.Value.kForward) {
      return true;
    }
    return false;
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
