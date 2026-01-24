package frc.robot.commands;

import com.ctre.phoenix6.swerve.SwerveRequest;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CommandSwerveDrivetrain;

public class Rotate360Degrees extends Command {
    private final CommandSwerveDrivetrain drivetrain;
    private final Timer timer = new Timer();

    public Rotate360Degrees(CommandSwerveDrivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        drivetrain.setControl(new SwerveRequest.RobotCentric().withRotationalRate(Math.PI));
    }

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(1.78);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.setControl(new SwerveRequest.RobotCentric());
    }
}
