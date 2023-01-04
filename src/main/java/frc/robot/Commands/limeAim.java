package frc.robot.Commands;

import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Subsystems.LimelightPIDSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

 /**
   * limelight aiming system
   *
   * @param DriveSubsystem @param LimelightPIDsubsystem The subsystems used by this command.
   */

public class limeAim extends CommandBase {
  //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem driveSub;
  private final LimelightPIDSubsystem limePIDSub;

  public limeAim(DriveSubsystem drive, LimelightPIDSubsystem lime) {
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
    limePIDSub.useOutput(limePIDSub.getMeasurement(),0.0);
  }

 @Override
  public boolean isFinished() {
      return true;
  }

}