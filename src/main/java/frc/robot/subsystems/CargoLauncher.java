/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CargoLauncher extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static DoubleSolenoid cargoCylinderAim;
  public static DoubleSolenoid cargoCylinderShoot;

  public CargoLauncher() {
    cargoCylinderAim = new DoubleSolenoid(RobotMap.CargoCylinderAimInput, RobotMap.CargoCylinderShootInput);
    cargoCylinderShoot = new DoubleSolenoid(RobotMap.CargoCylinderShootInput, RobotMap.CargoCylinderShootOutput);
  }

  public void aim () {
    cargoCylinderAim.set(DoubleSolenoid.Value.kForward);
  }

  public void unAim () {
    cargoCylinderAim.set(DoubleSolenoid.Value.kReverse);
  }

  public void shoot () {
    cargoCylinderShoot.set(DoubleSolenoid.Value.kForward);
  }

  public void releaseShooter () {
    cargoCylinderShoot.set(DoubleSolenoid.Value.kReverse);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
