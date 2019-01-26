/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.Talon;

/**
 * Add your docs here.
 */
public class HatchMechanism extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static final int countsPerRevolution = 1024;
  public static final int gearRatio = 1;
  public static final double maxDistance = 3; 

  public static Talon hatchMotor;
  public static Encoder encoder;
  public static DoubleSolenoid hatchCylinder1;
  public static DoubleSolenoid hatchCylinder2;
  public static DoubleSolenoid hatchCylinder3;
  public static DoubleSolenoid hatchCylinder4;

  public HatchMechanism() {
    hatchMotor = new Talon(RobotMap.HatchMotor);
    encoder = new Encoder(RobotMap.HatchEncoder, RobotMap.HatchEncoder);
    hatchCylinder1 = new DoubleSolenoid(RobotMap.HatchCylinder1Input, RobotMap.HatchCylinder1Output);
    hatchCylinder2 = new DoubleSolenoid(RobotMap.HatchCylinder2Input, RobotMap.HatchCylinder2Output);
    hatchCylinder3 = new DoubleSolenoid(RobotMap.HatchCylinder3Input, RobotMap.HatchCylinder3Output);
    hatchCylinder4 = new DoubleSolenoid(RobotMap.HatchCylinder4Input, RobotMap.HatchCylinder4Output);

    double dpp = gearRatio * (maxDistance/countsPerRevolution);
    encoder.setDistancePerPulse(dpp);
  }

  public void slideHatch(double speed){
      hatchMotor.set(speed);
  }

  public double getPosition(){
      return encoder.getDistance();
  }

  public double getRate(){
      return encoder.getRate();
  }

  public void resetEncoder(){
      encoder.reset();
  }

  public void extendHatchMechanism () {
      hatchCylinder1.set(DoubleSolenoid.Value.kForward);
      hatchCylinder2.set(DoubleSolenoid.Value.kForward);
      hatchCylinder3.set(DoubleSolenoid.Value.kForward);
      hatchCylinder4.set(DoubleSolenoid.Value.kForward);
  }

  public void retractHatchMechanism () {
      hatchCylinder1.set(DoubleSolenoid.Value.kReverse);
      hatchCylinder2.set(DoubleSolenoid.Value.kReverse);
      hatchCylinder3.set(DoubleSolenoid.Value.kReverse);
      hatchCylinder4.set(DoubleSolenoid.Value.kReverse);
  }

  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
