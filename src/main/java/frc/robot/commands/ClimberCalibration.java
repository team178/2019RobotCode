/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.*;
import frc.robot.*;

public class ClimberCalibration extends Command {
  
  private static Timer timer = new Timer();
  OI oi;
  Climber climber;
  
  private double power;
  
  public ClimberCalibration(double power) {
    requires(Robot.climber);
    this.power = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.reset();
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    climber.moveFrontMotors(power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return oi.trigger.get();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    climber.moveFrontMotors(0);
    System.out.println("TIME TO TARGET: " + timer.get() + " SECONDS");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    climber.moveFrontMotors(0);
  }
}
