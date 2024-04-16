// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;

public class AimDrivetrainAtGoal extends Command {
  /** Creates a new AimDrivetrainAtGoal. */
  double speed;
  long startTime;
  long lockOnTime;

  public AimDrivetrainAtGoal() {
    addRequirements(Robot.drivetrain);
    startTime = System.currentTimeMillis();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.drivetrain.setDriveBrake();
    startTime = System.currentTimeMillis();
    lockOnTime = 0;
    if (!Robot.limeLight.doWeSeeTag()) {
      end(true);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double x = Robot.limeLight.getXValue();
    if (Math.abs(x) > 2)
    {
      if (Math.abs(x) > 10){
        speed = x * 0.005;
      }
      else if (Math.abs(x) < 10) {
        speed = x * 0.01;
      }
    }
    if (x<3 && x>-3) {
      lockOnTime += 1;
    }
    Robot.drivetrain.arcadeDrive(-speed, speed);
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.drivetrain.setDriveCoast();
    Robot.drivetrain.arcadeDrive(0, 0);
    // // getArmPositionToShoot(Robot.limeLight.estimateDistanceToGoal());
    // ArmToPosition aimAndShoot = new ArmToPosition(30);
    // aimAndShoot.schedule();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return lockOnTime >= 20 || (System.currentTimeMillis() - startTime > 3000);
  }
}
