package org.firstinspires.ftc.teamcode.opmodes.autonomous.paths;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.arm.CreateArmMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDownCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmTravelCommand;
import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.commands.LineToXActionCommand;
import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.commands.SequentialActionCommand;
import org.firstinspires.ftc.teamcode.mechanisms.drivetrain.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.mechanisms.grabber.CreateGrabberMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.grabber.commands.GrabberCloseCommand;
import org.firstinspires.ftc.teamcode.mechanisms.grabber.commands.GrabberOpenCommand;
import org.firstinspires.ftc.teamcode.mechanisms.grabber_wrist.CreateGrabberWristMechanism;
import org.firstinspires.ftc.teamcode.mechanisms.grabber_wrist.commands.GrabberWristUpCommand;
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

        telemetry.addLine("in execute....");


        TrajectoryActionBuilder tab1 = drive.actionBuilder(startPose)
                .lineToX(-9);
        Action action1  = tab1.build();
        //SequentialActionCommand seqActionCommand1 = new SequentialActionCommand(action1);

        LineToXActionCommand lineToXActionCommand = new LineToXActionCommand(driveSubsystem, startPose, -9.0, telemetry);

        CreateGrabberMechanism createGrabber = new CreateGrabberMechanism(hwMap, "grab", telemetry);
        createGrabber.createBase();

        CreateArmMechanism createArmMechanism = new CreateArmMechanism(hwMap, "arm", telemetry);
        createArmMechanism.createBase();

        CreateGrabberWristMechanism createGrabberWristMechanism = new CreateGrabberWristMechanism(hwMap, "wrist_Motion", telemetry);
        createGrabberWristMechanism.createBase();


        GrabberCloseCommand grabberCloseCommand = createGrabber.getGrabberCloseCommand();
        GrabberOpenCommand grabberOpenCommand = createGrabber.getGrabberOpenCommand();

        ArmDownCommand armDownCommand = createArmMechanism.createArmDownCommand();
        ArmTravelCommand armTravelCommand = createArmMechanism.createArmTravelCommand();

        GrabberWristUpCommand grabberWristUpCommand = createGrabberWristMechanism.createGrabberWristUpCommand();


        telemetry.update();


        commandOpMode.schedule(new WaitUntilCommand(commandOpMode::isStarted).andThen(
                new SequentialCommandGroup(lineToXActionCommand,grabberOpenCommand,grabberWristUpCommand, armTravelCommand,grabberCloseCommand),armDownCommand));
    }
}
