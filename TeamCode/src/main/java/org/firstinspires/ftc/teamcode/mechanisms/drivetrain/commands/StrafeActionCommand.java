package org.firstinspires.ftc.teamcode.mechanisms.drivetrain.commands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.subsystems.DriveSubsystem;

public class StrafeActionCommand extends CommandBase {
    Action action;
    DriveSubsystem driveSubsystem;

    Pose2d initialPos = new Pose2d(0,0,Math.toRadians(0));

    Vector2d strafePosition;

    Telemetry telemetry;

    Action action1;



    public StrafeActionCommand(DriveSubsystem driveSubsystem, Pose2d initPos, Vector2d sPos, Telemetry t){

        this.driveSubsystem = driveSubsystem;

        initialPos = initPos;

        strafePosition = sPos;

        telemetry = t;

        TrajectoryActionBuilder tab1 = this.driveSubsystem.getRRDrive().actionBuilder(initialPos)
                .strafeTo(strafePosition);
        action1  = tab1.build();



        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        Actions.runBlocking(
                action1
        );
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
