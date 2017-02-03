package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class SetDrive extends Command{
	
	boolean isFinished = false;
	private double[] power;
	
	public SetDrive(double power){
		requires(Robot.driveTrain);
		this.power[0] = power;
		this.power[1] = power;
	}
	public SetDrive(float velocity, float[] velocityToPower){
		requires(Robot.driveTrain);
		power[0] = velocity * velocityToPower[0];
		power[1] = velocity * velocityToPower[1];
	}
	
	@Override
	protected void execute(){
		Robot.driveTrain.setPower(power);
		isFinished = true;
	}
	@Override
	protected boolean isFinished(){
		return isFinished;
	}

}
