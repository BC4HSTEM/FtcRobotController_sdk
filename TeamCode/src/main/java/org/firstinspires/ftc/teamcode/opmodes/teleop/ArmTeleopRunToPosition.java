package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Config
@TeleOp
public class ArmTeleopRunToPosition extends OpMode {

    // Various Arm coding references using the FTC SDK motor modes (PIDF calculated on motor controller)
    // https://docs.revrobotics.com/duo-control/hello-robot-java/part-3/arm-control-with-encoders-onbot-java/calculating-target-position
    // https://docs.revrobotics.com/ftc-kickoff-concepts/into-the-deep-2024-25/programming-teleop/programming-onbot-java-overview

    DcMotorEx arm_motor;

    // Arm Target Positions
    public static int downTargetPosition = 113; // A
    public static int upTargetPosition = 25; // Y
    public static int travelTargetPosition = 65; // B
    public static int homeTargetPosition = 0; // X

    public int armCurrentPos = 0;
    public int targetPos = 0;

    // Arm Manual
    public static int armManualIncrement = 1;
    public static int armMaxCount = 113;

    // Stop the motor before it hits home (zero) position
    public static int armShutdownThreshold = 10;

    // Arm Power: Sets a maximum power when using RTP as calculated by the Rev motor controller
    // Any idea what the "lowest" max could be?
    public static double armPower = 0.6;


    @Override
    public void init(){

        // FTC Dashboard
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        // Arm initialization
        arm_motor = hardwareMap.get(DcMotorEx.class, "arm");
        arm_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        arm_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm_motor.setPower(0);

    }

    @Override
    public void loop(){

        // Get Current Position
        armCurrentPos = arm_motor.getCurrentPosition();

        // Arm Run to Position
        if (gamepad1.a) {
            targetPos = downTargetPosition;

        } else if (gamepad1.y){
            targetPos = upTargetPosition;

        } else if (gamepad1.b){
            targetPos = travelTargetPosition;

        } else if (gamepad1.x){
            targetPos = homeTargetPosition;

        } else if (gamepad1.dpad_down && armCurrentPos <= armMaxCount){
            targetPos = armCurrentPos + armManualIncrement;

        } else if (gamepad1.dpad_up){
            // Primarily for resetting the motor if the encoder counts get out of sync.
            targetPos = armCurrentPos - armManualIncrement;
        }

        // Watchdog to shut down motor once the arm reaches the home position.
        // Ideally this would be a limit switch.
        // A reset can also be done manually with the "start" button (below)
        if (    arm_motor.getMode() == DcMotor.RunMode.RUN_TO_POSITION
                && arm_motor.getTargetPosition()  == homeTargetPosition
                && arm_motor.getCurrentPosition() <= armShutdownThreshold
        ) {
            arm_motor.setPower(0.0);
            arm_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            arm_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        } else if (targetPos != 0 ){
            // Run the arm to position
            arm_motor.setTargetPosition(targetPos);
            arm_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm_motor.setPower(armPower);
        }

        // Manual way to re-zero encoder actions
        if (gamepad1.start) {
            arm_motor.setPower(0.0);
            arm_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            arm_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            targetPos = homeTargetPosition;
        }


        telemetry.addData("Target Position", arm_motor.getTargetPosition() );
        telemetry.addData("Current Position", arm_motor.getCurrentPosition() );
        telemetry.addData("Current Power", arm_motor.getPower() );
        telemetry.addData("Max Power", armPower );
        // telemetry.addData("PIDF values", arm_motor.getPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION)); // does this work for RTP?
        telemetry.update();

    }




}
