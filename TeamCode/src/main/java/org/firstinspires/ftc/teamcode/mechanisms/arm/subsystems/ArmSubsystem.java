package org.firstinspires.ftc.teamcode.mechanisms.arm.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class ArmSubsystem extends SubsystemBase {

    private PIDController controller;


    // OPTION 1: decrease the P value in PID
    // to slow the rate at which the control loop reaches its target
    public static double p = 0.30; //.30 //0.44 //0.0099;
    public static double i = 0.0;
    public static double d = 0.0001; //0.0028556 //0.0002 // 0.0001;
    public static double f = 0.09; //0.09 //.09;

    public static int testTarget = 50;

    public static double motorDegrees = 360.0;
    //public static double largeGear = 108;

    //public static double smallGear = 30;

    //public double gearRatio = largeGear / smallGear;

    public double ticksPerRotation = 288;

    // NOTE: Sets offset for the starting position of the arm. Value used with the alternate code in Option 2 below
    // I think this is 144 however it would be good to check telemetry and the outputs.
    // I'm not quite sure I understand how negative/positive power from cosine affects the PID calculation
    public double ticksOffsetFromHorizontal = 144;

    public static int dropTargetPosition = 700;

    public static int dropMidPosition = 400;

    public static int pickUpTargetPosition = 20;

    public static int downTargetPosition = 110
            ;
    public static int upTargetPosition = 25;

    public static int travelTargetPosition = 65;

    // NOTE: ticks in degree should divide the ticks per 1 motor rotation into 360. (Removing gear ratio for now)
    // 288 / 360 = 1.25 ticks for every degree
    // Core Hex Motor Specs
    // https://www.revrobotics.com/rev-41-1300/
    private final double ticks_in_degree = motorDegrees / ticksPerRotation;

    private DcMotorEx arm;
    private Telemetry telemetry;

    public ArmSubsystem(DcMotorEx armMotor){

        arm = armMotor;
        createController();
    }
    //9. Added another constructor that takes in Telemetry
    public ArmSubsystem(DcMotorEx armMotor, Telemetry telemetry, boolean useDB){

        arm = armMotor;
        if (useDB){
            this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        }
        else{
            this.telemetry = telemetry;
        }

        createController();
    }



    private void createController(){

        controller = new PIDController(p,i,d);
    }

    public void setDropTargetPIDPosition(){
        controller.setPID(p,i,d);
        setPower(dropTargetPosition);
    }

    public void setDownTargetPIDPosition(){
        controller.setPID(p,i,d);
        setPower(downTargetPosition);
    }

    public void setPickUpTargetPIDPosition(){
        controller.setPID(p,i,d);
        setPower(pickUpTargetPosition);
    }

    public void setUpTargetPIDPosition(){
        controller.setPID(p,i,d);
        setPower(upTargetPosition);
    }

    public void setMidDropTargetPIDPosition(){
        controller.setPID(p,i,d);
        setPower(dropMidPosition);
    }

    public void setTravelTargetPIDPosition(){
        controller.setPID(p,i,d);
        setPower(travelTargetPosition);
    }

    public void setTargetPosition(int t){
        int armPos = arm.getCurrentPosition();
        arm.setTargetPosition(t);
        //arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        arm.setPower(0.8);

        //telemetry.addData("target power, ", 0.8);
        telemetry.addData("arm pos, ", armPos);
        telemetry.addData("target ", t);
        telemetry.addData("motor power ", arm.getPower());


        telemetry.update();

    }

    public void setDownTargetPosition(){
        //setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        setTargetPosition(downTargetPosition);


    }

    public void setUpTargetPosition(){
        //setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        setTargetPosition(upTargetPosition);


    }

    public void setTravelTargetPosition(){
        //setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        setTargetPosition(travelTargetPosition);


    }

    public void stopResetEncoder(){
        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    //// set the inversion factor
    //m_motor.setInverted(true);
    //
    //// get the inversion factor
    //boolean isInverted = m_motor.getInverted();
    //
    //// set the zero power behavior to BRAKE
    //m_motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    public void setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior behavior){
        arm.setZeroPowerBehavior(behavior);
    }

    public void setDirection(DcMotorEx.Direction direction){

        arm.setDirection(direction);
    }


    public void setMode(DcMotorEx.RunMode mode){

        arm.setMode(mode);
    }

    public void stopArm(){
        arm.setPower(0);
    }

    public double getPowwr(){

        return arm.getPower();
    }

    public boolean isAtDropTargetPosition(){
        return controller.atSetPoint();
    }

    public boolean isAtPickUpTargetPosition(){
        return controller.atSetPoint();
    }

    public boolean isAtDownTargetPosition(){
        return controller.atSetPoint();
    }

    public boolean isAtUpTargetPosition(){

        return controller.atSetPoint();
    }

    public boolean isAtTravelTargetPosition(){
        return controller.atSetPoint();
    }

    public double getPosition(){
        return arm.getCurrentPosition();
    }

    public double getTarget(){

        return arm.getTargetPosition();
    }

    private void setPower(int target){
        int armPos = arm.getCurrentPosition();
        double pid = controller.calculate(armPos, target);

        // OPTION 2: Determine trig and math corrections for the arm movement
        // https://www.geogebra.org/m/XC3D226P to visualize how cosine is used to produce power to the arm
        // Use armPos when setting ff so the power feedforward is proportional to the CURRENT arm angle by using COSINE
        // Offset the arm angle from horizontal to the starting position
        //  ** (note: in ticks, not angle!); determine correct value and needs tuned?
        // Looks like ticks should be multiplied (not divided) by the ticks_in_degree to get the correct angle to convert to radians

        //double ff = Math.cos( Math.toRadians( (armPos + ticksOffsetFromHorizontal) * ticks_in_degree)) * f;
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;


        arm.setPower(power);

        telemetry.addLine("ARM CONTROL");
        telemetry.addData("power", power);
        telemetry.addData("armPos ", armPos);
        telemetry.addData("target (position) ", target);
        telemetry.addData("test target (position) ", testTarget);
        telemetry.addData("target angle ", target / ticks_in_degree);
        telemetry.addData("motor power ", arm.getPower());
        telemetry.addData("ff", ff);

        telemetry.update();


    }

    public void setTargetPower(double power){


        arm.setPower(power);


    }





}
