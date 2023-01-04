package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.ShooterSubsystem;
/**
 * Creates a new ShooterCommand.
 *
 * @param ShooterSubsystem The subsystem used by this command.
 */

public class ShooterRun extends CommandBase{
    private final ShooterSubsystem shootSub;
   
    double iterations;
    double speedInt;
    double startTime;
  
    boolean isFin;
    
    public ShooterRun(ShooterSubsystem shoot){
      shootSub = shoot;
      
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(shootSub);

    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        shootSub.shoot(-.6);
    }
    @Override
    public boolean isFinished(){
        return true;
    }
}
