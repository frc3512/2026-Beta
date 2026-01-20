package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

    private final TalonFX motor = new TalonFX(10); // Put real ID

    public final double INTAKE = -0.7; // Adjust as necessary
    public final double OUTTAKE = 0.7; // Adjust as necessary
    public final double FEEDING = 0.15; // Adjust as necessary

    public Intake() {

        // Add Inverted if needed
        motor.setNeutralMode(NeutralModeValue.Brake);
    }

    // Add methods to control the intake mechanism here

    public Command runIntake() {
        return runOnce(() -> motor.set(INTAKE));
    }

    public Command runOuttake() {
        return runOnce(() -> motor.set(OUTTAKE));
    }

    public Command runFeeding() {
        return runOnce(() -> motor.set(FEEDING));
    }

    public Command stopIntake() {
        return runOnce(() -> motor.set(0.0));
    }
    
}