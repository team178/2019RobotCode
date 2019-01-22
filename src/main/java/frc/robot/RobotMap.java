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
  // All values are filler values to get rid of errors in other classes
  public static int DMTopRight = 0; //PWM
  public static int DMMiddleRight = 1; //PWM
  //public static int DMBottomRight = 2; //PWM
  public static int DMTopLeft = 3; //PWM
  public static int DMMiddleLeft = 4; //PWM
  //public static int DMBottomLeft = 5; //PWM
  public static int HatchMotor = 6; //PWM
  public static int ClimberMotor1 = 7; //PWM
  public static int ClimberMotor2 = 8; //PWM
  public static int ClimberMotor3 = 9; //PWM
  
  public static int PCM = 0; //CAN
  
  public static int CargoCylinder1Input = 0; //Analog
  public static int CargoCylinder1Output = 1; //Analog
  
  public static int CargoCylinder2Input = 2; //Analog
  public static int CargoCylinder2Output = 3; //Analog

  public static int HatchCylinder1Input = 4; //Analog
  public static int HatchCylinder1Output = 5; //Analog

  public static int HatchCylinder2Input = 6; //Analog
  public static int HatchCylinder2Output = 7; //Analog

  public static int HatchCylinder3Input = 8; //Analog
  public static int HatchCylinder3Output = 9; //Analog

  public static int HatchCylinder4Input = 10; //Analog
  public static int HatchCylinder4Output = 11; //Analog

  public static int DRIVEncoderRA = 0; //DIO
  public static int DRIVEncoderRB = 1; //DIO
  public static int DRIVEncoderLA = 2; //DIO
  public static int DRIVEncoderLB = 3; //DIO
  public static int HatchEncoder = 4; //DIO

  public static int JoystickPort = 0; //computer
  public static int JoystickPortXBOX = 1; //computer
}
