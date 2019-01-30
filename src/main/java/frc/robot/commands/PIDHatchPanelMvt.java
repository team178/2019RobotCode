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
import frc.robot.RobotMap.SubsystemIndex;

public class PIDHatchPanelMvt extends Command {
  
  HatchMechanism hatchMechanism;
  OI oi;
  
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
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    hatchMechanism = Robot.hatchMechanism;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double output;
    retrievePixyValues();
    error = setPoint - hatchMechanism.getPosition(); //error = desired - actual
    
    //TODO: either set kI to 0 or implement integral active zones
    
    P = error * kP;
    I += (error * kI);
    D = (currentError - previousError) * kD;
    output = P + I + D;
    
    hatchMechanism.slideHatch(output);
    previousError = error;
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
    hatchMechanism.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    hatchMechanism.set(0);
  }
  
  public void retrievePixyValues() {
    
  }
}
