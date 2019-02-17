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
import frc.robot.subsystems.HatchMechanism;
import frc.robot.subsystems.Pixy;

public class AlignHatchPanel extends Command {
  Pixy pixy;
  OI oi;
  HatchMechanism hatchmechanism;

  public AlignHatchPanel() {
    //requires(Robot.hatchMechanism);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    pixy = Robot.pixy;
    hatchmechanism = Robot.hatchMechanism;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    double desiredavg = 157;//checks if the pixy is inbetween the two pieces of tape
    Pixy.updateTargetValues();
    int firstLocation = Pixy.getLeft();
    int secondLocation = Pixy.getRight();
    double x1 = (double) firstLocation;
    double x2 = (double) secondLocation; 
    double avg = (x1 + x2)/2;
    while(avg > (desiredavg  + 10) || avg < (desiredavg - 10)) {
      double diff = desiredavg-avg;
      if (diff > 0) {
        hatchmechanism.moveLeadScrew(true, 1.0);
      } else {
        hatchmechanism.moveLeadScrew(false, 1.0);
      }
      Pixy.updateTargetValues();
      firstLocation = Pixy.getLeft();
      secondLocation = Pixy.getRight();
      x1 = (double) firstLocation;
      x2 = (double) secondLocation; 
      avg = (x1 + x2)/2;
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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