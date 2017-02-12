package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.OI;
import org.usfirst.frc.team2854.robot.subsystems.*;

import edu.wpi.first.wpilibj.command.Command;

public class SetClimb  extends Command{

	private Climb climb;
	private OI oi;
	
	public SetClimb(Climb climb, OI oi) {
		super();
		this.climb = climb;
		this.oi = oi;
	}
	
	@Override
	protected void execute() {
		climb.setPower(oi.controller0.alt.get());
	}
	
	@Override
	protected boolean isFinished() {
		return oi.controller0.alt.get()>0 || oi.controller0.art.get()>0;
	}

}
