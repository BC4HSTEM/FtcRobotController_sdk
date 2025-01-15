package org.firstinspires.ftc.teamcode.mechanisms.arm;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.LEFT_TRIGGER;
import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Trigger.RIGHT_TRIGGER;

import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanisms.CreateMechanismBase;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDDownCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDUpCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDownCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDropCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmDropPositionCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmFCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmFDownCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmFUpCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmICommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmIDownCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmIUpCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmMidDropCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmPCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmPDownCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmPUpCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmPickUpCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmPickUpPositionCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmTravelCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmUpCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.commands.ArmUpTargetCommand;
import org.firstinspires.ftc.teamcode.mechanisms.arm.subsystems.ArmSubsystem;

import java.util.function.DoubleSupplier;

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

    private ArmFCommand armFCommand;
    private ArmFUpCommand armFUpCommand;
    private ArmFDownCommand armFDownCommand;
    private ArmPCommand armPCommand;
    private ArmPUpCommand armPUpCommand;
    private ArmPDownCommand armPDownCommand;
    private ArmICommand armICommand;
    private ArmIUpCommand armIUpCommand;
    private ArmIDownCommand armIDownCommand;
    private ArmDUpCommand armDUpCommand;
    private ArmDDownCommand armDDownCommand;

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


        armDownCommand = createArmDownCommand();
        armUpCommand = createArmUpCommand();
        armUpTargetCommand = createArmUpTargetCommand();
        armTravelCommand = createArmTravelCommand();

        armFUpCommand = createArmFUpCommand();
        armFDownCommand = createArmFDownCommand();

        armPUpCommand = createArmPUpCommand();
        armPDownCommand = createArmPDownCommand();

        armIUpCommand = createArmIUpCommand();
        armIDownCommand = createArmIDownCommand();

        armDUpCommand = createArmDUpCommand();
        armDDownCommand = createArmDDownCommand();




        op.getGamepadButton(GamepadKeys.Button.Y).whenPressed(armUpCommand);
        op.getGamepadButton(GamepadKeys.Button.A).whenPressed(armDownCommand);
        op.getGamepadButton(GamepadKeys.Button.B).whenPressed(armTravelCommand);

        op.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(armFUpCommand);
        op.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(armFDownCommand);

        op.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(armPUpCommand);
        op.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(armPDownCommand);

        op.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(armIUpCommand);
        op.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(armIDownCommand);





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

    private ArmFCommand createArmFCommand(){
        return new ArmFCommand(armSubsystem,op::getLeftY , telemetry );
    }
    private ArmFUpCommand createArmFUpCommand(){
        return new ArmFUpCommand(armSubsystem , telemetry );
    }
    private ArmFDownCommand createArmFDownCommand(){
        return new ArmFDownCommand(armSubsystem , telemetry );
    }

    private ArmPCommand createArmPCommand(){
        return new ArmPCommand(armSubsystem,op::getRightY , telemetry );
    }

    private ArmPUpCommand createArmPUpCommand(){
        return new ArmPUpCommand(armSubsystem , telemetry );
    }
    private ArmPDownCommand createArmPDownCommand(){
        return new ArmPDownCommand(armSubsystem , telemetry );
    }


    /*private ArmICommand createArmICommand(){
        return new ArmICommand(armSubsystem,op::getTrigger. , telemetry );
    }*/

    private ArmIUpCommand createArmIUpCommand(){
        return new ArmIUpCommand(armSubsystem, telemetry );
    }



    private ArmIDownCommand createArmIDownCommand(){
        return new ArmIDownCommand(armSubsystem, telemetry );
    }

    private ArmDUpCommand createArmDUpCommand(){
        return new ArmDUpCommand(armSubsystem, telemetry );
    }



    private ArmDDownCommand createArmDDownCommand(){
        return new ArmDDownCommand(armSubsystem, telemetry );
    }





    private ArmPickUpPositionCommand createPickUpPositionCommand(){
        return new ArmPickUpPositionCommand(armSubsystem, telemetry);
    }


}
