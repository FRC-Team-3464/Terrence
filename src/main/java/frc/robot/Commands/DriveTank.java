package frc.robot.Commands;

import frc.robot.OI;
import frc.robot.Subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
   * Enables tank drive with 2 joysticks
   *
   * @param DriveSubsystem The subsystem used by this command.
   */

public class DriveTank extends CommandBase {
  //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem driveSub;

  public DriveTank(DriveSubsystem drive) {
    driveSub = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSub);
  }

  @Override
  public void initialize() {
        
  }
  @Override
  public void execute() {
    //sets left speed to left joystick value, and right speed to right joystick value with 80% multiplier
    driveSub.tankDrive(OI.leftStick.getY()*.8,OI.rightStick.getY()*.8);
  }

 @Override
  public boolean isFinished() {
      return false;
  }

}