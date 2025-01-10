package org.firstinspires.ftc.teamcode.mechanisms.arm.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.arm.subsystems.ArmSubsystem;

import java.util.function.DoubleSupplier;

public class ArmDCommand extends CommandBase {
    private ArmSubsystem armSubsystem;

    private DoubleSupplier dYValue;

    Telemetry t;

    public ArmDCommand(ArmSubsystem armSubsystem, DoubleSupplier dY, Telemetry t){
        this.armSubsystem = armSubsystem;
        dYValue = dY;


        this.t = t;


        addRequirements(armSubsystem);
    }

    @Override
    public void execute(){
        armSubsystem.setD(dYValue.getAsDouble());
    }
}
