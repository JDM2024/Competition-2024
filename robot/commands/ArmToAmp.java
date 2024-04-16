package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ArmToAmp extends Command {
  /** Creates a new ArmToAmp. */
  double armPosition;
  double directionToGo;

  public ArmToAmp() {
    addRequirements(Robot.arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    armPosition = Robot.arm.getLeftArmPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (armPosition < 0 ) {
      directionToGo = 1;
    }
    else {
      directionToGo = -1;
    }
    Robot.arm.liftArm(0.6 * directionToGo);
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
    return Math.abs(Robot.arm.getLeftArmPosition()) <= RobotMap.ARM_TOLERANCE;
  }
}
