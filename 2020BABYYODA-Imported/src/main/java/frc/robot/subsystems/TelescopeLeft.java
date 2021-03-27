/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.DriverStation;

public class TelescopeLeft extends SubsystemBase {

  private WPI_TalonSRX HookLeft;
  private WPI_TalonSRX HookRight;
  private boolean isLeft;

  public TelescopeLeft(boolean isLeft) {
    this.isLeft = isLeft;
    if(this.isLeft)
    {
      this.HookLeft = new WPI_TalonSRX(Constants.LEFT_HOOK); 
    }
    else {
      this.HookRight = new WPI_TalonSRX(Constants.RIGHT_HOOK);
    }
  }

  /*
    Hanging for the Left Side
  */
  public void HookUpLeft(){
    DriverStation.reportWarning("Left hook Up", false);
    HookLeft.set(-0.25);
  }
  
  public void HookDownLeft(){
    DriverStation.reportWarning("Left hook Down", false);
    HookLeft.set(0.25);
  }
  
  public void HookStopLeft(){
    DriverStation.reportWarning("Left hook Stop", false);
    HookLeft.set(0);
  }

  /*
    Hanging for the Right Side
  */
  public void HookUpRight(){
    HookRight.set(0.25);
  }

  public void HookDownRight(){
    HookRight.set(-0.25);
  }

  public void HookStopRight(){
    HookRight.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
