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

public class ExtendCargoShooter extends Command {
  OI oi;
  CargoLauncher cargolauncher;

  public ExtendCargoShooter() {
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

    /*
    check if the shooter is aiming up
       if yes:
        set shooter "punch" to forward
      if no:
      set shooter "punch" to reverse 
     */
    //if(cargolauncher.getAimSolenoidState() == DoubleSolenoid.Value.kForward){
      cargolauncher.shoot();
      //shoot ball
    //}
    //else if (cargolauncher.getAimSolenoidState() == DoubleSolenoid.Value.kReverse){
      cargolauncher.retract();
      //stay retracted
    //}
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    DoubleSolenoid.Value ShootSolenoidState = cargolauncher.getShootSolenoidState();
    //DoubleSolenoid.Value AimSolenoidState = cargolauncher.getAimSolenoidState();

    if (ShootSolenoidState == DoubleSolenoid.Value.kForward) {
      System.out.println("Finish shooting!");
      return true;
    //} else if(AimSolenoidState == DoubleSolenoid.Value.kReverse){
      //System.out.println("Could not shoot: Aimer is down. Try to raise aimer.");
      //cargolauncher.raiseLauncher();
      //return false;
    } else {
      System.out.println("Not finish shooting. Try again: " + ShootSolenoidState);
      return false;
    }

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
