// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.DriveSubsystem;
// import frc.robot.Subsystems.GyroSubsystem;
import frc.robot.Subsystems.NavXSubsystem;

public class NavXPIDCMD extends CommandBase {
  /** Creates a new NavXPIDCMD. */

  private final NavXSubsystem navXSub;
  private final DriveSubsystem driveSub;

  private final PIDController turnPID = new PIDController(0.0025,0,0);

  public NavXPIDCMD(NavXSubsystem navX, DriveSubsystem drive) {
    navXSub = navX;
    driveSub = drive;
    addRequirements(navXSub);
    addRequirements(driveSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSub.arcadeDrive(0, turnPID.calculate(navXSub.getDegrees(), 180));
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
