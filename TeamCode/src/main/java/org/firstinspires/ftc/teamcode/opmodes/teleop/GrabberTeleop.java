package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp
public class GrabberTeleop extends OpMode {



    SimpleServo right_grabber;
    SimpleServo left_grabber;

    Servo right_grabber_servo;
    Servo left_grabber_servo;

    private int MIN_ANGLE = 0;

    private int MAX_ANGLE = 180;

    private boolean grabberClosed = false;



    @Override
    public void init(){



        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        //right_grabber = new SimpleServo(hardwareMap, "grabber_right", MIN_ANGLE, MAX_ANGLE);
        //left_grabber = new SimpleServo(hardwareMap, "grabber_left", MIN_ANGLE, MAX_ANGLE);

        right_grabber_servo = hardwareMap.get(Servo.class, "grabber_right");
        left_grabber_servo = hardwareMap.get(Servo.class, "grabber_left");
        //left_grabber.setInverted(true);
        left_grabber_servo.setDirection(Servo.Direction.REVERSE);

        GamepadEx op = new GamepadEx(gamepad1);
        //op.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).toggleWhenPressed()







    }

    @Override
    public void loop(){


        if(gamepad1.right_bumper){
            if(grabberClosed){
                //open grabber
                //right_grabber.setPosition(.115);
                //left_grabber.setPosition(.115);
                right_grabber_servo.setPosition(.115);
                left_grabber_servo.setPosition(.115);

                grabberClosed = false;
            }
            else{
                //close grabber
                //right_grabber.setPosition(0.03);
                //left_grabber.setPosition(0.03);

                right_grabber_servo.setPosition(0.03);
                left_grabber_servo.setPosition(0.03);

                grabberClosed = true;
            }
        }





    }




}
