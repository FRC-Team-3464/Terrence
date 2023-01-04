package frc.robot.Subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class GyroSubsystem extends SubsystemBase {
  /**
   * Creates a new GyroSubsystem.
   * Also includes PID loop for turning to a specified angle
   */
  private final DriveSubsystem driveSub;
  private final ADXRS450_Gyro gyro = RobotMap.gyro;
  
  //create PID loop and rampUp
  PIDController pid = new PIDController(.015,0,0);
  RampUp ramper = new RampUp(1.33);

  private double speed;


  public GyroSubsystem(DriveSubsystem drive) {
    driveSub = drive;
  }

  //returns current angle of gyri
  public double getAngle(){

    return gyro.getAngle();

  }
  //calibrates gyro to 0 for current position (Takes 10 seconds to do, do not recommend use during a match)
  public void calibrate(){

    gyro.calibrate();

  }

  //automatically aim to a specified angle
  public void gyroAim(double angle){
     speed = pid.calculate(gyro.getAngle(),angle);
        System.out.println(speed);
        if(speed > .45){
            speed = .45;

        }
        else if(speed < -.45){
            speed = -.45;
        }
        speed = ramper.applyAsDouble(speed);
        driveSub.arcadeDrive(0,-speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}