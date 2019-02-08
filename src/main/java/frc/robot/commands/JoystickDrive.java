/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.*;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickDrive extends Command {
  
  OI oi;
  DriveTrain drivetrain;
  double yVal;
  double twistVal;
  
  public JoystickDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
   // requires(Robot.drivetrain);
  }


    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
      oi = Robot.oi;
    //  drivetrain = Robot.drivetrain;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
      
      
      if(oi.getTrigger() >= 0) {
        yVal = 0.75 * oi.getY();
        twistVal = 0.5 * (oi.getTwist());
        
        if(Math.abs(yVal)>0.1 || Math.abs(twistVal)>0.1) {
          drivetrain.drive(twistVal-yVal, twistVal+yVal);
        } else {
          drivetrain.drive(0,0);
        }
      } else {
        yVal = -oi.getY();
        twistVal = -0.5*(oi.getTwist());

        if(Math.abs(yVal)>0.1 || Math.abs(twistVal)>0.1) {
          drivetrain.drive(twistVal-yVal, twistVal+yVal);
        } else {
          drivetrain.drive(0,0);
        }
      }
    }
    //joystick drive

    
   


    @Override
    protected boolean isFinished() {
      return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
      drivetrain.drive(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
      drivetrain.drive(0,0);
    }
  }