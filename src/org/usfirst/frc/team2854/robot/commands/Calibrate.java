package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.subsystems.*;
import org.usfirst.frc.team2854.robot.commands.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.AnalogGyro;

public class Calibrate extends Command {
	private float[] velocityToPower = { 1, 1 };
	private float velocity = 0.05f;
	private float deltaT = 0.5f;
	private float threshold = 0.01f;

	Command deltaDrive = new DriveDeltaT(velocityToPower, velocity, deltaT);

	private static boolean isFinished = false;

	public Calibrate() {
		requires(Robot.driveTrain);
	}

	private static float[] limit(float[] input) {
		float ratio;
		if (input[0] > input[1]) {
			ratio = input[1] / input[0];
			input[0] = 1;
			input[1] = ratio;
		} else {
			ratio = input[0] / input[1];
			input[1] = 1;
			input[0] = ratio;
		}
		return input;
	}

	private boolean calibrateRotate() {
		Scheduler.getInstance().add(deltaDrive);
		Scheduler.getInstance().run();
		if (Math.abs(Robot.driveTrain.getAngle(Robot.driveTrain.gyro)) < threshold)
			return true;
		float scaleLeft;
		// s1/s2 = w/d + 1 
		
			
		return false;
	}
	private boolean calibrateRotateEncoder(){



	return false;

	}

	private boolean calibrateVelocityToPower() {
		return false;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (calibrateRotate()) {
			if (calibrateVelocityToPower())
				isFinished = true;
		}
	}

	@Override
	protected boolean isFinished() {

		return isFinished;
	}

	@Override
	protected void end() {

	}

	protected void interrupted() {
		System.out.println("ERROR: Calibration INTERRUPTED");
	}
}
