package frc.robot.Commands;

import frc.robot.Subsystems.AnalogSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
   * Enables pressure sensor to check if ball is successfully collected
   * DOES NOT WORK
   */
   
public class isBallCollected extends CommandBase {
  //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final AnalogSubsystem analogSub;

  public isBallCollected(AnalogSubsystem anall) {
    analogSub = anall;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(analogSub);
  }

  @Override
  public void initialize() {
        
  }
  @Override
  public void execute() {
    analogSub.isBallCollected();
  }

 @Override
  public boolean isFinished() {
      return false;
  }

}