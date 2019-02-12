/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Pixy extends Arduino {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public int firstLoc;
  public int secondLoc;

  public Pixy()
  {
    super(I2C.Port.kOnboard, RobotMap.pixyAddress);
    firstLoc = 0;
    secondLoc = 0;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public byte[] receiveMessage()
  {
    byte[] dataFromArduino = new byte[2];
    received = arduino.read(RobotMap.pixyAddress, 1, dataFromArduino);
    for (byte b : dataFromArduino) {//gets data in bytes from arduino and converts to binary 
      String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
      System.out.print(s1 + ", ");
      } 
      System.out.println();
      return dataFromArduino;
  }

  public void checkForPixyValues () {
    byte[] coordinatesFromPixy = receiveMessage();//gets first x value from pixy
    String x1Binary = ((Byte) coordinatesFromPixy[0]).toString();
    int counter = 1;
    int x1 = 0;
    for (int i = x1Binary.length() - 1; i >= 0; i--) {//converts binary to base 10
      if (x1Binary.charAt(i) == '1') {
        x1 = x1 + counter;
      }
      counter = counter * 2;
    }
    counter = 0;
    
    // delay
    coordinatesFromPixy = receiveMessage();//gets second x value from pixy 
    String x2Binary = ((Byte) coordinatesFromPixy[0]).toString();
    counter = 1;
    int x2 = 0;
    for (int i = x2Binary.length() - 1; i >= 0; i--) {//converts binary to base 10
      if (x2Binary.charAt(i) == '1') {
        x2 = x2 + counter;
      }
      counter = counter * 2;
    }
    firstLoc = x1;
    secondLoc = x2;
  }

  public boolean checkPixyAlign()//true if aligned, false if not
  {
    double desiredavg = 159;
    this.checkForPixyValues();
    int firstLocation = this.firstLoc;
    int secondLocation = this.secondLoc;
    double x1 = (double) firstLocation;
    double x2 = (double) secondLocation; 
    double avg = (x1 + x2)/2;
    if(avg > (desiredavg  + 10) || avg < (desiredavg - 10)){
      double diff = desiredavg-avg;
      if (diff>desiredavg){
        return true;
      }
    }
    return false;
  }


  public int getFirstLoc()
  {
    return firstLoc;
  }

  public int getSecondLoc()
  {
    return secondLoc;
  }

}