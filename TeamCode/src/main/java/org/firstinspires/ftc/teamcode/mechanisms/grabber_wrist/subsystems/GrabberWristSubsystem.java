package org.firstinspires.ftc.teamcode.mechanisms.grabber_wrist.subsystems;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
@Config
public class GrabberWristSubsystem extends SubsystemBase {
    private ServoEx grabberWrist;
    Telemetry telemetry;

    public static double downPosition = .5;
    public static double upPosition = .2;


    public GrabberWristSubsystem(ServoEx gw, Telemetry telemetry, boolean useDB){

        grabberWrist = gw;

        if (useDB){
            this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        }
        else{
            this.telemetry = telemetry;
        }
    }

    public void DownObject(){
        telemetry.addLine("grabber wrist down initialize");
        grabberWrist.setPosition(downPosition);

        telemetry.addData("Grabber wrist down position", grabberWrist.getPosition());
        telemetry.update();
    }

    public void UpObject(){
        telemetry.addLine("grabber wrist up initialize");
        grabberWrist.setPosition(upPosition);

        telemetry.addData("grabber wrist up position", grabberWrist.getPosition());
        telemetry.update();
    }

    public void setInverted(boolean invert){
        grabberWrist.setInverted(invert);
    }

    public double getGrabberWristPosition(){
        return grabberWrist.getPosition();
    }
}
