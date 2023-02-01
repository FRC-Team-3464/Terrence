// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.LimelightSubsystem;

public class toggleLimelightCommand extends CommandBase {
  /** Creates a new toggleLimelightCommand. */
  private final LimelightSubsystem limelightSub;
  private boolean onOff;

  public toggleLimelightCommand(LimelightSubsystem limeSub, boolean on) {
    limelightSub = limeSub;
    onOff = on;
    addRequirements(limeSub);
 
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    limelightSub.toggleLimelight(onOff);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    navXSub.resetGyro();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(turnPID.getPositionError()) < 25){
      return true;
    }else{
    return false;
  }
}
}
