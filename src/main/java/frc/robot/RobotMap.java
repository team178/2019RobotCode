/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  //CAN
  public static int DMTopRight = 1;
  public static int DMBottomRight = 2;
  public static int DMTopLeft = 3;
  public static int DMBottomLeft = 4;

  //CAN
  public static int ClimberBackWheel = 5;
  public static int ClimberMotor1 = 6;
  public static int ClimberMotor2 = 7;
  public static int ClimberMotor3 = 8;
  public static int ClimberMotor4 = 9;
  
  public static int PCM = 10;

  //Analog
  public static int PressureTranducer = 0;
  
  //PCM
  public static int CargoCylinderAimExtend = 0; 
  public static int CargoCylinderAimRetract = 1; 
  
  public static int CargoCylinderShootExtend = 2; 
  public static int CargoCylinderShootRetract = 3; 

  public static int HatchExtenderCylinderExtend = 4; 
  public static int HatchExtenderCylinderRetract = 5; 

  public static int HatchEjectorCylinderExtend = 7; 
  public static int HatchEjectorCylinderRetract = 6; 

  //PWM
  public static int linearactuator = 0;

  //DIO
  public static int LimitSwitchTop1 = 5;
  public static int LimitSwitchTop2 = 6;
  public static int LimitSwitchBottom1 = 7; 
  public static int LimitSwitchBottom2 = 8; 

  //computer
  public static int JoystickPortXBoxMain = 0; 
  public static int JoystickPortXBoxAux = 1; 

  //arduino
  public static int pixyAddress1 = 8;
  public static int pixyAddress2 = 9;
  public static int tofAddressL = 18;//left and right
  public static int tofAddressR = 19;
  public static int lightsAddress = 7;

  public enum SubsystemIndex {
    ALL, BUMPER
  }
}
