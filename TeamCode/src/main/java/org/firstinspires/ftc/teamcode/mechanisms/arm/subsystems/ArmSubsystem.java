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
import org.firstinspires.ftc.teamcode.globals.ArmAdjust;

@Config
public class ArmSubsystem extends SubsystemBase {

    private PIDController controller;

    public static double p = 0.08269999999999955;
    public static double i = 0.0;
    public static double d = 0.00;
    public static double f = 0.059000000000000045;

    public static double pIncrement = 0.001;
    public static double iIncrement = 0.001;
    public static double dIncrement = 0.001;
    public static double fIncrement = 0.001;

    public static int testTarget = 0;

    public static double motorDegrees = 360.0;

    public static double ticksOffsetFromHorizontal = 104;

    public double ticksPerRotation = 288;

    public static int dropTargetPosition = 700;

    public static int dropMidPosition = 400;

    public static int resetPosition = 0;
    public static int pickUpTargetPosition = 20;

    public static int downTargetPosition = 107;


    private final int resetDownTargetPosition = 107;
    public static int upTargetPosition = 25;

    private final int resetUpTargetPosition = 25;

    public static int travelTargetPosition = 65;

    private final int resetTravelTargetPosition = 65;

    public static int currentTargetPos = 0;

    public static int armDownIncrement = 1;

    public static int armUpDecrement = 1;

    //public static int armAdjust = 0;



