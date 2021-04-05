// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.Shooting;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class BetterSetShootingSpeed extends CommandBase {
  private final Shooting m_Shooting;
  private double newSpeed;
  
  /** Creates a new BetterSetShootingSpeed. */
  public BetterSetShootingSpeed(Shooting shooting_subsystem, double newSpeed) {

      m_Shooting = shooting_subsystem;
      this.newSpeed = newSpeed;
      addRequirements(m_Shooting);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DriverStation.reportWarning("test", false);
    m_Shooting.SetSpeed(newSpeed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Shooting.ShootingStop();
    DriverStation.reportWarning("betterEnd", false);
  }// Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
