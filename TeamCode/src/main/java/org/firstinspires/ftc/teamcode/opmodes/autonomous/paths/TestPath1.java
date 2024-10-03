package org.firstinspires.ftc.teamcode.opmodes.autonomous.paths;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.commands.SequentialActionCommand;
import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

public class TestPath1 {

    private HardwareMap hwMap;
    private Pose2d startPose;

    private Telemetry telemetry;

    private Motor fl, fr, bl, br;

    private DriveSubsystem driveSubsystem;

    private MecanumDrive drive;
    public TestPath1(HardwareMap hwMap, Pose2d sp, Telemetry telemetry){
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

        telemetry.addLine("execute....");


        TrajectoryActionBuilder tab1 = drive.actionBuilder(startPose)
                .lineToY(30);
        Action action1  = tab1.build();
        SequentialActionCommand seqCommand1 = new SequentialActionCommand(action1);

        telemetry.addLine("run blocking....");
            Actions.runBlocking(
                    new SequentialAction(
                            action1
                    )
            );

        telemetry.update();


        commandOpMode.schedule(new WaitUntilCommand(commandOpMode::isStarted).andThen(
                new InstantCommand(()->{
                    Actions.runBlocking(
                            new SequentialAction(
                                    action1
                            )
                    );
                })
        ));
    }
}
