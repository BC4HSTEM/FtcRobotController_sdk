package org.firstinspires.ftc.teamcode.mechanisms.drivetrain.commands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

public class LineToXActionCommand extends CommandBase {
    Action action;
    DriveSubsystem driveSubsystem;

    Pose2d initialPos = new Pose2d(0,0,Math.toRadians(0));

    Double xPosition = 0.0;

    Telemetry telemetry;

    Action action1;



    public LineToXActionCommand(DriveSubsystem driveSubsystem, Pose2d initPos, Double xPos, Telemetry t){

        this.driveSubsystem = driveSubsystem;
        
        initialPos = initPos;

        xPosition = xPos;

        telemetry = t;

        TrajectoryActionBuilder tab1 = this.driveSubsystem.getRRDrive().actionBuilder(initialPos)
                .lineToX(xPosition);
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
