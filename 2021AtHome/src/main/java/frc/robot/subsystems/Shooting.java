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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.OI;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.DriverStation;

public class Shooting extends SubsystemBase {
  /**
   * Creates a new Shooting.
   */
    private final WPI_TalonSRX shootingMotor = new WPI_TalonSRX(Constants.SHOOTER);
    private final double ramp = 0.2;
    public OI shooting_io;
    public double shootingSpeedPercent = 0;
    public double shootingSpeedVelocity = 0;
    public double shootingSpeedVelocityDefault = -3000; 

    private final NetworkTableInstance inst = NetworkTableInstance.getDefault();
    private NetworkTable table;
    private NetworkTableEntry range;
    private NetworkTableEntry isAligned;
    private NetworkTableEntry centerX;
    private double[] rangeValuesForAverage;
    private static int numValuesToAverage = 5;
    private int nextIndex = 0;
    private double averageRange;

  public Shooting() {

    shootingMotor.configFactoryDefault();
    shootingMotor.setNeutralMode(NeutralMode.Brake);
    shootingMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 1000);
    shootingMotor.configNominalOutputForward(0);
    shootingMotor.configNominalOutputReverse(0);
    shootingMotor.configPeakOutputForward(1);
    shootingMotor.configPeakOutputReverse(-1);
    shootingMotor.setInverted(false);

    shootingMotor.config_kF(0, .03);
    shootingMotor.config_kP(0, .05);
    shootingMotor.config_kI(0, 0);
    shootingMotor.config_kD(0, 0);

    this.table = inst.getTable("Test");
    this.range = table.getEntry("Range");
    this.isAligned = table.getEntry("IsAligned");
    this.centerX = table.getEntry("centerX");
    
    shooting_io = new OI();

    this.shootingSpeedPercent = -65;//85
    this.shootingSpeedVelocity = -30000;
    
  }

  public void ShootingReverse(){
    shootingMotor.set(-this.shootingSpeedVelocity);
  }

  public void ShootingLaunch(){
    
    //DriverStation.reportWarning("Bombs Away!" , false);
    //DriverStation.reportWarning("Shooting Speed:" + " " + this.shootingSpeed , false);
    // shootingMotor.set(ControlMode.PercentOutput, this.shootingSpeed/100);
    // shootingMotor.set(ControlMode.Velocity, setpoint * 1000);

    final double setpoint = 210 - 1.03 * averageRange + 0.00189 * Math.pow(averageRange, 2);
    DriverStation.reportWarning("Range: " + this.averageRange + "Setpoint: " + setpoint, false);
    // Enable line below for percentage output control.
    // shootingMotor.set(ControlMode.PercentOutput, this.shootingSpeed/100);
    shootingMotor.set(ControlMode.Velocity, this.shootingSpeedVelocity);
  }

  public void SetSpeedAdd(Double input){
    this.shootingSpeedVelocity = this.shootingSpeedVelocityDefault-input;
  }

  public void SetSpeedDecrease(Double input){
    this.shootingSpeedVelocity = this.shootingSpeedVelocityDefault+input;
  }

  public void SetSpeed(double newSpeed)
  {
    this.shootingSpeedVelocity = newSpeed;
  }




  public void incrementShootSpeed()
  {
    // this.shootingSpeed = this.shootingSpeed + 5;
    // if(this.shootingSpeed > 100000)
    // {
    //   this.shootingSpeed = 1;
    // }
    // DriverStation.reportWarning("Shooting Speed:" + " " + this.shootingSpeed , false);
  }

  public void decrementShootSpeed()
  {
    // this.shootingSpeed = this.shootingSpeed - 5;
    // if(this.shootingSpeed < -100000)
    // {
    //   this.shootingSpeed = -1;
    // }
    // DriverStation.reportWarning("Shooting Speed:" + " " + this.shootingSpeed , false);
  }

  public void ShootingStop(){
    
    //DriverStation.reportWarning("Shooting has stopped", false);
    shootingMotor.set(ControlMode.PercentOutput, 0);
  }


  public int returnVelocity(){
    return shootingMotor.getSelectedSensorVelocity();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    int error = shootingMotor.getClosedLoopError(1);
    if(error != 0)
    {
      DriverStation.reportWarning("Error: " + error, false);
    }
    int position = shootingMotor.getSelectedSensorPosition();
    int velocity = shootingMotor.getSelectedSensorVelocity();
   // DriverStation.reportWarning("Position: " + position + " Velocity: " + velocity, false);
   
    double currentCenter = this.centerX.getDouble(0);
    if(currentCenter < Constants.CENTER_TARGET + Constants.GOAL_ERROR && currentCenter > Constants.CENTER_TARGET - Constants.GOAL_ERROR)
    {
      this.isAligned.setBoolean(true);
    }
    else
    {
      this.isAligned.setBoolean(false);
    }
    
    if(this.range != null)
    {
      try {
        double newRangeValue = this.range.getDouble(0);
        rangeValuesForAverage[nextIndex] = newRangeValue;
        double total = 0;
        for(int i = 0;  i < numValuesToAverage; i++)
        {
          total += rangeValuesForAverage[i];
        }
        this.averageRange = total/numValuesToAverage;
        nextIndex++;
        DriverStation.reportWarning("Range: " + this.averageRange, false);
      } catch (Exception e) {
        //TODO: handle exception
      }
      finally{}
    }
  }
}
