
package frc.robot.Subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import frc.robot.OI;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

/**
 * 
 * Subsystem for controlling all drive train behavior
 * 
 */
public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */ 
  
  private final CANSparkMax
    leftFront = RobotMap.leftFront, 
    leftBack = RobotMap.leftBack, 
    rightFront = RobotMap.rightFront, 
    rightBack = RobotMap.rightBack;

  public DifferentialDrive drive = RobotMap.drive;

  //private RampComponent speedRamp = new RampComponent(1.33, 3);
  //private

  public DriveSubsystem(){
      //Invert left motor values (Backwards) 
      leftFront.setInverted(true);
      
  }

    
  
  //Drive robot in tank drive mode
  public void tankDrive(double left, double right){
    
    //stop minute movements, deadband
    if (Math.abs(left) < 0.07) {
      left = 0;
      
    }
    if (Math.abs(right) < 0.07) {
      right = 0;  
      
    }
    
    
    drive.tankDrive(left,right);
    //set back motors the same as front motors, because only front motors are set with tankDrive method
    leftBack.set(leftFront.get());
    rightBack.set(rightFront.get());
    //leftFront.getVoltage();
    
  }


  public void arcadeDrive(double speed, double rotation){
    
    //ramp.applyAsDouble(right);

    drive.arcadeDrive(speed, rotation);
    //set back motors the same as front motors, because only front motors are set with arcadeDrive method
    leftBack.set(leftFront.get());
    rightBack.set(rightFront.get());

    //System.out.println("Left Front"+leftFront.getBusVoltage());
    //System.out.println("Left Back"+leftBack.getBusVoltage());
    //System.out.println("Right Front"+rightFront.getBusVoltage());
    //System.out.println("Right Back"+rightBack.getBusVoltage());
    
  }

  //stop driveTrain motors
  public void stopDrive(){
    arcadeDrive(0, 0);
  }

  // public double getLeftDistance (){
  //   return leftFront.getP
  // }

  
  public double getLeft(){
    return leftFront.get();
  }
  public double getRight(){

    return rightFront.get();
  }

  //turns motors brake mode if true and coast mode if false
  public void enableMotors(boolean on){
    IdleMode mode;
    if (on) {
      mode = IdleMode.kBrake;
    }
    else{
      mode = IdleMode.kCoast;
    } 
    //set motors to mode
    leftFront.setIdleMode(mode);
    leftBack.setIdleMode(mode);
    rightFront.setIdleMode(mode);
    rightBack.setIdleMode(mode);
  }
 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}