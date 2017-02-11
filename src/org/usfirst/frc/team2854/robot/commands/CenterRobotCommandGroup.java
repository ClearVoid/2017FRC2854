package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.subsystems.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterRobotCommandGroup extends CommandGroup {
	private float pi = 3.141592653589323f;
	private float defaultVelocity = 1;
	private boolean simple = true;
	private float arbitraryDistance = 1;
	private float targetDistance =  1;//Distance away from the target
	
	public CenterRobotCommandGroup(float displaceTheta,float radius,int velocitySocket){
		if(simple){
			addSequential(new Rotate(pi/2, defaultVelocity/Robot.driveTrain.width, velocitySocket));
			addSequential(new DriveDeltaX((float) Math.sin(displaceTheta) * radius,defaultVelocity,velocitySocket));
			addSequential(new Rotate(pi/2, defaultVelocity/Robot.driveTrain.width, velocitySocket));
			addSequential(new DriveDeltaX((float) Math.cos(displaceTheta) * radius, defaultVelocity, velocitySocket));
		}else{
			addSequential(new Rotate(displaceTheta, defaultVelocity/Robot.driveTrain.width,velocitySocket));
			addSequential(new DriveDeltaX((float) arbitraryDistance, defaultVelocity,velocitySocket));
			addSequential(new Rotate(pi/2 - displaceTheta, defaultVelocity/Robot.driveTrain.width,velocitySocket));
			addSequential(new DriveDeltaX((float)(Math.sin(displaceTheta) * radius) * (1 - (arbitraryDistance/radius)),defaultVelocity,velocitySocket));
			addSequential(new Rotate(-pi/2,defaultVelocity/Robot.driveTrain.width,velocitySocket));
			addSequential(new DriveDeltaX((float)Math.cos(displaceTheta) * arbitraryDistance  - targetDistance, defaultVelocity,velocitySocket));
		}
	}
	public CenterRobotCommandGroup(float displaceTheta,float velocity,float radius, int velocitySocket){
		if(simple){
			addSequential(new Rotate(pi/2, velocity/Robot.driveTrain.width, velocitySocket));
			addSequential(new DriveDeltaX((float) Math.sin(displaceTheta) * radius,velocity,velocitySocket));
			addSequential(new Rotate(pi/2, velocity/Robot.driveTrain.width, velocitySocket));
			addSequential(new DriveDeltaX((float) Math.cos(displaceTheta) * radius, velocity, velocitySocket));
		}else{
			addSequential(new Rotate(displaceTheta, velocity/Robot.driveTrain.width,velocitySocket));
			addSequential(new DriveDeltaX((float) arbitraryDistance, velocity,velocitySocket));
			addSequential(new Rotate(pi/2 - displaceTheta, velocity/Robot.driveTrain.width,velocitySocket));
			addSequential(new DriveDeltaX((float)(Math.sin(displaceTheta) * radius) * (1 - (arbitraryDistance/radius)),velocity,velocitySocket));
			addSequential(new Rotate(-pi/2,velocity/Robot.driveTrain.width,velocitySocket));
			addSequential(new DriveDeltaX((float)Math.cos(displaceTheta) * arbitraryDistance  - targetDistance,velocity,velocitySocket));
		}
	}
}
