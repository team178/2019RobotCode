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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class DriveTrain extends Subsystem {

  public static VictorSPX left1;
  public static VictorSPX left2;
  public static VictorSPX right1;
  public static VictorSPX right2;
    
  public DriveTrain() {
	  left1 = new VictorSPX(RobotMap.DMTopLeft);
	  left2 = new VictorSPX(RobotMap.DMBottomLeft);
	  right1 = new VictorSPX(RobotMap.DMTopRight);
	  right2 = new VictorSPX(RobotMap.DMBottomRight);
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
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new XBoxDrive());
  }
}
