package org.firstinspires.ftc.teamcode.Opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Odometry;
import org.firstinspires.ftc.teamcode.Subsystems.PinPoint;
import org.firstinspires.ftc.teamcode.robot;


@TeleOp

public class Teleop extends LinearOpMode {
    static final boolean FIELD_CENTRIC = true;

    @Override
    public void runOpMode() throws InterruptedException {
        robot robot = new robot(hardwareMap, telemetry);
        Shooter shooter = new Shooter();
        GamepadEx driverOp = new GamepadEx(gamepad1);

        waitForStart();

        while (!isStopRequested()) {
            CommandScheduler.getInstance().run();
            if (!FIELD_CENTRIC) {
                //       // optional fourth parameter for squared inputs
                //       robot.drive.driveRobotCentric(
                //               driverOp.getLeftX(),
                //               driverOp.getLeftY(),
                //               driverOp.getRightX(),
                //               false
                //       );
                //   } else {
                //       // optional fifth parameter for squared inputs
                //       robot.drive.driveFieldCentric(
                //               driverOp.getLeftX(),
                //               driverOp.getLeftY(),
                //               driverOp.getRightX(),
                //               robot.imu.getRotation2d().getDegrees(),   // gyro value passed in here must be in degrees
                //               false
                //       );
                //   }
            }
        }

    }



}