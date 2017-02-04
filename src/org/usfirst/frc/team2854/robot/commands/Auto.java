package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.*;
public class Auto extends Command{
	private final int maneuverCount = 3;
	float[][] powers = new float[maneuverCount][2];
	private static boolean isFinished;
	private Command resetRobot = new ResetRobot();

	public Auto() {
		requires(Robot.driveTrain);
	}

	private float[] sumPower() {
		float[] finalPower = new float[2];
		finalPower[0] = 0;
		finalPower[1] = 0;
		for (int i = 0; i < maneuverCount; i++) {
			finalPower[0] = finalPower[0] + powers[i][0];
			finalPower[1] = finalPower[1] + powers[i][1];
		}
		return finalPower;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		if (true) {
			isFinished = true;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isFinished;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Scheduler.getInstance().add(resetRobot);
		Scheduler.getInstance().run();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		System.out.println("INTERRUPTED DURING HARD AUTO");
	}

}
