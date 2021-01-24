/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


/** This Command will transfer the data needed to drive during teleop. */


package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.OI;

public class TeleopDriveCMD extends CommandBase {
  /**
   * m_LeftYJoy and m_RightYJoy ranges from -1 to 1
   */

  private final Drive m_Drive;
  private final OI m_OI;

  public TeleopDriveCMD(Drive drive_subsystem, OI xbox_oi) {

    m_Drive = drive_subsystem;
    m_OI = xbox_oi;

    addRequirements(m_Drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // m_Drive.drivePower(-Math.pow(this.m_OI.getXboxLeftY(), 3)/1.25, Math.pow(this.m_OI.getXboxRightY(), 3)/1.25);
    m_Drive.drivePower(-this.m_OI.getXboxLeftY(), this.m_OI.getXboxRightY());
    DriverStation.reportWarning("Left Y:" + " " + m_OI.getXboxLeftY() + "and Right Y: " + m_OI.getXboxRightY() , false);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
