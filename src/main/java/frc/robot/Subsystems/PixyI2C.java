/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

/**
 *
 * Same thing as the other one, should be deleted
 */
public class PixyI2C {

  /**
   * Add your docs here.
   */

  public I2C Wire;
  public String retString;

  public PixyI2C() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    // Open a new I2C connection on port 4
    Wire = new I2C(Port.kOnboard, 0x77);
  }

  public void PollPixy() {
    // The string message to be sent to the Arduino
    String WriteString = "go";
    //String setStr = "     ";
    // Turn the string into a character array
    char[] CharArray = WriteString.toCharArray();
    // Make each character a single byte
    byte[] WriteData = new byte[CharArray.length];
    //Read in buffer
    // Make each character a single byte
    byte[] ReadData = new byte[10];
    // For each item in the character array, add it as a single byte to the I2C connection
    for (int i = 0; i < CharArray.length; i++) {
      WriteData[i] = (byte) CharArray[i];
    }

    // Send the data to the I2C connection   ReadData.length
    Wire.transaction(WriteData, WriteData.length, ReadData, 5);
    //Wire.read(0x77, 5, ReadData);
    // For each item in the character array, add it as a single byte to the I2C connection
    CharArray = new char[10];
    if (!(ReadData.length == 0)) {
      for (int i = 0; i < 5; i++) {
        CharArray[i] = (char) ReadData[i];
      }
    }
    //retString = "hello"; //test
    String sTemp = new String(CharArray);

    this.retString = sTemp;
  }

  public String getPixyString() {
    return this.retString;
  }
}
