package frc.robot.Subsystems;
import java.sql.Time;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

/**
 * This subsystem is used to target the robot to the center of the target the limelight creates
 * Utilises a PID loop
 * Check FRC PIDSubsystem documentation for more information
 */
public class BalancePIDSubsystem2Bots extends PIDSubsystem {
    //these should be using the subsystems declared in RobotContainer, not creating new ones
    //Works fine as is
    private final DriveSubsystem driveSub = new DriveSubsystem();
    private final EncoderSubsystem encoderSub = new EncoderSubsystem();
    //private final LimelightSubsystem limeSub = new LimelightSubsystem();
    private final NavXSubsystem navXSub = new NavXSubsystem();
    private double speed;
    private double distance; // create distance
    private Timer timer = new Timer();
   
    private double encoderDistance;
    //create PID with predetermined constants
    public BalancePIDSubsystem2Bots(){ // What runs when we first create this command. 
        super(new PIDController(-0.01, 0.00, .001)); 
        getController().setSetpoint(0);
        getController().setTolerance(3);
        timer.start();
        distance = 0;
        encoderDistance = 0;
    }
 
    @Override
    public void useOutput(double output, double setpoint){
        speed = getController().calculate(output, setpoint);
        if(speed > .35){
            speed = .35;
        }
        else if(speed < -.35){
            speed = -.35;
        }
        
        driveSub.arcadeDrive(speed, 0);
        System.out.println(speed);
    }
    @Override  
    public double getMeasurement(){
        return navXSub.getDegrees();
    }
    
    @Override
    public void periodic(){
        // Distance slipped we gotta find "4". 
        double currentTime = timer.getFPGATimestamp(); // Get the current time. 
        double accelerationX = navXSub.returnXAccelNum(); // Get our x acceleartion multiply by s^2 gives me meters. 

        double distanceTraveled = accelerationX * Math.pow(accelerationX, 2);
        distance += distanceTraveled;
        SmartDashboard.putNumber("Distance in Meters", distance);
        //   Now what I'm going to do is find the distance traveled to be the accleration times time squared. 
        timer.reset(); 
        //if(isEnabled())

        
            //driveSub.arcadeDrive(0, getController().calculate(getMeasurement()));
    }
    
}
