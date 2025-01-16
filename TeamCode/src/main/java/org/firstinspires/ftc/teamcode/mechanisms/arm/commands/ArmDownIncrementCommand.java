package org.firstinspires.ftc.teamcode.mechanisms.arm.commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.arm.subsystems.ArmSubsystem;


public class ArmDownIncrementCommand extends CommandBase {

    private ArmSubsystem armSubsystem;
    private Telemetry telemetry;



    public ArmDownIncrementCommand(ArmSubsystem armSubsystem){
        this.armSubsystem = armSubsystem;

        addRequirements(armSubsystem);
    }

    public ArmDownIncrementCommand(ArmSubsystem armSubsystem, Telemetry telemetry){
        this.armSubsystem = armSubsystem;
        this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());



        addRequirements(armSubsystem);
    }


    @Override
    public void execute(){
        armSubsystem.setArmDownIncrement();


    }

    //@Override
    /*public boolean isFinished(){
        return armSubsystem.isAtDownTargetPosition();
    }

    @Override
    public void end(boolean interrupt){
        armSubsystem.stopArm();
    }*/


}
