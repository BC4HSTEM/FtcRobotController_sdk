package org.firstinspires.ftc.teamcode.mechanisms.grabber.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//1. Extend Subsystem base class
@Config
public class GrabberSubsystem extends SubsystemBase {
    //2. setup a SeveroEx variable
    private ServoEx grabberRight;
    private ServoEx grabberLeft;

    private Telemetry telemetry;
    //3. Define the open and close position of the grabber
    public static double GRABBER_RIGHT_CLOSE_ANGLE = 100;
    public static double GRABBER_LEFT_CLOSE_ANGLE = 150;
    public static double GRABBER_RIGHT_OPEN_ANGLE = 150;
    public static double GRABBER_LEFT_OPEN_ANGLE = 100;

    public static double GRABBER_RIGHT_OPEN_POSITION = 0.3;
    public static double GRABBER_LEFT_OPEN_POSITION = 0.4;
    public static double GRABBER_RIGHT_CLOSE_POSITION = 0.115;
    public static double GRABBER_LEFT_CLOSE_POSITION = 0.115;

    //4. Define you constructor .... we should probably have one with telemetry passed to it
    public GrabberSubsystem(ServoEx grabberRight, ServoEx grabberLeft){

        this.grabberRight = grabberRight;
        this.grabberLeft = grabberLeft;
    }

    public GrabberSubsystem(ServoEx grabberRight, ServoEx grabberLeft, Telemetry telemetry, boolean useDB){

        this.grabberRight = grabberRight;
        this.grabberLeft = grabberLeft;
        if (useDB){
            this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        }
        else{
            this.telemetry = telemetry;
        }
    }
    //5. define a grab function that sets the servo position....this function should probably be private
    public void grabRightPosition(double rPosition){
        grabberRight.setPosition(rPosition);
    }
    public void grabLeftPosition(double lPosition){
        grabberLeft.setPosition(lPosition);
    }

    //6. Define functions that the commands can call
    public void closeGrabberPosition(){
        telemetry.addLine("inside closeGrabberPosition");
        telemetry.update();
        grabRightPosition(GRABBER_RIGHT_CLOSE_POSITION);
        grabLeftPosition(GRABBER_LEFT_CLOSE_POSITION);
    }


    public void openGrabberPosition(){
        telemetry.addLine("inside openGrabberPosition");
        telemetry.update();
        grabLeftPosition(GRABBER_LEFT_OPEN_POSITION);
        grabRightPosition(GRABBER_RIGHT_OPEN_POSITION);
    }

    //7. Accessors for telemetry and isFinished in Commands
    public double getGrabberRightPosition(){
        return grabberRight.getPosition();
    }
    public double getGrabberLeftPosition(){
        return grabberLeft.getPosition();
    }


    public double getLeftClosePosition(){
        return GRABBER_LEFT_CLOSE_POSITION;
    }

    public double getRightClosePosition(){
        return GRABBER_RIGHT_CLOSE_POSITION;
    }

    public double getRightOpenPosition(){
        return GRABBER_RIGHT_OPEN_POSITION;
    }
    public double getLeftOpenPosition(){
        return GRABBER_LEFT_OPEN_POSITION;
    }

    //8. go to GrabberCommand
}