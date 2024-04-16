// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;

public class FloorArm extends Command {
  /** Creates a new DropArm. */
  boolean finished;
  public FloorArm() {
    addRequirements(Robot.arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    finished = false;
    //again this command is also having problems and will only run once. I got it to work with whiles but it no longer works.
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.arm.liftArm(-.6);
    isFinished();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.drivetrain.setDriveCoast();
    Robot.arm.liftArm(0);
  }

  // Returns true when the command should end.

  @Override
  public boolean isFinished() {
    return (Robot.arm.getLeftArmPosition() < -54);
  }
}
