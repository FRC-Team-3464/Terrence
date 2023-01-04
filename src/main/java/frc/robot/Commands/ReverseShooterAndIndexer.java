package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.IntakeSubsystem;
import frc.robot.Subsystems.ShooterSubsystem;

/**
  * Reverses motion of flywheel and indexer to spit out cargo
  *
  * @param IntakeSubsystem @param ShooterSubsystem The subsystems used by this command.
  */

public class ReverseShooterAndIndexer extends CommandBase{
    private final ShooterSubsystem shootSub;
    private final IntakeSubsystem intakeSub;
   
    double iterations;
    double speedInt;
    double startTime;
  
    boolean isFin;
    
    public ReverseShooterAndIndexer(IntakeSubsystem intake, ShooterSubsystem shoot){
      shootSub = shoot;
      intakeSub = intake;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(shootSub);
      addRequirements(intakeSub);
    
    }
    @Override
    public void initialize() {

    }
    @Override
    public void execute() {
        intakeSub.reverse();
        shootSub.reverse();
     }

    @Override
    public boolean isFinished() {
      return true;
    }

}
