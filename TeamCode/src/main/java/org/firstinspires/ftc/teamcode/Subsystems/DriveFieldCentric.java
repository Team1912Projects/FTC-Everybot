package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class DriveFieldCentric extends SubsystemBase {

    private final MotorEx frontLeft;
    private final MotorEx frontRight;
    private final MotorEx backLeft;
    private final MotorEx backRight;

    private final MecanumDrive driving;
    private final IMU imu;



    public DriveFieldCentric(HardwareMap hardwareMap) {

        frontLeft = new MotorEx(hardwareMap, "frontLeft");
        frontRight = new MotorEx(hardwareMap, "frontRight");
        backLeft = new MotorEx(hardwareMap, "backLeft");
        backRight = new MotorEx(hardwareMap, "backRight");

        driving = new MecanumDrive(
                frontLeft,
                frontRight,
                backLeft,
                backRight
        );

        // Initialize IMU
        imu = hardwareMap.get(IMU.class, "imu");

        imu.initialize(new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.DOWN,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
                )
        ));

        // Brake motors
        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void driveFieldCentric(
            double forward,
            double strafe,
            double turn
    ) {

        double heading = imu.getRobotYawPitchRollAngles()
                .getYaw(AngleUnit.DEGREES);

        driving.driveFieldCentric(
                strafe,
                forward,
                turn,
                heading
        );
    }

    public void resetHeading() {
        imu.resetYaw();
    }

    public double getHeading() {
        return imu.getRobotYawPitchRollAngles()
                .getYaw(AngleUnit.DEGREES);
    }

    public void driveRobotCentric(
            double forward,
            double strafe,
            double turn
    ) {
        driving.driveRobotCentric(strafe, forward, turn);
    }

    public void stop() {
        driving.stop();
    }
}