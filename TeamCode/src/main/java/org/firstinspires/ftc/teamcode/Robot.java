package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;


import com.seattlesolvers.solverslib.hardware.RevIMU;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;





public class Robot {
    public Telemetry telemetry;
    public MecanumDrive drive;
    public MotorEx intakeMotor;
    public MotorEx kickerMotor;
    public MotorEx shooterMotor;

    public ServoEx rampServoLeft;
    public ServoEx rampServoRight;

    public RevIMU imu;
    public GoBildaPinpointDriver pinpointDriver;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        shooterMotor = new MotorEx(hardwareMap,"shooter"); // left side expansion hub //port 2
        intakeMotor = new MotorEx(hardwareMap, "intake"); // right side control hub //port 2
        kickerMotor = new MotorEx(hardwareMap, "kicker"); // right side control hub //port 3
        rampServoLeft = new ServoEx(hardwareMap, "rampLeft"); // left //port 0
        rampServoRight = new ServoEx(hardwareMap,"rampRight"); // right //port 0

        pinpointDriver = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint"); // ExpHub I2C 1

        Motor fl, fr, bl, br;
        fl = new MotorEx(hardwareMap, "frontLeft"); //port 0
        fr = new MotorEx(hardwareMap, "frontRight"); //port 0
        bl = new MotorEx(hardwareMap, "backLeft");  //port 1
        br = new MotorEx(hardwareMap, "backRight"); //port 1

        //drive = new DifferentialDrive(fl, fr, bl, br);
        drive = new MecanumDrive(fl, fr, bl, br);

        RevIMU imu = new RevIMU(hardwareMap);
        imu.init();
    }

}