package frc.robot.Subsystems;

import com.fasterxml.jackson.annotation.JsonCreator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleUnaryOperator;

/** A component for limiting the rate of change of a value.
 * In this case, used to limit acceleration and decelaration robot so it doesn't fall over
 */
public class RampComponent extends SubsystemBase implements DoubleUnaryOperator, Cloneable {

  /** The maximum allowed change in the value per second. */
  private final double maxIncreasePerMillis, maxDecreasePerMillis;

  /** The value most recently returned. */
  private double lastValue;

  /** The time, in milliseconds, that the value most recently returned was returned at. */
  private double lastTime;

  /**
   * Default constructor.
   *
   * @param maxIncreasePerSecond The maximum allowed increase in the value per second.
   * @param maxDecreasePerSecond The maximum allowed decrease in the value per second. Should be
   *     positive. Defaults to maxIncreasePerSecond.
   */
  @JsonCreator
  public RampComponent(double maxIncreasePerSecond, double maxDecreasePerSecond ) {
    this.maxIncreasePerMillis = maxIncreasePerSecond; /// 1000.0;
    this.maxDecreasePerMillis = /*maxDecreasePerSecond != null ?*/ maxDecreasePerSecond; /// 1000.0 /*: maxIncreasePerMillis*/;
    
  }

  /**
   * Ramp the given value.
   *
   * @param value The current value of whatever it is you're ramping
   * @return The ramped version of that value.
   */
  @Override
  public double applyAsDouble(double value) {
    if (value > lastValue) {
      lastValue =
          Math.min(
              value, lastValue + (Timer.getFPGATimestamp() - lastTime) * maxIncreasePerMillis);
        //System.out.println("Accelerating");
    } else {
      lastValue =
          Math.max(
              value, lastValue - (Timer.getFPGATimestamp() - lastTime) * maxDecreasePerMillis);
        //System.out.println("Decelerating");
    }
    lastTime = Timer.getFPGATimestamp();
    //System.out.println(lastValue);
    return lastValue;
  }

  /**
   * Get an a copy of this object.
   *
   * @return a new {@link RampComponent} with the same max change per second
   */
  //@Override
  //public RampComponent clone() {
    //return new RampComponent(maxIncreasePerMillis * 1000., maxDecreasePerMillis * 1000.);
  //}
}
