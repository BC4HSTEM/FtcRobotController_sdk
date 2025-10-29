package org.firstinspires.ftc.teamcode.mechanisms.launcher.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.launcher.subsystems.LauncherSubsystem;

import java.util.function.DoubleSupplier;

public class LaunchCommand extends CommandBase {

    private LauncherSubsystem launcherSubsystem;
    private DoubleSupplier power;
    private Telemetry telemetry;

    public LaunchCommand(LauncherSubsystem launcherSubsystem){
        this.launcherSubsystem = launcherSubsystem;
        //this.power = power;

        addRequirements(launcherSubsystem);
    }

    public LaunchCommand(LauncherSubsystem launcherSubsystem, Telemetry telemetry){
        this.launcherSubsystem = launcherSubsystem;
        //this.power = power;
        this.telemetry = telemetry;

        addRequirements(launcherSubsystem);
    }

    @Override
    public void initialize(){

        telemetry.addLine("LiftDown Executing");
        telemetry.update();
        launcherSubsystem.setPower(100);
        telemetry.addData("Motor Power", launcherSubsystem.getPower());
        //telemetry.addData("Motor Min Level in Set Lift Left Cmd", elevatorSubsystem.getMinTargetPosition());
        telemetry.addData("Motor Current Level in Set Lift Left Cmd", launcherSubsystem.getCurrentPositionLauncher());

        telemetry.update();
    }

    @Override
    public void end(boolean interupt){

        launcherSubsystem.stop();

    }
}
