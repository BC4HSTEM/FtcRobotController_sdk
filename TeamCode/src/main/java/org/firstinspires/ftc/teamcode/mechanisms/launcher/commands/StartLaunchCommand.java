package org.firstinspires.ftc.teamcode.mechanisms.launcher.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.launcher.subsystems.LauncherSubsystem;

import java.util.function.DoubleSupplier;

public class StartLaunchCommand extends CommandBase {

    private LauncherSubsystem launcherSubsystem;
    private DoubleSupplier power;
    private Telemetry telemetry;

    public StartLaunchCommand(LauncherSubsystem launcherSubsystem, DoubleSupplier power){
        this.launcherSubsystem = launcherSubsystem;
        this.power = power;

        addRequirements(launcherSubsystem);
    }

    public StartLaunchCommand(LauncherSubsystem launcherSubsystem, DoubleSupplier power, Telemetry telemetry){
        this.launcherSubsystem = launcherSubsystem;
        this.power = power;
        this.telemetry = telemetry;

        addRequirements(launcherSubsystem);
    }

    @Override
    public void execute(){

        telemetry.addLine("Launcher Executing");
        telemetry.update();

        launcherSubsystem.turn(-power.getAsDouble());

        telemetry.addData("Motor Power", launcherSubsystem.getPower());
        telemetry.update();
    }

    @Override
    public void end(boolean interupt){

        launcherSubsystem.stop();

    }
}
