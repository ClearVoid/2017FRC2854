package org.usfirst.frc.team2854.robot.subsystems;

import org.usfirst.frc.team2854.editLib.*;
import org.usfirst.frc.team2854.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDSourceType;

import com.ctre.CANTalon;

public class Climb extends Subsystem{
	RobotDrive drive;
	
	public Climb(Talon[] TALON, int controllerCount){
		
			for (int i = 0; i < controllerCount; i++) {LiveWindow.addActuator("DriveTrain", String.valueOf(i), TALON[i]);}


			drive = new RobotDrive(TALON, controllerCount);
			drive.setSafetyEnabled(true);
			drive.setExpiration(0.1);
			drive.setSensitivity(0.5);
			drive.setMaxOutput(1.0);
	}

	public void setPower(double output){
		drive.setMotorOutput(0, output);
		drive.setMotorOutput(1, output);
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	

}
