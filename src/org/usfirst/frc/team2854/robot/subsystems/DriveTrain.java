package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.editLib.RobotDrive;
import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDSourceType;

import java.math.*;

public class DriveTrain extends Subsystem {
	private static final float diameter = 0.05f;// meters
	private static final float pi = 3.1415926553589323f;
	private static final float e = 2.718281828459045235f;
	public static final float width = 1;// in meters
	// WIP
	// **************************************************************************************************************
	
	public static final float[] velocityToPower = {1,1};
	// Assuming that friction is constant
	// These constants are obtained thorough the Calibration command;
	// **************************************************************************************************************

	private static final int driveCimCount = 4;
	private SpeedController[] driveCim = new SpeedController[driveCimCount];
	// 0 = fl,1 = fr, 2 = bl, 3 = br; Even numbers left, odd right; int/2 is
	// front or back

	private static final int encoderCount = 2;
	public Encoder[] encoder = new Encoder[encoderCount];

	private static final int gyroPort = 2;
	public AnalogGyro gyro;
	public RobotDrive drive;

	public DriveTrain() {
		for (int i = 0; i < driveCimCount; i++) {
			driveCim[i] = new Victor(i);
		}
		for (int i = 0; i < driveCimCount; i++) {
			LiveWindow.addActuator("DriveTrain", String.valueOf(i), (Victor) driveCim[i]);
		}

		encoder[1] = new Encoder(1, 2, true, EncodingType.k4X);
		encoder[0] = new Encoder(3, 4, false, EncodingType.k4X);
		encoder[1].setPIDSourceType(PIDSourceType.kDisplacement);
		encoder[0].setPIDSourceType(PIDSourceType.kDisplacement);

		drive = new RobotDrive(driveCim, driveCimCount);
		drive.setSafetyEnabled(true);
		drive.setExpiration(0.1);
		drive.setSensitivity(0.5);
		drive.setMaxOutput(1.0);

		gyro = new AnalogGyro((gyroPort));
		if (Robot.isReal()) {
			gyro.setSensitivity(0.007); // TODO: Handle more gracefully?
		}
		LiveWindow.addSensor("DriveTrain", "Gyro", gyro);

	}

	public void initDefaultCommand() {
	}

	public void setPower(double left, double right) {
		drive.setArrayMotorOutputs(left, right);
	}
	
	public void setPower(double output){
		drive.setArrayMotorOutputs(output);
	}

	public void setPower(double[] output){
		drive.setArrayMotorOutputs(output[0],output[1]);
	}
	
	public void setPower(float[] output){
		drive.setArrayMotorOutputs((double)output[0],(double)output[1]);
	}
	
	public double getAngle(AnalogGyro gyro){
		return gyro.getAngle();
	}
	
	public double getAngle(Encoder[] encoder){
		return (getDistance(encoder[1])-getDistance(encoder[0]))/width;
	}
	
	public double getDistance(Encoder encoder) {
		return encoder.get() * diameter * 90 / pi;// in meters
		
	}

	public float[] rotateEncoder(float theta, float omega,boolean directionLeft,float threshold) {
		float[] output = new float[2];
		if(Math.abs((getDistance(encoder[0]) - getDistance(encoder[1]))) < threshold){
			output[directionLeft ? 1:0] = 0;
		}else{
			output[directionLeft ? 1:0] = omega * width * velocityToPower[directionLeft ? 1:0];
		}
		return output;
	}

	public float[] rotateGyro(float theta,float omega, boolean directionLeft, float threshold){
		float[] output = new float[2];
		if((gyro.getAngle() - theta) < threshold){
			output[directionLeft ? 1:0] = 0;
		}else{
			output[directionLeft ? 1:0] = omega * width * velocityToPower[directionLeft ? 1:0];
		}
		return output;
	}
	
	public float[] driveApproachParameterLinear(float currentX, float targetX, float threshold) {
		float[] output = new float[2];
		output[0] = (targetX - currentX) / targetX;
		output[1] = output[0];
		return output;
	}
	
	public float[] hardDrive(float distance, float velocity, float threshold){
		float[] output = new float[2];
		if(Math.abs((getDistance(encoder[0])+getDistance(encoder[1]))/2 - distance) < threshold){
			output[0] = 0;
			output[1] = output[0];
		}else{
			output[0] = velocityToPower[0] * velocity;
			output[1] = velocityToPower[1] * velocity;
		}
		return output;
	}

}
