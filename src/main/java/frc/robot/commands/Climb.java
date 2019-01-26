/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Climber;
import frc.robot.*;

public class Climb extends Command {

  OI oi;
  Climber climber;
  private double power;

  public Climb(double pwr) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.climber);
    power = pwr;
  }
 
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    climber = Robot.climber;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (power > 0) {
      climber.climb(power);
    } else if (power < 0) {
      climber.unclimb(power);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end()  {
    climber.climb(0);
  }
  

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    climber.climb(0);
  }
}
