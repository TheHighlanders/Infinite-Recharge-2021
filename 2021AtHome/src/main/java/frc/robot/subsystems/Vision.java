/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import grip.JavaGripPipeline;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry; 
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;

import java.util.*;

public class Vision extends SubsystemBase {
  /**
   * Creates a new Vision.
   */
  NetworkTableEntry Distance;
  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable table;
  private boolean TargetAligned = false;
  double xt = 0;
  
  public Vision() {
    DriverStation.reportWarning("Vision Ran" , false);
    table = inst.getTable("datatableeo"); 
    Distance = table.getEntry("Distance");

  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    Distance.setDouble(xt);
    xt += 0.1;
  }
}
