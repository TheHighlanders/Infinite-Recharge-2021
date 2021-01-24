/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;

public class DriveDistanceEncoderAUTO extends CommandBase {
  /**
   * Creates a new DriveByDistance.
   */

  private final Drive m_Drive;

  private Double distance;

  private WPI_TalonSRX left, right;


  public DriveDistanceEncoderAUTO(Drive m_Drive, Double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_Drive = m_Drive;
    this.left = m_Drive.left1;
    this.right = m_Drive.right1;
    distance = this.distance;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    left.setSelectedSensorPosition(0);
    right.setSelectedSensorPosition(0);
    m_Drive.setPositionAUTO(distance);
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return  Math.abs((left.getSelectedSensorPosition() / 4096 * 2) * Constants.INCHES_PER_ROTATION) >= distance - 8
    || Math.abs((right.getSelectedSensorPosition() / 4096 * 2) * Constants.INCHES_PER_ROTATION) >= distance - 8;
  }
}
