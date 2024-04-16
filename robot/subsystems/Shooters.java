// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Shooters extends SubsystemBase {
  /** Creates a new Shooters. */
  private static final CANSparkMax rightShooterMController = new CANSparkMax(RobotMap.RIGHT_SHOOTER, CANSparkLowLevel.MotorType.kBrushless);
  private static final CANSparkMax leftShooterMController = new CANSparkMax(RobotMap.LEFT_SHOOTER, CANSparkLowLevel.MotorType.kBrushless);
  private static final RelativeEncoder rightShooterMEncoder = rightShooterMController.getEncoder();
  private static final RelativeEncoder leftShooterMEncoder = leftShooterMController.getEncoder();

  public Shooters() {
  }

  public double getRightShooterVelocity() {
    return rightShooterMEncoder.getVelocity();
  } public double getRightShooterPosition() {
    return rightShooterMEncoder.getPosition();
  } public double getRightShooterTemp() {
    return rightShooterMController.getMotorTemperature();
  } public double getRightShooterOutputAmps() {
    return rightShooterMController.getOutputCurrent();
  } public double getRightShooterOutputVolts() {
    return rightShooterMController.getAppliedOutput();
  }

  public double getLeftShooterVelocity() {
    return leftShooterMEncoder.getVelocity();
  } public double getLeftShooterPosition() {
    return leftShooterMEncoder.getPosition();
  } public double getLeftShooterTemp() {
    return leftShooterMController.getMotorTemperature();
  } public double getLeftShooterOutputAmps() {
    return leftShooterMController.getOutputCurrent();
  } public double getLeftShooterOutputVolts() {
    return leftShooterMController.getAppliedOutput();
  }

  // positive spits note out
  public void shoot(double speed) {
    rightShooterMController.set(speed);
    leftShooterMController.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
