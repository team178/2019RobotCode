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
public class EmptyClimber extends Climber 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
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

  public EmptyClimber() 
  {
      super();
    /*arm1 = new TalonSRX(RobotMap.ClimberMotor1);
    arm2 = new TalonSRX(RobotMap.ClimberMotor2);
    back1 = new TalonSRX(RobotMap.ClimberMotor3);
    back2 = new TalonSRX(RobotMap.ClimberMotor4);
    backWheel = new VictorSPX(RobotMap.ClimberBackWheel);
    //Varun - go to RobotMap and make a LimitSwitch input for each one (DIO)
    limitswitchTop1 = new DigitalInput(RobotMap.LimitSwitchTop1);
    limitswitchTop2 = new DigitalInput(RobotMap.LimitSwitchTop2);
    limitswitchBottom1 = new DigitalInput(RobotMap.LimitSwitchBottom1);
    limitswitchBottom2 = new DigitalInput(RobotMap.LimitSwitchBottom2);*/
  }

public boolean isFrontClimberAtTop () {
  return true;
  //return !limitswitchTop1.get();
}

public boolean isBackClimberAtTop () {
  return true; // !limitswitchTop2.get();
}

public boolean isFrontClimberAtBottom () {
  return true; //!limitswitchBottom1.get();
}

public boolean isBackClimberAtBottom () {
  return true; //!limitswitchBottom2.get();
}

public void moveFrontMotors (double power)  {
  //arm1.set(ControlMode.PercentOutput,-power);
  //arm2.set(ControlMode.PercentOutput, -power);
}
public void moveBackMotors (double power) {
  //back1.set(ControlMode.PercentOutput, -power);
  //back2.set(ControlMode.PercentOutput, -power);
}
public void moveArm1 (double power) {
  //arm1.set(ControlMode.PercentOutput, -power);
}
public void moveArm2 (double power) {
  //arm2.set(ControlMode.PercentOutput,-power);
}
public void moveBack1(double power) {
  //back1.set(ControlMode.PercentOutput, -power);
}
public void moveBack2(double power) {
  //back2.set(ControlMode.PercentOutput, -power);
}

public void moveBackWheel(double power) {
  //backWheel.set(ControlMode.PercentOutput,-power);
}

//Varun - Make a method like this for all 4
public boolean getTopSwitchStatuses() {
  //Returns boolean output of switch
  return true ; //(limitswitchTop1.get() && limitswitchTop2.get());
}
public boolean getBottomSwitchStatuses() {
  return  true; //(limitswitchBottom1.get() && limitswitchBottom2.get());
}
  @Override
  public void initDefaultCommand() 
  {
    //setDefaultCommand(new ClimberCommands());
  }
}
