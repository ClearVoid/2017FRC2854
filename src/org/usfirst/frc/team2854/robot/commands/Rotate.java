package org.usfirst.frc.team2854.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2854.robot.Robot;


public class Rotate extends Command{
	private Float targetTheta;
	private float[] velocities = new float[2];
	private int velocitySocket;
	
	private float threshold = 0.01f;
	
	public Rotate(float targetTheta, float omega, int velocitySocket){
		requires(Robot.driveTrain);
		this.targetTheta = new Float(targetTheta);
		velocities[0] = -omega * Robot.driveTrain.width;
		velocities[1] = omega * Robot.driveTrain.width;
		this.velocitySocket = velocitySocket;
	}
	//15 feet start perp
	
	public Rotate(float omega, int velocitySocket){
		requires(Robot.driveTrain);
		velocities[0] = -omega * Robot.driveTrain.width;
		velocities[1] = omega * Robot.driveTrain.width;
		this.velocitySocket = velocitySocket;
	}
	
	@Override
	protected void execute(){
		if(targetTheta == null)
			Robot.updateVelocity.addVelocity(velocities,velocitySocket);
		else if(targetTheta - Robot.driveTrain.gyro.getAngle() > threshold)
			Robot.updateVelocity.addVelocity(velocities, velocitySocket);
		else
			Robot.updateVelocity.addVelocity(0,velocitySocket);
	}
	
	@Override 
	protected boolean isFinished(){
		return true;
	}
}
