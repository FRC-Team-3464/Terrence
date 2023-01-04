package frc.robot.Commands;

import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Subsystems.LimelightPIDDistanceSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
   * Uses distance subsystem to drive robot to desired setpoint, in this case 83.2 inches from the goal
   *
   * @param DriveSubsystem @param LimelightPIDDistanceSubsystem The subsystems used by this command.
   */

public class limeAimDistance extends CommandBase {
  //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem driveSub;
  private final LimelightPIDDistanceSubsystem limePIDSub;

  public limeAimDistance(DriveSubsystem drive, LimelightPIDDistanceSubsystem lime) {
    driveSub = drive;
    limePIDSub = lime;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSub);
    addRequirements(limePIDSub);
  }

  @Override
  public void initialize() {
        
  }
  @Override
  public void execute() {
    limePIDSub.useOutput(limePIDSub.getMeasurement(),83.2);
  }

 @Override
  public boolean isFinished() {
      return true;
  }

}