package org.usfirst.frc.team2854.robot.commands;

import java.math.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.subsystems.*;

public class CenterRobot extends Command {
	private static int driveTrainType;
	private static DriveTrain driveTrain;
	private static float targetTheta;
	private float threshold;
	private boolean isFinished;

	public CenterRobot(float targetTheta, float omega,float threshold) {
		// driveTrain = Robot.driveTrain;
		requires(Robot.driveTrain);
		this.targetTheta = targetTheta;
		this.threshold = threshold;
		
		isFinished = false;
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
	}

	protected void interrupted() {
		System.out.println("ERROR: CenterRobot INTERRUPTED");
	}

}
