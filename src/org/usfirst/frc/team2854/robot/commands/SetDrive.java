package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Joystick;


public class SetDrive extends Command{
	
	boolean isFinished = false;
	private double[] power;
	private float maxVelocity = 1;
	
	
	public SetDrive(float velocity){
		requires(Robot.driveTrain);
		power[0] = velocity * Robot.driveTrain.velocityToPower[0];
		power[1] = velocity * Robot.driveTrain.velocityToPower[1];
	}
	public SetDrive(Joystick[] stick){
		requires(Robot.driveTrain);
		power[0] = maxVelocity * stick[0].getY() * Robot.driveTrain.velocityToPower[0];
		power[1] = maxVelocity * stick[1].getY() * Robot.driveTrain.velocityToPower[1];
		
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
