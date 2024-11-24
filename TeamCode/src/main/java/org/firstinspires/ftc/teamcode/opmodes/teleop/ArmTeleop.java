package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@TeleOp
public class ArmTeleop extends OpMode {

    private PIDController pidController;
    public static double p = 0, i = 0, d = 0;
    public static double f = 0;

    public int target = 0;

    private final double ticks_in_degree = 288.0 / 360.0;

    DcMotorEx arm_motor;



    @Override
    public void init(){

        pidController = new PIDController(p, i, d);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        arm_motor = hardwareMap.get(DcMotorEx.class, "arm");





    }

    @Override
    public void loop(){
        pidController.setPID(p,i,d);

        int armPos = arm_motor.getCurrentPosition();
        double pid = pidController.calculate(armPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;

        arm_motor.setPower(power);

        telemetry.addData("pos", armPos);
        telemetry.addData("target" , target);
        telemetry.update();









    }




}
