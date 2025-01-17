package org.firstinspires.ftc.teamcode.mechanisms.arm.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//1. Extend Subsystem base class
@Config
public class ArmAdjustSubsystem extends SubsystemBase {
    //2. setup a SeveroEx variable

    private Telemetry telemetry;
    //3. Define the open and close position of the grabber




    //4. Define you constructor .... we should probably have one with telemetry passed to it
    public ArmAdjustSubsystem(){


    }

    public ArmAdjustSubsystem(Telemetry telemetry, boolean useDB){


        if (useDB){
            this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        }
        else{
            this.telemetry = telemetry;
        }
    }


}