/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.HatchMechanism;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.Timer;

public class FullHatchPlacement extends Command {

  HatchMechanism hatchMechanism;

  public FullHatchPlacement() {
    //requires(Robot.hatchMechanism);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    hatchMechanism = Robot.hatchMechanism;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //extend
    hatchMechanism.extendMechanism();
    //Timer.delay(1000);
    hatchMechanism.ejectPanel();
    //Timer.delay(500);

    //retract
    hatchMechanism.retractPanel();
    hatchMechanism.retractMechanism();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (hatchMechanism.getMechanismSolenoidState() == DoubleSolenoid.Value.kReverse) {
      if (hatchMechanism.getPanelSolenoidState() == DoubleSolenoid.Value.kReverse) {
        return true;
      }
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //hatchMechanism.retractMechanism();
    //hatchMechanism.retractPanel();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    hatchMechanism.retractMechanism();
    hatchMechanism.retractPanel();
  }
}
