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

  public static int DMTopRight = 1; //CAN
  public static int DMBottomRight = 2; //CAN
  public static int DMTopLeft = 3; //CAN
  public static int DMBottomLeft = 4; //CAN

  public static int HatchMotor = 6; //CAN

  public static int ClimberMotor1 = 7; //CAN
  public static int ClimberMotor2 = 8; //CAN
  public static int ClimberMotor3 = 9; //CAN
  public static int ClimberMotor4 = 10; //CAN
  
  public static int PCM = 5; //PCM
  public static int PressureTranducer = 0; //Analog
  
  public static int CargoCylinderAimInput = 0; //PCM
  public static int CargoCylinderAimOutput = 1; //PCM
  
  public static int CargoCylinderShootInput = 2; //PCM
  public static int CargoCylinderShootOutput = 3; //PCM

  public static int HatchExtenderCylinderExtend = 4; //PCM
  public static int HatchExtenderCylinderRetract = 5; //PCM

  public static int HatchEjectorCylinderExtend = 7; //PCM
  public static int HatchEjectorCylinderRetract = 6; //PCM

  public static int DRIVEncoderRA = 0; //DIO
  public static int DRIVEncoderRB = 1; //DIO
  public static int DRIVEncoderLA = 2; //DIO
  public static int DRIVEncoderLB = 3; //DIO
  public static int HatchEncoder = 4; //DIO
  public static int LimitSwitchTop1 = 5; //DIO
  public static int LimitSwitchTop2 = 6; //DIO
  public static int LimitSwitchBottom1 = 7; //DIO
  public static int LimitSwitchBottom2 = 8; //DIO

  public static int JoystickPort = 0; //computer
  public static int JoystickPortXBOX = 1; //computer

  public static int ArduinoAddress = 8;

  public enum SubsystemIndex {
    ALL, BUMPER
  }
}
