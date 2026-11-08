package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import org.firstinspires.ftc.teamcode.robot;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Intake extends SubsystemBase {
    robot robot;
    public Intake(robot robot) {
        this.robot = robot;
    }
    public static final Intake INSTANCE = new Intake();
    private Intake() { }




    private double INTAKE_OUT_POWER = 0.59;
    private double INTAKE_IN_POWER = -1.0;
    private int INTAKE_STOW_FLOWER = 0;
    private double INTAKE_OFF = 0;

    private double RAMP_DOWN = -5;
    private double RAMP_UP = 0;
    private double RAMP_POLLEN_ONLY = 2.5;

    public void intakeIn(){
        robot.intakeMotor.setVelocity(INTAKE_IN_POWER);
    }

    public void intakeOut(){
        robot.intakeMotor.setVelocity(INTAKE_OUT_POWER);
    }

    public void intakeStowFlower(){
        robot.intakeMotor.setTargetPosition(INTAKE_STOW_FLOWER);
    }

    public void intakeOff(){robot.intakeMotor.setVelocity(INTAKE_OFF);}
    public void rampDown() {
        double x = robot.rampServoLeft.get();
        robot.rampServoLeft.set(x + RAMP_DOWN);

        x = robot.rampServoRight.get();
        robot.rampServoRight.set(x + RAMP_DOWN);

    }

    public void rampUp() {
        double x = robot.rampServoLeft.get();
        robot.rampServoLeft.set(x - RAMP_DOWN);

        x = robot.rampServoRight.get();
        robot.rampServoRight.set(x - RAMP_DOWN);
    }

    public void rampPollen() {
        double x = robot.rampServoLeft.get();
        robot.rampServoLeft.set(x + RAMP_POLLEN_ONLY);

        x = robot.rampServoRight.get();
        robot.rampServoRight.set(x + RAMP_POLLEN_ONLY);
    }


    public void init(HardwareMap hMap) {



        robot.intakeMotor.setInverted(false);
    }


}
