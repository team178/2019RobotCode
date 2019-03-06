/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.ClimbDrive;
import frc.robot.commands.MoveFrontClimber;
import frc.robot.commands.MoveBackClimber;

public class ClimberCommands extends CommandGroup {
  
  /**
   * Adding all climber commands in one command group to add one default command in the Climber subsystem
   */
  
  public ClimberCommands(String climbDriveForward) {
    //May need to switch to addParallel()
    addParallel(new MoveFrontClimber());
    addParallel(new MoveBackClimber());
    addParallel(new ClimbDrive(climbDriveForward));
  }
}
