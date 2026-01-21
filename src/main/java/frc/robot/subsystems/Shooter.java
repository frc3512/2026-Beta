package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    private final TalonFX leadMotor = new TalonFX(12); // Change ID
    private final TalonFX followMotor = new TalonFX(13); // Change ID

    public Shooter() {

        // Add Inverted if needed
        leadMotor.setNeutralMode(NeutralModeValue.Coast);
        followMotor.setNeutralMode(NeutralModeValue.Coast);

    }

    public Command setShooter(double output) {
        return Commands.runOnce(() -> {
            leadMotor.set(-output);
            followMotor.set(output);
        });
    }

    @Override public void periodic() {}
    
}
