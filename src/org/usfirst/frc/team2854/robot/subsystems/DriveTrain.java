package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.editLib.RobotDrive;
import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDSourceType;
//import edu.wpi.first.wpilibj.RobotDrive;

public class DriveTrain extends Subsystem {
	private static final float diameter = 0.05f;// meters
	private static final float pi = 3.1415926553589323f;
	private static final float e = 2.718281828459045235f;
	public static final float width = 1;// in meters
	// WIP
	// **************************************************************************************************************
	public static final float leftPowerToVelocityConstant = 1;
	public static final float rightPowerToVelocityConstant = 1;
	// Assuming that friction is constant (Air resistance is negligible)
	// These constants are obtained thorough the Calibration command;
	// **************************************************************************************************************

	private static final int driveCimCount = 4;
	private SpeedController[] driveCim = new SpeedController[driveCimCount];
	// 0 = fl,1 = fr, 2 = bl, 4, br; Even numbers left, odd right; int/2 is
	// front or back

	private static final int encoderCount = 2;
	public Encoder[] encoder = new Encoder[encoderCount];

	private static final int gyroPort = 2;
	private AnalogGyro gyro;
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

	public double getDistance(Encoder encoder) {
		return encoder.get() * diameter * 90 / pi;
	}

	public void rotate(float theta, float omega) {
		
	}

	private static float sigmoid(float x, boolean reflect) {
		x = reflect ? -x : x;
		return (float) (1 / (1 + (Math.pow(e, -x))));
	}

	private static float sigmoidDerivative(float x, boolean reflect) {
		return (reflect ? -1 : 1) * (sigmoid(x, false) * (1 - sigmoid(x, false)));
	}

	public float[] updateDriveApproachParameter(float deltaX, float targetX, float threshold, float[] inputPower){
		sigmoid(,true);
		return inputPower;
	}
}
