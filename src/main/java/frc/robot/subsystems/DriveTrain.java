/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj.Encoder;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DriveTrain extends Subsystem {
	public static final double diameter = 6;
	public static final double gearRatio = 1; //figure out for main bot (5.95:1?)
	public static final double circumference = diameter * Math.PI;
	public static final double countsPerRevolution = 1024; //Units for encoders -- the encoder ticks 1024 times per rotation
	
	public static TalonSRX left1;
    public static TalonSRX left2;
    public static TalonSRX right1;
    public static TalonSRX right2;
    //public static Encoder right;
    //public static Encoder left;
    
    public DriveTrain() {
	    left1 = new TalonSRX(RobotMap.DMTopLeft);
	    left2 = new TalonSRX(RobotMap.DMBottomLeft);
	    right1 = new TalonSRX(RobotMap.DMTopRight);
	    right2 = new TalonSRX(RobotMap.DMBottomRight);
    }

  public void rightDrive(double speed) {
    right1.set(ControlMode.PercentOutput, speed);
    right2.set(ControlMode.PercentOutput, speed);
  }
  
  public void leftDrive(double speed) {
    left1.set(ControlMode.PercentOutput, speed);
    left2.set(ControlMode.PercentOutput, speed);
   }
  
  public void drive(double leftMotors, double rightMotors) {
    left1.set(ControlMode.PercentOutput, leftMotors);
    left2.set(ControlMode.PercentOutput, leftMotors);
    right1.set(ControlMode.PercentOutput, rightMotors);
    right2.set(ControlMode.PercentOutput, rightMotors);
  }

  public void resetGyro() {
    //implemented later
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //setDefaultCommand(new JoystickDrive());
    setDefaultCommand(new XBoxDrive());
  }
}
