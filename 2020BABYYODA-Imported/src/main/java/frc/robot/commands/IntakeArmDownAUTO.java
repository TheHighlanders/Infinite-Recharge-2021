/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeArm;
import edu.wpi.first.wpilibj.Timer;


public class IntakeArmDownAUTO extends CommandBase {
  /**
   * Creates a new IntakeArmDownAUTO.
   */
  private Timer timer = new Timer();
  private IntakeArm m_IntakeArm = new IntakeArm();
  private boolean done;
  private double timelimit;

  public IntakeArmDownAUTO() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_IntakeArm);
    timelimit = 0.3;
    done = false;
  }

  // Called when the command is initially scheduled.
  @Override

  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (timer.get() > timelimit) {  
      m_IntakeArm.ArmStop();    
      done = true;
    } 
    else {
      m_IntakeArm.ArmDown();
    }
     
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
