package frc.robot.Autos;

import frc.robot.Robot;
import frc.robot.Commands.Shoot2Balls;

//package frc.robot.Autos;

import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Subsystems.EncoderSubsystem;
import frc.robot.Subsystems.GyroSubsystem;
import frc.robot.Subsystems.ShooterSubsystem;
import frc.robot.Subsystems.IntakeSubsystem;
import frc.robot.Subsystems.LimelightPIDSubsystem;
import edu.wpi.first.wpilibj.Timer;
//import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.Commands.*;

/** Auto 1 is an auto that collecs two balls*/
public class Auto1 extends CommandBase {
// Auto 1: Upon execution, we go forward, then intake a ball. Then turn, auto angle, and shoot. 
// 

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem driveSub;
  private final EncoderSubsystem encoderSub;
  private final IntakeSubsystem intakeSub;
  private final ShooterSubsystem shootSub;
  private final limeAim aim;
  private final LimelightPIDSubsystem limePIDSub;
  private final GyroSubsystem gyroSub;

  private final Shoot2Balls shoot2;
  //private final IntakeBottom intakeBottom;
  //private final DriveTrain arcadeDrive; 
  private double leftEncoder;
  private double rightEncoder;
  private Timer autoTimer;
  double iterations;

  boolean isFin;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Auto1(DriveSubsystem drive, EncoderSubsystem enc, IntakeSubsystem intake, ShooterSubsystem shoot, LimelightPIDSubsystem lime, GyroSubsystem gyro) {
    driveSub = drive;
    encoderSub = enc;
    shootSub = shoot;
    intakeSub = intake;
    shoot2 = new Shoot2Balls(intakeSub, shootSub, -.55);
    aim = new limeAim(drive, lime);
    limePIDSub = lime;
    gyroSub = gyro;
    //intakeBottom = intake;
    
    //arcadeDrive = new DriveTrain(driveSub);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSub);
    addRequirements(encoderSub);
    addRequirements(intakeSub);
    addRequirements(shootSub);
    addRequirements(gyroSub);
    
    iterations = 0;
    leftEncoder = 0;
    rightEncoder = 0;
    isFin = false;
  }

  

  @Override
  public void initialize() {
    //create timer, set encoders to 0
    autoTimer = new Timer();
    encoderSub.resetEncoders();
    encoderSub.setLeftEncoder(0);
    encoderSub.setRightEncoder(0);
    autoTimer.stop();
    autoTimer.reset();
    
  }

  @Override
  public void execute() {
    //System.out.println("Auto1");
    //update encoder values
    double leftEncoder = encoderSub.getLeftEncoder();
    double rightEncoder = encoderSub.getRightEncoder();
    //driveSub.arcadeDrive(-.5, 0);

    //iterations is a variable used to keep track of what function the robot is currently running
    if(iterations == 0){
      //resets encoders
      encoderSub.resetEncoders();
      encoderSub.setLeftEncoder(0);
      encoderSub.setRightEncoder(0);
      //starts intake
      intakeSub.intakeBottom(-.75);
      iterations++;
      
    }
    if(iterations == 1){
      //drives until encoders hit -32 (encoder values are inverted)
    while(leftEncoder > -32 /*&& rightEncoder > -30*/){
      //updates encoder value in while loop
      leftEncoder = encoderSub.getLeftEncoder();
      //CommandScheduler.getInstance().schedule(intakeBottom);
      //driveSub.tankDrive(-.5, -.5); //FORWARD
      //drives forward
      driveSub.arcadeDrive(-0.6, 0);
      System.out.println("Forward/Intake");
      Robot.autoStatus = "Forward/Intake";
      
     }
     if(iterations == 1)
        //adds one to iterations to start next function
        iterations ++;
    }
     else if(iterations == 2){
       //starts timer
        autoTimer.start();
        //if(autoTimer.get() > 1){
        System.out.println("Poss");
          //runs until encoder hits -18 (moves backwards)
          while(leftEncoder < -18){
            leftEncoder = encoderSub.getLeftEncoder();
            driveSub.arcadeDrive(.5, 0);
            System.out.println("Back it up baby");
            System.out.println(autoTimer.get());
          }
          //increment iterations 
          iterations ++;
          //reset and stop timer
          autoTimer.reset();
          autoTimer.stop();
          System.out.println(autoTimer.get());
          //stop driveTrain and intakes
          driveSub.arcadeDrive(0, 0);
          intakeSub.stopIntakes();
          //start auto timer again
          autoTimer.start();
        //}
     }
      
    else if(iterations == 3){
       
      //aims with gyro to 180 degrees for 4.5 seconds
        while(autoTimer.get() < 4.5){
          gyroSub.gyroAim(180);
        }
        //increment iterations
        iterations++;
        //stop drive train
        driveSub.arcadeDrive(0, 0);
        //reset timer
        autoTimer.reset();
        System.out.println("Waiting");
        Robot.autoStatus = "Waiting";
    }
    else if(iterations == 4){

      System.out.println("Aiming??");
      //set drive train for aiming
      limePIDSub.useOutput(limePIDSub.getMeasurement(),0.0);
      //after one second
      if(autoTimer.get() > 1){
        System.out.println("shooting");
        iterations = 0;
        //schedule shooting command
        CommandScheduler.getInstance().schedule(shoot2);
        //stop command
        isFin = true;
      }
             
    }        
  }
      
  @Override
  public boolean isFinished() {
    return isFin;
  }
}


