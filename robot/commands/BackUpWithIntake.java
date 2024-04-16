// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;

public class BackUpWithIntake extends Command {
  /** Creates a new DropArm. */
  double goalPosition;
  double startingSpot;

  public BackUpWithIntake(double distanceInInches) {
    addRequirements(Robot.drivetrain);
    goalPosition = distanceInInches;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startingSpot = Robot.drivetrain.getFrontLeftPosition();
    Robot.drivetrain.setDriveBrake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //if I surround the code below in a while loop that contains the condition that I would use for isFinished() it works, 
    //but without the whiles it does not work and only runs one iteration of execute()
    Robot.intake.intake(.4);
    Robot.drivetrain.arcadeDrive(-.1, -.1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.drivetrain.setDriveCoast();
    Robot.drivetrain.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs(Robot.drivetrain.getFrontLeftPosition()-startingSpot) > goalPosition);
  }
}

