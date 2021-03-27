/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.OI;
import frc.robot.subsystems.Shooting;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SetSpeedAdd extends CommandBase {
  /**
   * Creates a new SetSpeedAdd.
   */

  private final Shooting m_Shooting;
  private final OI m_OI;

  public SetSpeedAdd(Shooting m_Shooting, OI m_OI) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_Shooting = m_Shooting;
    this.m_OI = m_OI;
    addRequirements(m_Shooting);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Shooting.SetSpeedDecrease(m_OI.getXboxRightTrigger());
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
