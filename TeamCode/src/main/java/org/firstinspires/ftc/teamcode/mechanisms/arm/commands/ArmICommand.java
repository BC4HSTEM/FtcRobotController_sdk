package org.firstinspires.ftc.teamcode.mechanisms.arm.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.arm.subsystems.ArmSubsystem;

import java.util.function.DoubleSupplier;

public class ArmICommand extends CommandBase {
    private ArmSubsystem armSubsystem;

    private DoubleSupplier iYValue;


    Telemetry t;

    public ArmICommand(ArmSubsystem armSubsystem, DoubleSupplier iY, Telemetry t){
        this.armSubsystem = armSubsystem;
        iYValue = iY;


        this.t = t;


        addRequirements(armSubsystem);
    }

    @Override
    public void execute(){
        armSubsystem.setI(iYValue.getAsDouble());
    }
}
