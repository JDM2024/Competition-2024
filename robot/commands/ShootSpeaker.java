// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;

public class ShootSpeaker extends Command {
  /** Creates a new Shoot. */
  long startTime;
  boolean finished;

  public ShootSpeaker() {
    addRequirements(Robot.intake, Robot.shooters);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //this method also no longer works. To get it to work earlier, I replaced the ifs with whiles so even if the execute only runs once
    //then it can still function. However, once I took out the whiles and replaces them with ifs as is standard in commands
    // it no longer works and again runs only one iteration.
    if((System.currentTimeMillis() - startTime < 30)) {
      Robot.intake.intake(-.2);
    }
    else if((System.currentTimeMillis() - startTime) < 630){
      Robot.shooters.shoot(1);
      Robot.intake.intake(0.01);
    }
    else {
      Robot.shooters.shoot(1);
      Robot.intake.intake(1);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.shooters.shoot(0);
    Robot.intake.intake(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ((System.currentTimeMillis() - startTime) > 900);
  }
}
