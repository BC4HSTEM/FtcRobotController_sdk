package org.firstinspires.ftc.teamcode.mechanisms.elevator.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ElevatorSubsystem extends SubsystemBase {

    private MotorEx ER;
    private MotorEx EL;

    private Telemetry telemetry;
    private DcMotorEx.Direction direction;
    private double power = 0.0;
    //6. Added in the ability to throttle power
    private double powerRatio = 0.75;
    //7. Created a runmode variable (https://docs.ftclib.org/ftclib/v/v2.0.0/features/hardware/motors#using-a-runmode)
    private DcMotorEx.RunMode mode;

    private static final int MIN_TARGET_POSITION = 0;
    private int maxTargetPosition = 4250;

    public ElevatorSubsystem(MotorEx ERMotor, MotorEx ELMotor  ){
        ER = ERMotor;
        EL = ELMotor;
    }
    //9. Added another constructor that takes in Telemetry
    public ElevatorSubsystem(MotorEx ERMotor, MotorEx ELMotor, Telemetry telemetry){

        ER = ERMotor;
        EL = ELMotor;
        this.telemetry = telemetry;
    }

    public void turn(double speed){
        setPower(speed*powerRatio);
    }
    public void stopResetEncoder(){
        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void setZeroPowerBehavoir(DcMotorEx.ZeroPowerBehavior behavior){
        ER.motorEx.setZeroPowerBehavior(behavior);
        EL.motorEx.setZeroPowerBehavior(behavior);
    }

    public void setDirection(DcMotorEx.Direction rDirection, DcMotorEx.Direction lDirection){
        //this.direction = direction;
        ER.motor.setDirection(rDirection);
        EL.motor.setDirection(lDirection);
    }

    public void setPower(double power){
        this.power = power;
        ER.set(this.power);
        EL.set(this.power);
    }

    public double getPower(){
        return power;
    }

    public void setMode(DcMotorEx.RunMode mode){
        this.mode = mode;
        ER.motorEx.setMode(this.mode);
        EL.motorEx.setMode(this.mode);
    }

    public void setBrake(){
        setZeroPowerBehavoir(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void stop(){
        ER.stopMotor();
        EL.stopMotor();
    }

    public void setInverted(boolean inv){
        ER.setInverted(inv);
        EL.setInverted(inv);
    }


    public double getERPower(){
        return ER.motorEx.getPower();
    }
    public double getELPower(){
        return EL.motorEx.getPower();
    }

    public DcMotorSimple.Direction getERDirection(){
        return ER.motorEx.getDirection();
    }

    public DcMotorSimple.Direction getDirection(){
        return ER.motorEx.getDirection();
    }
}
