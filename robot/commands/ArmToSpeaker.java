// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ArmToSpeaker extends Command {
  /** Creates a new DropArm. */
  double goalPosition;
  double armPosition;
  double directionToGo;
  boolean finished;
  public ArmToSpeaker() {
    addRequirements(Robot.arm);
    goalPosition = -44;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    armPosition = Robot.arm.getLeftArmPosition();
    finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //if I surround the code below in a while loop that contains the condition that I would use for isFinished() it works, 
    //but without the whiles it does not work and only runs one iteration of execute();
    if (armPosition - goalPosition <0 ) {
      directionToGo = 1;
    }
    else {
      directionToGo = -1;
    }
    Robot.arm.liftArm(.2 * directionToGo);
    isFinished();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.arm.liftArm(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(Robot.arm.getLeftArmPosition() - goalPosition) <= RobotMap.ARM_TOLERANCE;
  }
}

