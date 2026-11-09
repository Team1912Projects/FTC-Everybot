package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot;



public class Drive extends SubsystemBase {
    robot robot;
    public Drive(robot robot) {
        this.robot = robot;
    }


    public static final Drive INSTANCE = new Drive();

    private Drive() { }

    private AutoDrive autoDrive;
    private Telemetry telemetry;
    private DcMotorEx left_front = null;
    private DcMotorEx right_front = null;
    private DcMotorEx left_back = null;
    private DcMotorEx right_back = null;

    private String lf_name = "frontLeft";
    private String lb_name = "backLeft";
    private String rf_name = "frontRight";
    private String rb_name = "BackRight";



    public double fwdSpeed;

    public void init(HardwareMap hmap) {
        left_front = hmap.get(DcMotorEx.class,lf_name);
        right_front = hmap.get(DcMotorEx.class,rf_name);
        left_back = hmap.get(DcMotorEx.class,lb_name);
        right_back = hmap.get(DcMotorEx.class,rb_name);

        left_front.setDirection(DcMotorSimple.Direction.FORWARD);
        left_back.setDirection(DcMotorSimple.Direction.FORWARD);
        right_front.setDirection(DcMotorSimple.Direction.REVERSE);
        right_back.setDirection(DcMotorSimple.Direction.REVERSE);

        left_front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void stop() {
        left_front.setPower(0.);
        right_front.setPower(0.);
        left_back.setPower(0.);
        right_back.setPower(0.);
    }


    /*public void autoAim() {
        final double TURN_GAIN   =  0.01  ;   //  Turn Control "Gain".  e.g. Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)
        final double MAX_AUTO_TURN  = 0.3;   //  Clip the turn speed to this max value (adjust for your robot)
        double headingError = LimeLight.INSTANCE.getTargetBearing();
        double turn   = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN) ;
        moveRobot(0.,0.,turn);
    }*/
    public void moveRobot(double fwd, double strafe, double rot) {
        fwdSpeed = fwd;
        double denominator = Math.max(Math.abs(strafe) + Math.abs(fwd) + Math.abs(rot), 1.0);
        double frontLeftPower = (fwd - strafe - rot) / denominator;
        double frontRightPower = (fwd + strafe + rot) / denominator;
        double backLeftPower = (fwd + strafe - rot) / denominator;
        double backRightPower = (fwd - strafe + rot) / denominator;
        left_front.setPower(frontLeftPower);
        right_front.setPower(frontRightPower);
        left_back.setPower(backLeftPower);
        right_back.setPower(backRightPower);
    }



}
