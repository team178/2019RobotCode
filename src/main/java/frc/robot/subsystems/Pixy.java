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
  public static int status;

  public Pixy(Port port, int address)//use robotmap values
  {
    super(port, address);
    lLoc = 0;
    rLoc = 0;
    status = 0;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public byte[] receiveMessage(int address)
  {
    byte[] dataFromArduino = new byte[6];
    received = !arduino.read(address, 6, dataFromArduino);
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
    
    ByteBuffer bytebuffer3 = ByteBuffer.allocateDirect(4);
      bytebuffer3.order(ByteOrder.LITTLE_ENDIAN);
      bytebuffer3.put(coordinatesFromPixy[5]);
      bytebuffer3.put(coordinatesFromPixy[4]);
      bytebuffer3.put((byte) 0x00);
      bytebuffer3.put((byte) 0x00);
      bytebuffer3.flip();
      int x3 = bytebuffer3.getInt();

    lLoc = x1;
    rLoc = x2;
    status = x3;
    
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
    System.out.println("Left Location: " + lLoc);
    return lLoc;
  }

  public int getRight()
  {
    System.out.println("Right Location: " + rLoc);
    return rLoc;
  }

  public boolean canAutoAlign()//checks if it sees only two objects
  {
    if (lLoc == 0 || rLoc == 0) {
      return false;
    }
    return true; 
  }

  public String getObjectInfo()
  {
    if (status == 0) {
      return "No pixy communication";
    }
      int objects = getNumObjects();
      return "Currently detecting " + objects + " objects";
  }
  public int getNumObjects()
  {
    return status - 1;
  }
}