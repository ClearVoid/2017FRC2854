package org.usfirst.frc.team2854.robot.commands;

import edu.wpi.first.wpilibj.command.Command;



public class Rotate extends Command{
	private boolean isFinished = false;
	
	public Rotate(float targetTheta, float velocity){
		
		
	}
	@Override 
	protected boolean isFinished(){
		return isFinished;
	}

}
