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

public class MoveFrontClimber extends Command {

  OI oi;
  Climber climber;
  private double power;

  public MoveFrontClimber() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.climber);
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
    power = oi.getRightStickYAux();
    if (Math.abs(power) < 0.2){
      climber.moveFrontMotors(0);
    } else if (climber.isFrontClimberAtTop()) {
      if(power > 0){
        climber.moveFrontMotors(0);
      }else if(power < 0){
        climber.moveFrontMotors(power);
      }
    } else if (climber.isFrontClimberAtBottom()) {
            if(power > 0) {
        climber.moveFrontMotors(power);
      } else if(power < 0){
        climber.moveFrontMotors(0);
      }

    } else {
      climber.moveFrontMotors(power);
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end()  
  {
    //System.out.println("EjectClimberArms has ended");
  }
  

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    //System.out.println("EjectClimberArms has been interrupted");
  }
}
