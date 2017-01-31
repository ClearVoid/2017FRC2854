package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.subsystems.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.AnalogGyro;

public class Calibrate extends Command{
	private static AnalogGyro gyro;
	private static DriveTrain drive;
	private float[] power = new float[2];
	private static float defaultPower = 0.5f;
	
	public Calibrate(){
		requires(Robot.driveTrain);
		for(int i = 0; i < 2; i++){
			power[i] = 0;
		}
	}
	
	private static float[] limit(float[] input){
		float ratio;
		if(input[0] > input[1]){
			ratio = input[1]/input[0];
			input[0] = 1;
			input[1] = ratio;
		}
		else{
			ratio = input[0]/input[1];
			input[1]= 1;
			input[0] = ratio;
		}
		return input;
	}
	
	protected void initialize(){
		gyro = Robot.gyro;
		drive = Robot.driveTrain;
	}
	
	protected void execute(){
		
	}
	
	protected boolean isFinished(){
		return false;
	}
	
	protected void end(){
		
	}
	
	protected void interrupted() {
		System.out.println("ERROR: Calibration INTERRUPTED");
	}
}
