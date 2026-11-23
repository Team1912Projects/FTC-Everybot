package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.utils.PIDController;
import org.firstinspires.ftc.teamcode.utils.SimpleMotorFeedforward;

public class ShooterTuner {

    Robot robot;
    public ShooterTuner() {}

    public static final ShooterTuner INSTANCE = new ShooterTuner();

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
    static double TARGET_RPM_HIGH = 2740.;
    static double TARGET_RPM_AUTOSHOT = 2250;
    static double TARGET_RPM_MED = 2250.;
    static double TARGET_RPM_LOW = 2000.;

    public void init(Robot robot){
        this.robot = robot;
        robot.shooterMotor.setInverted(false);
        robot.shooterMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        robot.shooterMotor.setRunMode(Motor.RunMode.RawPower);

        robot.kickerMotor.setInverted(false);
        robot.kickerMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        robot.kickerMotor.setRunMode(Motor.RunMode.RawPower);
        setPID();
        setFF();
        stop();
        kickeroff();
    }

    public void stop() {
        RPM = 0;
        setRPM();
    }

    public void oldstop() {
        robot.shooterMotor.setVelocity(0.);
    }

    public void kickeron() {
        robot.kickerMotor.set(1.);
    }
    public void kickerslow(){
        robot.kickerMotor.set(0.25);
    }
    public void kickerout() {
        robot.kickerMotor.set(-1.0);
    }
    public void kickeroff() {
        robot.kickerMotor.set(0.);
    }

    public void inckS() { kS += .0001; }
    public void deckS() { kS -= .0001; kS = Math.max(kS, 0); }
    public void inckV() { kV += .0001; }
    public void deckV() { kV -= .0001; kV = Math.max(kV, 0); }
    public void inckA() { kA += .0001; }
    public void deckA() { kA -= .0001; kA = Math.max(kA, 0); }
    public void inckP() { kP += .0001; }
    public void deckP() { kP -= .0001; kP = Math.max(kP, 0); }
    public void inckI() { kI += .0001; }
    public void deckI() { kI -= .0001; kI = Math.max(kI, 0); }
    public void inckD() { kD += .0001; }
    public void deckD() { kD -= .0001; kD = Math.max(kD, 0); }

    public void incRPM() { RPM += 50; RPM = Math.min(RPM, 5000); }
    public void decRPM() { RPM -= 50; RPM = Math.max(RPM, 0); }

    public void setPID() {
        pidc.setPID(kP, kI, kD);
    }
    public void setFF() {
        ff.setKs(kS);
        ff.setKv(kV);
        ff.setKa(kA);
    }

    public double[] getVels() {
        double[] vals = {0.,0.};
        vals[0] = robot.shooterMotor.getVelocity();
        vals[1] = robot.shooterMotor.getCurrent(CurrentUnit.AMPS);
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

    public void setRPM() {
        double TARGET_VEL = RPM * TICKS_PER_REV * VEL_SCALE / 60;
        pidc.setSetpoint(TARGET_VEL);
        double feedback = pidc.calculate(robot.shooterMotor.getVelocity());
        double feedfw = ff.calculate(TARGET_VEL);
        robot.shooterMotor.set(pidc.clamp(feedfw+feedback,-1.,1.));
    }

}
