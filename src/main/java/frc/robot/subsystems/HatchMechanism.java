/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.AnalogInput;

import edu.wpi.first.wpilibj.Talon;

/**
 * Add your docs here.
 */
public class HatchMechanism extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static Talon hatchMotor;
  //Pneumatics
  public static DoubleSolenoid hatchCylinderExtend;
  public static DoubleSolenoid hatchCylinderEject;
  public static AnalogInput pressureTransducer;
  //Linear actuator
  public static PWM linearactuator;

  public HatchMechanism() {
    hatchCylinderExtend = new DoubleSolenoid(RobotMap.PCM, RobotMap.HatchExtenderCylinderExtend, RobotMap.HatchExtenderCylinderRetract);
    
    hatchCylinderEject = new DoubleSolenoid(RobotMap.PCM, RobotMap.HatchEjectorCylinderExtend, RobotMap.HatchEjectorCylinderRetract);
    pressureTransducer = new AnalogInput(RobotMap.PressureTranducer);

    linearactuator = new PWM(RobotMap.linearactuator);

    //Default position:
    setExtender("reverse");
    setEjector("reverse");
  }

  //sets extender to two positions depending on string parameter --> forward = extends, reverse = retracts
  public void setExtender(String pos){
      if(pos.toLowerCase().equals("forward")){
        hatchCylinderExtend.set(DoubleSolenoid.Value.kForward);
        

        System.out.println("Extender forward: " + getExtenderSolenoidState());
      } else if (pos.toLowerCase().equals("reverse")){
        hatchCylinderExtend.set(DoubleSolenoid.Value.kReverse);
        
        System.out.println("Extender reverse: " + getExtenderSolenoidState());
      } else{
          System.out.println("Invalid position. Please try again");
      }
  }
  //sets extender to two positions depending on string parameter --> forward = extends, reverse = retracts
  public void setEjector(String pos){
    if(pos.toLowerCase().equals("forward")){
        hatchCylinderEject.set(DoubleSolenoid.Value.kForward);
        
        System.out.println("Ejecter forward: " + getEjectorSolenoidState());
      } else if (pos.toLowerCase().equals("reverse")){
       hatchCylinderEject.set(DoubleSolenoid.Value.kReverse);
        System.out.println("Ejecter reverse: " + getEjectorSolenoidState());
      } else{
          System.out.println("Invalid position. Please try again");
      }
  }

  public DoubleSolenoid.Value getExtenderSolenoidState() {
    return hatchCylinderExtend.get();
  }

  public DoubleSolenoid.Value getEjectorSolenoidState() {
    return hatchCylinderEject.get();
  }

  public double getPressure() {
      return pressureTransducer.getVoltage();
  }

  public void setActuatorPosition(double val) {
    linearactuator.setPosition(val);
  }

  public double getActuatorPosition() {
    return linearactuator.getPosition();
  }

  public void moveActuator (boolean movingForward) {
    double currentPosition = getActuatorPosition();
    if (movingForward) {
      if (currentPosition < 1 || currentPosition >= 0) {
        currentPosition+=0.004;
        setActuatorPosition(currentPosition);
      }
    } else {
      if (currentPosition <= 1 || currentPosition > 0) {
        currentPosition-=0.004;
        setActuatorPosition(currentPosition);
      }
    }
    System.out.println(getActuatorPosition());
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
