/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.*;
import edu.wipi.first.wpilibj.DigitalInput;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static Talon climber1;
  public static Talon climber2;
  public static Talon climber3;
  public static Talon climber4;
  //Proximity Sensors (4)
  public static DigitalInput limitswitch1;

  public Climber() {
    climber1 = new Talon(RobotMap.ClimberMotor1);
    climber2 = new Talon(RobotMap.ClimberMotor2);
    climber3 = new Talon(RobotMap.ClimberMotor3);
    climber4 = new Talon(RobotMap.ClimberMotor4);
    //Varun - go to RobotMap and make a LimitSwitch input for each one (DIO)
    limitswitch1 = new DigitalInput(RobotMap.LimitSwitch)
  }

public void climb(double power)  {
  power = Math.abs(power);
  if (!getSwitchStatus){
    moveAllClimbers(power);
  }
  else
    moveAllClimbers(0);
}

public void unclimb(double power)  {
  //Varun - Same idea as climb, just use bottom sensors
  power = Math.abs(power);
  climber1.set(-power);
  climber2.set(-power);
  climber3.set(-power);
  climber4.set(-power);
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
  climber3.set(power);
}
public void moveClimber4(double power) {
  climber4.set(power);
}

//Varun - Make a method like this for all 4
public boolean getSwitchStatus(){
  //Returns boolean output of switch
  return limitswitch1.get();
}
  @Override
  public void initDefaultCommand() {
    
  }
}
