// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
//import frc.robot.commands.ExampleCommand;
//import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Subsystems.*;
import frc.robot.Autos.*;
import frc.robot.Commands.*;

/*
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // Some are not used in robot code, but defined anyways
  private final DriveSubsystem driveSub = new DriveSubsystem();
  private final EncoderSubsystem encoderSub = new EncoderSubsystem();
  private final ShooterSubsystem shootSub = new ShooterSubsystem();
  private final IntakeSubsystem intakeSub = new IntakeSubsystem();
  private final ElevatorSubsystem elevSub = new ElevatorSubsystem();
  private final AnalogSubsystem analogSub = new AnalogSubsystem();
  private final RampComponent rampSub = new RampComponent(1.33, 2.5);  
  // private final NavXSubsystem navX = new NavXSubsystem();

  private final LEDSubsystem ledSub = new LEDSubsystem();

  private final BalancePIDSubsystem balanceSub = new BalancePIDSubsystem();
  private final BalanceHoldPIDSubsystem balanceHoldSub = new BalanceHoldPIDSubsystem();
  private final BalancePIDSubsystem2Bots balanceTwoBots = new BalancePIDSubsystem2Bots();


  private final Shoot2Balls shoot2Comm = new Shoot2Balls(intakeSub, shootSub, Robot.shootInt);
  private final ShooterRun shootOnly = new ShooterRun(shootSub);
  private final DriveTrain arcadeDrive = new DriveTrain(driveSub, rampSub);
  // private final DriveTank tankDrive = new DriveTank(driveSub);
  private final LimelightPIDSubsystem limePIDSub = new LimelightPIDSubsystem();
  private final LimelightPIDDistanceSubsystem limeDistance = new LimelightPIDDistanceSubsystem(driveSub);
   
  private final IntakeBoth intakeBoth = new IntakeBoth(intakeSub);
  private final IntakeBottom intakeBottom = new IntakeBottom(intakeSub);
  private final IntakeTop intakeTop = new IntakeTop(intakeSub);
  private final ReverseShooterAndIndexer reverse = new ReverseShooterAndIndexer(intakeSub, shootSub);

  private final ElevatorDown elevDown = new ElevatorDown(elevSub);
  private final ElevatorUp elevUp = new ElevatorUp(elevSub);

  // private final LimelightSubsystem limeSub = new LimelightSubsystem();
  
  //private final toggleLimelightCommand turnLimeLiteOn = new toggleLimelightCommand(limeSub, true);
  //private final toggleLimelightCommand turnLimeLiteOff = new toggleLimelightCommand(limeSub, false);


  private final isBallCollected isBall = new isBallCollected(analogSub);
  private final limeAim aim = new limeAim(driveSub, limePIDSub);
  private final limeAimDistance distance = new limeAimDistance(driveSub, limeDistance);
  private final BalanceDistance balance = new BalanceDistance(driveSub, balanceSub);
  private final BalanceHold balanceHolder = new BalanceHold(balanceHoldSub, encoderSub);
  private final Balance2Bots TwoBotBalanceCMD = new Balance2Bots(balanceTwoBots, encoderSub);
  private final GyroSubsystem gyroSub = new GyroSubsystem(driveSub);
  
  
  //define autos
  private final Auto1 auto1 = new Auto1( driveSub, encoderSub, intakeSub, shootSub, limePIDSub, gyroSub);
  private final Auto2 auto2 = new Auto2(driveSub, encoderSub, intakeSub, shootSub, limePIDSub, limeDistance, gyroSub);
  private final Auto3 auto3 = new Auto3(driveSub, encoderSub, intakeBottom, shoot2Comm,gyroSub);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //makes default commands that run all the time
    CommandScheduler.getInstance().setDefaultCommand(driveSub, arcadeDrive);
    CommandScheduler.getInstance().setDefaultCommand(analogSub, isBall);
    
    //commands that are mapped to buttons, to run when button is pressed/held/etc.
    OI.triggerAux.onTrue(shoot2Comm);
        
    OI.button5Aux.whileTrue(intakeBottom);
    OI.button5Aux.onFalse(new InstantCommand(intakeSub::stopIntakes, intakeSub));

    OI.button2Aux.whileTrue(reverse);
    OI.button2Aux.onFalse(new InstantCommand(intakeSub::stopIntakes, intakeSub));
    OI.button2Aux.onFalse(new InstantCommand(shootSub::stopShooter, shootSub));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
    
  }
}
