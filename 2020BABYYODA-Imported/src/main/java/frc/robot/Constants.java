/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final int XBOX_PORT = 0;
    public static final int CONTROL2_PORT = 2;
    public static final int CONTROL3_PORT = 1;
    
    public static final int LEFT_WHEELS_1 = 41;//victor 
    public static final int LEFT_WHEELS_2 = 51;//talon
    public static final int RIGHT_WHEELS_1 = 44;//victor 
    public static final int RIGHT_WHEELS_2 = 50;//talon

    public static final int SHOOTER = 42;
    public static final int DOOR = 45;

    public static final int INTAKE_ARM = 52;

    public static final int INTAKE = 54;

    public static final int LEFT_HOOK = 40; 
    public static final int RIGHT_HOOK = 43; //spx

    public static final int LEFT_WINCH = 53;
    public static final int RIGHT_WINCH = 55;

    public static final int CONVEYOR = 46; 

    //the range that the goal has to be in so we can shoot
    public static final double GOAL_LEFT_BOUND = 141.0;//a

    public static final double GOAL_RIGHT_BOUND = 200.0;//b

    public static final double INCHES_PER_ROTATION = 6*Math.PI;

    public static final double GEAR_INCHES = 1*Math.PI;

    public static final int LEFT_LIMIT_SWITCH = 0;

    public static final int RIGHT_LIMIT_SWITCH = 1;

    public static final double CENTER_TARGET = 175;

    public static final double GOAL_ERROR = 10;

    // Button Constants
    // Control 3
    // Control 1 on driver statoin
    public static final int ARM_UP_BUTTON = 6;
    public static final int ARM_DOWN_BUTTON = 7;
    public static final int TELESCOPE_UP_LEFT = 9;
    public static final int TELESCOPE_DOWN_LEFT = 8;
    public static final int TELESCOPE_UP_RIGHT = 11;
    public static final int TELESCOPE_DOWN_RIGHT = 10;
    public static final int BALANCE = 1;
    public static final int WHITE_BUTTON = 5;
    public static final int BLUE_BUTTON = 4;
    public static final int WHITE_BUTTON_2 = 2;
    public static final int RED_BUTTON = 3;

    // Control 2
    public static final int CLIMB_UP_LEFT = 8;
    public static final int CLIMB_UP_RIGHT = 6;
    public static final int CLIMB_DOWN_LEFT = 9;
    public static final int CLIMB_DOWN_RIGHT = 7;
    public static final int SHOOT_BUTTON = 5;
    public static final int INTAKE_IN_BUTTON = 2;
    public static final int INTAKE_OUT_BUTTON = 1;
    public static final int CONVERYOR_IN_BUTTON = 4;
    public static final int CONVERYOR_OUT_BUTTON = 3;
    
   


}