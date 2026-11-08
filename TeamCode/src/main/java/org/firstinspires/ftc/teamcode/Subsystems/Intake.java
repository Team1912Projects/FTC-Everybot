package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    public static final Intake INSTANCE = new Intake();
    private Intake() { }

    private String motor_name = "intake_motor";
    public DcMotorEx intake_motor;

    private double INTAKE_OUT_POWER = 0.59;
    private double INTAKE_IN_POWER = -1.0;
    private int INTAKE_STOW = 0;

    public void intakeIn(){
        intake_motor.setPower(INTAKE_IN_POWER);
    }

    public void intakeOut(){
        intake_motor.setPower(INTAKE_OUT_POWER);
    }

    public void intakeStow(){
        intake_motor.setTargetPosition(INTAKE_STOW);
    }

    public void init(HardwareMap hMap) {
        intake_motor = hMap.get(DcMotorEx.class, motor_name);

        intake_motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }


}
