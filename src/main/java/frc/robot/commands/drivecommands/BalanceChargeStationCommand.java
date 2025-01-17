// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivecommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.staticsubsystems.RobotGyro;

/**
 * The command to drive the robot manually with joysticks.
 */
public class BalanceChargeStationCommand extends CommandBase {

    private final DriveTrainSubsystem driveTrain;

    private final double kP = 1d/120d;
    private final double MAX_SPEED = 0.35; // not nice

    public BalanceChargeStationCommand(DriveTrainSubsystem driveTrain) {
        this.driveTrain = driveTrain;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(driveTrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double pitch = RobotGyro.getGyroAngleDegreesPitch();
        double speed = pitch * kP; // tune later
        if (Math.abs(pitch) < 3) {
            speed = 0;
        }
        driveTrain.tankDrive(speed < MAX_SPEED ? speed : MAX_SPEED, 0);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
