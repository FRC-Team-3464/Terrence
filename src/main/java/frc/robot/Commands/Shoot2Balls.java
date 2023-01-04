package frc.robot.Commands;

//package frc.robot.Autos;
import frc.robot.Subsystems.ShooterSubsystem;
import frc.robot.Subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
   * Shoots two balls at a time, cycles intakes to stagger balls
   *
   * @param IntakeSubsystem, @param ShooterSubsystem The subsystems used by this command.
   */
   
public class Shoot2Balls extends CommandBase {
  private final ShooterSubsystem shootSub;
  private final IntakeSubsystem intakeSub;
 
  double iterations;
  double speedInt;
  Timer startTime;

  boolean isFin;
  
  public Shoot2Balls(IntakeSubsystem intake, ShooterSubsystem shoot, double speed){
    shootSub = shoot;
    intakeSub = intake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shootSub);
    addRequirements(intakeSub);
    
    speedInt = speed;
    isFin = false;
  }

  @Override
  public void initialize() {
    //startTime = Timer.getFPGATimestamp();
    startTime = new Timer();
    startTime.reset();
    startTime.start();
    isFin = false;
  }

  @Override
  public void execute(){
        /*
          When method is called, then we set shooter speed to be 75 percent. 
          Then after 0.5 seconds, the top intake turns on. 
          Then after 1 second, the lower intake turns on. 
          Finally after 2 seconds, all motors shot down. 
        */
    shootSub.shoot(speedInt);
    //System.out.println("Shooting");
    //Timer.delay(seconds);
    if(startTime.get() > .65) {
        intakeSub.intakeTop(-.8);
    }
        //System.out.println("TopIntake");
    if(startTime.get() > .8) {
        intakeSub.intakeBottom(-.65);
        //System.out.println("BottomIntake");
    }
    if(startTime.get() > 2) {
        shootSub.stopShooter();
        intakeSub.stopIntakes();
        isFin = true;
        //System.out.println("Ended");
    }
        //System.out.println("Shooting");
        
  }
  @Override
  public boolean isFinished(){
    return isFin;
  }
}
