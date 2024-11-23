package org.firstinspires.ftc.teamcode.mechanisms.arm;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.CreateMechanismBase;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDownCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDropCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDropPositionCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmMidDropCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmPickUpCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmPickUpPositionCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmTravelCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmUpCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmUpTargetCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.subsystems.ArmSubsystem;

public class CreateArmMechanism extends CreateMechanismBase {

    private ArmSubsystem armSubsystem;
    private ArmDropCommand armDropCommand;
    private ArmPickUpCommand armPickUpCommand;

    private ArmDownCommand armDownCommand;
    private ArmUpCommand armUpCommand;

    private ArmUpTargetCommand armUpTargetCommand;

    private ArmTravelCommand armTravelCommand;

    private ArmMidDropCommand armMidDropCommand;

    private ArmDropPositionCommand armDropPositionCommand;
    private ArmPickUpPositionCommand armPickUpPositionCommand;

    private DcMotorEx arm;

    public CreateArmMechanism(HardwareMap hwMap, String deviceName, GamepadEx op, Telemetry telemetry){
        super(hwMap, deviceName, op, telemetry);
    }

    public CreateArmMechanism(HardwareMap hwMap, String deviceName, GamepadEx op, Telemetry telemetry, boolean autoCreate){
        super(hwMap, deviceName, op, telemetry, autoCreate);
    }

    public CreateArmMechanism(final HardwareMap hwMap, final String deviceName, Telemetry telemetry){
        super(hwMap, deviceName, telemetry);

    }

    @Override
    public void create(){

        //33. after setting up our triggers we can now creates our subsystem
        createBase();

        //39. created commands

        //40. set motor to run without encoders

        armSubsystem.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);


        //41. instead of creating the command right here, we created a function for it so we
        //can reuse the code for Auto
        armDropCommand = createDropCommand();
        armMidDropCommand = createMidDropCommand();
        armPickUpCommand = createPickUpCommand();

        armDropPositionCommand = createDropPositionCommand();
        armPickUpPositionCommand = createPickUpPositionCommand();

        armDownCommand = createArmDownCommand();
        armUpCommand = createArmUpCommand();
        armUpTargetCommand = createArmUpTargetCommand();
        armTravelCommand = createArmTravelCommand();


        op.getGamepadButton(GamepadKeys.Button.Y).whenPressed(armUpCommand);
        op.getGamepadButton(GamepadKeys.Button.A).whenPressed(armDownCommand);
        op.getGamepadButton(GamepadKeys.Button.B).whenPressed(armTravelCommand);
        //armSubsystem.setDefaultCommand(armPickUpCommand);

    }

    @Override
    public void createAuto(){
        createBase();
    }

    @Override
    public void createBase(){
        telemetry.addLine("Arm createBase");

        arm = hwMap.get(DcMotorEx.class,deviceName );
        telemetry.addData("Arm", arm);
        telemetry.update();

        //35. create the subsystem, given that telemetry is used everywhere, use the constructor that sets it
        armSubsystem = new ArmSubsystem(arm, telemetry, true);
        //36. set what the motor would do when it has no power
        armSubsystem.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        //37.not using encoders at this point but recommend stopping them
        armSubsystem.stopResetEncoder();
        //38. set the direction of the motor, ideally this is tested while not on the lift
        armSubsystem.setDirection(DcMotorEx.Direction.FORWARD);

        //op.getGamepadButton(GamepadKeys.Button.Y).whenPressed(armPickUpCommand);


    }

    private ArmDropCommand createDropCommand(){
        return new ArmDropCommand(armSubsystem, telemetry);
    }

    private ArmPickUpCommand createPickUpCommand(){
        return new ArmPickUpCommand(armSubsystem, telemetry);
    }

    private ArmDropPositionCommand createDropPositionCommand(){
        return new ArmDropPositionCommand(armSubsystem, telemetry);
    }

    private ArmMidDropCommand createMidDropCommand(){
        return new ArmMidDropCommand(armSubsystem, telemetry);
    }

    private ArmDownCommand createArmDownCommand(){
        return new ArmDownCommand(armSubsystem, telemetry);
    }

    private ArmUpCommand createArmUpCommand(){
        return new ArmUpCommand(armSubsystem, telemetry);
    }

    private ArmUpTargetCommand createArmUpTargetCommand(){
        return new ArmUpTargetCommand(armSubsystem, telemetry);
    }

    private ArmTravelCommand createArmTravelCommand(){
        return new ArmTravelCommand(armSubsystem, telemetry);
    }



    private ArmPickUpPositionCommand createPickUpPositionCommand(){
        return new ArmPickUpPositionCommand(armSubsystem, telemetry);
    }


}
