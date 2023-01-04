
package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Subsystems.IntakeSubsystem;

/**
   * Enables top and bottom intake motors, spinning all 5 rollers
   *
   * @param IntakeSubsystem The subsystem used by this command.
   */

public class IntakeBoth extends CommandBase {
  //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeSubsystem intakeSub;

  public IntakeBoth(IntakeSubsystem intake) {
    intakeSub = intake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intakeSub);
  }

  @Override
  public void initialize() {
        
  }
  @Override
  public void execute() {
    intakeSub.intakeBoth();
  }

 @Override
  public boolean isFinished() {
      return true;
  }

}
