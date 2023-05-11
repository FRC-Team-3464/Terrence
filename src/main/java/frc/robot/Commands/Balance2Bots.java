// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.BalancePIDSubsystem2Bots;
import frc.robot.Subsystems.EncoderSubsystem;

public class Balance2Bots extends CommandBase {
 
  
  private final BalancePIDSubsystem2Bots balanceSub;
  private final EncoderSubsystem encoderSub;
  /** Creates a new BalanceHold. */
  public Balance2Bots(BalancePIDSubsystem2Bots balance, EncoderSubsystem encoder) {
    balanceSub = balance;
    encoderSub = encoder;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(balanceSub, encoderSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    encoderSub.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    balanceSub.useOutput(balanceSub.getMeasurement(), 0); // Run the autobalance PID Command
    System.out.println("Running Command AutoHold PID");
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
