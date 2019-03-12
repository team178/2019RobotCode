/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWM;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;

/**
 * Add your docs here.
 */

public class HatchMechanism extends Subsystem {
  //Pneumatics
  public static DoubleSolenoid hatchCylinderExtend;
  public static DoubleSolenoid hatchCylinderEject;
  public static AnalogInput pressureTransducer;
  public static DigitalInput limitSwitchLeft;
  public static DigitalInput limitSwitchRight;

  //Linear actuator died, so it was replaced by lead screw
  public static VictorSPX leadScrew;

  double leadScrewSpeed;

  public HatchMechanism() {
    //Pneumatics
    hatchCylinderExtend = new DoubleSolenoid(RobotMap.PCM, RobotMap.hatchExtenderCylinderExtend, RobotMap.hatchExtenderCylinderRetract);
    hatchCylinderEject = new DoubleSolenoid(RobotMap.PCM, RobotMap.hatchEjectorCylinderExtend, RobotMap.hatchEjectorCylinderRetract);
    //pressureTransducer = new AnalogInput(RobotMap.PressureTranducer);

    leadScrew = new VictorSPX(RobotMap.leadScrew);

    //Default positions
    setExtender("reverse");
    setEjector("reverse");

    //Limit switches
    limitSwitchLeft = new DigitalInput(RobotMap.hatchLimitSwitchLeft);
    limitSwitchRight = new DigitalInput(RobotMap.hatchLimitSwitchRight);
    leadScrewSpeed = 0;
  }

  //Sets extender to two positions depending on string parameter --> forward = extends, reverse = retracts
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
  
  //Sets extender to two positions depending on string parameter --> forward = extends, reverse = retracts
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

  public void moveLeadScrew(boolean movingForward, double factor) {
    leadScrewSpeed = factor;
    if (!movingForward) {
      factor *= -1; 
    }

    leadScrew.set(ControlMode.PercentOutput, factor * 0.4 );
    
   /* if (!hasReachedLeftBound() && !hasReachedRightBound()) {
      leadScrew.set(ControlMode.PercentOutput, factor);
    } else {
      leadScrew.set(ControlMode.PercentOutput, 0);
    } */
    //System.out.println(getActuatorPosition());
  }
  
  //Limit switch methods
  public boolean hasReachedLeftBound() {
    return limitSwitchLeft.get();
  }

  public boolean hasReachedRightBound() {
    return limitSwitchRight.get();
  }
  
  //Accessor methods
  public DoubleSolenoid.Value getExtenderSolenoidState() {
    return hatchCylinderExtend.get();
  }

  public DoubleSolenoid.Value getEjectorSolenoidState() {
    return hatchCylinderEject.get();
  }

  public double getPressure() {
      return pressureTransducer.getVoltage();
  }

  public String getLeadScrewMotion() {
    if (leadScrewSpeed > 0) {
      return "Right";
    } else if (leadScrewSpeed < 0) {
      return "Left";
    }
      return "No Movement";
  }


  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManuallyMoveLeadScrew());
  }
}
