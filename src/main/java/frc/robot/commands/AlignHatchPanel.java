/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.HatchMechanism;
import frc.robot.subsystems.Pixy;
import frc.robot.subsystems.Arduino;
import frc.robot.subsystems.DriveTrain;

public class AlignHatchPanel extends Command 
{
  Pixy pixy;
  Arduino lightsArduino;
  OI oi;
  HatchMechanism hatchmechanism;
  DriveTrain drivetrain;

  double diff;
  private final int TOLERANCE = 5;
  private final double DESIREDAVG = 157.5;//desired distance between the two objects that pixy recognizes 
  private boolean triggerPressed;//if the trigger is pressed, used for the purpose of an override 
  private boolean direction; //true = right; false = left

  /**
   * @param mode "left" or "right" based off of position of hatch panel port
   */
  public AlignHatchPanel() {
    requires(Robot.hatchMechanism);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    oi = Robot.oi;
    pixy = Robot.pixy;
    lightsArduino = Robot.lightsArduino;
    hatchmechanism = Robot.hatchMechanism;
    drivetrain = Robot.drivetrain;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {

    //gets x values of both objects from pixy and determines off centeredness
    System.out.println("Left:" + pixy.getLeft() + ", Right:" + pixy.getRight());
    triggerPressed = false; 
    pixy.updateTargetValues();//gets pixy values
    System.out.println("Status: " + pixy.getObjectInfo());
    int x1 = pixy.getLeft();
    int x2 = pixy.getRight();
    double avg = (x1 + x2)/2.0;
    diff = DESIREDAVG - avg;//calc difference based on distance from desired point, sign indicated direction needed to move 
    double power = oi.getRightTriggerAux() - oi.getLeftTriggerAux();
    SmartDashboard.putString("Test Align", "Difference: " + diff);
    /*
     *
     * DO i have 2 targets?
     * no: tell the drivers that we have more or less than 2 targets, do nothing. Light message: x (red) 
     * yes: 
     *    calculate the center of the targets
     *    compare to center of the camera.
     *    if zero (ish): tell the drivers that they are centered, do nothing. Light message: a (green) 
     *    if nonzero:
     *        Direction I need to go: (positive = right, negative = left)
     *        Direction I'm not allowed to go: (right, left, or none)
     * 
     *        if direction i need to go == direction i'm not allowed to go:
     *            tell the drivers they are out of range (and which direction, if possible). 
     *            Light message: a (red)
     *        else:
     *        Attempt to go in the above direction.
     *        Light message: (blue) 
     */
    
     if (pixy.getNumObjects() == 2) 
     {
       if (Math.abs(diff) < TOLERANCE)
        {
          //centered; tell drivers
          hatchmechanism.moveLeadScrew(true, 0);
          lightsArduino.sendMessage("a");
          SmartDashboard.putString("AutoAlign Status", "Finished!");
        }
        else
        {
          //try to align
          if (diff > 0) direction = false;
          else if (diff < 0) direction = true;
          if( (hatchmechanism.hasReachedLeftBound() && !direction) || (hatchmechanism.hasReachedRightBound() && direction) )
          {
            // can't move i guess
            hatchmechanism.moveLeadScrew(true, 0);
            if(direction){
              SmartDashboard.putString("AutoAlign Status", "Need to go right");
            } else {
              SmartDashboard.putString("AutoAlign Status", "Need to go left");
            }
          } else 
          {
            hatchmechanism.moveLeadScrew(direction, 1);
            SmartDashboard.putString("AutoAlign Status", "trying to center...");
          }
        }
     } 
     else 
     {
       //more or less than 2 targets
       //tell drivers it don't work 
       SmartDashboard.putString("AutoAlign Status", "wrong # of targets");
       
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
    hatchmechanism.moveLeadScrew(true, 0);
  }
  

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    hatchmechanism.moveLeadScrew(true, 0);
  }

}
