/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShootingGroup extends ParallelCommandGroup {
  /**
   * Creates a new ShootingGroup.
   */
  public ShootingGroup(Shooting m_Shooting, Conveyor m_Conveyor, IntakeBrush m_IntakeBrush, Door m_Door) {
    addCommands(
      new ShootingCMD(m_Shooting),
      new IntakeInCMD(m_IntakeBrush),
      new SequentialCommandGroup(
        new Halt(1.0),
        new ParallelCommandGroup(new ConveyorInCMD(m_Conveyor, -0.9), new DoorUpCMD(m_Door))         
        )

    );
    
  }
}
