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
  
  public static DoubleSolenoid railSolenoid;
  public static DoubleSolenoid shootSolenoid;

  public CargoLauncher() {
    railSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.cargoRailCylinderExtend, RobotMap.cargoRailCylinderRetract);
    shootSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.cargoCylinderShootExtend, RobotMap.cargoCylinderShootRetract);
    close();
    retract();
  }

  public void open() {
    railSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void close() {
    railSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
  
  public void shoot() {
    shootSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void retract() {
    shootSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public DoubleSolenoid.Value getRailSolenoidState() {
    return railSolenoid.get();
  }
  
  public DoubleSolenoid.Value getShootSolenoidState() {
    return shootSolenoid.get();
  }

  @Override
  public void initDefaultCommand() {
    
  }
}
