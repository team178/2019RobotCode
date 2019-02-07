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
import frc.robot.subsystems.Arduino;
import frc.robot.subsystems.LinearActuator;

public class AlignHatchPanel extends Command {
  Arduino pixyArduino;
  OI oi;
  LinearActuator linearactuator;

  public AlignHatchPanel() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    pixyArduino = Robot.arduino;
    linearactuator = Robot.linearactuator;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double desiredavg = 159;
    pixyArduino.checkForPixyValues();
    int firstLocation = pixyArduino.firstLocation;
    int secondLocation = pixyArduino.secondLocation;
    double x1 = (double) firstLocation;
    double x2 = (double) secondLocation; 
    double avg = (x1 + x2)/2;
    while(avg > (desiredavg  + 10) || avg < (desiredavg - 10)){
      double diff = desiredavg-avg;
      if (diff>desiredavg){
        linearactuator.moveActuator(false);
      } else {
        linearactuator.moveActuator(true);
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
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