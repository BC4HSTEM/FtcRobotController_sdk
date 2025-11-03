package org.firstinspires.ftc.teamcode.mechanisms.launch_pad;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.CreateMechanismBase;
import org.firstinspires.ftc.teamcode.mechanisms.launch_pad.commands.LaunchPadActivateFeeder;
import org.firstinspires.ftc.teamcode.mechanisms.launch_pad.subsystems.LaunchPadSubsystem;

//18. extend CreateMechanismBase
public class CreateLaunchPadMechanism extends CreateMechanismBase {

    //19. Define the subsystem for the mechanism
    private LaunchPadSubsystem launchPadSubsystem;

    //20. Define the Servo coming from the hardware map
    private CRServo feederRight;
    private CRServo feederLeft;

    //21. Define your Commands
    private LaunchPadActivateFeeder launchPadActivateFeeder;

    //23. Define your constructors
    public CreateLaunchPadMechanism(HardwareMap hwMap, String deviceName, GamepadEx op, Telemetry telemetry){
        super(hwMap, deviceName, op, telemetry);
    }

    //24. Constructor for Teleop .... notice the GamepdEx variable
    public CreateLaunchPadMechanism(HardwareMap hwMap, String deviceName, GamepadEx op, Telemetry telemetry, boolean autoCreate){
        super(hwMap, deviceName, op, telemetry, autoCreate);
    }

    //25. Constructor for Autonomous .... notice there is no GamePadEx variable
    public CreateLaunchPadMechanism(final HardwareMap hwMap, final String deviceName, Telemetry telemetry){
        super(hwMap, deviceName, telemetry);
    }

    @Override
    public void create(){
        //26. Create the subsystem and commands
        createBase();

        //30. determine which button you want to use
        //31. assign the command to the appropriate button action https://docs.ftclib.org/ftclib/v/v2.0.0/command-base/command-system/binding-commands-to-triggers
        op.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).toggleWhenPressed(launchPadActivateFeeder);

    }

    @Override
    public void createBase(){
        //27. get the servo from the hardware
        feederRight = new CRServo(hwMap, "grabber_right"); // @TODO rename feeder_right
        feederLeft = new CRServo(hwMap, "grabber_left"); // @TODO rename feeder_left
        feederLeft.setInverted(true); // @TODO Check which side should be inverted, probably left

        launchPadSubsystem = new LaunchPadSubsystem(feederRight, feederLeft, telemetry, true);

        //29. Create the commands, used functions so that autonomous would have less work to do when
        launchPadActivateFeeder = createLaunchPadActivateFeeder();

    }

    // Create Commands
    public LaunchPadActivateFeeder createLaunchPadActivateFeeder(){
        return new LaunchPadActivateFeeder(launchPadSubsystem, telemetry);
    }


}