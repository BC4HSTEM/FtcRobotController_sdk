/*
    This Teleop checks the LaunchPad mechanism
    using Continuous Rotation Servos with FTCLib. May need to use the FTC SDK instead.
    We should add in the launcher itself when confirmed working


*/
package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@TeleOp
public class LauncherTeleOp extends OpMode {

// FTCLib CRServos
CRServo feederRight;
CRServo feederLeft;

public static int launchPadRuntime = 2000;
private ElapsedTime servoTimer;
private boolean servosRunning = false;


@Override
public void init(){

    telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

    feederRight = new com.arcrobotics.ftclib.hardware.motors.CRServo(hardwareMap, "grabber_right"); // @TODO rename feeder_right
    feederLeft = new com.arcrobotics.ftclib.hardware.motors.CRServo(hardwareMap, "grabber_left"); // @TODO rename feeder_left
    feederLeft.setInverted(true); // @TODO Check which side should be inverted, probably left

    GamepadEx op = new GamepadEx(gamepad1);
    //op.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).toggleWhenPressed()

}

@Override
public void loop(){

    if(gamepad1.right_bumper){

        if (!servosRunning) {
            // Start servos at full power, reset the timer
            feederRight.set(1.0);
            feederRight.set(1.0);
            servoTimer.reset();
            servosRunning = true;

        } else if (servosRunning && servoTimer.milliseconds() >= launchPadRuntime) {
            // Stop the servo after timer
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

}




}
