package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.commands.*;
import org.usfirst.frc.team2854.robot.subsystems.*;
import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetDriveToJoy extends Command{
	private boolean isFinished = false;
	private float maxVelocity;
	public SetDriveToJoy(float maxVelocity){
		requires(Robot.driveTrain);
		this.maxVelocity = maxVelocity;
	}
	@Override
	protected void execute(){
		
		Robot.driveTrain.setVelocity(Robot.stick[0].getY(),Robot.stick[1].getY());
		
	}

	@Override
	protected boolean isFinished(){
		return isFinished;
	}

	@Override
	protected void end(){
		
	}
}
