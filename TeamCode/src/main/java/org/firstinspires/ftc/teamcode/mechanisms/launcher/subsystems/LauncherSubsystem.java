package org.firstinspires.ftc.teamcode.mechanisms.launcher.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LauncherSubsystem extends SubsystemBase {

    private MotorEx launcher;
    private Telemetry telemetry;
    private double power = 0.0;

    //6. Add in the ability to throttle power @TODO

    //7. Created a runmode variable (https://docs.ftclib.org/ftclib/v/v2.0.0/features/hardware/motors#using-a-runmode)
    private Motor.RunMode mode;

    public LauncherSubsystem(MotorEx launcherMotor){
        launcher = launcherMotor;
    }
    //9. Added another constructor that takes in Telemetry
    public LauncherSubsystem(MotorEx launcherMotor, Telemetry telemetry){
        launcher = launcherMotor;
        this.telemetry = telemetry;
    }

    public void turn(double speed){
        setPower(speed);
    }

    public void setZeroPowerBehavior(Motor.ZeroPowerBehavior behavior){
        launcher.setZeroPowerBehavior(behavior);
    }

    public void setMotorInverted(boolean direction){
        launcher.setInverted(direction);
    }

    public void setPower(double power){
        this.power = power;
        launcher.set(this.power);
    }

    public double getPower(){
        return power;
    }

    public void setMode(MotorEx.RunMode mode){
        this.mode = mode;
        launcher.setRunMode(this.mode);
    }

    public void stop(){
        launcher.stopMotor();
    }

    public double getLauncherPower(){
        return launcher.motorEx.getPower();
    }

    public double getCurrentPositionLauncher() {
        return launcher.getCurrentPosition();
    }

}
