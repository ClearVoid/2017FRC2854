package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.subsystems.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterRobotCommandGroup extends CommandGroup {
	private float pi = 3.141592653589323f;
	private float defaultOmega;
	
	public CenterRobotCommandGroup(float displaceTheta,float radius,int velocitySocket){
		addSequential(new Rotate(pi/2, defaultOmega,velocitySocket));
		addSequential(new Rotate(pi/2, defaultOmega,velocitySocket));
	}
	public CenterRobotCommandGroup(float displaceTheta,float omega,float radius, int velocitySocket){
		addSequential(new Rotate(pi/2, omega, velocitySocket));
		addSequential(new DriveDeltaX());
		addSequential(new Rotate(pi/2, omega, velocitySocket));
		
	}
}
