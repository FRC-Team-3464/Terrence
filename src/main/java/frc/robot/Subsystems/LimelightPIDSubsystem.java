package frc.robot.Subsystems;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

/**
 * This subsystem is used to target the robot to the center of the target the limelight creates
 * Utilises a PID loop
 * Check FRC PIDSubsystem documentation for more information
 */
public class LimelightPIDSubsystem extends PIDSubsystem {
    //these should be using the subsystems declared in RobotContainer, not creating new ones
    //Works fine as is
    private final DriveSubsystem driveSub = new DriveSubsystem();
    private final LimelightSubsystem limeSub = new LimelightSubsystem();
    
    //create PID with predetermined constants
    public LimelightPIDSubsystem(){
        super(new PIDController(0.05, 0, .0025));
        getController().setSetpoint(0);
    }

    @Override
    public void useOutput(double output, double setpoint){
        driveSub.arcadeDrive(0, getController().calculate(output, setpoint));
    }
    @Override  
    public double getMeasurement(){
        return limeSub.getLimeX();
    }

    @Override
    public void periodic(){
        //if(isEnabled())
            //driveSub.arcadeDrive(0, getController().calculate(getMeasurement()));
    }
    
}
