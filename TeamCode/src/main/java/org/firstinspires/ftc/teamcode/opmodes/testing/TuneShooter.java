package org.firstinspires.ftc.teamcode.opmodes.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name = "TuneShooter")
public class TuneShooter extends OpMode {

    double kP, kI, kD, kS, kV, kA, RPM;
    ElapsedTime m_timer = new ElapsedTime();

    private Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        telemetry.addData(">", "Initializing hardware.");
        telemetry.update();
        ShooterTuner.INSTANCE.init(robot);
        telemetry.addData(">", "Initialization complete.");
        telemetry.update();
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        /* Called periodically (set time interval) while the robot is enabled. */

        boolean inckS = gamepad1.dpad_up;
        boolean deckS = gamepad1.dpad_down;
        boolean inckV = gamepad1.dpad_left;
        boolean deckV = gamepad1.dpad_right;

        boolean inckP = gamepad1.y; // NORTH_FACE
        boolean deckP = gamepad1.a; // SOUTH_FACE
        boolean inckI = gamepad1.b; // EAST_FACE
        boolean deckI = gamepad1.x; // WEST_FACE

        if (inckS) {ShooterTuner.INSTANCE.inckS();}
        if (deckS) {ShooterTuner.INSTANCE.deckS();}
        if (inckV) {ShooterTuner.INSTANCE.inckV();}
        if (deckV) {ShooterTuner.INSTANCE.deckV();}
        if (inckP) {ShooterTuner.INSTANCE.inckP();}
        if (deckP) {ShooterTuner.INSTANCE.deckP();}
        if (inckI) {ShooterTuner.INSTANCE.inckI();}
        if (deckI) {ShooterTuner.INSTANCE.deckI();}

        kP = ShooterTuner.INSTANCE.kP;
        kI = ShooterTuner.INSTANCE.kI;
        kD = ShooterTuner.INSTANCE.kD;
        kS = ShooterTuner.INSTANCE.kS;
        kV = ShooterTuner.INSTANCE.kV;
        kA = ShooterTuner.INSTANCE.kA;

        telemetry.addData("kP", kP);
        telemetry.addData("kI", kI);
        telemetry.addData("kD", kD);
        telemetry.addData("kS", kS);
        telemetry.addData("kV", kV);
        telemetry.addData("kA", kA);

        boolean incRPM = gamepad1.right_trigger > 0.3;
        boolean decRPM = gamepad1.left_trigger > 0.3;
        if (incRPM) {ShooterTuner.INSTANCE.incRPM();}
        if (decRPM) {ShooterTuner.INSTANCE.decRPM();}
        RPM = ShooterTuner.INSTANCE.RPM;

        telemetry.addData("RPM", RPM);

        ShooterTuner.INSTANCE.setFF();
        ShooterTuner.INSTANCE.setPID();
        ShooterTuner.INSTANCE.setRPM();

        double vals[] = ShooterTuner.INSTANCE.getOuts();
        telemetry.addData("vals target tickspersec",vals[3]);
        telemetry.addData("vals feedbk",vals[0]);
        telemetry.addData("vals feedfw",vals[1]);
        telemetry.addData("vals power",vals[2]);

        double vels[] = ShooterTuner.INSTANCE.getVels();
        double TARGET_VEL = RPM * 28 / 60;
        telemetry.addData("target tickspersec speed",TARGET_VEL);
        telemetry.addData("shooter motor speed",vels[0]);
        telemetry.addData("shooter motor current",vels[1]);

        boolean kickeron = gamepad1.right_bumper;
        if (kickeron) {
            ShooterTuner.INSTANCE.kickeron();
        } else {
            ShooterTuner.INSTANCE.kickeroff();
        }

        telemetry.update();
    }
}
