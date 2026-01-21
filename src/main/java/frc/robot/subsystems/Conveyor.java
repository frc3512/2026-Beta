package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyor  extends SubsystemBase {

    private final TalonFX motor = new TalonFX(11); // Put real ID

    public Conveyor() {

        // Add Inverted if needed
        motor.setNeutralMode(NeutralModeValue.Coast);

    }

    public Command runConveyor() {
        return run(() -> motor.set(0.8));
    }

    public Command stopConveyor() {
        return runOnce(() -> motor.set(0.0));
    }
    
}