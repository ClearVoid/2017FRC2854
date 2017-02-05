package org.usfirst.frc.team2854.robot;

import org.usfirst.frc.team2854.robot.commands.*;
import org.usfirst.frc.team2854.robot.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogGyro;

//All of this is hypothetical as of now
public class Robot extends IterativeRobot {
    SendableChooser chooser;
	//ports
	private static int stickPorts[] = {0,1};
	public static int gyroPort = 2;
	//Things
	private static int stickCount = 2;
	public static Joystick stick[] = new Joystick[stickCount];//stick[0] would be the teleop, and stick[1] would be something else I suppose?
	public static OI oi;
	public static AnalogGyro gyro;
	//subsystems
	public static DriveTrain driveTrain;
	//public static ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	//initialize commands
	Command autonomousCommand;
	private static int portCount = 3;
	public static UpdateDrive updateDrive = new UpdateDrive(portCount);
	public static UpdateVelocity updateVelocity = new UpdateVelocity(portCount);
	public static Calibrate calibrate = new Calibrate(0.05f,0.5f,0.01f,0.01f,0);
	//port 0: Calibrate
	//port 1: Drive
	//port 2: Rotate

    public void robotInit(){
    	oi = new OI();
    	chooser = new SendableChooser();
    //	chooser.addDefault("Default Auto", new Auto());
    //	chooser.addObject("My Auto", new Auto());
    	SmartDashboard.putData("Auto mode", chooser);
    	for(int i = 0; i < stickCount; i++){stick[i] = new Joystick(stickPorts[i]);}
    	
    	gyro = new AnalogGyro(gyroPort);
    	driveTrain = new DriveTrain();
    	
    	Scheduler.getInstance().add(calibrate);
    	Scheduler.getInstance().run();
    }
	
    public void disabledInit(){
    	/**
         * This function is called once each time the robot enters Disabled mode.
         * You can use it to reset any subsystem information you want to clear when
    	 * the robot is disabled.
         */
    	Scheduler.getInstance().add(new ResetRobot());
		Scheduler.getInstance().run();
    }
	
	public void disabledPeriodic(){
		
		Scheduler.getInstance().run();
	}
	
    public void autonomousInit(){
    	Scheduler.getInstance().add(new ResetRobot());
        autonomousCommand = (Command) chooser.getSelected();
        
        
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic(){

    	Scheduler.getInstance().add(updateVelocity);
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	Scheduler.getInstance().add(new ResetRobot());
    	
        if (autonomousCommand != null) autonomousCommand.cancel();  
    }
    
    public void teleopPeriodic(){
    	Scheduler.getInstance().add(updateVelocity);
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic(){
    	LiveWindow.run();
    }
}
