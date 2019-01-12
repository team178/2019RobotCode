/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team178.robot.RobotMap;
import org.usfirst.frc.team178.robot.commands.*;

import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.Talon;

public class Drivetrain extends Subsystem {
	public static final double diameter = 6;
	public static final double gearRatio = 1; //figure out for main bot (5.95:1?)
	public static final double circumference = diameter * Math.PI;
	public static final double countsPerRevolution = 1024; //Units for encoders -- the encoder ticks 1024 times per rotation
	
	public static Talon left1;
    public static Talon left2;
    public static Talon left3;
    public static Talon right1;
    public static Talon right2;
    public static Talon right3;
    public static Encoder right;
    public static Encoder left;
    
    public Drivetrain() {
	    left1 = new Talon(RobotMap.DMTopLeft);
	    left2 = new Talon(RobotMap.DMMiddleLeft);
	    left3 = new Talon(RobotMap.DMBottomLeft);
	    right1 = new Talon(RobotMap.DMTopRight);
	    right2 = new Talon(RobotMap.DMMiddleRight);
	    right3 = new Talon(RobotMap.DMBottomRight);
	    right = new Encoder(RobotMap.DRIVEencoderRA,RobotMap.DRIVEencoderRB, false, Encoder.EncodingType.k4X);
	    left = new Encoder(RobotMap.DRIVEencoderLA, RobotMap.DRIVEencoderLB, true, Encoder.EncodingType.k4X);
	    
	    double dpp = gearRatio * (circumference/countsPerRevolution);
	    right.setDistancePerPulse(dpp); // must be changed for both right and left
	    left.setDistancePerPulse(dpp);   
    }
  
  public  void resetEncoders() {
    right.reset();
    left.reset();
  }
  
  public void rightDrive(double speed) {
    right1.set(speed);
    right2.set(speed);
    right3.set(speed);
  }
  
  public void leftDrive(double speed) {
    left1.set(speed);
    left2.set(speed);
    left3.set(speed);
  }
  
  public void drive(double leftMotors, double rightMotors) {
    left1.set(leftMotors);
    left2.set(leftMotors);
    left3.set(leftMotors);
    right1.set(rightMotors);
    right2.set(rightMotors);
    right3.set(rightMotors);
  }
  
  public double getLeftDistance() {
    return left.getDistance();
  }
  
  public double getRightDistance() {
    return right.getDistance();
  }
  
  public double getLeftSpeed() {
    return left.getRate();
  }
  
  public double getRightSpeed() {
    return right.getRate();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
