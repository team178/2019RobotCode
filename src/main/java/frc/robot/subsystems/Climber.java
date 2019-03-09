/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;
import frc.robot.commandgroups.*;
import frc.robot.commands.MoveBothClimbers;


/**
 * Add your docs here.
 */

public class Climber extends Subsystem  {
  
  public static TalonSRX arm1;
  public static TalonSRX arm2;
  public static TalonSRX back1;
  public static TalonSRX back2;
  public static VictorSPX backWheel;
  
  //Proximity Sensors (4)
  public static DigitalInput proxSensorTopFront;
  public static DigitalInput proxSensorTopBack;
  public static DigitalInput proxSensorBottomFront;
  public static DigitalInput proxSensorBottomBack;

  private String gimpDriveDirection = "";

  public Climber() {
    //Motor controller initializations
    arm1 = new TalonSRX(RobotMap.ClimberMotor1);
    arm2 = new TalonSRX(RobotMap.ClimberMotor2);
    back1 = new TalonSRX(RobotMap.ClimberMotor3);
    back2 = new TalonSRX(RobotMap.ClimberMotor4);
    backWheel = new VictorSPX(RobotMap.ClimberBackWheel);
    
    //Limit switch/proximity sensor initializations
    proxSensorTopFront = new DigitalInput(RobotMap.proxSensorTopFront);
    proxSensorTopBack = new DigitalInput(RobotMap.proxSensorTopBack);
    proxSensorBottomFront = new DigitalInput(RobotMap.proxSensorBottomFront);
    proxSensorBottomBack = new DigitalInput(RobotMap.proxSensorBottomBack);
  }
  
  //Climbing methods
  public void moveFrontMotors(double power)  {
    power *= 0.5;
    arm1.set(ControlMode.PercentOutput,-power);
    arm2.set(ControlMode.PercentOutput, -power);
  }
  
  public void moveBackMotors(double power) {
    power *= 0.5;
    back1.set(ControlMode.PercentOutput, -power);
    back2.set(ControlMode.PercentOutput, -power);
  }
  
  //Front climb methods
  public void moveArm1(double power) {
    arm1.set(ControlMode.PercentOutput, -power);
  }
  
  public void moveArm2(double power) {
    arm2.set(ControlMode.PercentOutput,-power);
  }
  
  //Back climb methods
  public void moveBack1(double power) {
    back1.set(ControlMode.PercentOutput, -power);
  }
  
  public void moveBack2(double power) {
    back2.set(ControlMode.PercentOutput, -power);
  }
  
  public void moveBackWheel(double power) {
    backWheel.set(ControlMode.PercentOutput,-power);
  }
  
  //Proximity sensor data
  public boolean getTopSwitchStatuses() {
    return (proxSensorTopFront.get() || proxSensorTopBack.get());
  }
  public boolean getBottomSwitchStatuses() {
    return (proxSensorBottomFront.get() || proxSensorBottomBack.get());
  }
  
  //Individual proximity sensor data
  public boolean isFrontClimberAtTop() {
    return !proxSensorTopFront.get();
  }

  public boolean isBackClimberAtTop() {
    return !proxSensorTopBack.get();
  }

  public boolean isFrontClimberAtBottom() {
    return !proxSensorBottomFront.get();
  }

  public boolean isBackClimberAtBottom() {
    return !proxSensorBottomBack.get();
  }

  public void setGimpDriveDirection(String gimpDriveDirection) {
    this.gimpDriveDirection = gimpDriveDirection;
  }
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveBothClimbers());
  }
}
