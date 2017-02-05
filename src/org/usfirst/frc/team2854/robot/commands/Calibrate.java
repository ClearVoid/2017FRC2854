package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.subsystems.*;
import org.usfirst.frc.team2854.robot.commands.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;

public class Calibrate extends Command {
	private float velocity = 0.05f;
	private float deltaT = 0.5f;
	private float encoderThreshold = 0.01f;
	private float gyroThreshold = 0.01f;

	private static boolean isFinished = false;

	public Calibrate(float velocity, float deltaT, float threshold, float threshold2) {
		requires(Robot.driveTrain);
		this.velocity = velocity;
		this.deltaT = deltaT;
		this.encoderThreshold = threshold;
		this.gyroThreshold = threshold2;
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
		if (gyro.getAngle() < gyroThreshold)
			return true;
		return false;
	}

	private boolean calibrateVelocityToPower(Encoder[] encoders) {
		boolean calibrated = true;
		for (int i = 0; i < 2; i++) {
			if (Math.abs(Robot.driveTrain.getDistance(encoders[i]) - velocity * deltaT) > encoderThreshold) {
				calibrated = false;
				Robot.driveTrain.velocityToPower[i] = (float) (velocity * deltaT / Robot.driveTrain.getDistance(encoders[i]));
			}
		}
		return calibrated;
	}

	@Override
	protected void initialize() {
		Scheduler.getInstance().add(new ResetRobot());
		Scheduler.getInstance().add(new SetDrive(velocity));
		Scheduler.getInstance().run();
	}

	@Override
	protected void execute() {
		Scheduler.getInstance().add(new ResetRobot());
		Scheduler.getInstance().run();
		if (calibrateVelocityToPower(Robot.driveTrain.encoders)) {
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
		limit(Robot.driveTrain.velocityToPower);
		Scheduler.getInstance().add(new ResetRobot());
		Scheduler.getInstance().run();
	}
	
	@Override	
	protected void interrupted() {
		System.out.println("ERROR: Calibration INTERRUPTED");
	}
}
