package org.usfirst.frc.team2854.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.commands.*;
import org.usfirst.frc.team2854.robot.subsystems.*;


public class UpdateVelocity extends Command{
	private boolean isFinished = false;
	private float[][] velocities;
	private float portCount;
	
	public UpdateVelocity(int portCount){
		requires(Robot.driveTrain);
		velocities = new float[2][portCount];
		this.portCount = portCount;
	}
	
	public void clearPort(int port){
		velocities[0][port] = 0;
		velocities[1][port] = 0;
	}
	
	public void addVelocity(float[] input, int port){
		velocities[0][port] = input[0];
		velocities[1][port] = input[1];
	}
	
	public void addVelocity(float input, int port){
		velocities[0][port] = input;
		velocities[1][port] = input;
	}
	
	private float[] sumVelocities(){
		float[] velocity = new float[2];
		for(int i = 0; i < portCount; i++){
			velocity[0] = velocity[0] + velocities[0][i];
			velocity[0] = velocity[0] + velocities[0][i];
		}
		return velocity;
	}
	
	@Override
	protected void execute(){
		Robot.driveTrain.setVelocity(sumVelocities());
		isFinished = true;
	}
	
	@Override
	protected boolean isFinished(){
		return isFinished;
	}
}
