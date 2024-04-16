// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Arm extends SubsystemBase {
  private static final CANSparkMax rightArmMController = new CANSparkMax(RobotMap.RIGHT_ARM, CANSparkLowLevel.MotorType.kBrushless);
  private static final CANSparkMax leftArmMController = new CANSparkMax(RobotMap.LEFT_ARM, CANSparkLowLevel.MotorType.kBrushless);
  private static final RelativeEncoder rightArmMEncoder = rightArmMController.getEncoder();
  private static final RelativeEncoder leftArmMEncoder = leftArmMController.getEncoder();

  public double getRightArmVelocity() {
    return rightArmMEncoder.getVelocity();
  } public double getRightArmPosition() {
    return rightArmMEncoder.getPosition();
  } public double getRightArmTemp() {
    return rightArmMController.getMotorTemperature();
  } public double getRightArmOutputAmps() {
    return rightArmMController.getOutputCurrent();
  } public double getRightArmOutputVolts() {
    return rightArmMController.getAppliedOutput();
  }

  public double getLeftArmVelocity() {
    return leftArmMEncoder.getVelocity();
  } public double getLeftArmPosition() {
    return leftArmMEncoder.getPosition();
  } public double getLeftArmTemp() {
    return leftArmMController.getMotorTemperature();
  } public double getLeftArmOutputAmps() {
    return leftArmMController.getOutputCurrent();
  } public double getLeftArmOutputVolts() {
    return leftArmMController.getAppliedOutput();
  }

  //If speed is positive, the arm will go up and if the speed is negative then arm goes down
  public void liftArm(double speed) {
    rightArmMController.set(-speed);
    leftArmMController.set(speed);
  }

  /** Creates a new Arm. */
  public Arm() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
