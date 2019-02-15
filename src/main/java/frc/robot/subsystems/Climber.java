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
import frc.robot.RobotMap;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  public static Victor climber1;
  public static Victor climber2;
  public static TalonSRX climber3;
  public static TalonSRX climber4;
  public static Victor backWheel;
  //Proximity Sensors (4)
  public static DigitalInput limitswitchTop1;
  public static DigitalInput limitswitchTop2;
  public static DigitalInput limitswitchBottom1;
  public static DigitalInput limitswitchBottom2;

  public Climber() {
    climber1 = new Victor(RobotMap.ClimberMotor1);
    climber2 = new Victor(RobotMap.ClimberMotor2);
    climber3 = new TalonSRX(RobotMap.ClimberMotor3);
    climber4 = new TalonSRX(RobotMap.ClimberMotor4);
    backWheel = new Victor(RobotMap.ClimberBackWheel);
    //Varun - go to RobotMap and make a LimitSwitch input for each one (DIO)
    limitswitchTop1 = new DigitalInput(RobotMap.LimitSwitchTop1);
    limitswitchTop2 = new DigitalInput(RobotMap.LimitSwitchTop2);
    limitswitchBottom1 = new DigitalInput(RobotMap.LimitSwitchBottom1);
    limitswitchBottom2 = new DigitalInput(RobotMap.LimitSwitchBottom2);

  }

public void climb(double power)  {
  power = Math.abs(power);
  if (!getTopSwitchStatuses()){
    moveAllClimbers(power);
  }
  else
    moveAllClimbers(0);
}

  

public void unclimb(double power)  {
  //Varun - Same idea as climb, just use bottom sensors
  power = Math.abs(power);
  if (!getBottomSwitchStatuses()){
    moveAllClimbers(-power);
  }
  else
    moveAllClimbers(0);
}

public void moveAllClimbers(double power){
    climber1.set(power);
    climber2.set(power);
    climber3.set(power);
    climber4.set(power);
}
public void moveClimber1(double power)  {
  climber1.set(power);
}
public void moveClimber2(double power)   {
climber2.set(power);
}
public void moveClimber3(double power)   {
  climber3.set(ControlMode.PercentOutput, power);
}
public void moveClimber4(double power) {
  climber4.set(ControlMode.PercentOutput, power);
}

public void moveBackWheel(double power) {
  backWheel.set(power);
}

//Varun - Make a method like this for all 4
public boolean getTopSwitchStatuses(){
  //Returns boolean output of switch
  return (limitswitchTop1.get() && limitswitchTop2.get());
}
public boolean getBottomSwitchStatuses(){
  return (limitswitchBottom1.get() && limitswitchBottom2.get());
}
  @Override
  public void initDefaultCommand() {
    
  }
}
