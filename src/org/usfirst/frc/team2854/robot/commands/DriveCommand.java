package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.OI;
import org.usfirst.frc.team2854.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand  extends Command{

	private DriveTrain train;
	private OI oi;
	
	public DriveCommand(DriveTrain train, OI oi) {
		super();
		this.train = train;
		this.oi = oi;
	}
	
	@Override
	protected void execute() {
		
		train.setPower(oi.controller0.ary.get(),-oi.controller0.aly.get());
	//I know it doesn't make sense. Just for testing;
	}
	
	@Override
	protected boolean isFinished() {
		return oi.controller0.alt.get()>0 || oi.controller0.art.get()>0;
	}

}
