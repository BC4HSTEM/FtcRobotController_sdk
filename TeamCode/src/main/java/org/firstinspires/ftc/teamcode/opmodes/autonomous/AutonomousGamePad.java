package org.firstinspires.ftc.teamcode.opmodes.autonomous;


import com.acmerobotics.dashboard.FtcDashboard;

import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.globals.Alliance;

import org.firstinspires.ftc.teamcode.globals.Path;
import org.firstinspires.ftc.teamcode.globals.Side;


import org.firstinspires.ftc.teamcode.opmodes.autonomous.paths.BlueAllianceBasketSidePath2;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.paths.BlueAllianceBasketSidePath1;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.paths.BlueAllianceObservationSidePath1;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.paths.RedAllianceBasketSidePath1;
import org.firstinspires.ftc.teamcode.opmodes.autonomous.paths.RedAllianceObservationSidePath1;


@Autonomous(name="Auto Gamepad", group="InToTheDeep")
public class AutonomousGamePad extends CommandOpMode {
    //final String[] selectedAlliance = new String[1];

    //final String[] selectedSide = new String[1];
    //final String[] selectedPath = new String[1];


    final Pose2d path1BaseketStartPose = new Pose2d(-38, -55, Math.toRadians(0));
    final Pose2d path1ObservationStartPose = new Pose2d(5, -55, Math.toRadians(0));

    final Pose2d path2BasketStartPose = new Pose2d(-38, -55, Math.toRadians(0));

    final Pose2d path2ObservationStartPose = new Pose2d(5, -55, Math.toRadians(0));


    private Pose2d selectedStartPos = new Pose2d(0,0,Math.toRadians(270));

    private SequentialCommandGroup webCamGroup;






    //private final Vector2d[] finalPosition = new Vector2d[1];

    @Override
    public void initialize() {
        FtcDashboard dashboard = FtcDashboard.getInstance();



        //Must reset static Positions global

        GamepadEx driver1 = new GamepadEx(gamepad1);

        showOptions(true);

        configureAllianceButtons(driver1);
        configureSelectSideButtons(driver1);
        configureSelectPathButtons(driver1);
        configureExecutePath(driver1);





        while (!this.isStarted()){

            CommandScheduler.getInstance().run();
        }
    }


    private void configureAllianceButtons(GamepadEx op){

        op.getGamepadButton(GamepadKeys.Button.X).whenPressed(()->{
            //selectedAlliance[0] = "Blue";
            Alliance.getInstance().setAllianceTeam(Alliance.AllianceTeam.BLUE);
            //createLEDs.createAuto();
            showSelection();
        });

        op.getGamepadButton(GamepadKeys.Button.B).whenPressed(()->{
            //selectedAlliance[0] = "Red";
            Alliance.getInstance().setAllianceTeam(Alliance.AllianceTeam.RED);
            //createLEDs.createAuto();
            showSelection();
        });


    }

    private void configureSelectSideButtons(GamepadEx op){



        InstantCommand yBind = new InstantCommand(()->{
            //selectedSide[0] = "Blue";
            Side.getInstance().setPositionSide(Side.PositionSide.BASKETS_SIDE);
            showSelection();

        });

        InstantCommand aBind = new InstantCommand(()->{
            //selectedSide[0] = "Red";
            Side.getInstance().setPositionSide(Side.PositionSide.OBSERVATION_ZONE_SIDE);
            showSelection();

        });

        op.getGamepadButton(GamepadKeys.Button.Y).whenPressed(yBind).whenReleased(yBind::cancel);

        op.getGamepadButton(GamepadKeys.Button.A).whenPressed(aBind).whenReleased(aBind::cancel);


    }

    private void configureSelectPathButtons(GamepadEx op){


        op.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(()->{
            //selectedPath[0] = "Path 1";
            Path.getInstance().setSelectedPathToFollow(Path.PositionToFollow.PATH_1);
            showSelection();
        });

        op.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(()->{
            //selectedPath[0] = "Path 2";
            Path.getInstance().setSelectedPathToFollow(Path.PositionToFollow.PATH_2);
            showSelection();
        });

        op.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(()->{
            //selectedPath[0] = "Path 3";
            Path.getInstance().setSelectedPathToFollow(Path.PositionToFollow.PATH_3);
            showSelection();
        });

        op.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(()->{
            //selectedPath[0] = "Path 4";
            Path.getInstance().setSelectedPathToFollow(Path.PositionToFollow.PATH_4);
            showSelection();
        });
    }

    private void configureExecutePath(GamepadEx op){
        op.getGamepadButton(GamepadKeys.Button.START).whenPressed(()->{


            telemetry.addLine("Path Executing.....");


            executePath();


        });

    }

