// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Subsystems.EncoderSubsystem;

public class MaintainBalancePID extends CommandBase {
  /** Creates a new MaintainBalancePID. */
  private final EncoderSubsystem encoderSub = new EncoderSubsystem();
  private final DriveSubsystem driveSub = new DriveSubsystem();

  private final PIDController maintainPID = new PIDController(0.03, 0, .001);
  
  public MaintainBalancePID() {
    maintainPID.setSetpoint(0); // We want to center. 
    maintainPID.setTolerance(1, 0.5);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
