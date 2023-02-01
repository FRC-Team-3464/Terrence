// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NavXSubsystem extends SubsystemBase {
  /** Creates a new NavXSubsystem. */
  private final AHRS ahrs = new AHRS(Port.kMXP);

  public NavXSubsystem() {}

  public double getDegrees(){
    return ahrs.getPitch();
  }

  public void resetGyro(){
    ahrs.reset();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
