package org.usfirst.frc.team2854.robot.commands.maneuvers;

import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class DriveDeltaA extends Command{
//	
	
	@Override
	protected void execute(){
		//<3
	}
	public DriveDeltaA() {
		requires(Robot.driveTrain);
	}
	@Override
	protected boolean isFinished() {
		return true;
	}

}
