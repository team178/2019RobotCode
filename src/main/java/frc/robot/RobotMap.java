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

  //CAN IDs
  public static int DMTopRight = 1;
  public static int DMBottomRight = 2;
  public static int DMTopLeft = 3;
  public static int DMBottomLeft = 4; 
  public static int ClimberMotor1 = 5;
  public static int ClimberMotor2 = 6;
  public static int ClimberMotor3 = 7;
  public static int ClimberMotor4 = 8;
  public static int ClimberBackWheel = 9;
  public static int leadScrew = 10;
  public static int PCM = 11;
  
  //PCM  
  public static int cargoCylinderShootExtend = 0; 
  public static int cargoCylinderShootRetract = 1; 

  public static int hatchExtenderCylinderExtend = 2; 
  public static int hatchExtenderCylinderRetract = 3; 

  public static int hatchEjectorCylinderExtend = 4; 
  public static int hatchEjectorCylinderRetract = 5;

  //DIO ports
  public static int hatchLimitSwitchLeft = 2;
  public static int hatchLimitSwitchCenter = 3;
  public static int hatchLimitSwitchRight = 4;
  
  public static int proxSensorTopFront = 5;
  public static int proxSensorTopBack = 6;
  public static int proxSensorBottomFront = 7;
  public static int proxSensorBottomBack = 8;
  
  //Computer USB ports
  public static int ActualJoystick = 0;
  public static int JoystickPortXBoxAux = 1; 
  public static int JoystickPortXBoxMain = 2;

  //Arduino addresses
  public static int lightsAddress = 7;
  public static int pixyAddress = 8;

  public enum SubsystemIndex {
    ALL, BUMPER
  }
}
