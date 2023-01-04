
package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Subsystems.IntakeSubsystem;

/**
   * Enables the top intake motor, spinning only the top 2 rollers
   *
   * @param IntakeSubsystem The subsystem used by this command.
   */
  
public class IntakeTop extends CommandBase {
  //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeSubsystem intakeSub;

  public IntakeTop(IntakeSubsystem intake) {
    intakeSub = intake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intakeSub);
  }

  @Override
  public void initialize() {
        
  }
  @Override
  public void execute() {
    intakeSub.intakeTop(-.65);
  }

 @Override
  public boolean isFinished() {
      return true;
  }

}
