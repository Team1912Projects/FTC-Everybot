package org.firstinspires.ftc.teamcode.Opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Odometry;
import org.firstinspires.ftc.teamcode.Subsystems.PinPoint;
import org.firstinspires.ftc.teamcode.Commands.rampUp;
import org.firstinspires.ftc.teamcode.Commands.rampPollen;
import org.firstinspires.ftc.teamcode.Commands.rampDown;
import org.firstinspires.ftc.teamcode.Commands.IntakeOut;
import org.firstinspires.ftc.teamcode.Commands.IntakeIn;
import org.firstinspires.ftc.teamcode.Commands.RunShooter;



@TeleOp

public class Teleop extends OpMode{
    final private ElapsedTime teleopTimer = new ElapsedTime();
    final private ElapsedTime blinkTimer = new ElapsedTime();
    boolean endGameWarning = false;



    @Override
    public void init() {
        telemetry.addData(">", "Initializing hardware.");
        telemetry.update();
        Drive.INSTANCE.init(hardwareMap);
        Shooter.INSTANCE.init(hardwareMap);
        Intake.INSTANCE.init(hardwareMap);
        PinPoint.INSTANCE.init(hardwareMap);
        Odometry.INSTANCE.teleinit();
        telemetry.addData(">", "Initialization complete.");
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        teleopTimer.reset();
        endGameWarning = false;
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        Shooter.INSTANCE.run();

        if (teleopTimer.seconds() >= 80. & teleopTimer.seconds() <= 90.) {
            if (! endGameWarning) {
                blinkTimer.reset();
                endGameWarning = ! endGameWarning;
            }
            if (Math.round(blinkTimer.seconds()) % 2 == 1) {
                gamepad1.rumble(250);
            }
        }

        //Gamepad Buttons
        GamepadEx driverOp = new GamepadEx(gamepad1);

        /*driverOp.getGamepadButton(GamepadKeys.Button.A)
                .and(driverOp.getGamepadButton(GamepadKeys.Button.B).negate())
                .whenActive(exampleSubsystem.moveDown());*/

    }






}