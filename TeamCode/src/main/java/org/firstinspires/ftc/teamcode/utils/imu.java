/*package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class imu {
    public imu() {}
    public IMU imu;

    public void init(HardwareMap hwMap ){
        imu = hwMap.get(IMU.class,"imu");

        RevHubOrientationOnRobot RevHubOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.DOWN,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
        );

        imu.initialize(new IMU.Parameters(RevHubOrientation));



    }

    public double getHeading(AngleUnit angleUnit){
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    public void resetHeading() {
        imu.resetYaw();
    }



}*/
