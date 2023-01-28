package frc.robot.Subsystems;



import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Subsystem used for all limelight function
 * In this case, the primary function is to calculate the horizontal distance between the robot and the goal
 */
public class LimelightSubsystem extends SubsystemBase {

  //grabs table for limelight
  private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");;
  //Tx is the horizontal offset from center of target limelight has made
  private final NetworkTableEntry tx = table.getEntry("tx");
  //Ty is the vertical offset
  private final NetworkTableEntry ty = table.getEntry("ty");
  //Ta is the area of the screen the target takes up (Never utilised)
  private final NetworkTableEntry ta = table.getEntry("ta");

  
  private double tx_value;
  private double ty_value;

  double targetOffsetAngle_Vertical = ty.getDouble(0.0);
  
  // how many degrees back is your limelight rotated from perfectly vertical?
  double limelightMountAngleDegrees = 30.0;
  
  // distance from the center of the Limelight lens to the floor
  double limelightLensHeightInches = 28.5;
  
  // distance from the target to the floor
  double goalHeightInches = 104.0;
  
  //Math equations
  double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
  double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
  
  //calculate distance
  double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches)/Math.tan(angleToGoalRadians);
  

  public void toggleLimelight(boolean on){
    if(on){
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setDouble(3.0D);
    }else{
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setDouble(1.0D);
    }
  }




  public LimelightSubsystem(/*DriveSubsystem DriveSubb*/) {
    //addRequirements(driveSub);
    ///driveSub = DriveSubb;
    
  
    
    tx_value = this.tx.getDouble(0);
  }

  /*public void targetLime(){
    //if (RobotMap.controller.getRawButton(3)) {
      //NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setDouble(3.0D);
      tx_value = this.tx.getDouble(0.0D);
      if (this.tx.getDouble(0.0D) > 2.0D) {
        driveSub.arcadeDrive(0, degree_small * tx_value - min_speed);
      } 
      else if (this.tx.getDouble(0.0D) < -2.0D) {
        driveSub.arcadeDrive(0, degree_small * tx_value + min_speed);
      } 
    //}

  }*/

  //returns tx value
  public double getLimeX(){
    return tx_value;
  }

  //does Math with ty Value to find the horizontal distance from the goal in inches
  public double getDistanceInches(){
    //System.out.println(ty_value);
    //System.out.println(tx_value);
    targetOffsetAngle_Vertical = ty_value;
    angleToGoalDegrees = limelightMountAngleDegrees + ty_value;
    angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
    distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches)/Math.tan(angleToGoalRadians);
    //System.out.println(distanceFromLimelightToGoalInches);
    return distanceFromLimelightToGoalInches;
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    //Continuously updates tx and ty values
    tx_value = this.tx.getDouble(0);
    ty_value = this.ty.getDouble(0);
    
  }
}