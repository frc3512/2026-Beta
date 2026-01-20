package frc.robot;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.events.EventTrigger;
import com.ctre.phoenix6.swerve.SwerveRequest;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;

public class RobotContainer {
    private double MaxSpeed = 1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity
    
    /* Setting up bindings for necessary control of the swerve drive platform */
    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
    .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
    .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
    
    private final CommandXboxController controller = new CommandXboxController(0);
    
    public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();
    
    private final SendableChooser<Command> autoChooser = AutoBuilder.buildAutoChooser();
    
    public RobotContainer() {

        NamedCommands.registerCommand("spin", rotate360Degrees());
        
        configureBindings();
        
        SmartDashboard.putData("Auto Modes", autoChooser);
    }
    /**
     * Returns a command that rotates the robot 360 degrees.
     * This method creates a command that spins the robot in place for a full rotation.
     */
    public Command rotate360Degrees() {
        return drivetrain.applyRequest(() -> new SwerveRequest.RobotCentric().withRotationalRate(Math.PI)).withTimeout(1.78);
    }


    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
    }


    private void configureBindings() {
        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        drivetrain.setDefaultCommand(
            // Drivetrain will execute this command periodically
            drivetrain.applyRequest(() ->
            drive.withVelocityX(-controller.getLeftY() * MaxSpeed) // Drive forward with negative Y (forward)
            .withVelocityY(-controller.getLeftX() * MaxSpeed) // Drive left with negative X (left)
            .withRotationalRate(-controller.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
            )
            );

        controller.y().onTrue(rotate360Degrees());
        
        controller.x().onTrue(drivetrain.runOnce(drivetrain::seedFieldCentric));

        }
    }
