package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

@Config
@Autonomous(name = "BLUE_TEST_AUTO_I2DEEP", group = "Autonomous")
public class AutoTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        final Pose2d path1StartPose = new Pose2d(-38, -55, Math.toRadians(0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, path1StartPose);

        TrajectoryActionBuilder tab1 = drive.actionBuilder(path1StartPose)
                .lineToX(35)
                .waitSeconds(2);

        while (!isStopRequested() && !opModeIsActive()) {
            int position = 1;
            telemetry.addData("Position during Init", position);
            telemetry.update();
        }

        waitForStart();
        Action trajectoryActionChosen;

        trajectoryActionChosen = tab1.build();

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionChosen

                )
        );






    }
}
