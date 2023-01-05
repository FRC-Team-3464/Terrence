package frc.robot.Commands;

import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Subsystems.EncoderSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
   * Enables tank drive with 2 joysticks
   *
   * The subsystem used by this command.
   */

public class pidCMDforward extends CommandBase {
  //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final PIDController driverPID = new PIDController(0.015625, 0, 0);
//   private final PIDCommand driveFowardPID = new PIDCommand(driverPID, measurementSource, setpointSource, useOutput, requirements);
private final DriveSubsystem driveSub;
private final EncoderSubsystem encoderSub;
  public pidCMDforward(DriveSubsystem drive, EncoderSubsystem encode) {
      driveSub = drive;
      encoderSub = encode;
      addRequirements(driveSub);
      addRequirements(encoderSub);
  }

  @Override
  public void initialize() {
        
  }
  @Override
  public void execute() {
    driveSub.arcadeDrive(driverPID.calculate(encoderSub.getRightEncoder(), -32), 0);
  }


  @Override
  public void end(boolean interrupted){
    encoderSub.resetEncoders();
  }

 @Override
  public boolean isFinished() {
    if(Math.abs(driverPID.getPositionError()) < 12){
        return true;
    }
    else{
        return false;
  }}

}