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
	
	/*
  	public static Joystick joystick1 = new Joystick(RobotMap.JoystickPort);
  	public Button button1 = new JoystickButton(joystick1, 1);
	public Button button2 = new JoystickButton(joystick1, 2);
	public Button button3 = new JoystickButton(joystick1, 3);
	public Button button4 = new JoystickButton(joystick1, 4);
	public Button button5 = new JoystickButton(joystick1, 5);
	public Button button6 = new JoystickButton(joystick1, 6);
	public Button button7 = new JoystickButton(joystick1, 7);
	*/
	//joystick drive

	public static Joystick xbox1 = new Joystick(RobotMap.JoystickPortXBox);
	public Button buttonA1 = new JoystickButton(xbox1, 1);
	public Button buttonB1= new JoystickButton(xbox1, 2);
	public Button buttonX1 = new JoystickButton(xbox1, 3);
	public Button buttonY1 = new JoystickButton(xbox1, 4);
	public Button lBumper1 = new JoystickButton(xbox1, 5);
	public Button rBumper1 = new JoystickButton(xbox1, 6);
	public Button backButton1 = new JoystickButton(xbox1, 7);
	public Button startButton1 = new JoystickButton(xbox1, 8);
	
	
	public static Joystick xbox2 = new Joystick(RobotMap.JoystickPortXBoxAux);
	public Button buttonA2 = new JoystickButton(xbox2, 1);
	public Button buttonB2 = new JoystickButton(xbox2, 2);
	public Button buttonX2 = new JoystickButton(xbox2, 3);
	public Button buttonY2 = new JoystickButton(xbox2, 4);
	public Button lBumper2 = new JoystickButton(xbox2, 5);
	public Button rBumper2 = new JoystickButton(xbox2, 6);
	public Button backButton2 = new JoystickButton(xbox2, 7);
	public Button startButton2 = new JoystickButton(xbox2, 8);
	//xbox drive
	
  
	public OI() {

	}

 	public double getX() {
		return xbox1.getX();
	}
	
	public double getY() {
		return xbox1.getY();
	}

	public double getLeftY()
	{
		return xbox1.getRawAxis(1);
	}

	public double getRightX()
	{
		return xbox1.getRawAxis(4);
	}
	
	/*
	public double getTwist() {
		return joystick1.getRawAxis(3);
	}
	
	public double getTrigger() {
		return joystick1.getRawAxis(3);
	}
	*/
}
	
