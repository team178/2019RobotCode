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
import frc.robot.subsystems.CargoLauncher;
import edu.wpi.first.wpilibj.DoubleSolenoid;


public class ScoreCargoLow extends Command {
  OI oi;
  CargoLauncher cargolauncher;

  public ScoreCargoLow() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.cargolauncher);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    oi = Robot.oi;
    cargolauncher = Robot.cargolauncher;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*check if "punch" solenoid is reverse 
    if yes: 
    set aim solenoid to reverse 
    if no: 
    set "punch" solenoid to reverse
    set aim solenoid to reverse 
    */
    if(cargolauncher.getShootSolenoidState() == DoubleSolenoid.Value.kReverse){
      cargolauncher.lowerLauncher();
    }
    else if (cargolauncher.getShootSolenoidState() == DoubleSolenoid.Value.kForward){
      cargolauncher.retract();
      
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    DoubleSolenoid.Value AimSolenoidState = cargolauncher.getAimSolenoidState();

    if (AimSolenoidState == DoubleSolenoid.Value.kReverse) {
      System.out.println("Finish shooting lower!");
      return true;
    } else {
      System.out.println("Not finish shooting lower. Try again: " + AimSolenoidState);
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    cargolauncher.raiseLauncher();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
