package frc.robot.subsystems;


import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TankDriveSubsystem extends SubsystemBase {

    // With eager singleton initialization, any static variables/fields used in the 
    // constructor must appear before the "INSTANCE" variable so that they are initialized 
    // before the constructor is called when the "INSTANCE" variable initializes.
    public static final SparkMax sparkleftleader = new SparkMax(1, SparkLowLevel.MotorType.kBrushless);
    private final SparkMax sparkleftfollower = new SparkMax(2, SparkLowLevel.MotorType.kBrushless);
    public static final SparkMax sparkrightleader = new SparkMax(3, SparkLowLevel.MotorType.kBrushless);

    private final SparkMax sparkrightfollower = new SparkMax(4, SparkLowLevel.MotorType.kBrushless);
    private final SparkClosedLoopController rightcontroller = sparkrightfollower.getClosedLoopController();
    private final SparkClosedLoopController leftcontroller = sparkleftfollower.getClosedLoopController();



    /**
     * The Singleton instance of this TankDriveSubsystem. Code should use
     * the  method to get the single instance (rather
     * than trying to construct an instance of this class.)
     */

    /**
     * Returns the Singleton instance of this TankDriveSubsystem. This static method
     * should be used, rather than the constructor, to get the single instance
     * of this class. For example: {@code TankDriveSubsystem.getInstance();}
     */
    @SuppressWarnings("WeakerAccess")

    /**
     * Creates a new instance of this TankDriveSubsystem. This constructor
     * is private since this class is a Singleton. Code should use
     * the {@link #getInstance()} method to get the singleton instance.
     */ public TankDriveSubsystem() {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
        //       Also, you can call addChild(name, sendableChild) to associate sendables with the subsystem
        //       such as SpeedControllers, Encoders, DigitalInputs, etc.
        SparkMaxConfig configLeft = new SparkMaxConfig();
        configLeft.smartCurrentLimit(90).idleMode(SparkBaseConfig.IdleMode.kBrake).follow(1).closedLoopRampRate(1.4);
        sparkleftfollower.configure(configLeft, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
        SparkMaxConfig configRight = new SparkMaxConfig();
        configRight.smartCurrentLimit(90).idleMode(SparkBaseConfig.IdleMode.kBrake).follow(3).closedLoopRampRate(1.4);
        sparkrightfollower.configure(configRight, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);


    }
    public Command velocity(double velocity) {
       return run(
               () -> {
                   leftcontroller.setReference(velocity, SparkBase.ControlType.kVelocity);
                   rightcontroller.setReference(velocity, SparkBase.ControlType.kVelocity);
               });
    }
    public Command runMotors(double speed) {
        return run(
                () -> {
                    sparkrightfollower.set(speed);
                    sparkleftfollower.set(speed);
                });
    }
    public Command runRightMotors(double speed) {
        return run(
                () -> {
                    sparkrightfollower.set(speed);
                });
    }
    public Command runLeftMotors(double speed) {
        return run(
                () -> {
                    sparkleftfollower.set(speed);
                });
    }




}

