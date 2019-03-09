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
import frc.robot.subsystems.Climber;

public class MoveBothClimbers extends Command {

  OI oi;
  Climber climber;
  private double frontPower;
  private double backPower;

  public MoveBothClimbers() {
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
    frontPower = oi.getLeftStickYAux();
    backPower = -oi.getRightStickYAux();

    //Front climber
    if (Math.abs(frontPower) < 0.1){
      climber.moveFrontMotors(0);
    } else if (climber.isFrontClimberAtTop()) {
      if(frontPower > 0){
        climber.moveFrontMotors(0);
      }else if(frontPower < 0){
        climber.moveFrontMotors(frontPower);
      }
    } else if (climber.isFrontClimberAtBottom()) {
      if(frontPower > 0) {
        climber.moveFrontMotors(frontPower);
      } else if(frontPower < 0){
        climber.moveFrontMotors(0);
      }
    } else {
      climber.moveFrontMotors(frontPower);
    }

    //Back climb
    if (Math.abs(backPower) < 0.1){
      climber.moveBackMotors(0);
    } else if (climber.isBackClimberAtTop()) {
      if(backPower > 0){
        climber.moveBackMotors(0);
      } else if(backPower < 0){
        climber.moveBackMotors(backPower);
      }
    } else if (climber.isBackClimberAtBottom()) {
      if(backPower > 0) {
        climber.moveBackMotors(backPower);
      } else if(backPower < 0){
        climber.moveBackMotors(0);
      }
    } else {
      climber.moveBackMotors(backPower);
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
    climber.moveFrontMotors(0);
    climber.moveBackMotors(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    climber.moveFrontMotors(0);
    climber.moveBackMotors(0);
  }
}
