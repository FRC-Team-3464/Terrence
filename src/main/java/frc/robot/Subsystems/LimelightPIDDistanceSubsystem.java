package frc.robot.Subsystems;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

/**
 * This subsystem is used to move the robot to the specified distance from the goal automatically
 * Utilises a PID loop
 * Check FRC PIDSubsystem documentation for more information
 */

public class LimelightPIDDistanceSubsystem extends PIDSubsystem {
    private final DriveSubsystem driveSub;
    private final LimelightSubsystem limeSub = new LimelightSubsystem();
    //declares a RampUp function
    private final RampUp ramper = new RampUp(1.33);
    
    private double speed;

    public LimelightPIDDistanceSubsystem(DriveSubsystem drive){
        super(new PIDController(0.034, 0.000, 0.00));
        getController().setSetpoint(82.33);
        driveSub = drive;
    }

    @Override  
    public void useOutput(double output, double setpoint){
        speed = getController().calculate(output, setpoint);
        //when no target is found, the inches equation defaults to about 130.6
        //To counteract crazy behavior when robot cant see the target, speed is set to 0
        if(output > 130 && output < 131)
            speed = 0;
        
        //System.out.println(speed);

        //sets an upper limit speed of 65% forward and backward
        if(speed > .65){
            speed = .65;
        }
        else if(speed < -.65){
            speed = -.65;
        }
        //ramp the speed acceleration, so robot does not wheelie and fall over when PIDLoop first initiated
        speed = ramper.applyAsDouble(speed);
        //set speed to driveTrain
        driveSub.arcadeDrive(speed, 0);
        /*double distance_error = setpoint - output;
        double driving_adjust = .01 * distance_error;
        speed = driving_adjust;
        driveSub.arcadeDrive(speed, 0);*/
        
    }

    //Aiming that technically works, but is less accurate than other aiming and is not tuned
    public void loserAiming(){
        double distance_error = 82.0 - getMeasurement();
        double driving_adjust = .01 * distance_error;
        speed += driving_adjust;
        driveSub.arcadeDrive(speed,0);
    }
    @Override  
    public double getMeasurement(){
        return limeSub.getDistanceInches();
    }

    @Override
    public void periodic(){
        //if(isEnabled())
            //driveSub.arcadeDrive(0, getController().calculate(getMeasurement()));
    }
    
}
