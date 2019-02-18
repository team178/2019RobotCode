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

public class AlignHatchPanel extends Command 
{
  Pixy pixy;
  OI oi;
  HatchMechanism hatchmechanism;
  double diff;
  private final int TOLERANCE = 10;

  public AlignHatchPanel() {
    //requires(Robot.hatchMechanism);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    oi = Robot.oi;
    pixy = Robot.pixy;
    hatchmechanism = Robot.hatchMechanism;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    
    double desiredavg = 157.0;//checks if the pixy is inbetween the two pieces of tape
    pixy.updateTargetValues();
    int firstLocation = pixy.getLeft();
    int secondLocation = pixy.getRight();
    double x1 = (double) firstLocation;
    double x2 = (double) secondLocation; 
    double avg = (x1 + x2)/2.0;
    if (avg > 0)
    {
      diff = desiredavg - avg;
    }
    else
    {
      diff = avg - desiredavg - avg;
    }
    boolean triggerPressed = false;

    if(Math.abs(diff) > TOLERANCE && !triggerPressed) {
      if (oi.getLeftTriggerAux() != 0 || oi.getRightTriggerAux() != 0) {
        hatchmechanism.moveLeadScrew(true, oi.getRightTriggerAux() - oi.getLeftTriggerAux());
        triggerPressed = true;
      } 
      else 
      {
        if (diff > 0) 
        {
          hatchmechanism.moveLeadScrew(false, 0.5);
          //switched false and true because test screw is in wrong direction
        } 
        else 
        {
          hatchmechanism.moveLeadScrew(true, 0.5);
        }
      }
    }
    else
    {
      hatchmechanism.moveLeadScrew(true, 0);
    }
    pixy.updateTargetValues();
    firstLocation = pixy.getLeft();
    secondLocation = pixy.getRight();
    x1 = (double) firstLocation;
    x2 = (double) secondLocation; 
    avg = (x1 + x2)/2.0;

    diff = desiredavg-avg;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Math.abs(diff) < TOLERANCE);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
  }
  

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    hatchmechanism.moveLeadScrew(true, 0);
  }
}
