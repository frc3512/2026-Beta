package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    private final TalonFX leadMotor = new TalonFX(103); // Change ID
    private final TalonFX followMotor = new TalonFX(104); // Change ID

    private final VelocityVoltage target = new VelocityVoltage(0.0);

    public Shooter() {

        // Add Inverted if needed
        leadMotor.setNeutralMode(NeutralModeValue.Coast);
        followMotor.setNeutralMode(NeutralModeValue.Coast);

        followMotor.setControl(new Follower(leadMotor.getDeviceID(), MotorAlignmentValue.Aligned));
        
    }

    public Command setShooter(double rpm) {
        double rps = rpm / 60.0;
        return runOnce(() -> 
        target.Velocity = rps);
    }

    @Override public void periodic() {
        leadMotor.setControl(target);
    }
    
}
