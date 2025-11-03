package org.firstinspires.ftc.teamcode.opmodes.autonomous.paths;

import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.commands.LineToXActionCommand;
import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.commands.WaitActionCommand;
import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

public class RedAllianceBasketSidePath1 {

    private HardwareMap hwMap;
    private Pose2d startPose;

    private Telemetry telemetry;

    private Motor fl, fr, bl, br;

    private DriveSubsystem driveSubsystem;

    private MecanumDrive drive;
    public RedAllianceBasketSidePath1(HardwareMap hwMap, Pose2d sp, Telemetry telemetry){
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

        LineToXActionCommand lineToXActionCommand = new LineToXActionCommand(driveSubsystem, startPose, 23.0, telemetry);

        WaitActionCommand waitActionCommand = new WaitActionCommand(20);

        telemetry.update();


        commandOpMode.schedule(new WaitUntilCommand(commandOpMode::isStarted).andThen(
                new SequentialCommandGroup(
                        waitActionCommand,
                        lineToXActionCommand
                )));
    }
}
