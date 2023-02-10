package frc.robot.Autos;

import frc.robot.Robot;
import frc.robot.Commands.Shoot2Balls;

//package frc.robot.Autos;

import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Subsystems.EncoderSubsystem;
import frc.robot.Subsystems.GyroSubsystem;
import frc.robot.Subsystems.ShooterSubsystem;
import frc.robot.Subsystems.IntakeSubsystem;
import frc.robot.Subsystems.LimelightPIDDistanceSubsystem;
import frc.robot.Subsystems.LimelightPIDSubsystem;
import edu.wpi.first.wpilibj.Timer;
//import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Commands.*;

/** An example command that uses an example subsystem. */
public class Auto2 extends CommandBase {
// Auto 2: Go forward, intake, turn, aim and shoot, then grab second ball then shoot it as well. 
// WARNING only turns via encoder values. 

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem driveSub;
  private final EncoderSubsystem encoderSub;
  private final IntakeSubsystem intakeSub;
  private final ShooterSubsystem shootSub;
  private final limeAim aim;
  private final LimelightPIDSubsystem limePIDSub;
  private final LimelightPIDDistanceSubsystem limeDistanceSub;
  private final GyroSubsystem gyroSub;

  private final Shoot2Balls shoot2;
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
  public Auto2(DriveSubsystem drive, EncoderSubsystem enc, IntakeSubsystem intake, ShooterSubsystem shoot, LimelightPIDSubsystem lime, LimelightPIDDistanceSubsystem limeDistancePID, GyroSubsystem gyro) {
    driveSub = drive;
    encoderSub = enc;
    shootSub = shoot;
    intakeSub = intake;
    shoot2 = new Shoot2Balls(intakeSub, shootSub, -.55);
    aim = new limeAim(drive, lime);
    limePIDSub = lime;
    limeDistanceSub = limeDistancePID;
    gyroSub = gyro;
    //intakeBottom = intake;
    
    //arcadeDrive = new DriveTrain(driveSub);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSub);
    addRequirements(encoderSub);
    addRequirements(intakeSub);
    addRequirements(shootSub);
    addRequirements(limeDistanceSub);
    addRequirements(limePIDSub);
    addRequirements(gyroSub);
    
    iterations = 0;
    leftEncoder = 0;
    rightEncoder = 0;
    isFin = false;
  }

  

  @Override
  public void initialize() {
    autoTimer = new Timer();
    encoderSub.resetEncoders();
    encoderSub.setLeftEncoder(0);
    encoderSub.setRightEncoder(0);
    iterations = 0;
    //intakeBottom.schedule();
    //driveSub.arcadeDrive(-0.5, 0);
    autoTimer.stop();
    autoTimer.reset();
  }

