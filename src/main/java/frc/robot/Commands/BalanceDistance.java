package frc.robot.Commands;

import frc.robot.Subsystems.BalancePIDSubsystem;
import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Subsystems.LimelightPIDDistanceSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
   * Uses distance subsystem to drive robot to desired setpoint, in this case 83.2 inches from the goal
   *
   * @param DriveSubsystem @param LimelightPIDDistanceSubsystem The subsystems used by this command.
   */

public class BalanceDistance extends CommandBase {
  //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem driveSub;
  //private final LimelightPIDDistanceSubsystem limePIDSub;
  private final BalancePIDSubsystem balanceSub;

  public BalanceDistance(DriveSubsystem drive, BalancePIDSubsystem lime) {
    driveSub = drive;
    balanceSub = lime;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSub);
    addRequirements(balanceSub);
  }

  @Override
  public void initialize() {
        
  }
  @Override
  public void execute() {
    balanceSub.useOutput(balanceSub.getMeasurement(), 0);
    System.out.println("Running Command PID");
  }

 @Override
  public boolean isFinished() {
      return false;
  }

}