package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Controllers extends SubsystemBase {

  int portNum;
  Joystick joystick;

  public Controllers(int portNum) {
    this.portNum = portNum;
    joystick = new Joystick(portNum);
  }

  public void initDefaultCommand() {
  }

  public double getDriverAxis(int axis) {
    return joystick.getRawAxis(axis);
  }

  public double getDifferenceInTriggers() {
    return (getDriverAxis(RobotMap.RIGHT_TRIGGER) - getDriverAxis(RobotMap.LEFT_TRIGGER));
  }

  public int booleanToOneOrZero(boolean trueOrFalse) {
    if (trueOrFalse == true) {
      return 1;
    } else {
      return 0;
    }
  }

  public boolean isButtonPressed(int buttonID) {
    return joystick.getRawButton(buttonID);
  }

  public int getIsButtonPressed (int buttonID){
    return booleanToOneOrZero(isButtonPressed(buttonID));
  }

  public double getDifferenceInBumpers() {
    return (booleanToOneOrZero(isButtonPressed(RobotMap.RIGHT_BUMPER)) - booleanToOneOrZero(isButtonPressed(RobotMap.LEFT_BUMPER)));
  }

  @Override
  public void periodic() {

  }
}
