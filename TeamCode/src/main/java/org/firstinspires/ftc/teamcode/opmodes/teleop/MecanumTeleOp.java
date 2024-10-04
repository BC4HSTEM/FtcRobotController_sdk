package org.firstinspires.ftc.teamcode.opmodes.teleop;



import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;



@Config
@TeleOp(name="MecanumTeleOp", group="FreightFrenzy")
public class MecanumTeleOp extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {



        //import the hardware map
        Motor fl = new Motor(hardwareMap, "FL");
        Motor bl = new Motor(hardwareMap, "BL");
        Motor fr = new Motor(hardwareMap, "FR");
        Motor br = new Motor(hardwareMap, "BR");


        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        //set the drivetrain slowdown
        double slowdown = 2.0;


        //set the controller stick values
        double controller1lstickx = 0.0;
        double controller1lsticky = 0.0;
        double controller1rstickx = 0.0;

        //init loop
        while (! isStarted()) {

        }


        waitForStart();

        if (isStopRequested()) return;

        // Set the Arm position and motor mode once before the while loop


            /* Drivetrain Mecanum, Gamepad 1
             * Leftstick controls forward/back and strafing
             * Rightstick controls rotation
             * B button press toggles slowdown, cuts speed in half
             * Custom deadzone created to account for joystick drift
             */
            // Deadzone to correct drift
            controller1lstickx = gamepad1.left_stick_x;
            controller1lsticky = gamepad1.left_stick_y;
            controller1rstickx = gamepad1.right_stick_x;
            
            if (controller1lstickx < 0.25 && controller1lstickx > -0.25)
                controller1lstickx = 0.0;
            if (controller1lsticky < 0.25 && controller1lsticky > -0.25)
                controller1lsticky = 0.0;
            if (controller1rstickx < 0.25 && controller1rstickx > -0.25)
                controller1rstickx = 0.0;



            //Add slowdown
            controller1lstickx = controller1lstickx * slowdown;
            controller1lsticky = controller1lsticky * slowdown;
            controller1rstickx = controller1rstickx * slowdown;

            double y = controller1lsticky;
            double x = -controller1lstickx * 1.1; // * 1.1 Counteract imperfect strafing
            double rx = -controller1rstickx;
            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = ((y + x + rx) / denominator);
            double backLeftPower = ((y - x + rx) / denominator);
            double frontRightPower = ((y - x - rx) / denominator);
            double backRightPower = ((y + x - rx) / denominator);



            fl.motor.setPower(frontLeftPower);
            bl.motor.setPower(backLeftPower);
            fr.motor.setPower(frontRightPower);
            br.motor.setPower(backRightPower);


            telemetry.update();
        }
}



