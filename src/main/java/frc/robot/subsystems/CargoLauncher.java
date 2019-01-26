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

  public static DoubleSolenoid cylinder1;
  public static DoubleSolenoid cylinder2;

  public CargoLauncher() {
    cylinder1 = new DoubleSolenoid(RobotMap.CargoCylinder1Input, RobotMap.CargoCylinder1Output);
    cylinder2 = new DoubleSolenoid(RobotMap.CargoCylinder2Input, RobotMap.CargoCylinder2Output);
  }

  public void shootCargoShip () {
    cylinder1.set(DoubleSolenoid.Value.kReverse);
    cylinder2.set(DoubleSolenoid.Value.kReverse);
  }

  public void shootRocket () {
    cylinder1.set(DoubleSolenoid.Value.kForward);
    cylinder2.set(DoubleSolenoid.Value.kForward);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
