package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting2 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(90), Math.toRadians(90), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-38, -55, 0))
                        .turn(Math.toRadians(90))
                        .forward(5)
                        .turn(Math.toRadians(90))
                        .forward(10)
                        .turn(Math.toRadians(35))
                        .forward(5)
                        //put the thing down
                        .back(5)
                        .turn(Math.toRadians(-27))
                        .back(10)
                        .turn(Math.toRadians(-90))
                        //base code to start the different paths maybe ^
                        .forward(18.5)
                        .turn(Math.toRadians(30))
                        .forward(1.5)
                        /*.turn(Math.toRadians(-90))
                        .forward(50)
                        .splineTo(new Vector2d(45, 50), Math.toRadians(0))
                        .turn(Math.toRadians(-90))
                        .forward(80)
                        .splineTo(new Vector2d(-40, -26), Math.toRadians(90))
                        .turn(Math.toRadians(-90))
                        .forward(90)
                        .turn(Math.toRadians(-90))
                        .forward(60)
                        .forward(60)
                        .strafeLeft(60)
                        .back(60)
                        .strafeRight(60)
                        .forward(60)
                        .strafeLeft(60)*/
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}