package org.usfirst.frc.team2854.robot.commands;

import java.io.IOException;

import org.usfirst.frc.team2854.robot.OI;
import org.usfirst.frc.team2854.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import Networking.*;
import Visual.*;

public class DriveCommand  extends Command{

	private DriveTrain train;
	private OI oi;

	private Client client = new Client("192.168.50.233",44);
	VisualData[] visualData;
	
	public DriveCommand(DriveTrain train, OI oi) {
		super();
		this.train = train;
		this.oi = oi;
	}
	@Override
	protected void initialize(){
	}
	@Override
	protected void execute() {
		try {
			if(client.isNewData()) {
				visualData = VisualData.decode(client.getLatest());
			}
			if(visualData != null) {
				SmartDashboard.putString("visualData-idk", visualData[0].toString());
			}
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		train.setPower(oi.controller0.ary.get(),-oi.controller0.aly.get());
	//I know it doesn't make sense. Just for testing;
	}
	
	@Override
	protected boolean isFinished() {
		return oi.controller0.alt.get()>0 || oi.controller0.art.get()>0;
	}

}
