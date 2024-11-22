package org.firstinspires.ftc.teamcode.mechanisms.grabber.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.grabber.subsystems.GrabberSubsystem;
import org.firstinspires.ftc.teamcode.mechanisms.grabber.subsystems.GrabberSubsystem;

public class GrabberLeftCloseCommand extends CommandBase{
    private GrabberSubsystem grabberSubsystem;
    private Telemetry telemetry;

    //private double position = 0.5;

    public GrabberLeftCloseCommand(GrabberSubsystem grabberSubsystem){
        this.grabberSubsystem = grabberSubsystem;

        addRequirements(grabberSubsystem);
    }

    public GrabberLeftCloseCommand(GrabberSubsystem grabberSubsystem, Telemetry telemetry){
        this.grabberSubsystem = grabberSubsystem;
        this.telemetry = telemetry;

        addRequirements(grabberSubsystem);
    }

    @Override
    public void initialize(){
        telemetry.addLine("grabber left close initialize");
        //telemetry.addData("grabber position", grabberSubsystem.getPosition());
        //telemetry.addData("grabber close position", grabberSubsystem.getClosePosition());
        telemetry.update();
        grabberSubsystem.closeLeftGrabberPosition();
    }

    @Override
    public void execute(){
        telemetry.addData("grabber left position", grabberSubsystem.getGrabberLeftPosition());
        telemetry.addData("grabber left close position", grabberSubsystem.getLeftClosePosition());
        telemetry.update();
    }


    /*@Override
    public boolean isFinished(){
        return grabberSubsystem.getPosition() >= grabberSubsystem.getClosePosition();
    }*/

}