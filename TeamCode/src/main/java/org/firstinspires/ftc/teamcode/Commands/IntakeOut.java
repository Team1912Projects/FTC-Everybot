package org.firstinspires.ftc.teamcode.Commands;


import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class IntakeOut extends CommandBase {
    Intake intake;

    public IntakeOut(){

        addRequirements(intake);

    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        Intake.INSTANCE.intakeOut();
    }

    @Override
    public void end(boolean interrupted) {
     Intake.INSTANCE.intakeOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