  @Override
  public void execute() {
    System.out.println("Auto2");
    double leftEncoder = encoderSub.getLeftEncoder();
    double rightEncoder = encoderSub.getRightEncoder();
    //driveSub.arcadeDrive(-.5, 0);
    if(iterations == 0){
      encoderSub.resetEncoders();
      encoderSub.setLeftEncoder(0);
      encoderSub.setRightEncoder(0);
      intakeSub.intakeBottom(-.75);
      iterations++;
      
    }
    if(iterations == 1){
    while(leftEncoder > -32 /*&& rightEncoder > -30*/){
      leftEncoder = encoderSub.getLeftEncoder();
      //CommandScheduler.getInstance().schedule(intakeBottom);
      //driveSub.tankDrive(-.5, -.5); //FORWARD
      driveSub.arcadeDrive(-0.6, 0);
      System.out.println("Forward/Intake");
      Robot.autoStatus = "Forward/Intake";
      
     }
     if(iterations == 1)
        iterations ++;
    }
     else if(iterations == 2){
        autoTimer.start();
        //if(autoTimer.get() > 1){
        System.out.println("Poss");
          while(leftEncoder < -18){
            leftEncoder = encoderSub.getLeftEncoder();
            driveSub.arcadeDrive(.5, 0);
            System.out.println("Back it up baby");
            System.out.println(autoTimer.get());
          }
          iterations ++;
          autoTimer.reset();
          autoTimer.stop();
          System.out.println(autoTimer.get());
          driveSub.arcadeDrive(0, 0);
          intakeSub.stopIntakes();
          autoTimer.start();
        //}
     }

    else if(iterations == 3){
      //OLD TURNING CODE, NOT UPDATED TO GYRO
        while(leftEncoder > -32.5){
          leftEncoder = encoderSub.getLeftEncoder();
          driveSub.arcadeDrive(0.0, -.5);
          System.out.println("Spinning");
          Robot.autoStatus = "Spinning";
          System.out.println(autoTimer.get());
        }
        iterations++;
        driveSub.arcadeDrive(0, 0);
        autoTimer.reset();
        System.out.println("Waiting");
        Robot.autoStatus = "Waiting";
    }
    else if(iterations == 4){
      System.out.println("Aiming??");
      limePIDSub.useOutput(limePIDSub.getMeasurement(),0.0);
      if(autoTimer.get() > 1){
        System.out.println(leftEncoder);
        //shoot command, just not a command
        shootSub.shoot(-.55);
        if(autoTimer.get() > 1.65) {
          intakeSub.intakeTop(-.8);
        }
          //System.out.println("TopIntake");
        if(autoTimer.get() > 1.8) {
          intakeSub.intakeBottom(-.65);
          //System.out.println("BottomIntake");
        }
        if(autoTimer.get() > 2.75) {
          shootSub.stopShooter();
          intakeSub.stopIntakes();
          iterations++;
          autoTimer.reset();
          //isFin = true;
          //System.out.println("Ended");
        }
        
      }
             
    }
    else if(iterations==5){
      System.out.println("Turning Back");
      Robot.autoStatus = "Turning Back";
      double leftEncoderTemp = leftEncoder;
      //boolean bool = true;
      //turns to 95 degrees for 3.5 seconds
      while(autoTimer.get() < 3.5){
        gyroSub.gyroAim(95);
      }
      /*while(gyroSub.getAngle()>90){
        leftEncoder = encoderSub.getLeftEncoder();
        driveSub.arcadeDrive(0.0, .5);
        System.out.println("Turning Back");
        Robot.autoStatus = "Turning Back";
        System.out.println(autoTimer.get());
        System.out.println(leftEncoder);
      }*/
      iterations++;
      //turn on intake, off drive train
      driveSub.arcadeDrive(0, 0);
      intakeSub.intakeBottom(-.75);
      autoTimer.reset();
    }
    else if(iterations==6){
      intakeSub.intakeBottom(-.75);
      //drive forward until encoder hits -84
      while(leftEncoder > -84 /*&& rightEncoder > -30*/){
        leftEncoder = encoderSub.getLeftEncoder();
        
        driveSub.arcadeDrive(-0.7, 0);
        System.out.println("Forward/Intake");
        Robot.autoStatus = "Forward/Intake";
        
       }
       
       iterations ++;
       driveSub.arcadeDrive(0, 0);
       autoTimer.reset();
    }
    else if(iterations==7){
      //OLD SPIN DO NOT USE
      while(leftEncoder > -105){
        leftEncoder = encoderSub.getLeftEncoder();
        driveSub.arcadeDrive(0.0, -.5);
        System.out.println("Spinning");
        Robot.autoStatus = "Spinning";
        System.out.println(autoTimer.get());
      }
      iterations++;
      driveSub.arcadeDrive(0, 0);
      intakeSub.stopIntakes();
      autoTimer.reset();
    }
    else if(iterations == 8){
        //aim for one second
        if(autoTimer.get() < 1){
          System.out.println("Aiming??");
          limePIDSub.useOutput(limePIDSub.getMeasurement(),0.0);
        }
        //after .75 seconds
        else if(autoTimer.get() > .75){
          System.out.println("shooting");
          //CommandScheduler.getInstance().schedule(shoot2);
          //
          //distance robot to 83.2 inches
          limeDistanceSub.useOutput(limeDistanceSub.getMeasurement(),83.2);
          //shoot command but not a command
          shootSub.shoot(-.55);
          if(autoTimer.get() > 1.65) {
            intakeSub.intakeTop(-.8);
          }
            //System.out.println("TopIntake");
          if(autoTimer.get() > 1.8) {
            intakeSub.intakeBottom(-.65);
            //System.out.println("BottomIntake");
          }
          if(autoTimer.get() > 3) {
            shootSub.stopShooter();
            intakeSub.stopIntakes();
            iterations++;
            isFin = true;
            //System.out.println("Ended");
          }
          if(autoTimer.get() > 1){
            
      }
      
    }
  }

    
          


    
  }   
       
       
  
  

  @Override
  public boolean isFinished() {
    return isFin;
  }
}


