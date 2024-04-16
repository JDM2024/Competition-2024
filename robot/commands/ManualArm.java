// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ManualArm extends Command {
  /** Creates a new ManualArm. */
  public ManualArm() {
    addRequirements(Robot.arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Robot.arm.getLeftArmPosition() > 0 && Robot.controller2.getDriverAxis(RobotMap.RIGHT_STICK_Y) < 0) {
      Robot.arm.liftArm(0);
    }
    else if (Robot.arm.getLeftArmPosition() < -54 && Robot.controller2.getDriverAxis(RobotMap.RIGHT_STICK_Y) > 0) {
      Robot.arm.liftArm(0);
    }
    else {Robot.arm.liftArm(-Robot.controller2.getDriverAxis(RobotMap.RIGHT_STICK_Y) * Robot.gui.getArmPower());}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.arm.liftArm(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
