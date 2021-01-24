/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Conveyor;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

public class ConveyorPulse extends SequentialCommandGroup {

  public ConveyorPulse(Conveyor m_Conveyor, Double speedIn, Double speedOut, Double timeIn, Double timeOut, Double haltTime1, Double haltTime2) {
    // Remember negative speed is in and pos speed is out
    addCommands(
      new ConveyorPowerCMD(m_Conveyor, speedIn).withTimeout(timeIn),
      new Halt(haltTime1),
      new ConveyorPowerCMD(m_Conveyor, speedOut).withTimeout(timeOut),
      new Halt(haltTime2)
    );
    // Example Conveyor Shake back and forth is ConveyorPulse(m_Conveyor, -1,1,0.5,0.5,0.0,0.0)
    // Pulse in would be ConveyorPulse(m_Conveyor,-1,0.0,0.5,0,0.5,0)
  }
}
