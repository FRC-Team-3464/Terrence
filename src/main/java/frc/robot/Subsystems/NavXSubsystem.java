// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import java.text.DecimalFormat;

import com.kauailabs.navx.frc.AHRS;

// import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 

public class NavXSubsystem extends SubsystemBase {
  /* Creates a new NavXSubsystem. */
  private final AHRS ahrs = new AHRS(SPI.Port.kMXP);
  private DecimalFormat df;

  public NavXSubsystem() {
    df = new DecimalFormat("#.##");
  }

  public double getDegrees(){
    return ahrs.getPitch();
  }

  public void resetGyro(){
    ahrs.reset();
  }

  public double getAcceleration(){
    return ahrs.getAccelFullScaleRangeG(); // Bruh what
  }

  public String getXAccel(){
    //return ahrs.getRawAccelX();
    return df.format(ahrs.getWorldLinearAccelX());
  }
  public double returnXAccelNum(){
    return ahrs.getWorldLinearAccelX();
  }

  public double getWeather(){
    return ahrs.getBarometricPressure();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("NavX Acceleration", getAcceleration()); 
    SmartDashboard.putNumber("NavX Pitch", getDegrees());
    SmartDashboard.putBoolean("Connected", ahrs.isConnected());
    SmartDashboard.putString("X Acceleration", getXAccel());
    //SmartDashboard.putNumber("NavX Weather", getWeather());
  }
}
