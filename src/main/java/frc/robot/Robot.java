// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.Subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  /* Forewarning, A LOT OF SPAGHETTI CODE HERE
  *  This is a mess of actual Robot code and stuff I couldn't get to work through commands
  *  Please bear with me
  */
  


  //string selected auto will be stored in
  public static String m_autoSelected;

  //create a sendable chooser to pick auto With
  public static final SendableChooser<String> m_chooser = new SendableChooser<>();


  //Pixy stuff I forgot what it does
  private Integer iCount = Integer.valueOf(0);
 
  // private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final PixyI2C ballFinder = new PixyI2C();

  //A...limelight subsystem? In Robot?
  private final LimelightSubsystem limeSub = new LimelightSubsystem();
  
  //actual autonomous command picked from sendable chooser stored here
  Command m_autonomousCommand;


  //shoot speed for shooter, from -1 to 1
  // Changing speed to -0.55 -  original as of 10/6/22 was -0.5
  static double shootInt = -.55; // This is the new one
  //variables for storing left/right encoder values
  double leftEncoder = 0;
  double rightEncoder = 0;


  //for debugging, dont know why thats still here
  public static String autoStatus = "IDK LMAO";
  double TEMPVALUE = 0;
  int STOP = 0;
  int iterations = 0;

  //A... driveSubsystem???? OMG what was I doing
  DriveSubsystem driveSub = new DriveSubsystem();


  //Create an OI class
  OI m_OI = new OI();

  //Creates RobotContainer class
  RobotContainer m_robotContainer = new RobotContainer();

  //double for holding voltage readout from pressure sensor
  double analog;

  //bool for detecting if pressure sensor is tripped or not
  boolean bool;

  //Manual limelight stuff that defines limelight values
  //Explanation in LimelightSubsystem
  NetworkTable table;
  NetworkTableEntry tx;
  NetworkTableEntry ty;
  NetworkTableEntry ta;
  double limex;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    //adding options to choose from the sendable chooser
    m_chooser.setDefaultOption("Default Auto1", "Auto1");
    m_chooser.addOption("Auto 1", "Auto1");
    m_chooser.addOption("Auto 2", "Auto2");
    m_chooser.addOption("Auto 3", "Auto3");
    //Actually sending chooser to Shuffle Board so drivers can see it
    SmartDashboard.putData("Autos", m_chooser);

    //Starts USB vision camera
    CameraServer.startAutomaticCapture(0);
    

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    //Runs command scheduler
    CommandScheduler.getInstance().run();
    
    //defines limelight value table from network table
    table = NetworkTableInstance.getDefault().getTable("limelight");
    
    //defines variables by recieving values from limelight network table
    tx = this.table.getEntry("tx");
    
    ty = this.table.getEntry("ty");
    
    ta = this.table.getEntry("ta");

    //Conversion factor for encoder -  ticks to inches -  never used and dont know if its even accurate
    // double ENCODER_CONVERSION = (6*Math.PI)/(42*12.75);
    
    /*if(OI.rightStick.getRawButton(3)){
     RobotMap.leftFrontEncoder.setPosition(0);
     RobotMap.leftBackEncoder.setPosition(0);
     RobotMap.rightFrontEncoder.setPosition(0);
     RobotMap.rightBackEncoder.setPosition(0);
    }*/

    
    //Code to recieve I2C communication and send it to ShuffleBoard (should be a command)
    this.iCount = Integer.valueOf(this.iCount.intValue() + 1);
    this.iCount = Integer.valueOf(this.iCount.intValue() % 100);
    if (this.iCount.intValue() == 0) {
      this.ballFinder.PollPixy();
      SmartDashboard.putString("Pixy str", this.ballFinder.getPixyString());
    } 

    //Code to tell if Analog Sensor is tripped (also should be a command)
    analog = RobotMap.pressureSensor.getVoltage();
    if(analog < 2){
      bool = true;
    }
    else{
      bool = false;
    }
    SmartDashboard.putBoolean("Ball???", bool);


    //sending limelight values to ShuffleBoard
    limex = this.tx.getDouble(0.0D);
    double limey = this.ty.getDouble(0.0D);
    double limeArea = this.ta.getDouble(0.0D);
    SmartDashboard.putNumber("LimelightX", limex);
    SmartDashboard.putNumber("LimelightY", limey);
    SmartDashboard.putNumber("LimelightArea", limeArea);


    //sending values to ShuffleBoard (should be a command but couldnt get it to work)
    SmartDashboard.putString("auto status", autoStatus);
    SmartDashboard.putNumber("ShootInt", shootInt);
    SmartDashboard.putNumber("Distance", limeSub.getDistanceInches()); //83.2

    //Should not be directly accessing gyro from robotmap, should use A COMMAND with gyro subsystem
    SmartDashboard.putBoolean("Connected", RobotMap.gyro.isConnected()); 
    SmartDashboard.putNumber("Gryo value", RobotMap.gyro.getAngle());
    //SmartDashboard.putBoolean("POVDown", OI.povButtonDown.get());
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    //get selected auto from shuffleBoard
    m_autoSelected = m_chooser.getSelected();
    System.out.println("Auto selected: " + m_autoSelected);

    //idk what this is for and im too afraid to remove it
    iterations = 0;

    //get chosen auto command
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    //enable motors (brake mode)
    driveSub.enableMotors(true);

    //schedule the autonomous command 
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }
  

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    //because we used a command, nothing necessary in here
  }


  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    //cancels auto command if still running
    if (this.m_autonomousCommand != null)
      this.m_autonomousCommand.cancel(); 

    //enable motors (if not already)
    driveSub.enableMotors(true);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    //if statements that run if a button is pressed
    //should be tuned into commands that are used in robot container, but NOOOOOOOOOO, that would be TOO MUCH WORK

    // //turns flywheel speed down to 50%
    // if(OI.button9Aux.get()){ //7
    //   //ShooterIndexer.reverse();
    //   //RobotMap.shooter.set(0.5);
    //   shootInt = -.30;
    // }

    // //turns flywheel speed up to 55%
    // else if(OI.button10Aux.get()){ //8
    //   //ShooterIndexer.reverse();
    //   //RobotMap.shooter.set(0.5);
    //   shootInt= -.50;
    // }

    // //turns limelight on
    // OI.button12Aux.onTrue(NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setDouble(3.0D)); 
      //RobotMap.shootingSolenoid1.set(true);
    // }
    // //turns limelight off (these may be backwards but you get it)
    // else if (OI.button11Aux.get()){ //6
    //   NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setDouble(1.0D); 
    //   //RobotMap.shootingSolenoid1.set(false);
    // }
    
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {

    //disable motors (coast mode)
    //allows for easy moving of robot by hand
    driveSub.enableMotors(false);
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

 /* private void enableMotors(boolean on){
    IdleMode mode;
    if (on) {
      mode = IdleMode.kBrake;
    }
    else{
      mode = IdleMode.kCoast;
    } 
    //set motors to mode
    leftFront.setIdleMode(mode);
    leftBack.setIdleMode(mode);
    rightFront.setIdleMode(mode);
    rightBack.setIdleMode(mode);
  }*/

  
}
