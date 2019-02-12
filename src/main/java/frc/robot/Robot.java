/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CameraServer;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;


 public class Robot extends TimedRobot {
   
 public static OI oi;

 
 public static DriveTrain drivetrain;
 
 public static HatchMechanism hatchMechanism;
 public static Climber climber;
 public static CargoIntake cargointake;
 public static CargoLauncher cargolauncher;
 public static LinearActuator linearactuator;
 public static Arduino arduino;
 public static Pixy pixy;
 public static TimeOfFlight tof1;
 public static TimeOfFlight tof2;
 public static UsbCamera camera;

  @Override
  public void robotInit() {
    oi = new OI();
    drivetrain = new DriveTrain();
    hatchMechanism = new HatchMechanism();
    climber = new Climber();  
    cargointake = new CargoIntake();
    cargolauncher = new CargoLauncher(); 
    arduino = new Arduino(I2C.Port.kOnboard, 1);//1 is placeholder
    pixy = new Pixy();
    tof1 = new TimeOfFlight(RobotMap.tofAddress1);
    tof2 = new TimeOfFlight(RobotMap.tofAddress2);

    linearactuator = new LinearActuator();

    camera = CameraServer.getInstance().startAutomaticCapture(0);
    camera.setResolution(160, 120);
    camera.setFPS(14);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
    //Scheduler.getInstance().run();
  }
}
