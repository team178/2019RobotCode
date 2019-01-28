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
  public static DoubleSolenoid hatchCylinderExtend;
  public static DoubleSolenoid hatchCylinderEject;

  public HatchMechanism() {
    hatchMotor = new Talon(RobotMap.HatchMotor);
    encoder = new Encoder(RobotMap.HatchEncoder, 0);
    hatchCylinderExtend = new DoubleSolenoid(RobotMap.HatchCylinderExtendInput, RobotMap.HatchCylinderExtendOutput);
    hatchCylinderEject = new DoubleSolenoid(RobotMap.HatchCylinderEjectInput, RobotMap.HatchCylinderEjectOutput);

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

  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
