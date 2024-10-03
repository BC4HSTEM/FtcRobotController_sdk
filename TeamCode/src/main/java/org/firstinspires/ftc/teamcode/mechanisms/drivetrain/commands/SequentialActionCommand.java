package org.firstinspires.ftc.teamcode.mechanisms.drivetrain.commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TimeTurn;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.subsystems.DriveSubsystem;

public class SequentialActionCommand extends CommandBase {

    TrajectoryActionBuilder tab;
    Action action;
    DriveSubsystem driveSubsystem;

    public SequentialActionCommand(Action action){

        this.action = action;


        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        Actions.runBlocking(
                new SequentialAction(
                        action
                )
        );
    }

}
