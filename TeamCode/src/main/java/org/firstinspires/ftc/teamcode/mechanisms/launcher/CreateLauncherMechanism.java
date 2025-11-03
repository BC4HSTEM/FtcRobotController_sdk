package org.firstinspires.ftc.teamcode.mechanisms.launcher;

import org.firstinspires.ftc.teamcode.mechanisms.CreateMechanismBase;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.RIGHT_TRIGGER;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.launcher.commands.StartLaunchCommand;
import org.firstinspires.ftc.teamcode.mechanisms.launcher.subsystems.LauncherSubsystem;

import java.util.function.DoubleSupplier;

public class CreateLauncherMechanism extends CreateMechanismBase {

    private LauncherSubsystem launcherSubsystem;
    private StartLaunchCommand startLaunchCommand;

    // 27. Changed DcMotor to MotorEx to match our subsystem implementation
    private MotorEx LAUNCHER_MOTOR;

    private final String launcherDeviceName = "launcher";

    private DoubleSupplier controlLaunchDS;

    //29. Tyler created the constructors based on the CreateMechanismBase
    //what's special about the CreateMechanism

    public CreateLauncherMechanism(HardwareMap hwMap, String launcherDeviceName, GamepadEx op, Telemetry telemetry){
        super(hwMap, launcherDeviceName, op, telemetry);
    }

    //30. autoCreate triggers a method in the base class to tell the mechanism to go ahead and create the subsystem
    //and map the commands to robot (controller, camera, etc)
    //there are times where you want to delay creating the mechanism until it's actually needed
    //most times we just use the autoCreate option
    public CreateLauncherMechanism(HardwareMap hwMap, String deviceName, GamepadEx op, Telemetry telemetry, boolean autoCreate){
        super(hwMap, deviceName, op, telemetry, autoCreate);
    }

    public CreateLauncherMechanism(final HardwareMap hwMap, final String deviceName, Telemetry telemetry){
        super(hwMap, deviceName, telemetry);
    }

    @Override
    public void create(){

        controlLaunchDS = (() -> op.getTrigger(RIGHT_TRIGGER));

        createBase();

        launcherSubsystem.setMode(Motor.RunMode.VelocityControl);

        Trigger rTrigger = new Trigger(() -> op.getTrigger(RIGHT_TRIGGER) > 0.5);
        rTrigger.whenActive(startLaunchCommand);

    }

    @Override
    public void createBase(){
        telemetry.addLine("Launcher createBase");
        //34. Create the motor
        // Make sure the device name from the OpMode matches what's in the hardware map
        // the GoBilda part of this may not be needed based on the attached motor
        // FtcLib has special features for GoBuilda motors
        LAUNCHER_MOTOR = new MotorEx(hwMap, launcherDeviceName, Motor.GoBILDA.RPM_312);
        telemetry.addData("Launcher", LAUNCHER_MOTOR);
        telemetry.update();

        //35. create the subsystem, given that telemetry is used everywhere, use the constructor that sets it
        launcherSubsystem = new LauncherSubsystem(LAUNCHER_MOTOR, telemetry);
        //36. set what the motor would do when it has no power
        launcherSubsystem.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        launcherSubsystem.setMode(Motor.RunMode.VelocityControl);
        //37.not using encoders at this point but recommend stopping them
        // launcherSubsystem.stopResetEncoder();
        //38. set the direction of the motor, ideally this is tested while not on the mechanism
        launcherSubsystem.setMotorInverted(false);

    }

    public StartLaunchCommand createStartLaunchCommand(){
        return new StartLaunchCommand(launcherSubsystem, controlLaunchDS, telemetry);
    }


    public LauncherSubsystem getlauncherSubsystem() {
        return launcherSubsystem;
    }

}
