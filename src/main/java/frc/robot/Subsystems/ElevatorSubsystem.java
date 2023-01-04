package frc.robot.Subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
/**
 * Subsystem for all elevator functions
 * 
 */
public class ElevatorSubsystem extends SubsystemBase {

    private final Spark elevatorMotor = RobotMap.elevatorMotor;
    private final DigitalInput bottomLimitSwitch = RobotMap.bottomLimitSwitch;
    private final DigitalInput topLimitSwitch = RobotMap.topLimitSwitch;

    //elevator moves up until limit switch is hit
    
    public void elevatorUp(){
      //when limit switch hit, no longer goes up
        if (!this.topLimitSwitch.get()) {
            elevatorMotor.set(0.0);
          }
          else{
            elevatorMotor.set(-1);
          }
    }
    //elevator moves down until limit switch is hit
    
    public void elevatorDown(){
      //when limit switch hit, no longer goes down
        if (!this.bottomLimitSwitch.get()){
            elevatorMotor.set(0.0); 
          }
          else{
            elevatorMotor.set(1);
          }
    }

    //stop elevator motor
    public void stopElevator(){
      elevatorMotor.set(0);
    }

  
  
    @Override
    public void periodic() {
    // This method will be called once per scheduler run
    }
}


