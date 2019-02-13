/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class TimeOfFlight extends Arduino {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  int address;

  public TimeOfFlight(int address)//use robotmap values
  {
    super(I2C.Port.kOnboard, address);
    this.address = address;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static int getTofDistance () 
  {//need to decide if we gonna put calculations on here or arduino
    byte[] tofDistance = Robot.tofL.receiveMessage(RobotMap.tofAddressL);//gets first tof value
    String dist = ((Byte) tofDistance[0]).toString();
    int counter = 1;
    int distance = 0;
    for (int i = dist.length() - 1; i >= 0; i--) {//converts binary to base 10 
      if (dist.charAt(i) == '1') {
        distance = distance + counter;
      }
      counter = counter * 2;
    }
    System.out.println(distance);
    return distance;
    }

  public static boolean checkTofAlign()//need to see what the strings are in binary or could just change to ints 
  {
    return false;
  }


@Override
  public byte[] receiveMessage(int address)
  {
    byte[] dataFromArduino = new byte[2];
    received = arduino.read(this.address, 2, dataFromArduino);
    for (byte b : dataFromArduino) {//gets data in bytes from arduino and converts to binary 
      String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
      System.out.print(s1 + ", ");
      } 
      System.out.println();
      return dataFromArduino;
  }
}