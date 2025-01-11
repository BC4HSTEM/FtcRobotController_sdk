package org.firstinspires.ftc.teamcode.mechanisms.arm.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.arm.subsystems.ArmSubsystem;

public class ArmFDownCommand extends CommandBase {
    private ArmSubsystem armSubsystem;

    Telemetry telemetry;

    public ArmFDownCommand(ArmSubsystem armSubsystem, Telemetry t){
        this.armSubsystem = armSubsystem;

        this.telemetry = t;


        addRequirements(armSubsystem);
    }

    @Override
    public void initialize(){

        armSubsystem.setFDown();
        //telemetry.addData("f", fYValue);
    }

    public boolean isFinished(){
        return true;
    }
}
