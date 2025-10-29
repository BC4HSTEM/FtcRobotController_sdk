package org.firstinspires.ftc.teamcode.mechanisms.elevator.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.elevator.subsystems.ElevatorSubsystem;

import java.util.function.DoubleSupplier;

public class ElevatorDownCommand extends CommandBase {

    private ElevatorSubsystem elevatorSubsystem;
    private DoubleSupplier power;
    private Telemetry telemetry;

    public ElevatorDownCommand(ElevatorSubsystem elevatorSubsystem, DoubleSupplier power){
        this.elevatorSubsystem = elevatorSubsystem;
        this.power = power;

        addRequirements(elevatorSubsystem);
    }

    public ElevatorDownCommand(ElevatorSubsystem elevatorSubsystem, DoubleSupplier power, Telemetry telemetry){
        this.elevatorSubsystem = elevatorSubsystem;
        this.power = power;
        this.telemetry = telemetry;

        addRequirements(elevatorSubsystem);
    }

    @Override
    public void execute(){

        telemetry.addLine("LiftDown Executing");
        telemetry.update();
        elevatorSubsystem.turn(-power.getAsDouble());
        telemetry.addData("Motor Power", elevatorSubsystem.getPower());
        //telemetry.addData("Motor Min Level in Set Lift Left Cmd", elevatorSubsystem.getMinTargetPosition());
        telemetry.addData("Motor Current Level in Set Lift Left Cmd", elevatorSubsystem.getCurrentPositionER());
        telemetry.addData("Motor Current Level in Set Lift Left Cmd", elevatorSubsystem.getCurrentPositionEL());

        telemetry.update();
    }

    @Override
    public void end(boolean interupt){

        elevatorSubsystem.stop();

    }
}
