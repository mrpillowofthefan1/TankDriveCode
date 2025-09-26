package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;

public class MotorConstants {
    public static final SparkMax sparkleftleader = new SparkMax(1, SparkLowLevel.MotorType.kBrushless);
    private final SparkMax sparkleftfollower = new SparkMax(2, SparkLowLevel.MotorType.kBrushless);
    public static final SparkMax sparkrightleader = new SparkMax(3, SparkLowLevel.MotorType.kBrushless);

    private final SparkMax sparkrightfollower = new SparkMax(4, SparkLowLevel.MotorType.kBrushless);



}
