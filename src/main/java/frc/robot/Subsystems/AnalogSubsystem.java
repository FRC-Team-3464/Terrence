package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
/**
 * Subsystem for pressure sensor
 * 
 */
public class AnalogSubsystem extends SubsystemBase {

  private final AnalogInput analog = RobotMap.pressureSensor;
  private  boolean bool;
  public AnalogSubsystem() {

  }

  //checks if pressure sensor is triggered (doesnt work for some reason)
  public void isBallCollected(){
    if(analog.getVoltage() < 2){
      bool = true;
    }
    else{
      bool = false;
    }
    //send to smart Dashboard
    SmartDashboard.putBoolean("Ball???", bool);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}