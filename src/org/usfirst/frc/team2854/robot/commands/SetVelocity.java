package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class SetVelocity extends Command{
	private int port;
	private Float velocity;
	private float targetX;
	private float threshold = 0.01f;
	
	public SetVelocity(float targetX, float velocity, int port){
		requires(Robot.driveTrain);
		this.port = port;
		this.velocity = new Float(velocity);
		this.targetX = targetX;
	}
	
	public SetVelocity(float velocity, int port){
		requires(Robot.driveTrain);
		this.port = port;
		this.velocity = velocity;
	}
	
	@Override
	protected void execute(){
		if(velocity == null)
			Robot.updateVelocity.addVelocity(velocity,port);
		else if (targetX - Robot.driveTrain.getAvgDistance() < threshold){
			Robot.updateVelocity.addVelocity(velocity,port);
		}else{
			Robot.updateVelocity.addVelocity(0,port);
		}
	
	}
	
	@Override
	protected boolean isFinished(){
		return true;
	}
}
