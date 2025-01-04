package org.firstinspires.ftc.teamcode.mechanisms.elevator;

import org.firstinspires.ftc.teamcode.mechanisms.CreateMechanismBase;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.LEFT_TRIGGER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.RIGHT_TRIGGER;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.elevator.commands.ElevatorDownCommand;
import org.firstinspires.ftc.teamcode.mechanisms.elevator.commands.ElevatorUpCommand;
import org.firstinspires.ftc.teamcode.mechanisms.elevator.subsystems.ElevatorSubsystem;

import java.util.function.DoubleSupplier;

public class CreateElevatorMechanism extends CreateMechanismBase {

    private ElevatorSubsystem elevatorSubsystem;
    private ElevatorUpCommand elevatorUpCommand;
    private ElevatorDownCommand elevatorDownCommand;
    //27. Changed DcMotor to MotorEx to match our subsystem implementation
    private MotorEx ER;
    private MotorEx EL;

    private final String rDeviceName = "rLift";
    private final String lDeviceName = "lLift";

    private DoubleSupplier liftDownDS;
    private DoubleSupplier liftUpDS;

    //29. Tyler created the constructors based on the CreateMechanismBase
    //what's special about the CreateMechanism

    public CreateElevatorMechanism(HardwareMap hwMap, String deviceName, GamepadEx op, Telemetry telemetry){
        super(hwMap, deviceName, op, telemetry);
    }

    //30. autoCreate triggers a method in the base class to tell the mechanism to go ahead and create the subsystem
    //and map the commands to robot (controller, camera, etc)
    //there are times where you want to delay creating the mechanism until it's actually needed
    //most times we just use the autoCreate option
    public CreateElevatorMechanism(HardwareMap hwMap, String deviceName, GamepadEx op, Telemetry telemetry, boolean autoCreate){
        super(hwMap, deviceName, op, telemetry, autoCreate);
    }

    public CreateElevatorMechanism(final HardwareMap hwMap, final String deviceName, Telemetry telemetry){
        super(hwMap, deviceName, telemetry);

    }

    @Override
    public void create(){

        liftDownDS = (() -> op.getTrigger(LEFT_TRIGGER));
        liftUpDS = (() -> op.getTrigger(RIGHT_TRIGGER));


        createBase();


        elevatorSubsystem.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);


        elevatorUpCommand = createElevatorUpCommand();
        elevatorDownCommand = createElevatorDownCommand();


        Trigger lTrigger = new Trigger(() -> op.getTrigger(LEFT_TRIGGER) > 0.5);
        Trigger rTrigger = new Trigger(() -> op.getTrigger(RIGHT_TRIGGER) > 0.5);


        rTrigger.whenActive(elevatorUpCommand);
        lTrigger.whenActive(elevatorDownCommand);


    }

    @Override
    public void createBase(){
        telemetry.addLine("Lift createBase");
        //34. Create the motor
        //Make sure the device name from the OpMode matches what's in the hardware map
        //the GoBilda part of this may not be needed based on the attached motor
        //FtcLib has special features for GoBuilda motors
        ER = new MotorEx(hwMap, rDeviceName, Motor.GoBILDA.RPM_30);
        EL = new MotorEx(hwMap, lDeviceName, Motor.GoBILDA.RPM_30);
        telemetry.addData("Ri", ER);
        telemetry.addData("Li", EL);
        telemetry.update();

        //35. create the subsystem, given that telemetry is used everywhere, use the constructor that sets it
        elevatorSubsystem = new ElevatorSubsystem(ER, EL, telemetry);
        //36. set what the motor would do when it has no power
        elevatorSubsystem.setZeroPowerBehavoir(DcMotorEx.ZeroPowerBehavior.BRAKE);
        //37.not using encoders at this point but recommend stopping them
        elevatorSubsystem.stopResetEncoder();
        //38. set the direction of the motor, ideally this is tested while not on the lift
        elevatorSubsystem.setDirection(DcMotorEx.Direction.FORWARD, DcMotorEx.Direction.REVERSE);

    }

    public ElevatorUpCommand createElevatorUpCommand(){
        return new ElevatorUpCommand(elevatorSubsystem, liftUpDS, telemetry);
    }

    public ElevatorDownCommand createElevatorDownCommand(){
        return new ElevatorDownCommand(elevatorSubsystem, liftDownDS, telemetry);
    }

    public ElevatorSubsystem getElevatorSubsystem() {
        return elevatorSubsystem;
    }

}
