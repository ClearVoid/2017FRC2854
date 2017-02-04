package org.usfirst.frc.team2854.robot.commands;

import edu.wpi.first.wpilibj.command.Command;



import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.editLib.*;

public class ResetRobot extends Command {
	private static boolean isFinished;
	
	public ResetRobot(){
		requires(Robot.driveTrain);
		isFinished = false;
	}
	
	@Override 
	protected void initialize(){
		//Reset the current subsystems
		Robot.driveTrain.setPower(0);
		Robot.driveTrain.gyro.reset();
		Robot.driveTrain.encoders[0].reset();
		Robot.driveTrain.encoders[1].reset();
		
	}
	
	@Override 
	protected void execute(){
		isFinished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return isFinished;
	}
	
	@Override
	protected void end(){
		
	}
	

}
