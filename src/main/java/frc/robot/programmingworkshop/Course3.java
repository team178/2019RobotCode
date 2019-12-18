/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.programmingworkshop;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DriveByTime;

public class Course3 extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Course3() {
    System.out.println("yeet");
    addSequential(new DriveByTime(0.3, 0.5, 1));
  }
}
