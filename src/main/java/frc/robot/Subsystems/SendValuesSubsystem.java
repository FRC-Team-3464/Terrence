package frc.robot.Subsystems;



import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

/*
*
*
*   DOES NOT WORK
*  Supposed to send values to ShuffleBoard, does not actually do it though
*
*
*/
public class SendValuesSubsystem extends SubsystemBase {

  private final NetworkTable table;
  private final NetworkTableEntry tx;
  private final NetworkTableEntry ty;
  private final NetworkTableEntry ta;

  private final double degree_small = -0.02D;
  private final double min_speed = 0.095D;

  private final AnalogSubsystem analog = new AnalogSubsystem();
  private final ShooterSubsystem shooter = new ShooterSubsystem();
  private final LimelightSubsystem limeSub = new LimelightSubsystem(); 


  public SendValuesSubsystem() {
    //gets limelight table
    table = NetworkTableInstance.getDefault().getTable("limelight");
    //gets entry for each respective value
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
  }

  public void sendValues(){
    SmartDashboard.putNumber("Pressure Sensor Voltage", RobotMap.pressureSensor.getVoltage());
    SmartDashboard.putNumber("LFSpeed",RobotMap.leftFront.get());
    SmartDashboard.putNumber("RFSpeed",RobotMap.rightFront.get());

    //LEFT inverted, RIGHT not
    SmartDashboard.putNumber("LFEncoder",RobotMap.leftFrontEncoder.getPosition());
    SmartDashboard.putNumber("RFEncoder",RobotMap.rightFrontEncoder.getPosition());

    double limex = this.tx.getDouble(0.0D);
    double limey = this.ty.getDouble(0.0D);
    double limeArea = this.ta.getDouble(0.0D);
    SmartDashboard.putNumber("LimelightX", limex);
    SmartDashboard.putNumber("LimelightY", limey);
    SmartDashboard.putNumber("LimelightArea", limeArea);
    SmartDashboard.putNumber("Shooter", shooter.getSpeed());
    //SmartDashboard.putNumber("Distance", limeSub.getDistanceInches());
    //SmartDashboard.putBoolean("Ball???", );
  }

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
  }
}