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

    public static double motorDegrees = 360.0;
    //public static double largeGear = 108;

    //public static double smallGear = 30;

    //public double gearRatio = largeGear / smallGear;

    public double ticksPerRotation = 288;
    private PIDController pidController;
    public static double p = .06, i = 0, d = 0.0001;
    public static double f = 0.08;


    //downPos = 102;
    public static int target = 50;

    // NOTE: Sets offset for the starting position of the arm. Value used with the alternate code in Option 2 below
    // I think this is 144 however it would be good to check telemetry and the outputs.
    // I'm not quite sure I understand how negative/positive power from cosine affects the PID calculation
    public double ticksOffsetFromHorizontal = 144;

    // NOTE: ticks in degree should divide the ticks per 1 motor rotation into 360. (Removing gear ratio for now)
    // 288 / 360 = 1.25 ticks for every degree
    // Core Hex Motor Specs
    // https://www.revrobotics.com/rev-41-1300/
    private final double ticks_in_degree = motorDegrees / ticksPerRotation;

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

        // OPTION 2: Determine trig and math corrections for the arm movement
        // https://www.geogebra.org/m/XC3D226P to visualize how cosine is used to produce power to the arm
        // Use armPos when setting ff so the power feedforward is proportional to the CURRENT arm angle by using COSINE
        // Offset the arm angle from horizontal to the starting position
        //  ** (note: in ticks, not angle!); determine correct value and needs tuned?
        // Looks like ticks should be multiplied (not divided) by the ticks_in_degree to get the correct angle to convert to radians

        // double ff = Math.cos( Math.toRadians( (armPos + ticksOffsetFromHorizontal) * ticks_in_degree)) * f;
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;

        arm_motor.setPower(power);

        telemetry.addData("pos", armPos);
        telemetry.addData("target" , target);
        telemetry.addData("power", power);
        telemetry.update();









    }




}
