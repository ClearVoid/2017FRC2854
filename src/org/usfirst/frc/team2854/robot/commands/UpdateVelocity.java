package org.usfirst.frc.team2854.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.commands.*;
import org.usfirst.frc.team2854.robot.subsystems.*;


public class UpdateVelocity extends Command{
	private boolean isFinished = false;
	private float[][] velocities;
	public UpdateVelocity(int portCount){
		requires(Robot.driveTrain);
		velocities = new float[2][portCount];
	}
	
	@Override
	protected void execute(){
		isFinished = true;
	}
	
	@Override
	protected boolean isFinished(){
		return isFinished;
	}
}
