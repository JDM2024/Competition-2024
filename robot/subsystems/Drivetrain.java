package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Drivetrain extends SubsystemBase {

  private static final ADIS16470_IMU gyroscope = new ADIS16470_IMU();

  private static final CANSparkMax frontRightMController = new CANSparkMax(RobotMap.RIGHT_FRONT_DRIVE, CANSparkLowLevel.MotorType.kBrushless);
  private static final CANSparkMax frontLeftMController = new CANSparkMax(RobotMap.LEFT_FRONT_DRIVE, CANSparkLowLevel.MotorType.kBrushless);
  private static final CANSparkMax rearRightMController = new CANSparkMax(RobotMap.RIGHT_REAR_DRIVE, CANSparkLowLevel.MotorType.kBrushless);
  private static final CANSparkMax rearLeftMController = new CANSparkMax(RobotMap.LEFT_REAR_DRIVE, CANSparkLowLevel.MotorType.kBrushless);
  
  private static final RelativeEncoder frontRightMEncoder = frontRightMController.getEncoder();
  private static final RelativeEncoder frontLeftMEncoder = frontLeftMController.getEncoder();
  private static final RelativeEncoder rearRightMEncoder = rearRightMController.getEncoder();
  private static final RelativeEncoder rearLeftMEncoder = rearLeftMController.getEncoder();

  public Drivetrain(){
  }


  public void arcadeDrive(double leftSpeed, double rightSpeed) {
    frontLeftMController.set(-leftSpeed);
    rearLeftMController.set(-leftSpeed);
    frontRightMController.set(rightSpeed);
    rearRightMController.set(rightSpeed);
  }

  public double getRate() {
    return Math.round(gyroscope.getRate());
  } 
  public double getAngle() {
    double realAngle = Math.round(gyroscope.getAngle()) - (360 * Math.floor(Math.round(gyroscope.getAngle()) / 360));
    if (realAngle < 0) { return (realAngle + 360); } 
    else { return realAngle; }
  }
  public void calibrate() {
    gyroscope.calibrate();
  } 
  public void gyroReset() {
    gyroscope.reset();
  }

  public double getBatteryVoltage() {
    double batteryVoltage = RobotController.getBatteryVoltage();
    return batteryVoltage;
  } public boolean doWeHavePower() {
    if (getBatteryVoltage() <= 10) { return false; } 
    else { return true; }
  }

  @Override
  public void periodic() {
  }






          public double getFrontRightVelocity() {
          return frontRightMEncoder.getVelocity();
        } public double getFrontRightPosition() {
          return frontRightMEncoder.getPosition();
        } public double getFrontRightTemp() {
          return frontRightMController.getMotorTemperature();
        } public double getFrontRightOutputAmps() {
          return frontRightMController.getOutputCurrent();
        } public double getFrontRightOutputVolts() {
          return frontRightMController.getAppliedOutput();
        }

        public double getFrontLeftVelocity() {
          return frontLeftMEncoder.getVelocity();
        } public double getFrontLeftPosition() {
          return frontLeftMEncoder.getPosition();
        } public double getFrontLeftTemp() {
          return frontLeftMController.getMotorTemperature();
        } public double getFrontLeftOutputAmps() {
          return frontLeftMController.getOutputCurrent();
        } public double getFrontLeftOutputVolts() {
          return frontLeftMController.getAppliedOutput();
        }

        public double getRearRightVelocity() {
          return rearRightMEncoder.getVelocity();
        } public double getRearRightPosition() {
          return rearRightMEncoder.getPosition();
        } public double getRearRightTemp() {
          return rearRightMController.getMotorTemperature();
        } public double getRearRightOutputAmps() {
          return rearRightMController.getOutputCurrent();
        } public double getRearRightOutputVolts() {
          return rearRightMController.getAppliedOutput();
        }

        public double getRearLeftVelocity() {
          return rearLeftMEncoder.getVelocity();
        } public double getRearLeftPosition() {
          return rearLeftMEncoder.getPosition();
        } public double getRearLeftTemp() {
          return rearLeftMController.getMotorTemperature();
        } public double getRearLeftOutputAmps() {
          return rearLeftMController.getOutputCurrent();
        } public double getRearLeftOutputVolts() {
          return rearLeftMController.getAppliedOutput();
        }

        public void setDriveBrake() {
          rearLeftMController.setIdleMode(IdleMode.kBrake);
          rearRightMController.setIdleMode(IdleMode.kBrake);
          frontLeftMController.setIdleMode(IdleMode.kBrake);
          frontRightMController.setIdleMode(IdleMode.kBrake);
        }
        public void setDriveCoast() {
          rearLeftMController.setIdleMode(IdleMode.kCoast);
          rearRightMController.setIdleMode(IdleMode.kCoast);
          frontLeftMController.setIdleMode(IdleMode.kCoast);
          frontRightMController.setIdleMode(IdleMode.kCoast);
        }

}
