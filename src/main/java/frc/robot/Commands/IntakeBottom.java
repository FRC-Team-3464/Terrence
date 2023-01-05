
package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Subsystems.IntakeSubsystem;

/**
   * Enables only the bottom intake motor, spinning the bottom 3 rollers
   *
   * @param IntakeSubsystem The subsystem used by this command.
   */
  
public class IntakeBottom extends CommandBase {
  //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeSubsystem intakeSub; // Add the sub

  public IntakeBottom(IntakeSubsystem intake) {
    intakeSub = intake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intakeSub);
  }

  @Override
  public void initialize() {
        
  }
  @Override
  public void execute() {
    intakeSub.intakeBottom(-.75);
    intakeSub.reLaySetDirection(1);
    //Intake forward
  }
  // public void stop(){
  //   intakeSub.reLaySetDirection(0);
  // }

 

 @Override
  public boolean isFinished() {
      return true;
  }

}
