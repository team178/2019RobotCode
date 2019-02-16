/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.Arduino;
import frc.robot.Robot;

public class SendMessage extends Command {

  OI oi;
  public Arduino lightsArduino;
  String t; 
  public boolean sent; 

  public SendMessage(String t) {
  this.t = t;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    lightsArduino = Robot.lightsArduino;
  }



  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sent = lightsArduino.sendMessage(t);
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