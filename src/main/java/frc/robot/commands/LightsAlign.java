/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Arduino;

public class LightsAlign extends Command {
  
  Arduino lightsArduino; 
  boolean sent;

  private boolean alignable;

  public LightsAlign(boolean alignable) {
    requires(Robot.lightsArduino);
    this.alignable = alignable;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    lightsArduino = Robot.lightsArduino;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (alignable) {
      sent = lightsArduino.sendMessage("a");
    } else {
      sent = lightsArduino.sendMessage("x");
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return sent;
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
