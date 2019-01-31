/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

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

  public void sendMessage(SubsystemIndex subsystem, String pattern) {
    String message = subsystem.ordinal() + pattern;
    message = message.toLowerCase();
    System.out.println(message);
    arduino.writeBulk(message.getBytes());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
