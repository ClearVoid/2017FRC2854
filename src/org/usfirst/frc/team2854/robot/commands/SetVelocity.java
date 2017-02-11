package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class SetVelocity extends Command{
	private int velocitySocket;
	private Float velocity;
	private float targetX;
	private float threshold = 0.01f;
	
	public SetVelocity(float targetX, float velocity, int velocitySocket){
		requires(Robot.driveTrain);
		this.velocitySocket = velocitySocket;
		this.velocity = new Float(velocity);
		this.targetX = targetX;
	}
	
	public SetVelocity(float velocity, int velocitySocket){
		requires(Robot.driveTrain);
		this.velocitySocket = velocitySocket;
		this.velocity = velocity;
	}
	
	@Override
	protected void execute(){
		if(velocity == null)
			Robot.updateVelocity.addVelocity(velocity,velocitySocket);
		else if (targetX - Robot.driveTrain.getAvgDistance() < threshold){
			Robot.updateVelocity.addVelocity(velocity,velocitySocket);
		}else{
			Robot.updateVelocity.addVelocity(0,velocitySocket);
		}
	}
	
	@Override
	protected boolean isFinished(){
		return true;
	}
}
