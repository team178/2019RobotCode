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
import frc.robot.subsystems.Pixy;

public class LightsAlign extends Command {
  
  Arduino lightsArduino; 
  boolean sent;
  Pixy pixy;

  private boolean alignable;

  public LightsAlign() {
    requires(Robot.lightsArduino);
    alignable = false;  
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    pixy = Robot.pixy;
    lightsArduino = Robot.lightsArduino;
    alignable = pixy.canAutoAlign();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    alignable = pixy.canAutoAlign();
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
