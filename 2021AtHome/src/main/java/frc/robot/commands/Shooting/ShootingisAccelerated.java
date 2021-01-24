/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooting;

public class ShootingisAccelerated extends CommandBase {

  private final Shooting m_Shooting;
  private boolean isAccelerated;
  private double v;

  public ShootingisAccelerated(Shooting m_Shooting) {
    this.m_Shooting = m_Shooting;
    addRequirements(m_Shooting);
    isAccelerated = false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    isAccelerated = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    v =  m_Shooting.returnVelocity();
    if (v > m_Shooting.shootingSpeedVelocity - 100){
      isAccelerated = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isAccelerated;
  }
}
