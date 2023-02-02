// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import java.util.ArrayList;
import java.util.concurrent.DelayQueue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Subsystems.NavX;


public class tiltPIDCmd extends CommandBase {
  /** Creates a new tiltPIDCmd. */

  // private final PIDController balanceController = new PIDController(-0.025, 0, 0);
  
  private final NavX navX;
  private final DriveSubsystem driveSubsystem;
  private double outputSpeed;
  private boolean status;

  // private double[] past5Errors; 
  private double testCaseNumber = 0; // testCaseNumber increases by one if it's on the ramp -> used to help set the ramp angle. 
  private boolean isOn;
  // private double rampAngle;
  private double robotAngle;
  private double changeInError;
  ArrayList<Double> deltaX = new ArrayList<Double>();
  public int runCount = 0;
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
    // balanceController.setSetpoint(0); // We need 
    // balanceController.setTolerance(2);
    outputSpeed = 0;
    isOn = false;
    status  = false; // True is green - good
    robotAngle = navX.returnPitch();
    double angle2 = navX.returnPitch();
    deltaX.add(angle2-robotAngle);
    outputSpeed = 0.3;
    // Get angle to be the navX reading
    // perviousError = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Math.abs(navX.returnPitch())> 8 ){
      outputSpeed=0.3;
    }
    else if(Math.abs(navX.returnPitch())> 4 ){
      outputSpeed=0.2;
    }
    else if(Math.abs(navX.returnPitch())> 2 ){
      outputSpeed=0.1;
    }
    changeInError = navX.returnPitch() - deltaX.get(deltaX.size()-1);
    if (!(changeInError - deltaX.get(deltaX.size()-1) > 2)) // If the change in error is 2 degrees greater than the previous...don't add it. 
    deltaX.add(changeInError);
    // Take the difference between the navX value now than before. 
   // if(!status){
      if(changeInError < 0 ){
          
        driveSubsystem.arcadeDrive(-outputSpeed, 0);
         
        
      }
      else if(changeInError > .05 && deltaX.get(deltaX.size()-2) >0.05 ) // If current change in error is greater than 0.5 and the previous entry is too....
      {
        if(Math.abs(navX.returnPitch())> 1.25){ 
        driveSubsystem.arcadeDrive(outputSpeed, 0);
       
      }else{
        driveSubsystem.arcadeDrive(0, 0);
        //status = true;
        runCount =0;
      }
    }
   //   }
    
   
    robotAngle = navX.returnPitch();
    SmartDashboard.putNumber("Error", deltaX.get(deltaX.size()-1));
    SmartDashboard.putNumber("Change in error", changeInError);
    SmartDashboard.putNumber("Balance Speed", outputSpeed); 
    SmartDashboard.putBoolean("On Ramp", isOn); // RED IS NOT GOOD
    SmartDashboard.putBoolean("Completed", status); // RED IS NOT GOOD
   
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
