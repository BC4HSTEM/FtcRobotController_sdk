package org.firstinspires.ftc.teamcode.mechanisms.arm;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.CreateMechanismBase;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDownAdjustCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDownAutoCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDownCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDownIncrementCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDropCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDropPositionCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmMidDropCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmPickUpCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmPickUpPositionCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmResetAdjustCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmResetCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmTravelAutoCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmTravelCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmUpAdjustCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmUpAutoCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmUpCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmUpDecrementCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmUpTargetCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.subsystems.ArmAdjustSubsystem;
import org.firstinspires.ftc.teamcode.mechanisms.arm.subsystems.ArmSubsystem;

public class CreateArmMechanism extends CreateMechanismBase {

    private ArmSubsystem armSubsystem;
    private ArmAdjustSubsystem armAdjustSubsystem;
    private ArmDropCommand armDropCommand;
    private ArmPickUpCommand armPickUpCommand;

    private ArmDownCommand armDownCommand;
    private ArmUpCommand armUpCommand;

    private ArmUpTargetCommand armUpTargetCommand;

    private ArmTravelCommand armTravelCommand;

    private ArmDownAutoCommand armDownAutoCommand;
    private ArmUpAutoCommand armUpAutoCommand;


    private ArmTravelAutoCommand armTravelAutoCommand;

    private ArmResetCommand armResetCommand;

    private ArmDownIncrementCommand armDownIncrementCommand;
    private ArmDownAdjustCommand armDownAdjustCommand;
    private ArmUpAdjustCommand armUpAdjustCommand;

    private ArmResetAdjustCommand armResetAdjustCommand;
    private ArmUpDecrementCommand armUpDecrementCommand;

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
        armResetCommand = createArmResetCommand();

        armDownIncrementCommand = createArmDownIncrementCommand();
        armUpDecrementCommand = createArmUpDecrementCommand();

        armDownAdjustCommand = createArmDownAdjustCommand();
        armUpAdjustCommand = createArmUpAdjustCommand();
        armResetAdjustCommand = createArmResetAdjustCommand();


        op.getGamepadButton(GamepadKeys.Button.Y).whenPressed(armUpCommand);
        op.getGamepadButton(GamepadKeys.Button.A).whenPressed(armDownCommand);
        op.getGamepadButton(GamepadKeys.Button.B).whenPressed(armTravelCommand);
        op.getGamepadButton(GamepadKeys.Button.START).whenPressed(armResetCommand);

        op.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(armDownAdjustCommand);
        op.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(armUpAdjustCommand);
        op.getGamepadButton(GamepadKeys.Button.BACK).whenPressed(armResetAdjustCommand);



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
        armSubsystem.setDirection(DcMotorEx.Direction.REVERSE);

        armAdjustSubsystem = new ArmAdjustSubsystem(telemetry, true);

        armSubsystem.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);


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

    public ArmDownCommand createArmDownCommand(){
        return new ArmDownCommand(armSubsystem, telemetry);
    }

    private ArmUpCommand createArmUpCommand(){
        return new ArmUpCommand(armSubsystem, telemetry);
    }

    private ArmUpTargetCommand createArmUpTargetCommand(){
        return new ArmUpTargetCommand(armSubsystem, telemetry);
    }

    public ArmTravelCommand createArmTravelCommand(){
        return new ArmTravelCommand(armSubsystem, telemetry);
    }

    public ArmDownAutoCommand createArmDownAutoCommand(){
        return new ArmDownAutoCommand(armSubsystem, telemetry);
    }

    private ArmUpAutoCommand createArmUpAutoCommand(){
        return new ArmUpAutoCommand(armSubsystem, telemetry);
    }

    public ArmTravelAutoCommand createArmTravelAutoCommand(){
        return new ArmTravelAutoCommand(armSubsystem, telemetry);
    }



    private ArmPickUpPositionCommand createPickUpPositionCommand(){
        return new ArmPickUpPositionCommand(armSubsystem, telemetry);
    }

    private ArmResetCommand createArmResetCommand(){
        return new ArmResetCommand(armSubsystem, telemetry);
    }

    private ArmDownIncrementCommand createArmDownIncrementCommand(){
        return new ArmDownIncrementCommand(armSubsystem, telemetry);
    }

    private ArmUpDecrementCommand createArmUpDecrementCommand(){
        return new ArmUpDecrementCommand(armSubsystem, telemetry);
    }

    private ArmDownAdjustCommand createArmDownAdjustCommand(){
        return new ArmDownAdjustCommand(armAdjustSubsystem, telemetry);
    }

    private ArmUpAdjustCommand createArmUpAdjustCommand(){
        return new ArmUpAdjustCommand(armAdjustSubsystem, telemetry);
    }

    private ArmResetAdjustCommand createArmResetAdjustCommand(){
        return new ArmResetAdjustCommand(armAdjustSubsystem, telemetry);
    }


}
