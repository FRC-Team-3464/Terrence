package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

//Defining motors and other devices on robot
public class RobotMap {

  public static int 
  leftStickPort = 0, 
  rightStickPort = 1,
  auxPort = 2, 
  leftBackInt = 6, 
  leftFrontInt = 5, 
  rightBackInt = 8, 
  rightFrontInt = 7, 
  shooterInt = 10, 
   
  intake1Int = 0, 
  intake2Int = 1; 

  //define motors that use SparkMax motor controller (brushless motor) over CAN bus
  public static CANSparkMax
    shooter = new CANSparkMax(shooterInt, CANSparkMaxLowLevel.MotorType.kBrushless),
    leftFront = new CANSparkMax(leftFrontInt, CANSparkMaxLowLevel.MotorType.kBrushless), 
    leftBack = new CANSparkMax(leftBackInt, CANSparkMaxLowLevel.MotorType.kBrushless), 
    rightFront = new CANSparkMax(rightFrontInt, CANSparkMaxLowLevel.MotorType.kBrushless), 
    rightBack = new CANSparkMax( rightBackInt, CANSparkMaxLowLevel.MotorType.kBrushless);

  //way to use SparkMax over PWM, DO NOT RECOMMEND
  //public static PWMSparkMax
    //s_shooter = new PWMSparkMax(3);

  //create differential drive (used for arcade and tank drive)
  public static DifferentialDrive drive = new DifferentialDrive(leftFront, rightFront);

  //Define motors for motors using Spark controllers (CIMs in this case)
  public static Spark 
  intakeBottom= new Spark(0), 
  intakeTop = new Spark(1), //    Call Sparks
  elevatorMotor = new Spark(2);

  //Define encoders for all 4 drive train motors 
  public static RelativeEncoder 
  leftFrontEncoder = leftFront.getEncoder(), 
  leftBackEncoder = leftBack.getEncoder(), 
  rightFrontEncoder = rightFront.getEncoder(), 
  rightBackEncoder = rightBack.getEncoder();

  //No idea why this is here, its not needed and these are already declared in OI
  public static Joystick 
  leftStick = new Joystick(RobotMap.leftStickPort),
  rightStick = new Joystick(RobotMap.rightStickPort),
  auxStick = new Joystick(RobotMap.auxPort),
  controller = new Joystick(3);

  //make pressure sensor(will work for any device using analog ports on RoboRIO)
  public static AnalogInput pressureSensor = new AnalogInput(0);

  //defines limit switches
  public static DigitalInput bottomLimitSwitch = new DigitalInput(0);
  public static DigitalInput topLimitSwitch = new DigitalInput(1);

  //defines Gryo 
  public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
}
