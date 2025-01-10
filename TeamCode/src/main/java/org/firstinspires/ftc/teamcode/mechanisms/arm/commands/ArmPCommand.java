package org.firstinspires.ftc.teamcode.mechanisms.arm.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.arm.subsystems.ArmSubsystem;

import java.util.function.DoubleSupplier;

public class ArmPCommand extends CommandBase {
    private ArmSubsystem armSubsystem;

    private DoubleSupplier pYValue;


    Telemetry t;

    public ArmPCommand(ArmSubsystem armSubsystem, DoubleSupplier pY, Telemetry t){
        this.armSubsystem = armSubsystem;
        pYValue = pY;


        this.t = t;


        addRequirements(armSubsystem);
    }

    @Override
    public void execute(){
        armSubsystem.setP(pYValue.getAsDouble());
    }
}
