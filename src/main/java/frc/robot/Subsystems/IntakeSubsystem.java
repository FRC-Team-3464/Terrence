package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
/**
 * 
 * 
 */
public class IntakeSubsystem extends SubsystemBase {

  private final Relay intakeRelay = new Relay(0);

  private Spark 
    intakeBottom= RobotMap.intakeBottom, 
    intakeTop = RobotMap.intakeTop; 

  //turn on both bottom and top intake
  public void intakeBoth() {
      intakeBottom.set(-.75);
      intakeTop.set(-.65);
    
  }
  //-1, Reverse; 0, Off; 1 Forward
  public void reLaySetDirection(int d){
if(d== -1){
  intakeRelay.set(Value.kReverse);
}
if(d== 0){
  intakeRelay.set(Value.kOff);
}
if(d== 1){
  intakeRelay.set(Value.kForward);
}

  }

  

  public void intakeBottom(double speed) {
    // Turn on motor for intake bottom
      intakeBottom.set(speed);    
  }
  public void intakeTop(double speed) {
    // Turn on motor for intake bottom
      intakeTop.set(speed);    
  }




  public void stopIntakes() {
    // Stops all motors
    intakeBottom.set(0);
    intakeTop.set(0);
  }

  public void reverse(){
    //reverses both intakes
    intakeBottom.set(.75);
    intakeTop.set(.65);
  }
}
