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

public class AlignHatchPanel extends Command 
{
  Pixy pixy;
  OI oi;
  HatchMechanism hatchmechanism;

  double diff;
  private final int TOLERANCE = 15;
  private final double DESIREDAVG = 157.5;//desired distance between the two objects that pixy recognizes 
  private boolean triggerPressed;//if the trigger is pressed, used for the purpose of an override 

  public AlignHatchPanel() {
    requires(Robot.hatchMechanism);
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
    triggerPressed = false; 
    pixy.updateTargetValues();//gets pixy values

    int firstLocation = pixy.getLeft();
    int secondLocation = pixy.getRight();
    double x1 = (double) firstLocation;//casts to double, in order to divide accurately 
    double x2 = (double) secondLocation; 
    double avg = (x1 + x2)/2.0;
    diff = DESIREDAVG - avg;//calc difference based on distance from desired point, sign indicated direction needed to move 
    double power = oi.getRightTriggerAux() - oi.getLeftTriggerAux();
    SmartDashboard.putString("Test Align", "Difference: " + diff);
    if (hatchmechanism.hasReachedLeftBound()) {
      if (power < 0) {
        hatchmechanism.moveLeadScrew(true, 0);
      } else {
        hatchmechanism.moveLeadScrew(true, power);
      }
    } else if (hatchmechanism.hasReachedRightBound()) {
      if (power > 0) {
        hatchmechanism.moveLeadScrew(true, 0);
      } else {
        hatchmechanism.moveLeadScrew(true, power);
      }
    } else {
      if (oi.getLeftTriggerAux() != 0 || oi.getRightTriggerAux() != 0) {
        triggerPressed = true;
      } else {
        if (x1 == 0 || x2 == 0) {
          hatchmechanism.moveLeadScrew(true, 0);
        } else if (x1 == 316 || x2 == 316) {
          hatchmechanism.moveLeadScrew(true, 0);
        } else if (diff > 0) {
          hatchmechanism.moveLeadScrew(false, 1);
        } else {
          hatchmechanism.moveLeadScrew(true, 1);
        }
      }
    }

    /*    if (oi.getLeftTriggerAux() != 0 || oi.getRightTriggerAux() != 0) {//check for interruption, manual override with triggers 
      //hatchmechanism.moveLeadScrew(true, oi.getRightTriggerAux() - oi.getLeftTriggerAux());
      triggerPressed = true;
    } else {

      if (x1 == 0 || x2 == 0) {//cant do that, need to take out
      hatchmechanism.moveLeadScrew(true, 0);
    } else if (Math.abs(x1 - 316) < 0.0001 || Math.abs(x2 - 316) < 0.0001) {
      hatchmechanism.moveLeadScrew(true, 0);
      SmartDashboard.putString("Test Align", "Diff is 0");
    } else if (diff > 0) {
        hatchmechanism.moveLeadScrew(false, 1); //switched false and true because test screw is in wrong direction
        SmartDashboard.putString("Test Align", "Trying to go right");
      } else {
        hatchmechanism.moveLeadScrew(true, 1);
        SmartDashboard.putString("Test Align", "Trying to go left");
      }
    } */
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Math.abs(diff) < TOLERANCE || triggerPressed);
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
