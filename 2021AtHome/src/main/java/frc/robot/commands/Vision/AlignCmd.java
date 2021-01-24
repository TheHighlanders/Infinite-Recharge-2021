/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;


public class AlignCmd extends CommandBase {
  private final Drive m_drive;

  NetworkTableEntry ValueMiddleX;
  NetworkTableEntry ValueMiddleY;
  NetworkTableEntry ValueIsAligned;
  NetworkTableEntry updateCounterEntry;
  NetworkTableEntry range;

  double xValue;
  double yValue;
  boolean isAligned = false;
  
  private int stableCount;
  private boolean done = false;

  double middleOfGoal = 175;

  double perviousUpdateCounter = 0;
  double targetAngle = 0;

  double kP = 0.006;
  double maxSpeed = 0.6;
  double minSpeed = 0.23;
 

  public AlignCmd(Drive drive_subsystem) {
    //getting Drive Train classes
    m_drive = drive_subsystem;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTableInstance networkTables = NetworkTableInstance.getDefault();
    NetworkTable table = networkTables.getTable("Test");
    this.ValueMiddleX = table.getEntry("centerX");
    this.ValueMiddleY = table.getEntry("centerY");
    this.ValueIsAligned = table.getEntry("IsAligned");
    this.updateCounterEntry = table.getEntry("Update Counter");
    this.range = table.getEntry("Range");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double updateCounter = updateCounterEntry.getDouble(0);

    if(this.perviousUpdateCounter != updateCounter){
      this.xValue = ValueMiddleX.getDouble(0);
      this.yValue = ValueMiddleY.getDouble(0);
      this.isAligned = ValueIsAligned.getBoolean(true);
      double newRangeValue = this.range.getDouble(0);

      if(xValue > middleOfGoal + Constants.GOAL_ERROR){
        this.targetAngle =+ 1;
        /*
        just keep this commented out
        double angle = newRangeValue /(middleOfGoal - xValue);
        this.targetAngle = Math.sin(angle);
        */

      }
      //🌓
      else if (xValue < middleOfGoal - Constants.GOAL_ERROR){
        this.targetAngle =- 1; 
         /*
         this too
        double angle = newRangeValue /(middleOfGoal - xValue);
        this.targetAngle = Math.sin(angle);
        */
      }

    }
    this.perviousUpdateCounter = updateCounter;

    double currentAngle = m_drive.getHeading();

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

    if(Math.abs(error) < 1)
    {
      this.m_drive.drivePower(0, 0);
      this.stableCount++;
      if(this.stableCount > 10)
      {
        this.done = true;
      }
      return;
    }
    this.stableCount = 0;
    this.m_drive.drivePower(setpoint, setpoint);    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.isAligned;
    // return false;
  }
}
