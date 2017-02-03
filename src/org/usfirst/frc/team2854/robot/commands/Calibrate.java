package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.subsystems.*;
import org.usfirst.frc.team2854.robot.commands.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;

public class Calibrate extends Command {
	private float[] velocityToPower = { 1, 1 };
	private float velocity = 0.05f;
	private float deltaT = 0.5f;
	private float threshold = 0.01f;

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

	private boolean checkRotate(AnalogGyro gyro) {
		if (gyro.getAngle() < threshold)
			return true;
		return false;
	}

	private boolean calibrateVelocityToPower(Encoder[] encoders) {
		boolean calibrated = true;
		for (int i = 0; i < 2; i++) {
			if (Math.abs(Robot.driveTrain.getDistance(encoders[i]) - velocity * deltaT) > threshold) {
				calibrated = false;
				velocityToPower[i] = (float) (velocity * deltaT / Robot.driveTrain.getDistance(encoders[i]));
			}
		}
		return calibrated;
	}

	@Override
	protected void initialize() {
		Scheduler.getInstance().add(new ResetRobot());
		Scheduler.getInstance().add(new SetDrive(velocity, velocityToPower));
		Scheduler.getInstance().run();
	}

	@Override
	protected void execute() {
		if (calibrateVelocityToPower(Robot.driveTrain.encoders)) {
			
			Scheduler.getInstance().add(new ResetRobot());
			Scheduler.getInstance().run();
			
			if (checkRotate(Robot.driveTrain.gyro)) {
				isFinished = true;
			}
			
		}
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
	
	@Override	
	protected void interrupted() {
		System.out.println("ERROR: Calibration INTERRUPTED");
	}
}
