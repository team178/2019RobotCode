/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.Arrays;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.RobotMap.SubsystemIndex;

/**
 * Add your docs here.
 */
public class Arduino extends Subsystem {
  private I2C arduino;

  public Arduino() {
    arduino = new I2C(I2C.Port.kOnboard, RobotMap.ArduinoAddress); // check these values
  }

  public void sendMessage(String pattern) {
    String message = pattern;
    message = message.toLowerCase();
    System.out.println(message);
    arduino.writeBulk(message.getBytes());
  }

  public void sendMessage(SubsystemIndex subsystem, String pattern) {
    String message = subsystem.ordinal() + pattern;
    message = message.toLowerCase();
    System.out.println(message);
    arduino.writeBulk(message.getBytes());
  }

  public void receiveMessage()
  {
    byte dataFromPixy[] = new byte[2];
    arduino.read(RobotMap.ArduinoAddress, 2, dataFromPixy);
    System.out.println(Arrays.toString(dataFromPixy));
  }
 
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
