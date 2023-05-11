// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.RelativeEncoder;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
/**
 * Subsystem for Encoders (drive train)
 * 
 */
public class EncoderSubsystem extends SubsystemBase {
  /** Creates a new EncoderSubsystem. */
  private RelativeEncoder
    leftFrontEncoder = RobotMap.leftFrontEncoder,
    leftBackEncoder = RobotMap.leftFrontEncoder,
    rightFrontEncoder = RobotMap.leftFrontEncoder,
    rightBackEncoder = RobotMap.leftFrontEncoder;


  public EncoderSubsystem() {
    //resets encoders when robot turn on
    resetEncoders();
    // leftFrontEncoder.setPositionConversionFactor(2*((1/42) * (2 * Math.PI * 3) * (1/10.71)));
  }
  // ticks * (one rotation/numTicks) * (2piR/one rotation ) * gear ratios. 
  //resets all encoders to 0
  public void resetEncoders(){
    leftFrontEncoder.setPosition(0);
    rightFrontEncoder.setPosition(0);
    leftBackEncoder.setPosition(0);
    rightBackEncoder.setPosition(0);
  }
  
  //get left encoder 
  //no need for both left encoders due to equal values
  public double getLeftEncoder(){
    return leftFrontEncoder.getPosition();
  }
  //get right encoder 
  //no need for both right encoders due to equal values
  public double getRightEncoder(){
    return rightFrontEncoder.getPosition() ;
  }

  //set left encoder to specified value
  public void setLeftEncoder(double num){
    leftFrontEncoder.setPosition(num);
  }
  //set right encoder to specified value
  public void setRightEncoder(double num){
    rightFrontEncoder.setPosition(num);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
