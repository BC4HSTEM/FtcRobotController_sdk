package org.firstinspires.ftc.teamcode.mechanisms.launch_pad.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//1. Extend Subsystem base class
@Config
public class LaunchPadSubsystem extends SubsystemBase {

    private Telemetry telemetry;

    //2. setup a CRServo variable
    private CRServo feederRight;
    private CRServo feederLeft;

    //3. Define the length of time to run in milliseconds
    public static int launchPadRuntime = 2000;
    private ElapsedTime servoTimer;
    private boolean servosRunning = false;

    //4. Define you constructor
    public LaunchPadSubsystem(CRServo feederLeft, CRServo feederRight){
        this.feederRight = feederRight;
        this.feederLeft = feederLeft;
    }

    public LaunchPadSubsystem(CRServo feederLeft, CRServo feederRight, Telemetry telemetry, boolean useDB){

        this.feederRight = feederRight;
        this.feederLeft = feederLeft;
        if (useDB){
            this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        }
        else{
            this.telemetry = telemetry;
        }
    }
    //5. tell the launchpad to push a ball into the launcher
    public void activateFeeder(){

        if (!servosRunning) {
            // Start servos at full power
            feederRight.set(1.0);
            feederRight.set(1.0);
            servoTimer.reset();
            servosRunning = true;
        }

        // Stop the servo after timer
        if (servosRunning && servoTimer.milliseconds() >= launchPadRuntime) {
            feederRight.set(0);
            feederRight.set(0);
            servosRunning = false;
        }

        // Telemetry for debugging
        telemetry.addData("Servos Running", servosRunning);
        telemetry.addData("Timer (ms)", servoTimer.milliseconds());
        telemetry.addData("Right CRServo Power", feederRight.get());
        telemetry.addData("Left CRServo Power", feederRight.get());
        telemetry.update();
    }

    //7. Accessors for telemetry and isFinished in Commands
    public double getFeederLeftSpeed(){
        return feederLeft.get();
    }
    public double getFeederRightSpeed(){
        return feederRight.get();
    }
}