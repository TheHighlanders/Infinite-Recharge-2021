/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.OI;

public class Conveyor extends SubsystemBase {
  private WPI_TalonSRX conveyorMotor = new WPI_TalonSRX(Constants.CONVEYOR);
  private double ramp = 0.2;
  public OI intake_io;

  
  public Conveyor() {
    conveyorMotor.configOpenloopRamp(ramp,0);
    conveyorMotor.setNeutralMode(NeutralMode.Brake);

    intake_io = new OI();

  }

  public void ConveyorIN(double speed) {
    conveyorMotor.set(speed);
  }

  public void ConveyorOUT() {
    conveyorMotor.set(1);
  }
  
  public void ConveyorSTOP(){
    conveyorMotor.set(0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
