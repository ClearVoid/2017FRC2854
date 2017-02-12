package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.editLib.*;
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

import com.ctre.CANTalon;

public class DriveTrain extends Subsystem {
	private static final float diameter = 0.05f;// meters
	private static final float pi = 3.1415926553589323f;
	// /private static final float e = 2.718281828459045235f;
	public static final float width = 1;// in meters
	public float[] velocityToPower = { 1, 1 };
	// These constants are obtained thorough the Calibration command;
//	private static final int driveCimCount = 4;
//	private SpeedController[] driveCim = new SpeedController[driveCimCount];
	public RobotDrive drive;
	// 0 = fl,1 = fr, 2 = bl, 3 = br; Even numbers left, odd right; int/2 is
	// front or back

	private static final int encoderCount = 2;
	public Encoder[] encoders = new Encoder[encoderCount];

	private static final int gyroPort = 2;
	
	public AnalogGyro gyro;
	

	public DriveTrain(CANTalon[] TALON, int controllerCount) {
		for (int i = 0; i < controllerCount; i++) {LiveWindow.addActuator("DriveTrain", String.valueOf(i), TALON[i]);}


		drive = new RobotDrive(TALON, controllerCount);
		drive.setSafetyEnabled(true);
		drive.setExpiration(0.1);
		drive.setSensitivity(0.5);
		drive.setMaxOutput(1.0);
		//'gyro = new AnalogGyro((gyroPort));
		//if (Robot.isReal()) {
			//gyro.setSensitivity(0.007); // TODO: Handle more gracefully?
		//}
		//LiveWindow.addSensor("DriveTrain", "Gyro", gyro);

	}

	public void initDefaultCommand() {
		
	}

	public void setPower(double left, double right) {
		drive.setArrayMotorOutputs(left, right);
	}

	public void setPower(double[] output) {
		drive.setArrayMotorOutputs(output[0], output[1]);
	}

	public void setPower(double output) {
		drive.setArrayMotorOutputs(output, output);
	}

	public void setPower(float left, float right) {
		drive.setArrayMotorOutputs(left, right);
	}

	public void setPower(float[] output) {
		drive.setArrayMotorOutputs(output[0], output[1]);
	}

	public void setPower(float output) {
		drive.setArrayMotorOutputs(output, output);
	}

	public void setVelocity(double left, double right) {
		drive.setArrayMotorOutputs(left * velocityToPower[0], right * velocityToPower[1]);
	}

	public void setVelocity(double[] output) {
		drive.setArrayMotorOutputs(output[0] * velocityToPower[0], output[1] * velocityToPower[1]);
	}

	public void setVelocity(double output) {
		drive.setArrayMotorOutputs(output * velocityToPower[0], output * velocityToPower[1]);
	}

	public void setVelocity(float left, float right) {
		drive.setArrayMotorOutputs(left * velocityToPower[0], right * velocityToPower[1]);
	}

	public void setVelocity(float[] output) {
		drive.setArrayMotorOutputs(output[0] * velocityToPower[0], output[1] * velocityToPower[1]);
	}

	public void setVelocity(float output) {
		drive.setArrayMotorOutputs(output * velocityToPower[0], output * velocityToPower[1]);
	}

	public double getAngle(AnalogGyro gyro) {
		return gyro.getAngle();
	}

	public double getAngle() {
		return (getDistance(encoders[1]) - getDistance(encoders[0])) / width;
	}
	
	public double getDistance(Encoder encoder){
		return (encoder.get()) * diameter * 90 / pi / 2;
	}

	public double getAvgDistance() {
		return (encoders[0].get() + encoders[1].get() ) * diameter * 90 / pi / 2;/// in meter
	}
	
	public double[] getDistances(Encoder[] encoder){
		double[] output = new double[2];
		output[0] = (encoder[0].get()) * diameter * 90 / pi / 2;
		output[1] = (encoder[1].get()) * diameter * 90 / pi / 2;
		
		return output;
	}

}
