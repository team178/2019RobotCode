/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import frc.robot.commands.*;
import frc.robot.Robot;

public class HatchPickupExtend extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HatchPickupExtend() {
    requires(Robot.hatchMechanism);
    addSequential(new EjectHatchPanel());
    addSequential(new WaitCommand(0.3));
    addSequential(new ExtendHatchMechanism());
  }
}
