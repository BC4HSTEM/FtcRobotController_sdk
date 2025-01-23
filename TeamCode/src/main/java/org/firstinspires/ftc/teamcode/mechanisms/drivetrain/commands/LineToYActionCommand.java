package org.firstinspires.ftc.teamcode.mechanisms.drivetrain.commands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.subsystems.DriveSubsystem;

public class LineToYActionCommand extends CommandBase {
    Action action;
    DriveSubsystem driveSubsystem;

    Pose2d initialPos = new Pose2d(0,0,Math.toRadians(0));

    Double yPosition = 0.0;

    Telemetry telemetry;

    Action action1;



    public LineToYActionCommand(DriveSubsystem driveSubsystem, Pose2d initPos, Double yPos, Telemetry t){

        this.driveSubsystem = driveSubsystem;

        initialPos = initPos;

        yPosition = yPos;

        telemetry = t;

        TrajectoryActionBuilder tab1 = this.driveSubsystem.getRRDrive().actionBuilder(initialPos)
                .lineToY(yPosition);
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
