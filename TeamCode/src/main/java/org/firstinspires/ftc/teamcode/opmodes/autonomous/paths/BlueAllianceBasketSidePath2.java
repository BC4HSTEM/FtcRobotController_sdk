package org.firstinspires.ftc.teamcode.opmodes.autonomous.paths;

import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.commands.LineToYActionCommand;
import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

public class BlueAllianceBasketSidePath2 {

    private HardwareMap hwMap;
    private Pose2d startPose;

    private Telemetry telemetry;

    private Motor fl, fr, bl, br;

    private DriveSubsystem driveSubsystem;

    private MecanumDrive drive;
    public BlueAllianceBasketSidePath2(HardwareMap hwMap, Pose2d sp, Telemetry telemetry){
        this.hwMap = hwMap;
        startPose = sp;
        this.telemetry = telemetry;
        driveSubsystem = new DriveSubsystem(hwMap, startPose);
        drive = driveSubsystem.getRRDrive();

    }

    public void createPath(){
        telemetry.addLine("In create path");
        telemetry.update();


    }

    public void execute(CommandOpMode commandOpMode){

        telemetry.addLine("in execute....");

        //StrafeActionCommand strafeActionCommand = new StrafeActionCommand(driveSubsystem, startPose, new Vector2d(-38, -68), telemetry);
        LineToYActionCommand lineToYActionCommand = new LineToYActionCommand(driveSubsystem, startPose, -60.0, telemetry);

        telemetry.update();

        commandOpMode.schedule(new WaitUntilCommand(commandOpMode::isStarted).andThen(
                new SequentialCommandGroup(lineToYActionCommand)));
    }
}
