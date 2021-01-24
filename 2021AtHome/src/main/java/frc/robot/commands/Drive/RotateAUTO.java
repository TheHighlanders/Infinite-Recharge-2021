/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class RotateAUTO extends CommandBase {

  private final Drive m_Drive;
  private boolean done;
  private double commandAngle;
  private double startAngle;
  private double targetAngle;
  private int stableCount;

  double kP = 0.006;
  double kI = 0.0001;
  double maxSpeed = 0.6;
  double minSpeed = 0.23;

  public RotateAUTO(Drive m_Drive, double angle) {

    done = false;
  
    this.m_Drive = m_Drive;
    this.commandAngle = angle;
    this.stableCount = 0;
    
    addRequirements(m_Drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    done = false;
    this.startAngle = m_Drive.getHeading();
    this.targetAngle = this.startAngle + this.commandAngle;
    if(this.targetAngle > 180)
    {
      this.targetAngle = (this.targetAngle -360);
    }
    else if(this.targetAngle < -180)
    {
      this.targetAngle = (this.targetAngle + 360) ;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currentAngle = m_Drive.getHeading();

    double error = this.targetAngle - currentAngle;
    if(error > 180)
    {
      error -= 360;
    }
    else if(error < -180)
    {
      error += 360;
    }
    
    

    double setpoint = kP * error;
    double sign = (setpoint < 0) ? -1 : 1;
    double abs = Math.abs(setpoint);
    if(abs > maxSpeed){
      setpoint = sign*maxSpeed;
    }
    else if(abs < minSpeed)
    {
      setpoint = sign*minSpeed;
    }

    DriverStation.reportError("error: " + error + " target: " + this.targetAngle + " speed: " + setpoint, false);
    if(Math.abs(error) < 1)
    {
      this.m_Drive.drivePower(0, 0);
      this.stableCount++;
      if(this.stableCount > 10)
      {
        this.done = true;
      }
      return;
    }
    this.stableCount = 0;
    this.m_Drive.drivePower(setpoint, setpoint);    

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
