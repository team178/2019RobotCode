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
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.LightsDefault;
import frc.robot.commands.LightsOff;
import frc.robot.subsystems.*;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.hal.sim.DriverStationSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DriverStation;


 public class Robot extends TimedRobot {
  
 //All subsystems & classes declared
 public static OI oi;
 public static DriveTrain drivetrain;
 public static HatchMechanism hatchMechanism;
 public static Climber climber;
 public static CargoLauncher cargolauncher;
 public static Arduino lightsArduino;
 public static Pixy pixy;

 //for lights
 public static DriverStation ds;


 public static boolean isAligned;
 
 //USB Camera declarations
 public static CameraServer camserv;
 public static UsbCamera camera1;
 public static UsbCamera camera2;
 public static UsbCamera camera3;

  //Here is where each of the subsystem fields declared above are initiatilized with their constructors when robot is started up
  @Override 
  public void robotInit() {
    drivetrain = new DriveTrain();
    hatchMechanism = new HatchMechanism();
    climber = new Climber();
    cargolauncher = new CargoLauncher(); 
    lightsArduino = new Arduino(I2C.Port.kOnboard, RobotMap.lightsAddress); //lightsArduino will always be plugged into MXP port
    pixy = new Pixy(I2C.Port.kOnboard, RobotMap.pixyAddress); //pixy will always be plugged into onboard port
    oi = new OI();
    ds = DriverStation.getInstance();
    

    isAligned = false;

    //Camera initializations
    camserv = CameraServer.getInstance();
    
    //Camera 1
    camera1 = camserv.startAutomaticCapture("cam0", 0);
    camera1.setResolution(160, 90);
    camera1.setFPS(14);
    camera1.setPixelFormat(PixelFormat.kYUYV); //formats video specifications for cameras

    //Camera 2
    camera2 = CameraServer.getInstance().startAutomaticCapture("cam1", 1);
    camera2.setResolution(160, 120);
    camera2.setFPS(14);
    camera2.setPixelFormat(PixelFormat.kYUYV); //formats video specifications for cameras

    // //Camera 3
    // camera3 = CameraServer.getInstance().startAutomaticCapture("cam2", 2);
    // camera3.setResolution(160, 120);
    // camera3.setFPS(14);
    // camera3.setPixelFormat(PixelFormat.kYUYV); //formats video specifications for cameras
 
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putBoolean("Can Auto Align", pixy.canAutoAlign());
    SmartDashboard.putString("Pixy Status", pixy.getObjectInfo());
    SmartDashboard.putBoolean("Hatch Mechanism Centered", isAligned);


  }

  @Override
  public void disabledInit() {
    lightsArduino.sendMessage("n");//lights off
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    //lightsArduino.sendMessage("x");//lights red
   
   //lights
    if (ds.getAlliance() == Alliance.Blue) { //set alliance color code 
      lightsArduino.sendMessage("d");
    }
    else if (ds.getAlliance() == Alliance.Red){
      lightsArduino.sendMessage("s");
    }
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
    lightsArduino.sendMessage("f");//enforcers colors 
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
