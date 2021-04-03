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
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;



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
  private final Drive m_robotDrive = new Drive();
  private final ClimberLeft m_climberLeft = new ClimberLeft(true);
  private final Climber m_climberRight = new Climber(false);
  private final Conveyor m_Conveyor = new Conveyor();
  private final Shooting m_Shooting = new Shooting();
  private final IntakeArm m_IntakeArm = new IntakeArm();
  private final IntakeBrush m_IntakeBrush = new IntakeBrush();
  private final Door m_Door = new Door();
  private Command m_autoCommand;
 // private final Vision m_Vision = new Vision();
  

  
  /** private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

*/

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
   configureButtonBindings();
   // m_autoCommand = new AlignCmd(m_robotDrive);
    // m_autoCommand = new AutoGroup("Back",m_robotDrive, m_Shooting, m_Conveyor, m_IntakeBrush);

    m_autoCommand = new AutoGroup("Back", m_robotDrive, m_Shooting, m_Conveyor, m_IntakeBrush, m_Door);
    
    m_robotDrive.setDefaultCommand(new TeleopDriveCMD(m_robotDrive, m_OI));

    DriverStation.reportWarning("Initialized",false);

    m_robotDrive.left1.setSelectedSensorPosition(0);

    new SetSpeedDecrease(m_Shooting, m_OI);
    new SetSpeedAdd(m_Shooting, m_OI);
    
    
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    //Joystick joystick2 = new Joystick(Constants.CONTROL2_PORT);
    //Joystick joystick3 = new Joystick(Constants.CONTROL3_PORT);

    // Xbox is Controller #1 (In Port 1)
    // Left side of the Control Panel is Controller #3 (In Port 3)
    // Right side of the Control Panel is Controller #2 (In Port 2)

    /*
      Controller #3
      Moving the Intake Arm up and down (Called Intake Up and Down on the Panel)
    */
    JoystickButton ArmUp = new JoystickButton(m_OI.Control3,Constants.ARM_UP_BUTTON);
    JoystickButton ArmDown = new JoystickButton(m_OI.Control3,Constants.ARM_DOWN_BUTTON);
    ArmUp.whileHeld(new IntakeArmUpCMD(m_IntakeArm));
    ArmDown.whileHeld(new IntakeArmDownCMD(m_IntakeArm));
    
    /*
      Controller #2
      Intake in and out (Called Intaker IN and OUT on the Panel)
    */
    JoystickButton IntakeIn = new JoystickButton(m_OI.Control2, Constants.INTAKE_IN_BUTTON);
    JoystickButton IntakeOUT = new JoystickButton(m_OI.Control2, Constants.INTAKE_OUT_BUTTON);
    IntakeIn.whileHeld(new IntakeInCMD(m_IntakeBrush));
    IntakeOUT.whileHeld(new IntakeOutCmd(m_IntakeBrush));
    
    /*
      Controller #2
      Conveyor in and out
    */
    JoystickButton ConveyorIn = new JoystickButton(m_OI.Control2, Constants.CONVERYOR_IN_BUTTON);
    JoystickButton ConveyorOut = new JoystickButton(m_OI.Control2, Constants.CONVERYOR_OUT_BUTTON);
    ConveyorIn.whileHeld(new ConveyorInCMD(m_Conveyor, -0.5));
    ConveyorOut.whileHeld(new ConveyorOutCMD(m_Conveyor));

    /*
      Drive #2 controllers
      Climbing: Moving the robot up
    */
     
    // Initialize buttons. This is actually being controlled in the climber subsystem.
    // JoystickButton ClimbLeft = new JoystickButton(m_OI.Control3, 1);
    // JoystickButton ClimbRight = new JoystickButton(m_OI.Control3, 5);

    JoystickButton ClimbUpLeft = new JoystickButton(m_OI.Control2, Constants.CLIMB_UP_LEFT);
    ClimbUpLeft.whileHeld(new SetShootingSpeed(m_climberLeft));
    JoystickButton ClimbUpRight = new JoystickButton(m_OI.Control2, Constants.CLIMB_UP_RIGHT);
    ClimbUpRight.whileHeld(new ClimberUpRight(m_climberRight));
    JoystickButton ClimbDownLeft = new JoystickButton(m_OI.Control2, Constants.CLIMB_DOWN_LEFT);
    ClimbDownLeft.whileHeld(new ClimberDownLeft(m_climberLeft));
    JoystickButton ClimbDownRight = new JoystickButton(m_OI.Control2, Constants.CLIMB_DOWN_RIGHT);
    ClimbDownRight.whileHeld(new ClimberDownRight(m_climberRight));

    
    JoystickButton HookUpLeft = new JoystickButton(m_OI.Control3,Constants.TELESCOPE_UP_LEFT);
    HookUpLeft.whileHeld(new HookLeftUpCMD(m_telescopeLeft));
    JoystickButton HookDownLeft = new JoystickButton(m_OI.Control3,Constants.TELESCOPE_DOWN_LEFT);
    HookDownLeft.whileHeld(new HookLeftDownCMD(m_telescopeLeft));
    JoystickButton HookUpRight = new JoystickButton(m_OI.Control3,Constants.TELESCOPE_UP_RIGHT);
    HookUpRight.whileHeld(new HookRightUpCMD(m_telescopeRight));
    JoystickButton HookDownRight = new JoystickButton(m_OI.Control3,Constants.TELESCOPE_DOWN_RIGHT);
    HookDownRight.whileHeld(new HookRightDownCMD(m_telescopeRight));

    /*
      Controller #2
      Shoot
    */
    JoystickButton Shoot = new JoystickButton(m_OI.Control2, Constants.SHOOT_BUTTON);
    Shoot.whileHeld(new ShootingCMD(m_Shooting));
    // JoystickButton ShootReverse = new JoystickButton(m_OI.Control2, 6);
    // ShootReverse.whileHeld(new ShootingReverseCMD(m_Shooting));
    
    JoystickButton red = new JoystickButton(m_OI.Control2, Constants.CLIMB_UP_LEFT);
    red.whenPressed(new SetShootingSpeed(m_Shooting, .75 * m_Shooting.shootingSpeedVelocity));
    JoystickButton yellow = new JoystickButton(m_OI.Control2, Constants.CLIMB_UP_RIGHT);
    yellow.whenPressed(new SetShootingSpeed(m_Shooting, .8 * m_Shooting.shootingSpeedVelocity));
    JoystickButton green = new JoystickButton(m_OI.Control2, Constants.CLIMB_DOWN_LEFT);
    green.whenPressed(new SetShootingSpeed(m_Shooting, .95 * m_Shooting.shootingSpeedVelocity);
    JoystickButton blue = new JoystickButton(m_OI.Control2, Constants.CLIMB_DOWN_RIGHT);
    blue.whenPressed(new SetShootingSpeed(m_Shooting, 1 * m_Shooting.shootingSpeedVelocity));
    
    

    /*
      Controller #1 Xbox
    */
    JoystickButton xboxA = new JoystickButton(m_OI.xbox,1);
    JoystickButton xboxB = new JoystickButton(m_OI.xbox,2);
    JoystickButton xboxC = new JoystickButton(m_OI.xbox,3);
    JoystickButton xboxD = new JoystickButton(m_OI.xbox,4);
    JoystickButton xboxLeftBumper = new JoystickButton(m_OI.xbox, 5);
    JoystickButton xboxRightBumper = new JoystickButton(m_OI.xbox, 6);

    xboxA.whileHeld(new ShootingCMD(m_Shooting));
    // xboxA.whileHeld(new ShootingGroup(m_Shooting, m_Conveyor, m_IntakeBrush, m_Door));
    xboxB.whileHeld(new ConveyorInCMD(m_Conveyor, -0.5));
    xboxC.whileHeld(new IntakeInCMD(m_IntakeBrush));
    xboxD.whileHeld(new IntakeArmUpCMD(m_IntakeArm));
    // xboxA.whileHeld(new HookLeftUpCMD(m_telescopeLeft));
    // xboxB.whileHeld(new HookRightUpCMD(m_telescopeRight));
    // xboxC.whileHeld(new HookLeftDownCMD(m_telescopeLeft));
    // xboxD.whileHeld(new HookRightDownCMD(m_telescopeRight));

    xboxLeftBumper.whenPressed(new DoorDownCMD(m_Door));
    xboxRightBumper.whenPressed(new DoorUpCMD(m_Door));

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
