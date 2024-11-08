package org.firstinspires.ftc.teamcode.mechanisms.drivetrain.subsystems;

import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem extends SubsystemBase {
    private HardwareMap hardwareMap;
    private MecanumDrive drive;
    private org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive rrDrive;
    private Motor fl, fr, bl, br;

    private Pose2d initialPose;

    public DriveSubsystem(Motor frontL, Motor frontR, Motor backL, Motor backR){
                    fl = frontL;
                    fr = frontR;
                    bl = backL;
                    br = backR;
                    drive = new MecanumDrive(fl, fr, bl, br);
    }

    public DriveSubsystem(HardwareMap hardwareMap, Pose2d initialPose){
        this.rrDrive =  new org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive(hardwareMap, initialPose);
    }


    public void drive(double strafe, double forward, double turn){

            drive.driveRobotCentric(-strafe, forward, -turn); //Control of the robot
    }

    public Motor getFl(){
        return fl;
    }
    public Motor getFr(){
        return fr;
    }
    public Motor getBl(){
        return bl;
    }
    public Motor getBr(){
        return br;
    }

    public org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive getRRDrive() {
        return rrDrive;
    }


}
