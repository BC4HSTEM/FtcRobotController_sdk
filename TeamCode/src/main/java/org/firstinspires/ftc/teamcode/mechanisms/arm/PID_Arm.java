package org.firstinspires.ftc.teamcode.mechanisms.arm;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class PID_Arm extends OpMode {
    private PIDController controller;

    public static double p = 0, i =0, d = 0;
    public static double f = 0;

    public static int target = 0;

    private final double ticks_in_degree = 700 / 100.0;

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
