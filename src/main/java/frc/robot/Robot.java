/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.LightsOff;
import frc.robot.subsystems.*;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.I2C;


 public class Robot extends TimedRobot {
  
 //All subsystems & classes declared
 public static OI oi;
 public static DriveTrain drivetrain;
 public static HatchMechanism hatchMechanism;
 public static Climber climber;
 public static CargoLauncher cargolauncher;
 public static Arduino lightsArduino;
 public static Pixy pixy;

 public static boolean isAligned;
 
 //USB Camera declarations
 public static CameraServer camserv;
 public static UsbCamera camera1;
 public static UsbCamera camera2;
 
  //Here is where each of the subsystem fields declared above are initiatilized with their constructors when robot is started up
  @Override 
  public void robotInit() {
    drivetrain = new DriveTrain();
    hatchMechanism = new HatchMechanism();
    climber = new Climber();
    cargolauncher = new CargoLauncher(); 
    lightsArduino = new Arduino(I2C.Port.kMXP, RobotMap.lightsAddress); //lightsArduino will always be plugged into MXP port
    pixy = new Pixy(I2C.Port.kOnboard, RobotMap.pixyAddress); //pixy will always be plugged into onboard port
    oi = new OI();
    lightsArduino.setAllianceColor(); //Sets light strips to color of alliance (red or blue)

    isAligned = false;

    //Camera initializations
    camserv = CameraServer.getInstance();
    
    //Camera 1
    camera1 = camserv.startAutomaticCapture("cam0", 0);
    camera1.setResolution(160, 120);
    camera1.setFPS(14);
    camera1.setPixelFormat(PixelFormat.kYUYV); //formats video specifications for cameras

    //Camera 2
    camera2 = CameraServer.getInstance().startAutomaticCapture("cam1", 1);
    camera2.setResolution(160, 120);
    camera2.setFPS(14);
    camera2.setPixelFormat(PixelFormat.kYUYV); //formats video specifications for cameras
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putBoolean("Can Auto Align", pixy.canAutoAlign());
    SmartDashboard.putString("Pixy Status", pixy.getObjectInfo());
    SmartDashboard.putString("Lead Screw Motion", hatchMechanism.getLeadScrewMotion());
    SmartDashboard.putBoolean("Hatch Mechanism Centered", isAligned);
    SmartDashboard.putString("Robot tilt", climber.getTilt());
  }

  @Override
  public void disabledInit() {
    lightsArduino.sendMessage("n");
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

  //Called repeatedly and automatically during teleop --> Scheduler automatically stores all actions robot needs to do
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }


  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}
