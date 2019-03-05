/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

/**
 * Add your docs here.
 */
public class Pixy extends Arduino {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static int lLoc;
  public static int rLoc;

  public Pixy(Port port, int address)//use robotmap values
  {
    super(port, address);
    lLoc = 318;//when it doesn't recognize anything, higher than the value
    rLoc = 318;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public byte[] receiveMessage(int address)
  {
    byte[] dataFromArduino = new byte[4];
    received = !arduino.read(address, 4, dataFromArduino);
      return dataFromArduino;
  }

  public void updateTargetValues () {
    byte[] coordinatesFromPixy = Robot.pixy.receiveMessage(RobotMap.pixyAddress);//gets first x value from pixy
    ByteBuffer bytebuffer1 = ByteBuffer.allocateDirect(4); //allocating 4 bytes for an integer in this ByteBuffer object
      bytebuffer1.order(ByteOrder.LITTLE_ENDIAN); //makes it so that it goes from least significant bit to most significant bit
      bytebuffer1.put(coordinatesFromPixy[1]);
      bytebuffer1.put(coordinatesFromPixy[0]);
      bytebuffer1.put((byte) 0x00);
      bytebuffer1.put((byte) 0x00);
      bytebuffer1.flip(); //flips order of the bytes we put in the bytebuffer and stages it to convert to base 10
      int x1 = bytebuffer1.getInt(); //converts base 2 value to base 10
    //break
   // int x2 = (int) coordinatesFromPixy[0];
    ByteBuffer bytebuffer2 = ByteBuffer.allocateDirect(4); //allocating 4 bytes for an integer in this ByteBuffer object
     bytebuffer2.order(ByteOrder.LITTLE_ENDIAN); //makes it so that it goes from least significant bit to most significant bit
     bytebuffer2.put(coordinatesFromPixy[3]);
     bytebuffer2.put(coordinatesFromPixy[2]);
     bytebuffer2.put((byte) 0x00);
     bytebuffer2.put((byte) 0x00);
     bytebuffer2.flip(); //flips order of the bytes we put in the bytebuffer and stages it to convert to base 10
     int x2 = bytebuffer2.getInt(); //converts base 2 value to base 10
  
    lLoc = x1;
    rLoc = x2;
    
    if (x1 > x2)//determines which one is on the left 
    {
      lLoc = x2;
      rLoc = x1;
    }
    else 
    {
      lLoc = x1;
      rLoc = x2;
    } 
    System.out.println("received 2nd value " + lLoc);
    System.out.println("received 1st value " + rLoc);
   }

  public boolean checkPixyAlign()//true if aligned, false if not
  {
    double desiredavg = 159;
    updateTargetValues();
    int leftLocation = lLoc;
    int rightLocation = rLoc;
    double x1 = (double) leftLocation;
    double x2 = (double) rightLocation; 
    double avg = (x1 + x2)/2;
    if(avg > (desiredavg  + 10) || avg < (desiredavg - 10)){
      double diff = desiredavg-avg;
      if (diff>desiredavg){
        return true;
      }
    }
    return false;
  }


  public int getLeft()
  {
    //System.out.println("Left Location: " + lLoc);
    return lLoc;
  }

  public int getRight()
  {
    //System.out.println("Right Location: " + rLoc);
    return rLoc;
  }

  public boolean canAutoAlign()//checks if it sees only two objects
  {
    if (lLoc == 0 || rLoc == 0 || lLoc > 315 || rLoc > 315) {
      return false;
    }
    return true; 
  }

  public String getObjectInfo()
  {
    if (this.getRight() == 316 || this.getLeft() == 316)//if one or zero objects are detected
    {
        return "not enough tapes detected";
    }
    if (this.getRight() == 317 || this.getLeft() == 317)//if three or more objects are detected 
    {
      return "too many tapes detected";
    }
    if (this.getRight() == 318 || this.getLeft() == 318)//if the arduino isn't sending values 
    {
      return "no communication with pixy";
    }
    return "two tapes detected, go auto align!";
  }
}
