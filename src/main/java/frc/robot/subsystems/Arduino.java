/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Add your docs here.
 */
public class Arduino extends Subsystem {
  protected I2C arduino;
  DriverStation ds;
  boolean received;
  boolean sent;

  public Arduino(Port port , int address) {
    arduino = new I2C(port, address); // check these values
    ds = DriverStation.getInstance();
  }

  public boolean sendMessage(String pattern) {
    //String message = subsystem.ordinal() + pattern;
    boolean sent = false;
    String message = pattern;
    message = message.toLowerCase();
    System.out.println(message);
    sent = !arduino.writeBulk(message.getBytes());//because true if aborted, false if worked
    System.out.println(arduino.addressOnly());

    return sent;
  }

  public byte[] receiveMessage(int address)//for which i2c address to read from 
  {
    byte[] dataFromArduino = new byte[2];//change based on type of data 
    received = !arduino.read(address, 2, dataFromArduino);//because true if aborted, false if worked
    for (byte b : dataFromArduino) {//gets data in bytes from arduino and converts to binary 
    String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    System.out.print(s1 + ", ");
    } 
    System.out.println();

    return dataFromArduino;
  }


  //checkers
  public boolean checkIfReceived()
  {
    return received;
  }

  public boolean checkIfSent()
  {
    return sent;
  }

  //lights methods, move these to oi
  public boolean setAllianceColor()
  {
    if (ds.getAlliance() == Alliance.Blue)
    {
      return sendMessage("b");
    }
    else 
    {
      return sendMessage("r");
    }
  }

  public boolean lightsHatchPanel()
  {
    return sendMessage("h");
  }

  public boolean lightsCargoPanel()
  {
    return sendMessage("c");
  }

  public boolean lightsOff()
  {
    return sendMessage("n");
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}