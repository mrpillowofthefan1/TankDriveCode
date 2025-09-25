package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.FunnelSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;

import java.util.function.BooleanSupplier;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

    private final DifferentialDrive m_robotDrive = new DifferentialDrive(TankDriveSubsystem.sparkleftleader::set, TankDriveSubsystem.sparkrightleader::set);
    private final GenericHID m_stick = new GenericHID(0);
    private final TankDriveSubsystem m_tankDriveSubsystem = new TankDriveSubsystem();


    // Replace with CommandPS4Controller or CommandJoystick if needed
    private final CommandXboxController m_driverController =
            new CommandXboxController(Constants.OperatorConstants.kDriverControllerPort);


    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the trigger bindings
        configureBindings();


    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * {@link Trigger#Trigger(BooleanSupplier)} constructor with an arbitrary
     * predicate, or via the named factories in {@link
     * CommandGenericHID}'s subclasses for {@link
     * CommandXboxController Xbox}/{@link CommandPS4Controller
     * PS4} controllers or {@link CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
        new Trigger(m_exampleSubsystem::exampleCondition)
                .onTrue(new ExampleCommand(m_exampleSubsystem));
        m_driverController.leftStick().whileTrue(m_tankDriveSubsystem.runMotors(Math.pow(m_driverController.getRawAxis(0), 2)));
        if (m_driverController.getRightX() > 0) {
            m_driverController.rightStick().whileTrue(m_tankDriveSubsystem.runRightMotors(Math.pow(m_driverController.getRawAxis(0), 2)));
        }
        else if (m_driverController.getRightX() < 0) {
            m_driverController.rightStick().whileTrue(m_tankDriveSubsystem.runRightMotors(Math.pow(m_driverController.getRawAxis(0), 2)));
        }
        m_tankDriveSubsystem.setDefaultCommand(m_tankDriveSubsystem.runMotors(0));

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An example command will be run in autonomous
        return Autos.exampleAuto(m_exampleSubsystem);
    }
}
