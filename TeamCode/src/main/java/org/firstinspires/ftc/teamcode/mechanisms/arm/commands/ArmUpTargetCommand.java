package org.firstinspires.ftc.teamcode.mechanisms.arm.commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.arm.subsystems.ArmSubsystem;


public class ArmUpTargetCommand extends CommandBase {

    private ArmSubsystem armSubsystem;
    private Telemetry telemetry;



    public ArmUpTargetCommand(ArmSubsystem armSubsystem){
        this.armSubsystem = armSubsystem;

        addRequirements(armSubsystem);
    }

    public ArmUpTargetCommand(ArmSubsystem armSubsystem, Telemetry telemetry){
        this.armSubsystem = armSubsystem;
        this.telemetry = telemetry;
        this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        addRequirements(armSubsystem);
    }


    @Override
    public void initialize(){
        armSubsystem.setTargetPosition(20);
    }
    @Override
    public void execute(){



    }

    public boolean isFinished(){
        return armSubsystem.isAtUpTargetPosition();
    }

    @Override
    public void end(boolean interrupt){
        armSubsystem.stopArm();
    }
}