/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class AutoClimb extends Command {
  
  OI oi;
  Climber climber;
  
  private final double POWER = 1.00;
  private double overidePowerFront;
  private double overridePowerBack;
  
  private boolean frontClimberFinished;
  private boolean rightClimberFinished;
  private boolean override;
  
  public AutoClimb() {
    requires(Robot.climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.OI;
    climber = Robot.climber;
    
    frontClimberFinished = false;
    rightClimberFinished = false;
    overrideControls = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    overridePowerFront = oi.getLeftStickYAux();
    overridePowerBack = -oi.getRightStickYAux();
    
    //Front climber
    if (Math.abs(overridePowerFront) > 0.1){
      overrideControls = true;
    } else if (climber.isFrontClimberAtBottom()) {
      climber.moveFrontMotors(0);
      frontClimberFinished = true;
    } else {
      climber.moveFrontMotors(POWER);
    }

    //Back climb
    if (Math.abs(overridePowerBack) > 0.1){
      overrideControls = true;
    } else if (climber.isBackClimberAtBottom()) {
      climber.moveBackMotors(0);
      backClimberFinished = true;
    } else {
      climber.moveBackMotors(-POWER);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (frontClimberFinished && backClimberFinished) || override;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    climber.moveBackMotors(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    climber.moveBackMotors(0);
  }
}
