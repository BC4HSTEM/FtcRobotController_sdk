package org.firstinspires.ftc.teamcode.mechanisms.launch_pad.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.launch_pad.subsystems.LaunchPadSubsystem;
import org.firstinspires.ftc.teamcode.mechanisms.launcher.subsystems.LauncherSubsystem;

public class LaunchPadResetLaunchCommand extends CommandBase{
    private LauncherSubsystem launcherSubsystem;
    private LaunchPadSubsystem launchPadSubsystem;
    private Telemetry telemetry;

    //private double position = 0.5;

    public LaunchPadResetLaunchCommand(LaunchPadSubsystem launchPadSubsystem){
        this.launchPadSubsystem = launchPadSubsystem;
        addRequirements(launchPadSubsystem);
    }

    public LaunchPadResetLaunchCommand(LaunchPadSubsystem launchPadSubsystem, Telemetry telemetry){
        this.launchPadSubsystem = launchPadSubsystem;
        this.telemetry = telemetry;

        addRequirements(launchPadSubsystem);
    }

    @Override
    public void initialize(){
        telemetry.addLine("grabber close initialize");
        telemetry.update();

        //telemetry.addData("grabber position", grabberSubsystem.getPosition());
        //telemetry.addData("grabber close position", grabberSubsystem.getClosePosition());

        // launchPadSubsystem.setLaunchPadLaunchPosition();

        // telemetry.addData("grabber right position", launchPadSubsystem.getLaunchPadRightPosition());
        // telemetry.addData("grabber left position", launchPadSubsystem.getLaunchPadLeftPosition());
        // telemetry.addData("grabber right close position", launchPadSubsystem.getLeftResetPosition());
        // telemetry.addData("grabber left close position", launchPadSubsystem.getLeftResetPosition());
        // telemetry.update();
    }

   /* @Override
    public void execute(){
        telemetry.addData("grabber right position", grabberSubsystem.getGrabberRightPosition());
        telemetry.addData("grabber right close position", grabberSubsystem.getRightClosePosition());
        telemetry.update();
    }*/


    // @Override
    // public boolean isFinished(){
    //     // return launchPadSubsystem.getLaunchPadRightPosition() >= launchPadSubsystem.getRightResetPosition();
    // }

    @Override
    public void end(boolean interrupt){
        // launchPadSubsystem.launchPadResetPosition();
    }



}