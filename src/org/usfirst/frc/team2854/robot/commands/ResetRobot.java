package org.usfirst.frc.team2854.robot.commands;

import edu.wpi.first.wpilibj.command.Command;



import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.editLib.*;

public class ResetRobot extends Command {
	Robot robot;
	private boolean isFinished;
	public ResetRobot(Robot robot){
		this.robot = robot;
		isFinished = false;
	}
	@Override 
	protected void execute(){
		//Reset the current subsystems
		robot.driveTrain.drive.setArrayMotorOutputs(0);
		
		isFinished = true;
	}
	@Override
	protected boolean isFinished() {
		return isFinished;
	}
	

}
