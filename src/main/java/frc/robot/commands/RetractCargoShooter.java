/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.CargoLauncher;

public class RetractCargoShooter extends Command {

  OI oi;
  CargoLauncher cargoLauncher;

  public RetractCargoShooter() {
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
    while (cargoLauncher.getShootSolenoidState() != DoubleSolenoid.Value.kReverse) {
      cargoLauncher.retract();
    }
    cargoLauncher.close();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (cargoLauncher.getShootSolenoidState() == DoubleSolenoid.Value.kReverse){
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
