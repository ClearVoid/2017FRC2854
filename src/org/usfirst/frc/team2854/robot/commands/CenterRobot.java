package org.usfirst.frc.team2854.robot.commands;

import java.math.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.subsystems.*;

public class CenterRobot extends Command {
	private static int driveTrainType;
	private static DriveTrain driveTrain;
	private static float targetTheta;
	private float threshold;
	private boolean isFinished = false;

	public CenterRobot(float radius, float targetTheta, float veloticy ,float threshold) {
		requires(Robot.driveTrain);
		this.targetTheta = targetTheta;
		this.threshold = threshold;
		
	}
	private void perpRotate(){
		
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		
		isFinished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return isFinished;
	}
	
	@Override
	protected void end() {
		Scheduler.getInstance().add(new ResetRobot());
		Scheduler.getInstance().run();
		
	}

	protected void interrupted() {
		System.out.println("ERROR: CenterRobot INTERRUPTED");
	}

}
