
package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;


@Config
@TeleOp
public class ArmTeleopRunToPositionAlternate extends OpMode {

    // MODIFIED FROM EXAMPLE:
    // https://docs.revrobotics.com/ftc-kickoff-concepts/into-the-deep-2024-25/programming-teleop/programming-onbot-java-overview


        // Declare OpMode members.
        private ElapsedTime runtime = new ElapsedTime();
        private DcMotor arm_motor = null;

        private boolean manualMode = false;
        private double armSetpoint = 0.0;

        private final double armManualDeadband = 0.03;

        private final int armHomePosition = 0; // X
        private final int armUpPosition = 25; // Y
        private final int armTravelPosition = 65; // B
        private final int armDownPosition = 113; // A
        private final int armShutdownThreshold = 5;


        /*
         * Code to run ONCE when the driver hits INIT
         */
        @Override
        public void init() {
            telemetry.addData("Status", "Initialized");

            // FTC Dashboard
            telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

            arm_motor = hardwareMap.get(DcMotorEx.class, "arm");

            arm_motor.setDirection(DcMotor.Direction.FORWARD);
            arm_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            arm_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            arm_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            arm_motor.setPower(0.0);

            telemetry.addData("Status", "Initialized");
        }

        /*
         * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
         */
        @Override
        public void init_loop() {
        }

        /*
         * Code to run ONCE when the driver hits PLAY
         */
        @Override
        public void start() {
            runtime.reset();

            arm_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            arm_motor.setTargetPosition(armHomePosition);
            arm_motor.setPower(1.0);
            arm_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        /*
         * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
         */
        @Override
        public void loop() {
            double manualArmPower;
            
            //ARM & WRIST
            manualArmPower = gamepad1.right_trigger - gamepad1.left_trigger;
            if (Math.abs(manualArmPower) > armManualDeadband) {
                if (!manualMode) {
                    arm_motor.setPower(0.0);
                    arm_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    manualMode = true;
                }
                arm_motor.setPower(manualArmPower);
            }
            else {
                if (manualMode) {
                    arm_motor.setTargetPosition(arm_motor.getCurrentPosition());
                    arm_motor.setPower(0.3);
                    arm_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    manualMode = false;
                }

                //preset buttons
                if (gamepad1.x) {
                    arm_motor.setTargetPosition(armHomePosition);
                    arm_motor.setPower(0.3);
                    arm_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
                else if (gamepad1.y) {
                    arm_motor.setTargetPosition(armUpPosition);
                    arm_motor.setPower(0.3);
                    arm_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
                else if (gamepad1.b) {
                    arm_motor.setTargetPosition(armTravelPosition);
                    arm_motor.setPower(0.3);
                    arm_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
                else if (gamepad1.a) {
                    arm_motor.setTargetPosition(armDownPosition);
                    arm_motor.setPower(0.3);
                    arm_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
            }

            //Re-zero encoder button
            if (gamepad1.start) {
                arm_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                arm_motor.setPower(0.0);
                manualMode = false;
            }

            //Watchdog to shut down motor once the arm reaches the home position
            if (!manualMode &&
                    arm_motor.getMode() == DcMotor.RunMode.RUN_TO_POSITION &&
                    arm_motor.getTargetPosition() <= armShutdownThreshold &&
                    arm_motor.getCurrentPosition() <= armShutdownThreshold
            ) {
                arm_motor.setPower(0.0);
                arm_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }


            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Manual Power", manualArmPower);
            telemetry.addData("Arm Current Pos:",
                    "left = " + ((Integer)arm_motor.getCurrentPosition()).toString());
            telemetry.addData("Arm Target Pos:",
                    "left = " +  ((Integer)arm_motor.getTargetPosition()).toString());
        }

        /*
         * Code to run ONCE after the driver hits STOP
         */
        @Override
        public void stop() {
        }



}
