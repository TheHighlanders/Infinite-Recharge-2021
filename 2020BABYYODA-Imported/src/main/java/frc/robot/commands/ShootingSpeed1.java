// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.Constants;
import frc.robot.subsystems.Shooting;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class ShootingSpeed1 extends CommandBase {
  private final Shooting m_Shooting;
  
  private double Speed1 = Constants.SHOOTER_PERCENT_1;

  public ShootingSpeed1(Shooting shooting_subsystem) {
    
    m_Shooting = shooting_subsystem;
    addRequirements(m_Shooting);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DriverStation.reportWarning("executeSpeed1", false);
    //Speed1 = Constants.SHOOTER_PERCENT_1*Shooting.shootingSpeedVelocity;
   
    /*
      This is just calling the speedOne method in the shootingSpeed subsystem
      and running the Constants.SHOOTER_PERCENT_1 * Shooting.shootingSpeedVelocity part
    */
    DriverStation.reportWarning("executeSpeed1" + (Shooting.shootingSpeedVelocity * Constants.SHOOTER_PERCENT_1), false);
    m_Shooting.speedOne();
  }

  @Override
  public void end(boolean interrupted) {
    m_Shooting.ShootingStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
