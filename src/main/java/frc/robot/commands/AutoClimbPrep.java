/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Climber; 
import frc.robot.subsystems.Arduino;
import frc.robot.*;

public class AutoClimbPrep extends Command {

  OI oi;
  Climber climber;

  private int level;
  private boolean override;

  public AutoClimbPrep(int level) {
    requires(Robot.climber);
    this.level = level;
    override = false;
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
    override = getLeftStickYAux() >= 0.1 || getRightStickYAux() >= 0.1;
    if (level == 2) {
      
    } else if (level == 3) {
      if (!climber.isFrontClimberAtTop()) {
        climber.moveFrontMotors(0.8);
      }
      if (!climber.isBackClimberAtTop()) {
        climber.moveBackMotors(0.2);
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (level == 2) {
      
    } else if (level == 3) {
      return override || (climber.isFrontClimberAtTop() && climber.isBackClimberAtTop());
    }
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
