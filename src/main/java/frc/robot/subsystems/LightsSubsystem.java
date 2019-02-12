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
public class Lights extends Arduino {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private DriverStation ds;

  public Lights()
  {
    super(I2C.Port.kOnboard, RobotMap.lightsAddress);
    ds = DriverStation.getInstance();
    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setAllianceColor()
  {
    if (ds.getAlliance() == Alliance.Blue)
    {
        new SendMessage("b");
    }
    else
    {
        new SendMessage("r");
    }
  }

  public void needHatchPanel()
  {
    new SendMessage("h");
  }

  public void needCargo()
  {
    new SendMessage("c");
  }

}