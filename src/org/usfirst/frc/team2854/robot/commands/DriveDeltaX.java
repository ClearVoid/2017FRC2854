package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.commands.*;
import org.usfirst.frc.team2854.robot.subsystems.*;
import org.usfirst.frc.team2854.robot.Robot;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class DriveDeltaX extends Command{
	private float velocity = 1;//defulatVelocity
	private float deltaX;
	private int velocitySocket;
	public DriveDeltaX(float velocity, float deltaX, int velocitySocket){
		requires(Robot.driveTrain);
		this.velocity = velocity;
		this.deltaX = deltaX;
		this.velocity = velocitySocket;
	}
	public DriveDeltaX(float deltaX, int velocitySocket){
		requires(Robot.driveTrain);
		this.deltaX = deltaX;
		this.velocity = velocitySocket; 
	}
	
	@Override 
	protected void initialize(){
		Scheduler.getInstance().add(new ResetRobot());
		Scheduler.getInstance().run();
		Robot.driveTrain.setVelocity(velocity);
	}
	
	@Override 
	protected boolean isFinished(){
		return (Robot.driveTrain.getDistance(Robot.driveTrain.encoders[0]) < deltaX);
	}
	
	@Override
	protected void end(){
		Robot.driveTrain.setVelocity(0);
		Scheduler.getInstance().run();
	}
	

}
