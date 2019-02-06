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

import edu.wpi.first.wpilibj.AnalogInput;

import edu.wpi.first.wpilibj.Talon;

/**
 * Add your docs here.
 */
public class HatchMechanism extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static Talon hatchMotor;
  public static Encoder encoder;
  public static DoubleSolenoid hatchCylinderExtend;
  public static DoubleSolenoid hatchCylinderEject;
  public static AnalogInput pressureTransducer;

  public static final int countsPerRevolution = 1024;
  public static final int gearRatio = 1;
  public static final double maxDistance = 3; 

  public HatchMechanism() {
    hatchMotor = new Talon(RobotMap.HatchMotor);
    encoder = new Encoder(RobotMap.HatchEncoder, 0);
    hatchCylinderExtend = new DoubleSolenoid(RobotMap.PCM, RobotMap.HatchCylinderExtendInput, RobotMap.HatchCylinderExtendOutput);
    hatchCylinderEject = new DoubleSolenoid(RobotMap.PCM, RobotMap.HatchCylinderEjectInput, RobotMap.HatchCylinderEjectOutput);
    pressureTransducer = new AnalogInput(RobotMap.PressureTranducer);

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

  public void extendMechanism() {
      System.out.println("Setting hatchCylinder to kForward...");
      hatchCylinderExtend.set(DoubleSolenoid.Value.kForward);
  }

  public void ejectPanel() {
      hatchCylinderEject.set(DoubleSolenoid.Value.kForward);
  }

  public void retractMechanism() {
      hatchCylinderExtend.set(DoubleSolenoid.Value.kReverse);
  }

  public void retractPanel() {
      hatchCylinderEject.set(DoubleSolenoid.Value.kReverse);
  }

  public DoubleSolenoid.Value getMechanismSolenoidState() {
    return hatchCylinderExtend.get();
  }

  public DoubleSolenoid.Value getPanelSolenoidState() {
    return hatchCylinderEject.get();
  }

  public double getPressure() {
      return pressureTransducer.getVoltage();
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
