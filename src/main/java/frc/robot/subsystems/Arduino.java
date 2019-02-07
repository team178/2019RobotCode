/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.ArrayList;
import java.util.Arrays;

import frc.robot.RobotMap;
import frc.robot.RobotMap.SubsystemIndex;

/**
 * Add your docs here.
 */
public class Arduino extends Subsystem {
  private I2C arduino;
  public int firstLocation;
  public int secondLocation;

  public Arduino() {
    arduino = new I2C(I2C.Port.kOnboard, RobotMap.ArduinoAddress); // check these values
    firstLocation = 0;
    secondLocation = 0;
  }

  public void sendMessage(SubsystemIndex subsystem, String pattern) {
    //String message = subsystem.ordinal() + pattern;
    String message = pattern;
    message = message.toLowerCase();
    System.out.println(message);
    arduino.writeBulk(message.getBytes());
    System.out.println(arduino.addressOnly());
  }

  public byte[] receiveMessage()
  {
    byte[] dataFromPixy = new byte[1];
    boolean success = arduino.read(RobotMap.ArduinoAddress, 1, dataFromPixy);
    System.out.println(success);
    for (byte b : dataFromPixy) {
    String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    System.out.print(s1 + ", ");
    } 
    System.out.println();

    return dataFromPixy;
  }

  public void checkForPixyValues () {
    byte[] coordinatesFromPixy = receiveMessage();
    String x1Binary = ((Byte) coordinatesFromPixy[0]).toString();
    int counter = 1;
    int x1 = 0;
    for (int i = x1Binary.length(); i >= 0; i--) {
      if (x1Binary.charAt(i) == '1') {
        x1 = x1 + counter;
      }
      counter = counter * 2;
    }
    counter = 0;
    
    // delay
    coordinatesFromPixy = receiveMessage();
    String x2Binary = ((Byte) coordinatesFromPixy[0]).toString();
    counter = 1;
    int x2 = 0;
    for (int i = x2Binary.length(); i >= 0; i--) {
      if (x2Binary.charAt(i) == '1') {
        x2 = x2 + counter;
      }
      counter = counter * 2;
    }
    firstLocation = x1;
    secondLocation = x2;
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}