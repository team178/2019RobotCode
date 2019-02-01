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

public class PIDHatchPanelMvt extends Command {
  
  HatchMechanism hatchMechanism;
  OI oi;

  //TODO: Split all this up into two classes (one for cam, one for PID)
  private static final double LOOP_TIME = 0.02;
  private static final double FOV = Math.toRadians(75); //FOV in radians 
  private static final double distFromCargoShip = 0; //TODO: measure dist w/ bumpers against wall
  private static final double 
  
  private boolean sensingTarget;
  private double targetX;
  private double cameraX;
  
  private double setPoint;
  private double error;
  private double previousError;
  
  private double P, I, D;
  private double kP, kI, kD;
  
  public PIDHatchPanelMvt(double setPoint) {
    requires(Robot.hatchMechanism);
    requires(Robot.arduino);
    
    this.setPoint = setPoint;
    kP = 0;
    kI = 0;
    kD = 0;
    error = setPoint - hatchMechanism.getPosition();
    previousError = 0;
    sensingTarget = false;
  }

  //Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    hatchMechanism = Robot.hatchMechanism;
  }

  //Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (sensingTarget) {
      double output;
      retrievePixyValues();
      error = setPoint - hatchMechanism.getPosition(); //error = desired - actual

      //TODO: either set kI to 0 or implement integral active zones

      //P, I, and D w/o gains
      P = error;
      I += (error * LOOP_TIME);
      D = (error - previousError) / LOOP_TIME;

      //implementing gains
      P *= kD;
      I *= kI;
      D *= kD;

      output = P + I + D;
      hatchMechanism.slideHatch(output);
      previousError = error;
    }
   }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (Math.abs(setPoint - hatchMechanism.getPosition()) < 0.0001) {
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    hatchMechanism.slideHatch(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    hatchMechanism.slideHatch(0);
  }
  
  public void retrievePixyValues() {
    
  }
  
  public void calcSetpoint() {
    final double PIXELS_PER_INCH = 400 / (2 * distFromCargoShip * Math.tan(0.5 * FOV)); //how many cam pixels are in a real life inch
    double offset = targetX - cameraX; //positive -- mechanism is too much to the right; negative -- too much to left
    offset /= PIXELS_PER_INCH; //converting offset from pixels to inches
    
    //converting offset to encoder ticks
    offset /= hatchMechanism.gearRatio;
    offset *= hatchMechanism.countsPerRevolution;
    setPoint = offset; //setting setpoint in ticks (absolute setpoint, not relative to current position)
  }
}
