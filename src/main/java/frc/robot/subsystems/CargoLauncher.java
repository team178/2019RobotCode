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

  public static DoubleSolenoid aimCylinder;
  public static DoubleSolenoid shootCylinder;

  public CargoLauncher() {
    aimCylinder = new DoubleSolenoid(RobotMap.PCM,RobotMap.CargoCylinderAimExtend, RobotMap.CargoCylinderAimRetract);
    shootCylinder = new DoubleSolenoid(RobotMap.PCM, RobotMap.CargoCylinderShootExtend, RobotMap.CargoCylinderShootRetract);
  }

  public void raiseLauncher () {
    aimCylinder.set(DoubleSolenoid.Value.kForward);
  }

  public void lowerLauncher () {
    aimCylinder.set(DoubleSolenoid.Value.kReverse);
  }

  public void shoot () {
    shootCylinder.set(DoubleSolenoid.Value.kForward);
  }

  public void retract () {
    shootCylinder.set(DoubleSolenoid.Value.kReverse);
  }

  public DoubleSolenoid.Value getAimSolenoidState() {
    return aimCylinder.get();
  }

  public DoubleSolenoid.Value getShootSolenoidState() {
    return shootCylinder.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
