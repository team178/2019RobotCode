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

  private final int tolerance = 10;

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
    
    double desiredavg = 157.0;//checks if the pixy is inbetween the two pieces of tape
    pixy.updateTargetValues();
    int firstLocation = pixy.getLeft();
    int secondLocation = pixy.getRight();
    double x1 = (double) firstLocation;
    double x2 = (double) secondLocation; 
    double avg = (x1 + x2)/2.0;
    double diff = desiredavg-avg;
    boolean triggerPressed = false;

    if(Math.abs(diff) > tolerance && !triggerPressed) {
      if (oi.getLeftTriggerAux() != 0 || oi.getRightTriggerAux() != 0) {
        hatchmechanism.moveLeadScrew(true, oi.getRightTriggerAux() - oi.getLeftTriggerAux());
        triggerPressed = true;
      } else {
        if (diff > 0) {
          hatchmechanism.moveLeadScrew(false, 0.5);
        } else {
          hatchmechanism.moveLeadScrew(true, 0.5);
        }
        
        pixy.updateTargetValues();
        firstLocation = pixy.getLeft();
        secondLocation = pixy.getRight();
        x1 = (double) firstLocation;
        x2 = (double) secondLocation; 
        avg = (x1 + x2)/2.0;

        diff = desiredavg-avg;
      }
    }
    else
    {
      hatchmechanism.moveLeadScrew(true, 0);
    }

  }

  // Make this retur n true when this Command no longer needs to run execute()
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
