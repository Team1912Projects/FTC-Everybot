package org.firstinspires.ftc.teamcode.Commands;


import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.Subsystem;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class IntakeIn extends CommandBase {
    Intake intake;

    public IntakeIn(Intake i){
        intake = i;
        addRequirements(intake);

    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        Intake.INSTANCE.intakeIn();
    }

    @Override
    public void end(boolean interrupted) {
        Intake.INSTANCE.intakeStowFlower();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
