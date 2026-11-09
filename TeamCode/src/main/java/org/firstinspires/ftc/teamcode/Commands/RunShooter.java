package org.firstinspires.ftc.teamcode.Commands;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Shooter;


public class RunShooter extends CommandBase {
    Shooter shooter;

    public RunShooter(){

        addRequirements(shooter);
    }


    @Override
    public void initialize() {}

    @Override
    public void execute() {
        shooter.shooterOn();
        shooter.kickeron();
    }

    @Override
    public void end(boolean interrupted) {
        shooter.shooterOff();
        shooter.kickeroff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }


}
