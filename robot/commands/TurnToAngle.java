// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;

public class TurnToAngle extends Command {
  /** Creates a new TurnToAngle. */
  double error;
  double lockOnTime;
  double speed;
  double goalAngle;
  double directionToGo;

  public TurnToAngle(double goal) {
    goalAngle = goal;
    addRequirements(Robot.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.drivetrain.setDriveBrake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.intake.intake(0);
    if (Math.abs(getRealError(goalAngle)) > 45) {
      speed = 0.3 * (getRealError(goalAngle)/Math.abs(getRealError(goalAngle)));
    }
    else {
      speed = 0.1 * (getRealError(goalAngle)/Math.abs(getRealError(goalAngle)));
    }
    // Turns the robot to face the desired direction
    // SmartDashboard.putNumber("Error", goalAngle);
    SmartDashboard.putBoolean("Turn Finished", isFinished());
    Robot.drivetrain.arcadeDrive(speed, -speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.drivetrain.arcadeDrive(0, 0);
    Robot.drivetrain.setDriveCoast();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (getRealError(goalAngle) < 2 && getRealError(goalAngle) > -2);
  }

  public double getRealError(double goal){
    double difference = goal - Robot.drivetrain.getAngle();
    if (Math.abs(difference) > 180){
      if (difference < 0) {
        difference = (360-Math.abs(difference));
      }
      else if (difference > 0) {
        difference = -(360-Math.abs(difference));
      }
    }
    return difference;
  }
}
