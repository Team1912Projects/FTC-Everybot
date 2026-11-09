package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Subsystems.DriveFieldCentric;
import org.firstinspires.ftc.teamcode.robot;

public class FieldCentric extends OpMode {
    private robot robot;
    private GamepadEx driverOp;

    @Override
    public void init() {
        robot = new robot(hardwareMap, telemetry);

        driverOp = new GamepadEx(gamepad1);
    }


    @Override
    public void loop() {
        // Field-centric joystick driving
        double forward = -squareInput(driverOp.getLeftY());
        double strafe = -squareInput(driverOp.getLeftX());
        double turn = -squareInput(driverOp.getRightX());
        driverOp.getGamepadButton(GamepadKeys.Button.START)
                        .whenPressed(new InstantCommand(
                                () -> robot.driv.resetHeading()
                                ));

        robot.driv.driveFieldCentric(
                forward,
                strafe,
                turn
        );


    }
    public double squareInput(double input) {
        return Math.copySign(input * input, input);
    }
}