    private final double ticks_in_degree = (/*gearRatio */ ticksPerRotation)  / motorDegrees;

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
        controller.setTolerance(5,10);
    }

    public void setDropTargetPIDPosition(){
        if(ArmAdjust.getInstance().getAdjustArm() == ArmAdjust.AdjustArm.ADJUST_DOWN){
            dropTargetPosition += 1;
        }
        else if (ArmAdjust.getInstance().getAdjustArm() == ArmAdjust.AdjustArm.ADJUST_UP){
            dropTargetPosition -= 1;
        }
        setPower(dropTargetPosition);
        ArmAdjust.getInstance().setAdjustArm(ArmAdjust.AdjustArm.ADJUST_NONE);
    }

    public void setDownTargetPIDPosition(){
        if(ArmAdjust.getInstance().getAdjustArm() == ArmAdjust.AdjustArm.ADJUST_DOWN){
            downTargetPosition += 1;
        }
        else if (ArmAdjust.getInstance().getAdjustArm() == ArmAdjust.AdjustArm.ADJUST_UP){
            downTargetPosition -= 1;
        }
        else if (ArmAdjust.getInstance().getAdjustArm() == ArmAdjust.AdjustArm.ADJUST_RESET) {
            downTargetPosition = resetDownTargetPosition;
        }
        setPower(downTargetPosition);
        ArmAdjust.getInstance().setAdjustArm(ArmAdjust.AdjustArm.ADJUST_NONE);
    }

    public void setPickUpTargetPIDPosition(){
        if(ArmAdjust.getInstance().getAdjustArm() == ArmAdjust.AdjustArm.ADJUST_DOWN){
            pickUpTargetPosition += 1;
        }
        else if (ArmAdjust.getInstance().getAdjustArm() == ArmAdjust.AdjustArm.ADJUST_UP){
            pickUpTargetPosition -= 1;
        }
        else if (ArmAdjust.getInstance().getAdjustArm() == ArmAdjust.AdjustArm.ADJUST_RESET) {
            pickUpTargetPosition = resetUpTargetPosition;
        }
        setPower(pickUpTargetPosition);
        ArmAdjust.getInstance().setAdjustArm(ArmAdjust.AdjustArm.ADJUST_NONE);
    }

    public void setUpTargetPIDPosition(){
        if(ArmAdjust.getInstance().getAdjustArm() == ArmAdjust.AdjustArm.ADJUST_DOWN){
            upTargetPosition += 1;
        }
        else if (ArmAdjust.getInstance().getAdjustArm() == ArmAdjust.AdjustArm.ADJUST_UP){
            upTargetPosition -= 1;
        }
        setPower(upTargetPosition);
        ArmAdjust.getInstance().setAdjustArm(ArmAdjust.AdjustArm.ADJUST_NONE);
    }

    public void setMidDropTargetPIDPosition(){
        if(ArmAdjust.getInstance().getAdjustArm() == ArmAdjust.AdjustArm.ADJUST_DOWN){
            dropMidPosition += 1;
        }
        else if (ArmAdjust.getInstance().getAdjustArm() == ArmAdjust.AdjustArm.ADJUST_RESET) {
            dropMidPosition = resetTravelTargetPosition;
        }
        else if (ArmAdjust.getInstance().getAdjustArm() == ArmAdjust.AdjustArm.ADJUST_UP){
            travelTargetPosition -= 1;
        }
        setPower(dropMidPosition);
        ArmAdjust.getInstance().setAdjustArm(ArmAdjust.AdjustArm.ADJUST_NONE);
    }

    public void setTravelTargetPIDPosition(){

        if(ArmAdjust.getInstance().getAdjustArm() == ArmAdjust.AdjustArm.ADJUST_DOWN){
            travelTargetPosition += 1;
        }
        else if (ArmAdjust.getInstance().getAdjustArm() == ArmAdjust.AdjustArm.ADJUST_UP){
            travelTargetPosition -= 1;
        }
        setPower(travelTargetPosition);

        ArmAdjust.getInstance().setAdjustArm(ArmAdjust.AdjustArm.ADJUST_NONE);
    }

    public void setResetTargetPIDPosition(){


        setPower(resetPosition);
        stopResetEncoder();
        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setArmDownIncrement(){
        int downIncrement = currentTargetPos + armDownIncrement;

        setPower(downIncrement);

    }

    public void setArmUpDecrement(){
        int upDecrement = currentTargetPos - armUpDecrement;

        setPower(upDecrement);

    }

    /*public void setArmAdjust(int adjust){
        armAdjust = adjust;
        telemetry.addData("armAdjust", armAdjust);
        telemetry.update();
    }*/

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

    public void setF(double f){
        ArmSubsystem.f = f;
        //setPower(0);
    }

    public void setFUp(){
        f += fIncrement;
        telemetry.addData("f update", f);

        setPower(currentTargetPos);

    }

    public void setFDown(){
        f -=  fIncrement;
        telemetry.addData("f downdate", f);

        setPower(currentTargetPos);
    }

    public void setP(double p){
        ArmSubsystem.p = p;
        setPower(0);
    }

    public void setPUp(){
        p += pIncrement;
        telemetry.addData("p update", p);

        setPower(currentTargetPos);

    }

    public void setPDown(){
        p -=  pIncrement;
        telemetry.addData("p downdate", p);

        setPower(currentTargetPos);
    }

    public void setI(double i){
        ArmSubsystem.i = i;
        setPower(0);
    }

    public void setIUp(){
        i += iIncrement;
        telemetry.addData("i update", i);

        setPower(currentTargetPos);

    }

    public void setIDown(){
        i -=  iIncrement;
        telemetry.addData("i downdate", i);

        setPower(currentTargetPos);
    }

    public void setD(double d){
        ArmSubsystem.d = d;
        setPower(0);
    }
    public void setDUp(){
        d += dIncrement;
        telemetry.addData("d update", d);
        telemetry.update();

        //setPower(currentTargetPos);

    }

    public void setDDown(){
        d -=  dIncrement;
        telemetry.addData("d downdate", d);
        telemetry.update();

        //setPower(currentTargetPos);
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


        controller.setPID(p,i,d);
        currentTargetPos = target;
        int armPos = arm.getCurrentPosition();
        double pid = controller.calculate(armPos, target);
        double ff = Math.cos( Math.toRadians( (ticksOffsetFromHorizontal - armPos) * ticks_in_degree)) * (-f);



        double power = pid + ff;



        arm.setPower(power);




        telemetry.addLine("ARM CONTROL");
        telemetry.addData("power", power);
        telemetry.addData("armPos ", armPos);
        telemetry.addData("target (position) ", target);
        telemetry.addData("test target (position) ", testTarget);
        telemetry.addData("current target (position) ", currentTargetPos);
        telemetry.addData("target angle ", target / ticks_in_degree);
        telemetry.addData("motor power ", arm.getPower());
        telemetry.addData("ff", ff);
        telemetry.addData("f", f);
        telemetry.addData("p", p);
        telemetry.addData("i", i);
        telemetry.addData("d", d);

        telemetry.addData("ArmAdjustGlobal" , ArmAdjust.getInstance().getAdjustArm());

        telemetry.update();




    }

    public void setTargetPower(double power){


        arm.setPower(power);


    }





}
