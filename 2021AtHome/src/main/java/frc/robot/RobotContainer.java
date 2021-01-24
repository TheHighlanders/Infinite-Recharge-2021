/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

import frc.robot.commands.Conveyor.*;
import frc.robot.commands.Door.*;
import frc.robot.commands.Drive.*;
import frc.robot.commands.Hook.*;
import frc.robot.commands.IntakeArm.*;
import frc.robot.commands.IntakeBrush.*;
import frc.robot.commands.Shooting.*;
import frc.robot.commands.Telescope.*;
import frc.robot.commands.Vision.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final OI m_OI = new OI();
  private final Telescope m_telescopeLeft = new Telescope(true);
  private final Telescope m_telescopeRight = new Telescope(false);
  private final Drive m_Drive = new Drive();
  private final ClimberLeft m_climberLeft = new ClimberLeft(true);
  private final Climber m_climberRight = new Climber(false);
  private final Conveyor m_Conveyor = new Conveyor();
  private final Shooting m_Shooting = new Shooting();
  private final IntakeArm m_IntakeArm = new IntakeArm();
  private final IntakeBrush m_IntakeBrush = new IntakeBrush();
  private final Door m_Door = new Door();  
  //private final Vision m_Vision = new Vision();
  private Command m_autoCommand;
  
 

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  
  public RobotContainer() {
    
    DriverStation.reportWarning("Initialized",false);

    // Configure the button bindings
    configureButtonBindings();
    configureLeftControlPanel();
    configureRightControlPanel();
    configureXboxBindings();
    // configureTestBindings();

    // Configure Auto Commands
    // m_autoCommand = new AlignCmd(m_Drive);
    m_autoCommand = new AutoGroup("Back", m_Drive, m_Shooting, m_Conveyor, m_IntakeBrush, m_Door);
    
    // Configure Commands

    m_Drive.setDefaultCommand(new TeleopDriveCMD(m_Drive, m_OI));
    
    // Configure Sensors

    m_Drive.left1.setSelectedSensorPosition(0);
    m_Drive.right1.setSelectedSensorPosition(0);
    
    
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void configureLeftControlPanel(){
    // Configure Left Control Panel, Port 3

    // IntakeArm
    JoystickButton ArmUp = new JoystickButton(m_OI.Control3,Constants.ARM_UP_BUTTON);
    JoystickButton ArmDown = new JoystickButton(m_OI.Control3,Constants.ARM_DOWN_BUTTON);
    ArmUp.whileHeld(new IntakeArmUpCMD(m_IntakeArm));
    ArmDown.whileHeld(new IntakeArmDownCMD(m_IntakeArm));

    // Hook?? Why is constants called telescope?

    JoystickButton HookUpLeft = new JoystickButton(m_OI.Control3,Constants.TELESCOPE_UP_LEFT);
    HookUpLeft.whileHeld(new HookLeftUpCMD(m_telescopeLeft));
    JoystickButton HookDownLeft = new JoystickButton(m_OI.Control3,Constants.TELESCOPE_DOWN_LEFT);
    HookDownLeft.whileHeld(new HookLeftDownCMD(m_telescopeLeft));
    JoystickButton HookUpRight = new JoystickButton(m_OI.Control3,Constants.TELESCOPE_UP_RIGHT);
    HookUpRight.whileHeld(new HookRightUpCMD(m_telescopeRight));
    JoystickButton HookDownRight = new JoystickButton(m_OI.Control3,Constants.TELESCOPE_DOWN_RIGHT);
    HookDownRight.whileHeld(new HookRightDownCMD(m_telescopeRight));

  }

  private void configureRightControlPanel(){
    // Configure Left Control Panel, Port 2

    // Intake Brush
    JoystickButton IntakeIn = new JoystickButton(m_OI.Control2, Constants.INTAKE_IN_BUTTON);
    JoystickButton IntakeOUT = new JoystickButton(m_OI.Control2, Constants.INTAKE_OUT_BUTTON);
    IntakeIn.whileHeld(new IntakeInCMD(m_IntakeBrush));
    IntakeOUT.whileHeld(new IntakeOutCmd(m_IntakeBrush));
    
    // Conveyor
    JoystickButton ConveyorIn = new JoystickButton(m_OI.Control2, Constants.CONVERYOR_IN_BUTTON);
    JoystickButton ConveyorOut = new JoystickButton(m_OI.Control2, Constants.CONVERYOR_OUT_BUTTON);
    ConveyorIn.whileHeld(new ConveyorPowerCMD(m_Conveyor, -0.5));
    ConveyorOut.whileHeld(new ConveyorPowerCMD(m_Conveyor, 1.0));
    
    // Climb ??
    JoystickButton ClimbUpLeft = new JoystickButton(m_OI.Control2, Constants.CLIMB_UP_LEFT);
    ClimbUpLeft.whileHeld(new ClimberUpLeft(m_climberLeft));
    JoystickButton ClimbUpRight = new JoystickButton(m_OI.Control2, Constants.CLIMB_UP_RIGHT);
    ClimbUpRight.whileHeld(new ClimberUpRight(m_climberRight));
    JoystickButton ClimbDownLeft = new JoystickButton(m_OI.Control2, Constants.CLIMB_DOWN_LEFT);
    ClimbDownLeft.whileHeld(new ClimberDownLeft(m_climberLeft));
    JoystickButton ClimbDownRight = new JoystickButton(m_OI.Control2, Constants.CLIMB_DOWN_RIGHT);
    ClimbDownRight.whileHeld(new ClimberDownRight(m_climberRight));
    
    // Shoot

    JoystickButton Shoot = new JoystickButton(m_OI.Control2, Constants.SHOOT_BUTTON);
    Shoot.whileHeld(new ShootingCMD(m_Shooting));
    // JoystickButton ShootReverse = new JoystickButton(m_OI.Control2, 6);
    // ShootReverse.whileHeld(new ShootingReverseCMD(m_Shooting));   
    
    // Shooting Speed Set
    JoystickButton red = new JoystickButton(m_OI.Control2, Constants.WHITE_BUTTON);
    red.whenPressed(new SetShootingSpeed(m_Shooting, .75 * -30000));
    JoystickButton yellow = new JoystickButton(m_OI.Control2, Constants.BLUE_BUTTON);
    yellow.whenPressed(new SetShootingSpeed(m_Shooting, .8 * -30000));
    JoystickButton green = new JoystickButton(m_OI.Control2, Constants.WHITE_BUTTON_2);
    green.whenPressed(new SetShootingSpeed(m_Shooting, .95 * -30000));
    JoystickButton blue = new JoystickButton(m_OI.Control2, Constants.RED_BUTTON);
    blue.whenPressed(new SetShootingSpeed(m_Shooting, 1 * -30000));

  }
  
  private void configureButtonBindings() {
    
    // Initialize buttons. This is actually being controlled in the climber subsystem.
    // JoystickButton ClimbLeft = new JoystickButton(m_OI.Control3, 1);
    // JoystickButton ClimbRight = new JoystickButton(m_OI.Control3, 5);
  }

  private void configureXboxBindings(){
    // Xbox Bindings Port 0

    JoystickButton xboxA = new JoystickButton(m_OI.xbox,1);
    JoystickButton xboxB = new JoystickButton(m_OI.xbox,2);
    JoystickButton xboxC = new JoystickButton(m_OI.xbox,3);
    JoystickButton xboxD = new JoystickButton(m_OI.xbox,4);
    JoystickButton xboxLeftBumper = new JoystickButton(m_OI.xbox, 5);
    JoystickButton xboxRightBumper = new JoystickButton(m_OI.xbox, 6);

    // xboxA.whileHeld(new ShootingCMD(m_Shooting));
    // xboxA.whileHeld(new ShootingGroup(m_Shooting, m_Conveyor, m_IntakeBrush, m_Door));
    // xboxB.whileHeld(new ConveyorInCMD(m_Conveyor, -0.5));
    // xboxC.whileHeld(new IntakeInCMD(m_IntakeBrush));
    // xboxD.whileHeld(new IntakeArmUpCMD(m_IntakeArm));
    // xboxA.whileHeld(new HookLeftUpCMD(m_telescopeLeft));
    // xboxB.whileHeld(new HookRightUpCMD(m_telescopeRight));
    // xboxC.whileHeld(new HookLeftDownCMD(m_telescopeLeft));
    // xboxD.whileHeld(new HookRightDownCMD(m_telescopeRight));

    xboxLeftBumper.whenPressed(new DoorDownCMD(m_Door));
    xboxRightBumper.whenPressed(new DoorUpCMD(m_Door));
  }

  private void configureTestBindings(){
    // Put Test Bindings in here comment out in constructor if not to be used
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */ 
  
  
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
     return m_autoCommand;
     
  } 
}
