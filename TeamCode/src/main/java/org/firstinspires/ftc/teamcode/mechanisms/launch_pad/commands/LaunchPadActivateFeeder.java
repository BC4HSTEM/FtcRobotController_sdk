package org.firstinspires.ftc.teamcode.mechanisms.launch_pad.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.launch_pad.subsystems.LaunchPadSubsystem;

public class LaunchPadActivateFeeder extends CommandBase{
    private LaunchPadSubsystem launchPadSubsystem;
    private Telemetry telemetry;

    //private double position = 0.5;

    public LaunchPadActivateFeeder(LaunchPadSubsystem launchPadSubsystem){
        this.launchPadSubsystem = launchPadSubsystem;
        addRequirements(launchPadSubsystem);
    }

    public LaunchPadActivateFeeder(LaunchPadSubsystem launchPadSubsystem, Telemetry telemetry){
        this.launchPadSubsystem = launchPadSubsystem;
        this.telemetry = telemetry;

        addRequirements(launchPadSubsystem);
    }

    @Override
    public void initialize(){
        telemetry.addLine("Launchpad Feeder initialize");
        telemetry.update();


        //telemetry.addData("grabber position", launchPadSubsystem.getPosition());
        //telemetry.addData("grabber close position", launchPadSubsystem.getClosePosition());

        //launchPadSubsystem.closeGrabberPosition();

        // telemetry.addData("grabber right position", launchPadSubsystem.getGrabberRightPosition());
        // telemetry.addData("grabber left position", launchPadSubsystem.getGrabberLeftPosition());
        // telemetry.addData("grabber right close position", launchPadSubsystem.getRightClosePosition());
        // telemetry.addData("grabber left close position", launchPadSubsystem.getLeftClosePosition());
        // telemetry.update();
    }

    // @Override
    // public void execute(){
    //     telemetry.addData("grabber right position", launchPadSubsystem.getGrabberRightPosition());
    //     telemetry.addData("grabber right close position", launchPadSubsystem.getRightClosePosition());
    //     telemetry.update();
    // }


    // @Override
    // public boolean isFinished(){
    //     return 'finished';
    // }
//
}