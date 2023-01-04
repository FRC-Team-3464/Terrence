package frc.robot.Commands;
import frc.robot.Subsystems.ElevatorSubsystem;
//package frc.robot.Autos;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
   * Enables downward detraction of elevator
   *
   * @param ElevatorSub ystemThe subsystem used by this command.
   */

public class ElevatorDown extends CommandBase {
 
 private final ElevatorSubsystem elevSub;

  public ElevatorDown(ElevatorSubsystem elev){
    elevSub = elev;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(elevSub);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute(){

    elevSub.elevatorDown();
        
  }
  @Override
  public boolean isFinished(){
      return true;
  }
}
