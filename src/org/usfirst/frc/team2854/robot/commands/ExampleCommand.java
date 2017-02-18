package org.usfirst.frc.team2854.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.usfirst.frc.team2854.robot.Robot;

import Networking.Client;

/**
 *
 */
public class ExampleCommand extends Command {
	
	private Client c;
	
	public ExampleCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.exampleSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println("Running Command");
		try {
		c = new Client("10.28.54.31", 44);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		while(c.isNewData()) {
		System.out.println(c.getLatest());
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
