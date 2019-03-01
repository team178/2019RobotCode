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

	//JOYSTICK buttons
	public static Joystick joystick = new Joystick(RobotMap.ActualJoystick);
	public Button button1 = new JoystickButton(joystick, 1);
	public Button button2 = new JoystickButton(joystick, 2);
	public Button button3 = new JoystickButton(joystick, 3);
	public Button button4 = new JoystickButton(joystick, 4);
	public Button button5 = new JoystickButton(joystick, 5);
	public Button button6 = new JoystickButton(joystick, 6);
	public Button button7 = new JoystickButton(joystick, 7);
	public Button button8 = new JoystickButton(joystick, 8);
	public Button button9 = new JoystickButton(joystick, 9);
	public Button button10 = new JoystickButton(joystick, 10);
	public Button button11 = new JoystickButton(joystick, 11);
	public Button button12 = new JoystickButton(joystick, 12);

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
	
	//MAIN controller buttons -- MAIN XBOX NOT BEING USED
	public static Joystick xboxMain = new Joystick(RobotMap.JoystickPortXBoxMain); //Controller
	public Button mainA = new JoystickButton(xboxMain, 1);
	public Button mainB = new JoystickButton(xboxMain, 2);
	public Button mainX = new JoystickButton(xboxMain, 3);
	public Button mainY = new JoystickButton(xboxMain, 4);
	public Button mainLeftBumper = new JoystickButton(xboxMain, 5);
	public Button mainRightBumper = new JoystickButton(xboxMain, 6);
	public Button mainBack = new JoystickButton(xboxMain, 7);
	public Button mainStart = new JoystickButton(xboxMain, 8);
	
	public OI() {
		//Setting JOYSTICK channels
		joystick.setXChannel(0);
		joystick.setYChannel(3);
		joystick.setZChannel(2);
		joystick.setTwistChannel(1);

		//JOYSTICK controls
		button1.whenPressed(new LightsOff());
		button2.whenPressed(new LightsHatch());
		button3.whenPressed(new LightsCargo());
		button4.whenPressed(new LightsDefault());
		button5.whenPressed(new LightsAlign());
		//button6.whenPressed(new LightsRainbow());

		button7.whenPressed(new ClimbDrive(true));
		button8.whenPressed(new ClimbDrive(false));
		
		//AUX controls
		auxA.whenPressed(new EjectHatchPanel());
		auxA.whenReleased(new RetractEjector());
		auxY.whenPressed(new ExtendHatchMechanism());
		auxY.whenReleased(new RetractHatchMechanism());
		
		auxB.whenPressed(new ExtendCargoShooter());
		auxB.whenReleased(new RetractCargoShooter());
		auxRightBumper.whileHeld(new AlignHatchPanel());

		//MAIN controls (joystick code in JoystickDrive)
		mainB.whenPressed(new LightsCargo());
		mainY.whenPressed(new LightsHatch());
		mainX.whenPressed(new LightsDefault());
		mainStart.whenPressed(new LightsOff());
		mainA.whenPressed(new LightsAlign());
		mainA.whenPressed(new LightsOff());
	}

	//JOYSTICK accessor methods
	public double getX() {
		return joystick.getX();
	}
	
	public double getY() {
		return joystick.getY(); //joystick.getRawAxis(3);
	}

	public double getTwist() {
		return joystick.getTwist(); //joystick.getRawAxis(3);
	}
	
	//AUX controller accessor methods
	public double getLeftStickYAux() {
		return xboxAux.getRawAxis(1);
	}
	
	public double getRightStickYAux() {
		return xboxAux.getRawAxis(5);
	}
	
	public double getLeftTriggerAux() {
		return xboxAux.getRawAxis(2);
	}
	
	public double getRightTriggerAux() {
		return xboxAux.getRawAxis(3);
	}

	//MAIN controller accessor methods
	public double getLeftStickYMain() {
		return xboxMain.getRawAxis(1);
	}
	
	public double getRightStickYMain() {
		return xboxMain.getRawAxis(5);
	}
	
	public double getLeftTriggerMain() {
		return xboxMain.getRawAxis(2);
	}
	
	public double getRightTriggerMain() {
		return xboxMain.getRawAxis(3);
	}

	public void printJoystickChannels() {
		System.out.println("X channel: " + joystick.getXChannel());
		System.out.println("Y channel: " + joystick.getYChannel());
		System.out.println("Z channel: " + joystick.getZChannel());
		System.out.println("Twist channel: " + joystick.getTwistChannel());
	}
}
