package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.Subsystems.DriveSubsystem;

/**
   * Enables straight driving with a single joystick.
   *
   * @param DriveSubsystem The subsystem used by this command.
   */
   
public class DriveStraightSingleJoystick extends CommandBase {
  //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem driveSub;

  public DriveStraightSingleJoystick(DriveSubsystem drive) {
    driveSub = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSub);
  }

  @Override
  public void initialize() {
        
  }
  @Override
  public void execute() {
    driveSub.tankDrive(OI.rightStick.getY(),OI.rightStick.getY());
  }

 @Override
  public boolean isFinished() {
      return true;
  }

}