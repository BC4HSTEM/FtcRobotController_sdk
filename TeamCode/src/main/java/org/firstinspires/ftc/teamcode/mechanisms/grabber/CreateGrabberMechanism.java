package org.firstinspires.ftc.teamcode.mechanisms.grabber;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.CreateMechanismBase;
import org.firstinspires.ftc.teamcode.mechanisms.grabber.commands.GrabberCloseCommand;
import org.firstinspires.ftc.teamcode.mechanisms.grabber.commands.GrabberOpenCommand;
import org.firstinspires.ftc.teamcode.mechanisms.grabber.subsystems.GrabberSubsystem;

//18. extend CreateMechanismBase
public class CreateGrabberMechanism extends CreateMechanismBase {
    //19. Define the subsystem for the mechanism
    private GrabberSubsystem grabberSubsystem;

    //20. Define the Servo coming from the hardware map
    private ServoEx grabberRight;
    private ServoEx grabberLeft;

    //21. Define your Commands
    private GrabberOpenCommand grabberOpenCommand;
    private GrabberCloseCommand grabberCloseCommand;

    //22. Is this a 180 or 360 servo, define your max and min
    private int MIN_ANGLE = 0;
    private int MAX_ANGLE = 180;

    //23. Define your constructors
    public CreateGrabberMechanism(HardwareMap hwMap, String deviceName, GamepadEx op, Telemetry telemetry){
        super(hwMap, deviceName, op, telemetry);

    }

    //24. Constructor for Teleop .... notice the GamepdEx variable
    public CreateGrabberMechanism(HardwareMap hwMap, String deviceName, GamepadEx op, Telemetry telemetry, boolean autoCreate){
        super(hwMap, deviceName, op, telemetry, autoCreate);

    }

    //25. Constructor for Autonomous .... notice there is no GamePadEx variable
    public CreateGrabberMechanism(final HardwareMap hwMap, final String deviceName, Telemetry telemetry){
        super(hwMap, deviceName, telemetry);

    }

    @Override
    public void create(){
        //26. Create the subsystem and commands
        createBase();

        //30. determine which button you want to use
        //31. assign the command to the appropriate button action https://docs.ftclib.org/ftclib/v/v2.0.0/command-base/command-system/binding-commands-to-triggers
        op.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).toggleWhenPressed(grabberCloseCommand,grabberOpenCommand);

    }

    @Override
    public void createBase(){
        //27. get the servo from the hardware
        grabberRight = new SimpleServo(hwMap, "grabber_right", MIN_ANGLE, MAX_ANGLE);
        grabberLeft = new SimpleServo(hwMap, "grabber_left", MIN_ANGLE, MAX_ANGLE);

        grabberLeft.setInverted(true);
        //28. create the subsystem
        grabberSubsystem = new GrabberSubsystem(grabberRight, grabberLeft, telemetry, true);

        //29. Create the commands, used functions so that autonomous would have less work to do when
        grabberOpenCommand = createGrabberOpenCommand();
        grabberCloseCommand = createGrabberCloseCommand();

    }


    public GrabberOpenCommand createGrabberOpenCommand(){
        return new GrabberOpenCommand(grabberSubsystem, telemetry);
    }
    public GrabberCloseCommand createGrabberCloseCommand(){
        return new GrabberCloseCommand(grabberSubsystem, telemetry);
    }

    public GrabberCloseCommand getGrabberCloseCommand(){
        return grabberCloseCommand;
    }

    public GrabberOpenCommand getGrabberOpenCommand(){
        return grabberOpenCommand;
    }


}