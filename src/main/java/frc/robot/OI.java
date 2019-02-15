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
	
	public static Joystick xboxMain = new Joystick(RobotMap.JoystickPortXBoxMain);
	public Button mainA = new JoystickButton(xboxMain, 1);
	public Button mainB = new JoystickButton(xboxMain, 2);
	public Button mainx = new JoystickButton(xboxMain, 3);
	public Button mainy = new JoystickButton(xboxMain, 4);
	public Button mainLeftBumper = new JoystickButton(xboxMain, 5);
	public Button mainRightBumper = new JoystickButton(xboxMain, 6);
	public Button mainBack = new JoystickButton(xboxMain, 7);
  	public Button mainStart = new JoystickButton(xboxMain, 8);
	
	public static Joystick xboxAux = new Joystick(RobotMap.JoystickPortXBoxAux);
	public Button auxA = new JoystickButton(xboxAux, 1);
	public Button auxB = new JoystickButton(xboxAux, 2);
	public Button auxX = new JoystickButton(xboxAux, 3);
	public Button auxY = new JoystickButton(xboxAux, 4);
	public Button auxLeftBumper = new JoystickButton(xboxAux, 5);
	public Button auxRightBumper = new JoystickButton(xboxAux, 6);
	public Button auxBack = new JoystickButton(xboxAux, 7);
  	public Button auxStart = new JoystickButton(xboxAux, 8);
  
	public OI() {
		buttonX.whenPressed(new ExtendHatchMechanism());
		buttonY.whenPressed(new RetractHatchMechanism());
		lBumper.whileHeld(new Climb(.1));
	}

 	public double getX() {
		return joystick1.getX();
	}
	
	public double getY() {
		return joystick1.getY();
	}

	public double getZ() {
		return joystick1.getZ();
	}
	
	public double getTwist() {
		return joystick1.getRawAxis(3);
	}
	
	public double getLeftTrigger() {
		return xboxMain.getRawAxis(3);
	}

	public double getRightTrigger() {
		return xboxMain.getRawAxis(6);
	}
}
