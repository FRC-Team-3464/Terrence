package frc.robot.Commands;
import frc.robot.Subsystems.ElevatorSubsystem;
//package frc.robot.Autos;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
   * Enables upward extension of elevator
   *
   * @param ElevatorSubsystem The subsystem used by this command.
   */
public class ElevatorUp extends CommandBase {
 
 private final ElevatorSubsystem elevSub;

  public ElevatorUp(ElevatorSubsystem elev){
    elevSub = elev;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(elevSub);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute(){

    elevSub.elevatorUp();
        
  }
  @Override
  public boolean isFinished(){
      return true;
  }
}
