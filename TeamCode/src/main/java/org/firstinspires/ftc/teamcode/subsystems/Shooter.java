package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.utils.PIDController;
import org.firstinspires.ftc.teamcode.utils.SimpleMotorFeedforward;
import org.firstinspires.ftc.teamcode.Robot;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class Shooter extends SubsystemBase {

    Robot robot;
    public Shooter() {}
    public static final Shooter INSTANCE = new Shooter();

    PIDController pidc = new PIDController(0, 0, 0);
    SimpleMotorFeedforward ff = new SimpleMotorFeedforward(0, 0, 0);

    public double kP = 0.007;
    public double kI = 0.0;
    public double kD = 0.0;
    public double kS = 0.00;
    public double kV = 0.000;
    public double kA = 0.0;
    public double RPM = 0.0;

    public static int TICKS_PER_REV = 28;
    public static double VEL_SCALE = 1.;
    static double TARGET_RPM = 1700.;

    public void setPID() {
        pidc.setPID(kP, kI, kD);
    }
    public void setFF() {
        ff.setKs(kS);
        ff.setKv(kV);
        ff.setKa(kA);
    }

    public void setRPM() {
        double TARGET_VEL = RPM * TICKS_PER_REV * VEL_SCALE / 60;
        pidc.setSetpoint(TARGET_VEL);
        double feedback = pidc.calculate(robot.shooterMotor.getVelocity());
        double feedfw = ff.calculate(TARGET_VEL);
        robot.shooterMotor.setVelocity(pidc.clamp(feedfw+feedback,-1.,1.));
    }

    public void init(Robot robot){
        this.robot = robot;
        this.robot.shooterMotor.setInverted(false);
        this.robot.shooterMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        this.robot.shooterMotor.setRunMode(Motor.RunMode.RawPower);

        this.robot.kickerMotor.setInverted(false);
        this.robot.kickerMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        this.robot.kickerMotor.setRunMode(Motor.RunMode.RawPower);
        setPID();
        setFF();
        stop();
    }

    public double[] getVels() {
        double[] vals = {0.,0.,0.,0.};
        vals[0] = robot.shooterMotor.getVelocity();
        vals[2] = robot.shooterMotor.getCurrent(CurrentUnit.AMPS);
        return vals;
    }

    public double[] getOuts() {
        double[] vals = {0.,0.,0.,0.};
        double TARGET_VEL = RPM * TICKS_PER_REV * VEL_SCALE / 60;
        pidc.setSetpoint(TARGET_VEL);
        vals[0] = pidc.calculate(robot.shooterMotor.getVelocity());
        vals[1] = ff.calculate(TARGET_VEL);
        vals[2] = vals[0]+vals[1];
        vals[3] = TARGET_VEL;
        return vals;
    }

    public void stop() {
        RPM = 0;
        setRPM();
    }

    public void log() {
        double pow = robot.shooterMotor.getVelocity();
        robot.telemetry.addData("Shooter target RPN:", RPM);
        robot.telemetry.addData("Shooter :", RPM);
        robot.telemetry.addData("Shooter motor vel:", pow);
    }

    public void shooterOn() {
        RPM = TARGET_RPM;
    }
    public void shooterOff() {
        RPM = 0;
        setRPM();
        robot.shooterMotor.setVelocity(0);
    }

    public void kickeron() {
        robot.kickerMotor.setVelocity(1.);
    }
    public void kickeroff() {
        robot.kickerMotor.setVelocity(0);
    }
}
