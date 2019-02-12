/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.ArrayList;
import java.util.Arrays;

import frc.robot.RobotMap;
import frc.robot.RobotMap.SubsystemIndex;

/**
 * Add your docs here.
 */
public class Arduino extends Subsystem {
  protected I2C arduino;
  boolean received;

  public Arduino(Port port , int address) {
    arduino = new I2C(port, address); // check these values
  }

  public boolean sendMessage(String pattern) {
    //String message = subsystem.ordinal() + pattern;
    boolean sent;
    String message = pattern;
    message = message.toLowerCase();
    System.out.println(message);
    sent = arduino.writeBulk(message.getBytes());
    System.out.println(arduino.addressOnly());

    return sent;
  }

  public byte[] receiveMessage()
  {
    byte[] dataFromArduino = new byte[2];//change based on type of data 
    received = arduino.read(RobotMap.lightsAddress, 1, dataFromArduino);
    for (byte b : dataFromArduino) {//gets data in bytes from arduino and converts to binary 
    String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    System.out.print(s1 + ", ");
    } 
    System.out.println();

    return dataFromArduino;
  }

  public boolean checkedForReceived()
  {
    return received;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}