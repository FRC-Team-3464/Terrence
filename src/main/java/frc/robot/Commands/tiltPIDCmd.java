// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Subsystems.NavX;


public class tiltPIDCmd extends CommandBase {
  /** Creates a new tiltPIDCmd. */

  private final PIDController balanceController = new PIDController(-0.0167, 0, 0);
  
  private final NavX navX;
  private final DriveSubsystem driveSubsystem;
  private double outputSpeed;
  private boolean status;

  public tiltPIDCmd(NavX navSub, DriveSubsystem driveSub) {
    navX = navSub;
    driveSubsystem = driveSub;
    addRequirements(navSub);
    addRequirements(driveSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    balanceController.setSetpoint(0); // We need 
    // balanceController.setTolerance(2);
    outputSpeed = 0;
    status  = true; // True is green - good
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    outputSpeed = balanceController.calculate(navX.returnPitch());
    if((Math.abs(outputSpeed) > 0.5) || (Math.abs(outputSpeed) < 0.05)){
      outputSpeed = 0;
      status = false;
    }else{
      status = true;      
    }

    driveSubsystem.arcadeDrive(outputSpeed, 0); // Just drive forward and back. 
    SmartDashboard.putNumber("Balance Speed", outputSpeed);
    SmartDashboard.putBoolean("Balance Status", status); // RED IS NOT GOOD
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
