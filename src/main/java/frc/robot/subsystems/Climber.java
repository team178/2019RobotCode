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

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commandgroups.ClimberCommands;
import frc.robot.RobotMap;
import frc.robot.commands.ClimbDrive;
import edu.wpi.first.wpilibj.DigitalInput;

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
  public static DigitalInput limitswitchTop1;
  public static DigitalInput limitswitchTop2;
  public static DigitalInput limitswitchBottom1;
  public static DigitalInput limitswitchBottom2;

  public Climber() {
    //Motor controller initializations
    arm1 = new TalonSRX(RobotMap.ClimberMotor1);
    arm2 = new TalonSRX(RobotMap.ClimberMotor2);
    back1 = new TalonSRX(RobotMap.ClimberMotor3);
    back2 = new TalonSRX(RobotMap.ClimberMotor4);
    backWheel = new VictorSPX(RobotMap.ClimberBackWheel);
    
    //Limit switch/proximity sensor initializations
    limitswitchTop1 = new DigitalInput(RobotMap.LimitSwitchTop1);
    limitswitchTop2 = new DigitalInput(RobotMap.LimitSwitchTop2);
    limitswitchBottom1 = new DigitalInput(RobotMap.LimitSwitchBottom1);
    limitswitchBottom2 = new DigitalInput(RobotMap.LimitSwitchBottom2);
  }
  
  //Climbing methods
  public void moveFrontMotors(double power)  {
    arm1.set(ControlMode.PercentOutput,-power);
    arm2.set(ControlMode.PercentOutput, -power);
  }
  
  public void moveBackMotors(double power) {
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
    return (limitswitchTop1.get() || limitswitchTop2.get());
  }
  public boolean getBottomSwitchStatuses() {
    return (limitswitchBottom1.get() || limitswitchBottom2.get());
  }
  
  //Individual proximity sensor data
  public boolean isFrontClimberAtTop() {
    return !limitswitchTop1.get();
  }

  public boolean isBackClimberAtTop() {
    return !limitswitchTop2.get();
  }

  public boolean isFrontClimberAtBottom() {
    return !limitswitchBottom1.get();
  }

  public boolean isBackClimberAtBottom() {
    return !limitswitchBottom2.get();
  }
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberCommands());
  }
}
