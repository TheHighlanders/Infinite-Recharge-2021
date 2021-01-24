/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooting;

public class SetShootingSpeed extends CommandBase {
  private final Shooting m_Shooting;
  private double newSpeed;


  public SetShootingSpeed(Shooting shooting_subsystem, double newSpeed) {
    m_Shooting = shooting_subsystem;
    this.newSpeed = newSpeed;
    addRequirements(m_Shooting);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.m_Shooting.SetSpeed(this.newSpeed);
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
    return true;
  }
}
