package org.usfirst.frc.team2854.robot.commands.auto;

import Networking.*;

import org.usfirst.frc.team2854.robot.commands.*;
import org.usfirst.frc.team2854.robot.commands.maneuvers.*;
import org.usfirst.frc.team2854.robot.subsystems.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Auto extends Command{ 	

	private boolean isFinished = false;
	private Client client;
	
	private final String ip;
	private final int port = 44;
	private Command driveAngle;
	private Command	driveDistance;
	private final double defaultOmega = 1;
	private final double defaultVelocity = 1;
	double distance;
	double angle;
	
	public Auto(String ip){
		this.ip = ip;
	}
	
	@Override
	protected void initialize(){
		client = new Client(ip,port);
	}
	
	@Override
	protected void execute(){
		Scheduler.getInstance().removeAll();
		String data = client.getLatest();
		VisualData[] visualData = null;
		try{
			visualData	= VisualData.decode(data);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(visualData);
		distance = (visualData[0].getDistance() + visualData[1].getDistance())/2.0;
		angle = (visualData[0].getAngle() + visualData[1].getAngle())/2.0;
//		driveAngle = new Rotate((float)angle, (float)defaultOmega,2);
//		driveDistance = new SetVelocity((float)distance * 2/3,(float)defaultVelocity,1);
//		Scheduler.getInstance().add(driveAngle);
//		Scheduler.getInstance().add(driveDistance);
//		Scheduler.getInstance().run();
		if(false){isFinished = true;}
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	

}
