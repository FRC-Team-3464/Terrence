package frc.robot.Commands;

import frc.robot.OI;
import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Subsystems.RampComponent;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
   * Enables drivetrain with any controller
   *
   * @param DriveSubsystem, @param RampComponent The subsystem used by this command.
   */
   
public class DriveTrain extends CommandBase {
  //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem driveSub;
  private double speed;
  private double rotation;
  private final RampComponent rampComp;
  private double trigger;

  public DriveTrain(DriveSubsystem drive, RampComponent rampC) {
    driveSub = drive;
    rampComp = rampC;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSub);
    addRequirements(rampComp);
  }

  @Override
  public void initialize() {
        
  }
  @Override
  public void execute() {
    //takes the right trigger of the xbox controller, from 0 to 1, and multiplies it by .2
    //the .2 multiplication is to make it account for 20% of the power
    trigger = .2*OI.controller.getRawAxis(3);
    //takes joystick on controller, -1 to 1, and multiplies by .8
    //.8 for 80% speed
    speed = OI.controller.getRawAxis(1)*.8;

    //deadband
    if (Math.abs(speed) < 0.07) {
      speed = 0;
      //System.out.println("L U Suck");
    }
    if (Math.abs(rotation) < 0.07) {
      rotation = 0;  
      //System.out.println("R ur succ");
    }
    //allows for speeding up both forwards and backwards
    if(speed > 0){
      //adds trigger speed to joystick speed when going forward
      speed += trigger;
    }
    else{
      //sub
      //System.out.println("R ur succ");
    }
    //allows for speeding up both forwards and backwards
    if(speed > 0){
      //adds trigger speed to joystick speed when going forward
      speed += trigger;
    }
    else{
      //subtracts trigger speed from joystick when going backwards
      speed -= trigger;
    }
    //applies ramp to drive train if trigger is pressed
    //if(OI.controller.getRawAxis(3) > .05)
      speed = rampComp.applyAsDouble(speed);
    
    //sets drive train to speed, and then the rotation to another joystick value with a 65% multiplier
    // This is -0.55.. I think .

    driveSub.arcadeDrive(speed, OI.controller.getRawAxis(4)*-.55);
  }
    

 @Override
  public boolean isFinished() {
      //default command, so always running unless inturrupted
      return false;
  }

}