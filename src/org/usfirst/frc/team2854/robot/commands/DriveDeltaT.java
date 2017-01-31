package org.usfirst.frc.team2854.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2854.robot.Robot;

public class DriveDeltaT extends Command{
	private static boolean isFinished = false;
	private float startTime;
	private float currentTime;
	private float[] power;
	private float deltaT;
	public DriveDeltaT(float[] velocityToPower, float velocity, float deltaT){
		// deltaT in seconds;
		requires(Robot.driveTrain);
		power[0] = velocity * velocityToPower[0];
		power[1] = velocity * velocityToPower[1];
		this.deltaT = deltaT;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
		currentTime = System.currentTimeMillis();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.driveTrain.setPower(power);
		if((currentTime - startTime) > deltaT*1000){isFinished = true;}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isFinished;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.driveTrain.setPower(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
