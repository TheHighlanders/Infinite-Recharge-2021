/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;

import frc.robot.Constants;
import frc.robot.OI;

public class Drive extends SubsystemBase {
  public WPI_TalonSRX left1;
  public WPI_VictorSPX left2;
  public WPI_TalonSRX right1;
  public WPI_VictorSPX right2;

  private double ramp = 0.2;
  public OI xbox_io;

  //private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  private AHRS imu = new AHRS(); 

  public Drive() {

    left1 = new WPI_TalonSRX(Constants.LEFT_WHEELS_1);
    left2 = new WPI_VictorSPX(Constants.LEFT_WHEELS_2);
    right1 = new WPI_TalonSRX(Constants.RIGHT_WHEELS_1);
    right2 = new WPI_VictorSPX(Constants.RIGHT_WHEELS_2);

    left1.configOpenloopRamp(ramp,0);
    left2.configOpenloopRamp(ramp,0);
    right1.configOpenloopRamp(ramp,0);
    right2.configOpenloopRamp(ramp,0);
     
    left1.setNeutralMode(NeutralMode.Coast);
    left2.setNeutralMode(NeutralMode.Coast);
    right1.setNeutralMode(NeutralMode.Coast);
    right2.setNeutralMode(NeutralMode.Coast);

    // left1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    // right1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

    xbox_io = new OI();

    left2.follow(left1);
    right2.follow(right1);

  }

  public void drivePower(double left_power, double right_power){
    left1.set(left_power);
    //left2.set(left_power);
    right1.set(right_power);
    //right2.set(right_power);
    // DriverStation.reportWarning("Left Y:" + " " + left_power + "and Right Y: " + right_power , false);
  }

  public void stopPower(){
   drivePower(0,0); 
  }

  public float getHeading(){
    return imu.getYaw();
  }

  public void resetHeading(){
    imu.zeroYaw();
  }

  /**
	 * Sets the drivetrain encoders back to 0 pulses
	 * Only one message will print here
	 * Leave this at -3000?
	 */
  
  public void setEncoders(int pulses){
    // left1.getSensorCollection().setPulseWidthPosition(pulses, 0);
    // right1.getSensorCollection().setPulseWidthPosition(pulses, 0);

  }

  /**
	 * 
	 * @return gets an approximation of the distance traveled in inches
	 *         since reset was last called. Will accumulate error
	 *         over time.
	 * 
	 */
  public double getDistanceTraveled() {
    //DriverStation.reportWarning("Left:" + (-(left1.getSensorCollection().getPulseWidthPosition())) + " Right: " + right1.getSensorCollection().getPulseWidthPosition(), false);
    return -((double) left1.getSensorCollection().getPulseWidthPosition() / 4096.0) * 6 * Math.PI;
  }

  public void setPositionAUTO(double distance){
    left1.set(ControlMode.Position, 4096*distance/Constants.INCHES_PER_ROTATION);
    right1.set(ControlMode.Position, 4096*distance/Constants.INCHES_PER_ROTATION); 
  }


  @Override
  public void periodic() {
    // DriverStation.reportError("RPY: " + imu.getRoll() + ", " + imu.getPitch() + ", " + imu.getYaw(), false);
    // getDistanceTraveled();
  }
}
