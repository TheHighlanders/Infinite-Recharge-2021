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

public class HookRightUpCMD extends CommandBase {

  private final Telescope m_Hook;
  private DigitalInput limitSwitch = new DigitalInput(Constants.RIGHT_LIMIT_SWITCH);
  private boolean done = false;

  public HookRightUpCMD(Telescope Hook_subsystem) {
    m_Hook = Hook_subsystem;
    addRequirements(m_Hook);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.done = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //if button is not pressed
    boolean switchIsGood = limitSwitch.get() == true;
    DriverStation.reportWarning("Sensor Status: " + switchIsGood, false);
    if(limitSwitch.get() == false){
      m_Hook.HookUpRight();
    }

    //if limit swtich was pressed
    if(limitSwitch.get() == true){
      m_Hook.HookStopRight();
      this.done = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Hook.HookStopRight();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.done;
  }
}
