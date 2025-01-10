package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@TeleOp
public class ArmTeleop extends OpMode {

    private PIDController pidController;
    public static double p = 0.00, i = 0, d = 0.0000;
    public static double f = 0.00;


    //downPos = 102;
    public static int target = 50;

    private final double ticks_in_degree = 288.0 / 360.0;

    DcMotorEx arm_motor;



    @Override
    public void init(){

        pidController = new PIDController(p, i, d);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        arm_motor = hardwareMap.get(DcMotorEx.class, "arm");
        arm_motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        arm_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm_motor.setDirection(DcMotorEx.Direction.REVERSE);
        arm_motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);





    }

    @Override
    public void loop(){
        pidController.setPID(p,i,d);

        int armPos = arm_motor.getCurrentPosition();
        double pid = pidController.calculate(armPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;

        arm_motor.setPower(-power);

        telemetry.addData("pos", armPos);
        telemetry.addData("target" , target);
        telemetry.addData("power", power);
        telemetry.update();









    }




}
