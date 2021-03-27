/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Telescope;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;

/*
  Climbs Left Down
*/

public class HookLeftUpCMD extends CommandBase {

  private final Telescope m_Hook;
  DigitalInput limitSwitch = new DigitalInput(Constants.LEFT_LIMIT_SWITCH);

  public HookLeftUpCMD(Telescope Hook_subsystem) {
    m_Hook = Hook_subsystem;
    addRequirements(m_Hook);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //if limit switch was not pressed
    boolean switchIsGood = limitSwitch.get() == true;
    DriverStation.reportWarning("Sensor Status: " + switchIsGood, false);
    if(limitSwitch.get() == false){
      //move left not right 
      DriverStation.reportWarning("Hook left up", false);
      m_Hook.HookUpLeft();
    }
     //if limit switch was pressed
    if(limitSwitch.get() == true){
      m_Hook.HookStopLeft();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Hook.HookStopLeft();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
