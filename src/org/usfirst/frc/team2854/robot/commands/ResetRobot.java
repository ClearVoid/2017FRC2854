package org.usfirst.frc.team2854.robot.commands;

import edu.wpi.first.wpilibj.command.Command;



import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.editLib.*;

public class ResetRobot extends Command {
	Robot robot;
	private static boolean isFinished;
	public ResetRobot(){
		requires(Robot.driveTrain);
		isFinished = false;
	}
	@Override 
	protected void execute(){
		//Reset the current subsystems
		Robot.driveTrain.drive.setArrayMotorOutputs(0);
		Robot.driveTrain.gyro.reset();
		isFinished = true;
	}
	@Override
	protected boolean isFinished() {
		return isFinished;
	}
	

}
