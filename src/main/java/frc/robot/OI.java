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
import frc.robot.commandgroups.*;
import frc.robot.programmingworkshop.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private int HATCH_CONTROLS = 1; //0 = old; 1 = new

	//JOYSTICK buttons
	public static Joystick joystick = new Joystick(RobotMap.ActualJoystick);
	public Button trigger = new JoystickButton(joystick, 1);
	public Button headBottom = new JoystickButton(joystick, 2);
	public Button headLeft = new JoystickButton(joystick, 3);
	public Button headRight = new JoystickButton(joystick, 4);
	public Button leftPadTop1 = new JoystickButton(joystick, 5);
	public Button leftPadTop2 = new JoystickButton(joystick, 6);
	public Button leftPadTop3 = new JoystickButton(joystick, 7);
	public Button leftPadBottom3 = new JoystickButton(joystick, 8);
	public Button leftPadBottom2  = new JoystickButton(joystick, 9);
	public Button leftPadBottom1 = new JoystickButton(joystick, 10);
	public Button rightPadTop3 = new JoystickButton(joystick, 11);
	public Button rightPadTop2 = new JoystickButton(joystick, 12);
	public Button rightPadTop1 = new JoystickButton(joystick, 13);
	public Button rightPadBottom1 = new JoystickButton(joystick, 14);
	public Button rightPadBottom2 = new JoystickButton(joystick, 15);
	public Button rightPadBottom3 = new JoystickButton(joystick, 16);

	//AUX controller buttons
	public static Joystick xboxAux = new Joystick(RobotMap.JoystickPortXBoxAux); //Controller
	public Button auxA = new JoystickButton(xboxAux, 1);
	public Button auxB = new JoystickButton(xboxAux, 2);
	public Button auxX = new JoystickButton(xboxAux, 3);
	public Button auxY = new JoystickButton(xboxAux, 4);
	public Button auxLeftBumper = new JoystickButton(xboxAux, 5);
	//replace with kid mode code 
	public Button auxRightBumper = new JoystickButton(xboxAux, 6);
	//replace with kid mode code
	public Button auxBack = new JoystickButton(xboxAux, 7);
	public Button auxStart = new JoystickButton(xboxAux, 8);
	
	//MAIN controller buttons -- MAIN XBOX NOT BEING USED
	/*public static Joystick xboxMain = new Joystick(RobotMap.JoystickPortXBoxMain); //Controller
	public Button mainA = new JoystickButton(xboxMain, 1);
	public Button mainB = new JoystickButton(xboxMain, 2);
	public Button mainX = new JoystickButton(xboxMain, 3);
	public Button mainY = new JoystickButton(xboxMain, 4);
	public Button mainLeftBumper = new JoystickButton(xboxMain, 5);
	public Button mainRightBumper = new JoystickButton(xboxMain, 6);
	public Button mainBack = new JoystickButton(xboxMain, 7);
	public Button mainStart = new JoystickButton(xboxMain, 8);*/
	
	public OI() {
		//Setting JOYSTICK channels
		joystick.setXChannel(3); //3
		joystick.setYChannel(2); //2
		joystick.setZChannel(0); //0
		joystick.setTwistChannel(1); //1

		//JOYSTICK controls
		leftPadBottom2.whenPressed(new LightsOff());
		leftPadTop1.whenPressed(new LightsHatch());
		leftPadTop2.whenPressed(new LightsCargo());
		leftPadTop3.whenPressed(new LightsDefault());
		leftPadBottom1.whenPressed(new LightsAlign());
		leftPadBottom3.whenPressed(new LightsRainbow());

		rightPadTop1.whileHeld(new ClimbDrive("forward"));
		rightPadBottom1.whileHeld(new ClimbDrive("backward"));
		
		//AUX controls
		if (HATCH_CONTROLS == 0) {
			auxY.whenPressed(new ExtendHatchMechanism());
			auxY.whenReleased(new RetractHatchMechanism());
			auxB.whenPressed(new EjectHatchPanel());
			auxB.whenReleased(new RetractEjector());
		} else if (HATCH_CONTROLS == 1) {
			auxY.whenPressed(new HatchPickupExtend());
			auxY.whenReleased(new HatchPickupRetract());
			auxB.whenPressed(new HatchDropoffExtend());
			auxB.whenReleased(new HatchDropoffRetract());
		}
		
		auxA.whenPressed(new ExtendCargoShooter());
		auxA.whenReleased(new RetractCargoShooter());
		auxX.whenPressed(new OpenCargoRails());
		auxX.whenReleased(new CloseCargoRails());
		
		//auxLeftBumper.whenPressed(new CenterHatchMechanism()); - replaced
		//auxRightBumper.whileHeld(new AlignHatchPanel()); - replaced
	

		rightPadTop2.whenPressed(new AutoClimbPrep(2));
		rightPadTop3.whenPressed(new AutoClimbPrep(3));
		rightPadBottom2.whenPressed(new AutoClimb(2));
		rightPadBottom3.whenPressed(new AutoClimb(3));
		
		//AUTO WORKSHOP
		headBottom.whenPressed(new Course2());
	}

	//JOYSTICK accessor methods
	public double getX() {
		return joystick.getX(); //joystick.getRawAxis(0);
	}
	
	public double getY() {
		return joystick.getTwist(); //joystick.getRawAxis(1);
	}

	public double getTwist() {
		return joystick.getY(); //joystick.getRawAxis(2);
	}

	//xbox kiddie code methods
	public double getRightStickAux(){
		return xboxAux.getRawAxis(5);
	}
	public double getLeftStickAux() {
		return xboxAux.getY();
	}

	/**
	 * @return the raw slider value that retuns 0- to +1 insteaad of -1 to +1
	 */
	public double getSlider() {
		return 1 - ((joystick.getRawAxis(3) + 1) / 2);
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

	public void printJoystickChannels() {
		System.out.println("X channel: " + joystick.getXChannel());
		System.out.println("Y channel: " + joystick.getYChannel());
		System.out.println("Z channel: " + joystick.getZChannel());
		System.out.println("Twist channel: " + joystick.getTwistChannel());
	}
}
