package org.firstinspires.ftc.teamcode.mechanisms.arm.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.arm.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class ArmFCommand extends CommandBase {
    private ArmSubsystem armSubsystem;
    private DoubleSupplier fUpValue;

    private DoubleSupplier fYValue;

    Telemetry t;

    public ArmFCommand(ArmSubsystem armSubsystem, DoubleSupplier fY, Telemetry t){
        this.armSubsystem = armSubsystem;
        fYValue = fY;


        this.t = t;


        addRequirements(armSubsystem);
    }

    @Override
    public void execute(){
        armSubsystem.setF(fYValue.getAsDouble());
    }
}
