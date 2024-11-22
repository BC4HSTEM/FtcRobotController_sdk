package org.firstinspires.ftc.teamcode.mechanisms.grabber_wrist;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.CreateMechanismBase;

import org.firstinspires.ftc.teamcode.mechanisms.grabber_wrist.commands.GrabberWristDownCommand;
import org.firstinspires.ftc.teamcode.mechanisms.grabber_wrist.commands.GrabberWristUpCommand;
import org.firstinspires.ftc.teamcode.mechanisms.grabber_wrist.subsystems.GrabberWristSubsystem;

public class CreateGrabberWristMechanism extends CreateMechanismBase {

    private GrabberWristSubsystem grabberWristSubsystem;
    private ServoEx grabberWrist;

    private GrabberWristDownCommand grabberWristDownCommand;
    private GrabberWristUpCommand grabberWristUpCommand;

    private int MIN_ANGLE = 0;
    private int MAX_ANGLE = 180;

    public CreateGrabberWristMechanism(HardwareMap hwMap, String deviceName, GamepadEx op, Telemetry telemetry){
        super(hwMap, deviceName, op, telemetry);

    }

    //24. Constructor for Teleop .... notice the GamepdEx variable
    public CreateGrabberWristMechanism(HardwareMap hwMap, String deviceName, GamepadEx op, Telemetry telemetry, boolean autoCreate){
        super(hwMap, deviceName, op, telemetry, autoCreate);

    }

    //25. Constructor for Autonomous .... notice there is no GamePadEx variable
    public CreateGrabberWristMechanism(final HardwareMap hwMap, final String deviceName, Telemetry telemetry){
        super(hwMap, deviceName, telemetry);

    }

    @Override
    public void create(){
        //26. Create the subsystem and commands
        createBase();



        op.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenReleased(grabberWristUpCommand);
        op.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenHeld(grabberWristDownCommand);


    }

    @Override
    public void createBase(){
        //27. get the servo from the hardware
        grabberWrist = new SimpleServo(hwMap, deviceName, MIN_ANGLE, MAX_ANGLE);


        //28. create the subsystem
        grabberWristSubsystem = new GrabberWristSubsystem(grabberWrist, telemetry, true);
        //grabberWristSubsystem.setInverted(true);

        //29. Create the commands, used functions so that autonomous would have less work to do when
        //creating the commands for that opmode
        grabberWristDownCommand = createGrabberWristDownCommand();
        grabberWristUpCommand = createGrabberWristUpCommand();

    }

    public GrabberWristDownCommand createGrabberWristDownCommand(){

        return new GrabberWristDownCommand(grabberWristSubsystem, telemetry);
    }

    public GrabberWristUpCommand createGrabberWristUpCommand(){

        return new GrabberWristUpCommand(grabberWristSubsystem, telemetry);
    }
}
