package org.firstinspires.ftc.teamcode.mechanisms.arm.commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.globals.ArmAdjust;
import org.firstinspires.ftc.teamcode.mechanisms.arm.subsystems.ArmAdjustSubsystem;


public class ArmResetAdjustCommand extends CommandBase {

    private ArmAdjustSubsystem armAdjustSubsystem;
    private Telemetry telemetry;



    public ArmResetAdjustCommand(ArmAdjustSubsystem armAdjustSubsystem){
        this.armAdjustSubsystem = armAdjustSubsystem;

        addRequirements(armAdjustSubsystem);
    }

    public ArmResetAdjustCommand(ArmAdjustSubsystem armAdjustSubsystem, Telemetry telemetry){
        this.armAdjustSubsystem = armAdjustSubsystem;
        this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());



        //addRequirements(armSubsystem);
    }


    @Override
    public void initialize(){
        ArmAdjust.getInstance().setAdjustArm(ArmAdjust.AdjustArm.ADJUST_RESET);


    }

    //@Override
    public boolean isFinished(){
        return true;
    }

    /*@Override
    public void end(boolean interrupt){
        armSubsystem.stopArm();
    }*/


}
