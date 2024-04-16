package frc.robot;

import frc.robot.commands.AimDrivetrainAtGoal;
import frc.robot.commands.ArmToAmp;
import frc.robot.commands.ArmToFling;
import frc.robot.commands.ArmToPosition;
import frc.robot.commands.ArmToSpeaker;
import frc.robot.commands.BackUpALittle;
import frc.robot.commands.BackUpWithIntake;
import frc.robot.commands.FloorArm;
import frc.robot.commands.ManualArm;
import frc.robot.commands.ManualShooters;
import frc.robot.commands.ShootAmp;
import frc.robot.commands.ShootSpeaker;
import frc.robot.commands.TurnToAngle;
import frc.robot.commands.ManualDrive;
import frc.robot.commands.ManualIntake;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Controllers;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GUI;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Shooters;
import java.text.DecimalFormat;

public class Robot extends TimedRobot {

  public static final Drivetrain drivetrain = new Drivetrain();
  public static final Arm arm = new Arm();
  public static final Intake intake = new Intake();
  public static final Shooters shooters = new Shooters();
  public static GUI gui = new GUI();
  public static LimeLight limeLight = new LimeLight();
  public static Lights lights = new Lights();
  public static Controllers controller1 = new Controllers(RobotMap.DRIVER_CONTROLLER1);
  public static Controllers controller2 = new Controllers(RobotMap.DRIVER_CONTROLLER2);
  public static final DecimalFormat twoDecimalPlaceFormat = new DecimalFormat("###.##");
  ManualIntake manualIntake = new ManualIntake();
    ManualDrive manualDrive = new ManualDrive();
    SequentialCommandGroup autonGroup = new SequentialCommandGroup(new FloorArm(), new ArmToSpeaker(), new ShootSpeaker(), new FloorArm());

  private static void setLEDColorForTeams() {
    if (DriverStation.getAlliance().equals(Alliance.Blue)) { 
      lights.setLED(RobotMap.BLUE);
    }
    else { lights.setLED(RobotMap.RED); }
  }

  @Override
  public void robotInit() {
    gui.startGui();
    gui.startCamera();
    drivetrain.calibrate();
    drivetrain.setDriveCoast();
    lights.setLED(RobotMap.GREEN);
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("X Value", limeLight.getXValue());
    SmartDashboard.putNumber("Drive Encoder", drivetrain.getFrontLeftPosition());
    SmartDashboard.putNumber("Distance to Goal", limeLight.estimateDistanceToGoal());
    SmartDashboard.putNumber("Left Arm Position", arm.getLeftArmPosition());
    SmartDashboard.putString("Battery Voltage", twoDecimalPlaceFormat.format(drivetrain.getBatteryVoltage()) + " Volts");
    SmartDashboard.putString("Gyroscope Angle", drivetrain.getAngle() + " Degrees");
    SmartDashboard.putBoolean("Valid Target", limeLight.doWeSeeTag());
    CommandScheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    setLEDColorForTeams();
    if (gui.getAutonType() == RobotMap.LEFT_AUTON){
      //left
      SequentialCommandGroup left = new SequentialCommandGroup(new FloorArm(), new ArmToSpeaker(), new ShootSpeaker(), new FloorArm(),
        new BackUpALittle(5.1), new TurnToAngle(63.1), new BackUpWithIntake(26), new TurnToAngle(0),
        new AimDrivetrainAtGoal(), new ArmToFling(), new ShootSpeaker(), new FloorArm());
      left.schedule();
    }
      //right
      if (gui.getAutonType() == RobotMap.RIGHT_AUTON){
        //right
        SequentialCommandGroup right = new SequentialCommandGroup(new FloorArm(), new ArmToSpeaker(), new ShootSpeaker(), new FloorArm(),
          new BackUpALittle(5.2), new TurnToAngle(297.1), new BackUpWithIntake(26), new TurnToAngle(0),
          new AimDrivetrainAtGoal(), new ArmToFling(), new ShootSpeaker(), new FloorArm());
        right.schedule();
      }
    }
      
    // } if (gui.getAutonType() == 2){
    //   //middle
    //   SequentialCommandGroup middle = new SequentialCommandGroup(new FloorArm(), new ArmToSpeaker(), new ShootSpeaker(), new FloorArm(), 
    //   new BackUpWithIntake(66.5), new ArmToPosition(), new ShootSpeaker(), new FloorArm());
    //   middle.schedule();

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    limeLight.turnOnLights();
    CommandScheduler.getInstance().setDefaultCommand(drivetrain, manualDrive);
    CommandScheduler.getInstance().setDefaultCommand(intake, manualIntake);
    manualDrive.schedule();
    manualIntake.schedule();
  }

  @Override
  public void teleopPeriodic() {
    // Amp
    if (Robot.controller2.isButtonPressed(RobotMap.A_BUTTON)) {
      SequentialCommandGroup ampGroup = new SequentialCommandGroup(new ArmToAmp(), new ShootAmp(), new FloorArm());
      ampGroup.schedule();
    }
    if (Robot.controller2.isButtonPressed(RobotMap.B_BUTTON)) {
      SequentialCommandGroup flingGroup = new SequentialCommandGroup(new ArmToFling(), new ShootSpeaker(), new FloorArm());
      flingGroup.schedule();
    }
    // Speaker
    if (Robot.controller2.isButtonPressed(RobotMap.X_BUTTON)) {
      SequentialCommandGroup speakerGroup = new SequentialCommandGroup(new AimDrivetrainAtGoal(), new ArmToPosition(), new ShootSpeaker(), new FloorArm());
      speakerGroup.schedule();
    }
    if (Robot.controller2.isButtonPressed(RobotMap.Y_BUTTON)) {
      SequentialCommandGroup speakerGroup = new SequentialCommandGroup(new ArmToSpeaker(), new ShootSpeaker(), new FloorArm());
      speakerGroup.schedule();
    }
    // Ready to hang
    if (Robot.controller2.isButtonPressed(RobotMap.BACK_BUTTON)) {
      ArmToAmp armToAmp = new ArmToAmp();
      armToAmp.schedule();
    }
    if (Robot.controller2.isButtonPressed(RobotMap.START_BUTTON)) {
      FloorArm floorArm = new FloorArm();
      floorArm.schedule();
    }
    // Hang
    if (controller2.isButtonPressed(RobotMap.BACK_BUTTON) && controller2.isButtonPressed(RobotMap.START_BUTTON)) {
      ManualArm manualArm = new ManualArm();
      ManualShooters manualShooters = new ManualShooters();
      manualArm.schedule();
      manualShooters.schedule();
    }
    CommandScheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    // limeLight.turnOffLights();
    // SequentialCommandGroup group = new SequentialCommandGroup(new AimDrivetrainAtGoal(), new ArmToPosition(-10), new ShootSpeaker(), new FloorArm());
    // group.schedule();
    // AimDrivetrainAtGoal aim = new AimDrivetrainAtGoal();
    // aim.schedule();
    // TurnToAngle turn = new TurnToAngle(180);
    // turn.schedule();
    // ManualArm manArm = new ManualArm();
    // ManualShooters manShooters = new ManualShooters();
    // ManualDrive manDrive = new ManualDrive();
    // ManualIntake manIntake = new ManualIntake();
    // manDrive.schedule();
    // manIntake.schedule();
    // manArm.schedule();
    // manShooters.schedule();
    FloorArm floor = new FloorArm();
    floor.schedule();
  }
  public void testPeriodic() {
    drivetrain.setDriveBrake();
    CommandScheduler.getInstance().run();
  }
}
