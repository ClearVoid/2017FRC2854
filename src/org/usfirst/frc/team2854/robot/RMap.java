package org.usfirst.frc.team2854.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RMap {
	public static CANTalon[] TALON = new CANTalon[4];
	public static Talon[] CLIMBTALON = new Talon[2];
	public RMap(){
		for(int i = 0; i < 4; i ++){
			TALON[i] = new CANTalon(i);
		}
		CLIMBTALON[0] = new Talon(0); // talon 4 and 5 are for the climb System
		CLIMBTALON[1] = new Talon(1); // talon 4 and 5 are for the climb System
	}
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
