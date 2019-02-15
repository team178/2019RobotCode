/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;

import frc.robot.RobotMap;
import frc.robot.commands.SendMessage;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class LightsSubsystem extends Arduino {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private DriverStation ds;

  public LightsSubsystem()
  {
    super(I2C.Port.kOnboard, RobotMap.lightsAddress);
    ds = DriverStation.getInstance();
    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public boolean setAllianceColor()
  {
    /*if (ds.getAlliance() == Alliance.Blue)
    {
        return new SendMessage("b").arduino.checkIfSent();
    }
    else
    {
        return new SendMessage("r").arduino.checkIfSent();
    }*/ return true;
  }
  public boolean needHatchPanel()
  {
    return true;
    //return new SendMessage("h").arduino.checkIfSent();
  }

  public boolean needCargo()
  {
    return new SendMessage("c").arduino.checkIfSent();
  }

 
}