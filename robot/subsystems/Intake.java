// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */

  private final CANSparkMax intakeMController = new CANSparkMax(RobotMap.INTAKE, CANSparkLowLevel.MotorType.kBrushless);
  private final RelativeEncoder intakeMEncoder = intakeMController.getEncoder();

  public Intake() {
  }

  public double getIntakeVelocity() {
    return intakeMEncoder.getVelocity();
  } public double getIntakePosition() {
    return intakeMEncoder.getPosition();
  } public double getIntakeTemp() {
    return intakeMController.getMotorTemperature();
  } public double getIntakeOutputAmps() {
    return intakeMController.getOutputCurrent();
  } public double getIntakeOutputVolts() {
    return intakeMController.getAppliedOutput();
  }

  // positive sucks a note in
  public void intake(double speed) {
    intakeMController.set(speed);
  }

  @Override
  public void periodic() {
  }
}
