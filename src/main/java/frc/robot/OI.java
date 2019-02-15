/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//MAIN controller buttons
	public static Joystick xboxMain = new Joystick(RobotMap.JoystickPortXBoxMain); //Controller
	public Button mainA = new JoystickButton(xboxMain, 1);
	public Button mainB = new JoystickButton(xboxMain, 2);
	public Button mainx = new JoystickButton(xboxMain, 3);
	public Button mainy = new JoystickButton(xboxMain, 4);
	public Button mainLeftBumper = new JoystickButton(xboxMain, 5);
	public Button mainRightBumper = new JoystickButton(xboxMain, 6);
	public Button mainBack = new JoystickButton(xboxMain, 7);
  	public Button mainStart = new JoystickButton(xboxMain, 8);
	
	//AUX controller buttons
	public static Joystick xboxAux = new Joystick(RobotMap.JoystickPortXBoxAux); //Controller
	public Button auxA = new JoystickButton(xboxAux, 1);
	public Button auxB = new JoystickButton(xboxAux, 2);
	public Button auxX = new JoystickButton(xboxAux, 3);
	public Button auxY = new JoystickButton(xboxAux, 4);
	public Button auxLeftBumper = new JoystickButton(xboxAux, 5);
	public Button auxRightBumper = new JoystickButton(xboxAux, 6);
	public Button auxBack = new JoystickButton(xboxAux, 7);
  	public Button auxStart = new JoystickButton(xboxAux, 8);
  
	public OI() {
		//MAIN controls (joystick code in JoystickDrive)
		
		
		//AUX controls (TODO: put climb controls in Climb command)
		
	}

	//MAIN controller accessor methods
 	public double getLeftStickYMain() {
		return xboxMain.getRawAxis(2);
	}
	
	public double getRightStickYMain() {
		return xboxMain.getRawAxis(5);
	}
	
	public double getLeftTriggerMain() {
		return xboxMain.getRawAxis(3);
	}
	
	public double getLeftTriggerMain() {
		return xboxMain.getRawAxis(4);
	}
	
	//AUX controller accessor methods
	public double getLeftStickYAux() {
		return xboxMain.getRawAxis(2);
	}
	
	public double getRightStickYAux() {
		return xboxMain.getRawAxis(5);
	}
	
	public double getLeftTriggerAux() {
		return xboxMain.getRawAxis(3);
	}
	
	public double getLeftTriggerAux() {
		return xboxMain.getRawAxis(4);
	}
}