    private void executePath(){


        if (Side.getInstance().getPositionSide() == Side.PositionSide.BASKETS_SIDE){
            if (Alliance.getInstance().getAllianceTeam() == Alliance.AllianceTeam.BLUE) {
                selectedStartPos = path1BaseketStartPose;
            } else if (Alliance.getInstance().getAllianceTeam() == Alliance.AllianceTeam.RED) {
                selectedStartPos = path1BaseketStartPose;
            }
        }
        else if(Side.getInstance().getPositionSide() == Side.PositionSide.OBSERVATION_ZONE_SIDE){
            if (Alliance.getInstance().getAllianceTeam() == Alliance.AllianceTeam.BLUE) {
                selectedStartPos = path1ObservationStartPose;
            } else if (Alliance.getInstance().getAllianceTeam() == Alliance.AllianceTeam.RED) {
                selectedStartPos = path1ObservationStartPose;
            }
        }

        //telemetry.clearAll();
        telemetry.addData("Selections Complete", String.format("Alliance: %s - Side: %s - Path: %s - Start Position: %s - TGE Side: %s",
                Alliance.getInstance().getAllianceTeam().toString(),
                Side.getInstance().getPositionSide().toString(),
                Path.getInstance().getSelectedPathToFollow(),selectedStartPos, "here"));
        telemetry.update();



        if (Path.getInstance().getSelectedPathToFollow() == Path.PositionToFollow.PATH_1) {
            Path.getInstance().setSelectedPathToFollow(Path.PositionToFollow.PATH_1);
            if (Alliance.getInstance().getAllianceTeam() == Alliance.AllianceTeam.BLUE && Side.getInstance().getPositionSide() == Side.PositionSide.BASKETS_SIDE) {
                BlueAllianceBasketSidePath1 bsPath1 = new BlueAllianceBasketSidePath1(hardwareMap, selectedStartPos, telemetry);
                bsPath1.createPath();
                bsPath1.execute(this);

            } else if(Alliance.getInstance().getAllianceTeam() == Alliance.AllianceTeam.RED && Side.getInstance().getPositionSide() == Side.PositionSide.BASKETS_SIDE){

                RedAllianceBasketSidePath1 rsPath1 = new RedAllianceBasketSidePath1(hardwareMap, selectedStartPos, telemetry);
                rsPath1.createPath();
                rsPath1.execute(this);
            }
            else if(Alliance.getInstance().getAllianceTeam() == Alliance.AllianceTeam.BLUE && Side.getInstance().getPositionSide() == Side.PositionSide.OBSERVATION_ZONE_SIDE)
            {
                BlueAllianceObservationSidePath1 bsosPath1 = new BlueAllianceObservationSidePath1(hardwareMap, selectedStartPos, telemetry);
                bsosPath1.createPath();
                bsosPath1.execute(this);
            }
            else if(Alliance.getInstance().getAllianceTeam() == Alliance.AllianceTeam.RED && Side.getInstance().getPositionSide() == Side.PositionSide.OBSERVATION_ZONE_SIDE){
                RedAllianceObservationSidePath1 rsosPath1 = new RedAllianceObservationSidePath1(hardwareMap, selectedStartPos, telemetry);
                rsosPath1.createPath();
                rsosPath1.execute(this);
            }
        }

        if (Path.getInstance().getSelectedPathToFollow() == Path.PositionToFollow.PATH_2) {
            Path.getInstance().setSelectedPathToFollow(Path.PositionToFollow.PATH_2);
            if (Alliance.getInstance().getAllianceTeam() == Alliance.AllianceTeam.BLUE && Side.getInstance().getPositionSide() == Side.PositionSide.BASKETS_SIDE) {
                //BlueAllianceStageSidePath2 bsPath2 = new BlueAllianceStageSidePath2(hardwareMap, selectedStartPos, telemetry);
                //bsPath2.createPath();
                //bsPath2.execute(this);

                BlueAllianceBasketSidePath2 bbsbPath2 = new BlueAllianceBasketSidePath2(hardwareMap, selectedStartPos, telemetry);
                bbsbPath2.createPath();
                bbsbPath2.execute(this);

            } else if(Alliance.getInstance().getAllianceTeam() == Alliance.AllianceTeam.RED && Side.getInstance().getPositionSide() == Side.PositionSide.BASKETS_SIDE){

                //RedAllianceStageSidePath2 rsPath2 = new RedAllianceStageSidePath2(hardwareMap, createPositionIdentifierMechanism,selectedStartPos, telemetry);
                //rsPath2.createPath();
                //rsPath2.execute(this);
            }
            else if(Alliance.getInstance().getAllianceTeam() == Alliance.AllianceTeam.BLUE && Side.getInstance().getPositionSide() == Side.PositionSide.OBSERVATION_ZONE_SIDE){
                //BlueAllianceNonStageSidePath2 bnsPath2 = new BlueAllianceNonStageSidePath2(hardwareMap, createPositionIdentifierMechanism,selectedStartPos, telemetry);
                //bnsPath2.createPath();
                //bnsPath2.execute(this);
            }
            else if(Alliance.getInstance().getAllianceTeam() == Alliance.AllianceTeam.RED && Side.getInstance().getPositionSide() == Side.PositionSide.OBSERVATION_ZONE_SIDE){
                //RedAllianceNonStageSidePath2 rnsPath2 = new RedAllianceNonStageSidePath2 (hardwareMap, createPositionIdentifierMechanism,selectedStartPos, telemetry);
                //rnsPath2.createPath();
                //rnsPath2.execute(this);
            }
        }

        if (Path.getInstance().getSelectedPathToFollow() == Path.PositionToFollow.PATH_3) {}

        if (Path.getInstance().getSelectedPathToFollow() == Path.PositionToFollow.PATH_4) {}
    }

    private void showOptions(boolean update){

        telemetry.addLine("Press (X) for BLUE Alliance, (B) for RED Alliance");
        telemetry.addLine(String.format("Press (Y) for %s, (A) for %s", Side.getInstance().getLeftSideFieldName(), Side.getInstance().getRightSideFieldName()));
        telemetry.addLine("Press (^) for Path 1");
        telemetry.addLine("Press (>) for Path 2");
        telemetry.addLine("Press (v) for Path 3");
        telemetry.addLine("Press (<) for Path 4");


        if(update){
            telemetry.update();
        }
    }

    private void showSelection(){
        showOptions(false);
        telemetry.addData("Selections ", String.format("Alliance: %s - Side: %s - Path: %s - Position: %s",Alliance.getInstance().getAllianceTeam().toString(),
                Side.getInstance().getPositionSide().toString(),
                Path.getInstance().getSelectedPathToFollow(),selectedStartPos));


        telemetry.update();

    }

}
