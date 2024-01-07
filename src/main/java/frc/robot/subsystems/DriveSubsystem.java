// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  public static class Hardware {
      CANSparkMax lMasterMotor;
      CANSparkMax rMasterMotor;
      CANSparkMax lSlaveMotor;
      CANSparkMax rSlaveMotor;
      public Hardware(CANSparkMax lMasterMotor, CANSparkMax rMasterMotor, CANSparkMax lSlaveMotor, CANSparkMax rSlaveMotor) {
        this.lMasterMotor = lMasterMotor;
        this.rMasterMotor = rMasterMotor;
        this.lSlaveMotor = lSlaveMotor;
        this.rSlaveMotor = rSlaveMotor;
      }
  }

  private CANSparkMax m_lMasterMotor;
  private CANSparkMax m_rMasterMotor;
  private CANSparkMax m_lSlaveMotor;
  private CANSparkMax m_rSlaveMotor;

private DifferentialDrive m_drivetrain;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem(Hardware drivetrainHardware) {
    m_lMasterMotor = drivetrainHardware.lMasterMotor;
    m_rMasterMotor = drivetrainHardware.rMasterMotor;
    m_lSlaveMotor = drivetrainHardware.lSlaveMotor;
    m_rSlaveMotor = drivetrainHardware.rSlaveMotor;

    m_drivetrain = new DifferentialDrive(m_lMasterMotor, m_rMasterMotor);
    
  m_rSlaveMotor.setInverted(true);
  m_lSlaveMotor.setInverted(true);

    m_lSlaveMotor.follow(m_lMasterMotor);
    m_rSlaveMotor.follow(m_rMasterMotor);
  }

  public static Hardware() {
    Hardware drivetrainHardware = new Hardware(
    new CANSparkMax(Constants.DriveHardware.LEFT_FRONT_MASTER_MOTOR, MotorType.kBrushless), 
    new CANSparkMax(Constants.DriveHardware.RIGHT_FRONT_MASTER_MOTOR, MotorType.kBrushless), 
    new CANSparkMax(Constants.DriveHardware.LEFT_REAR_SLAVE_MOTOR, MotorType.kBrushless),
    new CANSparkMax(Constants.DriveHardware.RIGHT_REAR_SLAVE_MOTOR, MotorType.kBrushless));
    return drivetrainHardware;
  }

  public void teleop(double speed, double turn) {
    m_drivetrain.arcadeDrive(speed, turn);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
