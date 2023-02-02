package frc.robot.Subsystems;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

/**
 * This subsystem is used to target the robot to the center of the target the limelight creates
 * Utilises a PID loop
 * Check FRC PIDSubsystem documentation for more information
 */
public class BalancePIDSubsystem extends PIDSubsystem {
    //these should be using the subsystems declared in RobotContainer, not creating new ones
    //Works fine as is
    private final DriveSubsystem driveSub = new DriveSubsystem();
    //private final LimelightSubsystem limeSub = new LimelightSubsystem();
    private final NavXSubsystem navXSub = new NavXSubsystem();
    private double speed;
    
    //create PID with predetermined constants
    public BalancePIDSubsystem(){
        super(new PIDController(-0.03, 0, .001));
        getController().setSetpoint(0);
        getController().setTolerance(3);
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
        //if(isEnabled())
            //driveSub.arcadeDrive(0, getController().calculate(getMeasurement()));
    }
    
}
