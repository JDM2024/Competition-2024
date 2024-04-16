package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ManualDrive extends Command{
  static double rightTurnValue;
  static double leftTurnValue;

  public ManualDrive() {
    addRequirements(Robot.drivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    switch (Robot.gui.getDrivingType()) {
      case RobotMap.DOUBLE_TANK_DRIVING:
        controller1DutiesTank();
        break;
      case RobotMap.DOUBLE_VIDEO_GAME_DRIVING:
        controller1DutiesRaceCar();
        break;
      default:
        controller1DutiesRaceCar();
        break;
    }
  }

  @Override
  public void end(boolean interrupted) {
    Robot.drivetrain.arcadeDrive(0,0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  private static double createLeftTurnValue () {
    double turnValue = Robot.controller1.getDriverAxis(RobotMap.LEFT_STICK_X);
    if (turnValue < 0) { return rightTurnValue = -turnValue; } 
    else { return rightTurnValue = 0; }
  } private static double createRightTurnValue() {
    double turnValue = Robot.controller1.getDriverAxis(RobotMap.LEFT_STICK_X);
    if (turnValue >= 0) { return leftTurnValue = turnValue; } 
    else { return leftTurnValue = 0; }
  }

  // private static void controller2Duties() {
  //   // Robot.arm.liftArm(-Robot.controller2.getDriverAxis(RobotMap.RIGHT_STICK_Y) * Robot.gui.getArmPower());
  //   // Robot.shooters.shoot(Robot.controller2.getDifferenceInTriggers() * Robot.gui.getShootingPower());
  // }

  private static void controller1DutiesTank() {
    Robot.drivetrain.arcadeDrive(
    Robot.controller1.getDriverAxis(RobotMap.RIGHT_STICK_Y) * Robot.gui.getDrivingPower(), 
    Robot.controller1.getDriverAxis(RobotMap.LEFT_STICK_Y) * Robot.gui.getDrivingPower());
  }
  private static void controller1DutiesRaceCar() {
    Robot.drivetrain.arcadeDrive(
      (-(Robot.controller1.getDifferenceInTriggers() - createLeftTurnValue()))* Robot.gui.getDrivingPower(), 
      (-(Robot.controller1.getDifferenceInTriggers() - createRightTurnValue())) * Robot.gui.getDrivingPower());
    if (Math.abs(Robot.controller1.getDifferenceInTriggers()) < 0.05 && Math.abs(Robot.controller1.getDriverAxis(RobotMap.LEFT_STICK_X)) > 0.05) {
      Robot.drivetrain.arcadeDrive
        (-Robot.controller1.getDriverAxis(RobotMap.LEFT_STICK_X) * Robot.gui.getDrivingPower()
        ,Robot.controller1.getDriverAxis(RobotMap.LEFT_STICK_X) * Robot.gui.getDrivingPower());
    }
  }
  
  // private static void controller1EverythingElse() {
  //   Robot.arm.liftArm(Robot.controller1.getDifferenceInTriggers() * Robot.gui.getArmPower());
  //   Robot.intake.intake(Robot.controller1.getDifferenceInBumpers() *Robot.gui.getIntakePower());
  //   Robot.shooters.shoot(Robot.controller1.getIsButtonPressed(RobotMap.A_BUTTON));
  // }

}