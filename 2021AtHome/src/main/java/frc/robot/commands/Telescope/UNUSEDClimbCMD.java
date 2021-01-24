/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Telescope;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj.Joystick;

public class UNUSEDClimbCMD extends CommandBase {
  
  private final Climber m_climber; 
  private final Joystick joystick;
  private boolean isLeft = false;
  public UNUSEDClimbCMD(Climber Climb_subsystem, Joystick joystick, boolean isLeft) {
    m_climber = Climb_subsystem;
    this.joystick = joystick;
    this.isLeft = isLeft;
    addRequirements(Climb_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(this.isLeft)
    {
      if (joystick.getY()>.5){
        m_climber.ClimbUpLeft();
      }
      else if (joystick.getY()<-.5){
        m_climber.ClimbDownLeft();
      }
      else {
        m_climber.ClimbStopLeft();
      }
  }
  else {
    
      if (joystick.getY()>.5){
        m_climber.ClimbUpRight();
      }
      else if (joystick.getY()<-.5){
        m_climber.ClimbDownRight();
      }
      else {
        m_climber.ClimbStopRight();
      }
    }
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
