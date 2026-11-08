package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.seattlesolvers.solverslib.hardware.RevIMU;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;





public class robot {
    public Telemetry telemetry;
    public MecanumDrive drive;
    public Motor intakeMotor;

    public Motor kickerMotor;
    public ServoEx rampServoLeft;
    public ServoEx rampServoRight;





    public RevIMU imu;


    public robot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        intakeMotor = new Motor(hardwareMap, "intake");
        kickerMotor = new Motor(hardwareMap, "kicker");
        rampServoLeft = new ServoEx(hardwareMap, "rampLeft");
        rampServoRight = new ServoEx(hardwareMap,"rampRight");



        Motor fl, fr, bl, br;
        fl = new Motor(hardwareMap, "frontLeft");
        fr = new Motor(hardwareMap, "frontRight");
        bl = new Motor(hardwareMap, "backLeft");
        br = new Motor(hardwareMap, "backRight");

        //drive = new DifferentialDrive(fl, fr, bl, br);
        drive = new MecanumDrive(fl, fr, bl, br);

        RevIMU imu = new RevIMU(hardwareMap);
        imu.init();
    }

}