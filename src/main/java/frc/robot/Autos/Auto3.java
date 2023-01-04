package frc.robot.Autos;

import frc.robot.Robot;
import frc.robot.Commands.Shoot2Balls;

//package frc.robot.Autos;

import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Subsystems.EncoderSubsystem;
import frc.robot.Subsystems.ShooterSubsystem;
import frc.robot.Subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.templates.commandbased.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Commands.*;
import frc.robot.Subsystems.*;

/** 
 * 
 * 
 * NEVER USED JUST AUTO 1
 * 
 * 
 */
public class Auto3 extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem driveSub;
  private final EncoderSubsystem encoderSub;

  private final Shoot2Balls shoot2;
  private final IntakeBottom intakeBottom;
  private double leftEncoder;
  private double rightEncoder;
  private Timer autoTimer;
  double iterations;
  private final GyroSubsystem gyroSub;

  boolean isFin;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Auto3(DriveSubsystem drive, EncoderSubsystem enc, IntakeBottom intake, Shoot2Balls shooter, GyroSubsystem gyro ) {
    driveSub = drive;
    encoderSub = enc;
    shoot2 = shooter;
    intakeBottom = intake;
    gyroSub = gyro;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSub);
    addRequirements(encoderSub);
    
    iterations = 0;
    leftEncoder = 0;
    rightEncoder = 0;
    isFin = false;
  }

  

  @Override
  public void initialize() {
    autoTimer = new Timer();
    encoderSub.resetEncoders();
    autoTimer.start();
  }

  @Override
  public void execute() {
    while(autoTimer.get() < 4){
      gyroSub.gyroAim(0);
    }
    while(autoTimer.get() > 3.5 && autoTimer.get() < 8){
      gyroSub.gyroAim(95);
    }
    isFin = true;
  }

  @Override
  public boolean isFinished() {
    return isFin;
  }
}


