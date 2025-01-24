package org.firstinspires.ftc.teamcode.mechanisms.drivetrain.commands;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.subsystems.DriveSubsystem;

public class WaitActionCommand extends CommandBase {

    TrajectoryActionBuilder tab;
    int sleep;

    Action sleepAction;
    DriveSubsystem driveSubsystem;

    public WaitActionCommand(int sleep){

        this.sleep = sleep;


        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        sleepAction = new SleepAction(sleep);
        Actions.runBlocking(
                sleepAction
        );
    }

    @Override
    public boolean isFinished(){
        TelemetryPacket packet = new TelemetryPacket();
        return !sleepAction.run(packet);

    }

}
