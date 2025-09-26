package frc.robot.subsystems;


import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HeadButtSubsystem extends SubsystemBase {

    // With eager singleton initialization, any static variables/fields used in the 
    // constructor must appear before the "INSTANCE" variable so that they are initialized 
    // before the constructor is called when the "INSTANCE" variable initializes.

    /**
     * The Singleton instance of this HeasButtSubsystem. Code should use
     * the {@link #getInstance()} method to get the single instance (rather
     * than trying to construct an instance of this class.)
     */
    public final static HeadButtSubsystem INSTANCE = new HeadButtSubsystem();

    /**
     * Returns the Singleton instance of this HeasButtSubsystem. This static method
     * should be used, rather than the constructor, to get the single instance
     * of this class. For example: {@code HeasButtSubsystem.getInstance();}
     */
    @SuppressWarnings("WeakerAccess")
    public static HeadButtSubsystem getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a new instance of this HeasButtSubsystem. This constructor
     * is private since this class is a Singleton. Code should use
     * the {@link #getInstance()} method to get the singleton instance.
     */
    public HeadButtSubsystem() {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.
        //       Also, you can call addChild(name, sendableChild) to associate sendables with the subsystem
        //       such as SpeedControllers, Encoders, DigitalInputs, etc.
        SparkMaxConfig configLeft = new SparkMaxConfig();
        configLeft.smartCurrentLimit(90).idleMode(SparkBaseConfig.IdleMode.kBrake).follow(1).closedLoopRampRate(1.4);
        MotorConstants.sparkleftleader.configure(configLeft, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
        SparkMaxConfig configRight = new SparkMaxConfig();
        configRight.smartCurrentLimit(90).idleMode(SparkBaseConfig.IdleMode.kBrake).follow(3).closedLoopRampRate(1.4);
        MotorConstants.sparkrightleader.configure(configRight, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);

    }
    public void D_one_cook_opponent(int direction) {
            MotorConstants.sparkleftleader.set(direction);
            MotorConstants.sparkrightleader.set(direction);

    }

    public Command CookedOpponent(int direction) {
        return this.run(
                () -> D_one_cook_opponent(direction)
        );
    }
}

