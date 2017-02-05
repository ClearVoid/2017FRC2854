package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.commands.*;
import org.usfirst.frc.team2854.robot.subsystems.*;

import edu.wpi.first.wpilibj.command.Command;

public class UpdateDrive extends Command{
	private boolean isFinished = false;
	private float powers[][];
	private int portCount;
	
	public UpdateDrive(int portCount){
		requires(Robot.driveTrain);
		powers = new float[2][portCount];
		this.portCount = portCount;
	}

	public void clearPort(int port){
		powers[0][port] = 0;
		powers[1][port] = 0;
	}
	
	public void addPower(float[] input, int port){
		powers[0][port] = input[0];
		powers[1][port] = input[1];
	}
	
	private float[] sumPower(){
		float[] power = new float[2];
		for(int i = 0; i < portCount; i++){
			power[0] = powers[0][i];
			power[1] = powers[1][i];
		}
		return power;
	}
	
	@Override
	protected void execute(){
		Robot.driveTrain.setPower(sumPower());
		isFinished = true;
	}
	
	@Override
	protected boolean isFinished(){
		return isFinished;
	}

}
