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

public class PIDHatchPanelMvt extends Command {
  
  HatchMechanism hatchMechanism;
  Pixy pixy;
  OI oi;

  private static final double LOOP_TIME = 0.02;
  //private static final double FOV = Math.toRadians(75); //FOV in radians 
  //private static final double distFromCargoShip = 0;
  
  private final double DESIREDAVG = 157.5;//desired distance between the two objects that pixy recognizes 
  private final double TOLERANCE = 7;
  
  private boolean triggerPressed; //if the trigger is pressed, used for the purpose of an override 
  private boolean limitSwitchTripped; //if a limit switch is tripped, thing will stop
  private boolean sensingBothTargets; //if cam is not sensing both targets, motors will not run
  
  private double error;
  private double previousError;
  
  private double P, I, D;
  private double kP, kI, kD;
  
  public PIDHatchPanelMvt() {
    requires(Robot.hatchMechanism);
    
    kP = 0;
    kI = 0;
    kD = 0;
  }

  //Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    hatchMechanism = Robot.hatchMechanism;
    pixy = Robot.pixy;
  }

  //Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    triggerPressed = false;
    limitSwitchTripped = false;
    sensingBothTargets = true;

    pixy.updateTargetValues();//gets pixy values

    double x1 = (double) pixy.getLeft();//casts to double, in order to divide accurately 
    double x2 = (double) pixy.getRight(); 
    double avg = (x1 + x2)/2.0;
    error = DESIREDAVG - avg;

    if (hatchMechanism.hasReachedLeftBound() || hatchMechanism.hasReachedRightBound()) {
      hatchMechanism.moveLeadScrew(true, 0);
      triggerPressed = true;
    } else if (oi.getLeftTriggerAux() != 0 || oi.getRightTriggerAux() != 0) {
      hatchMechanism.moveLeadScrew(true, 0);
      limitSwitchTripped = true;
    } else if (x1 == 0 || x2 == 0 || x1 == 316 || x2 == 316) {
      hatchMechanism.moveLeadScrew(true, 0);
      sensingBothTargets = false;
    } else {
      //P, I, and D w/o gains
      P = error;
      I += (error * LOOP_TIME);
      D = (error - previousError) / LOOP_TIME;
  
      //implementing gains
      P *= kD;
      I *= kI;
      D *= kD;
  
      double output = P + I + D;
      hatchMechanism.moveLeadScrew(true, output);
      previousError = error;
    }
   }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(error) <= TOLERANCE || triggerPressed || limitSwitchTripped || !sensingBothTargets;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    hatchMechanism.moveLeadScrew(true, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    hatchMechanism.moveLeadScrew(true, 0);
  }
}
