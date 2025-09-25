// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.servohub.ServoHub;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static frc.robot.Constants.OperatorConstants.*;

public class FunnelSubsystem extends SubsystemBase {
  /** Creates a new FunnelSubsystem. */
  private final SparkMax sparky1 = new SparkMax(3, SparkLowLevel.MotorType.kBrushless);
 private final SparkMax sparky2 = new SparkMax(4, SparkLowLevel.MotorType.kBrushless);
 private final SparkClosedLoopController controller = sparky1.getClosedLoopController();


    public FunnelSubsystem() {
      SparkMaxConfig config = new SparkMaxConfig();
      config.smartCurrentLimit(50).idleMode(SparkBaseConfig.IdleMode.kBrake).follow(3, true);
      sparky2.configure(config, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);

  }
    public Command runVelocity(double velocity) {
        return run(
                () -> {
                    controller.setReference(velocity, SparkBase.ControlType.kVelocity);
                });
    }
    public Command runSpeed(double speed) {
        return run(
                () -> {
                    sparky1.set(speed);
                });
    }



    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
