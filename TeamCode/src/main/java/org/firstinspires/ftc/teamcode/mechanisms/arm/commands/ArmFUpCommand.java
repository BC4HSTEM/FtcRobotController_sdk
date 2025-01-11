package org.firstinspires.ftc.teamcode.mechanisms.arm.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.arm.subsystems.ArmSubsystem;

import java.util.function.DoubleSupplier;

public class ArmFUpCommand extends CommandBase {
    private ArmSubsystem armSubsystem;

    Telemetry telemetry;

    public ArmFUpCommand(ArmSubsystem armSubsystem, Telemetry t){
        this.armSubsystem = armSubsystem;

        this.telemetry = t;


        addRequirements(armSubsystem);
    }

    @Override
    public void initialize(){

        armSubsystem.setFUp();

    }

    public boolean isFinished(){
        return true;
    }
}
