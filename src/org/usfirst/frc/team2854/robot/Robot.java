//KEVIN ZHENG 2854 PROJECT
package org.usfirst.frc.team2854.robot;

import org.usfirst.frc.team2854.robot.commands.*;
import org.usfirst.frc.team2854.robot.commands.auto.*;
import org.usfirst.frc.team2854.robot.commands.maneuvers.*;
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
    SendableChooser<Command> chooser;
	private Command autonomousCommand;
	private RMap rmap = new RMap();
	//ports
	private static int stickPorts[] = {0,1,2,3};
	public static int gyroPort = 2;
	//Things
	private static int stickCount = 2;
	public static Joystick stick[] = new Joystick[stickCount];//stick[0] would be the teleop, and stick[1] would be something else I suppose?
	public static OI oi;
	public static AnalogGyro gyro;
	//subsystems 
	public static DriveTrain driveTrain;
	public static Climb climb;
	//public static ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	//initialize commands
	private static int socketCount = 3;
	//public static UpdateDrive updateDrive = new UpdateDrive(socketCount);
	//public static UpdateVelocity updateVelocity = new UpdateVelocity(socketCount);
	//public static Calibrate calibrate = new Calibrate(0.05f,0.5f,0.01f,0.01f,0);
	
	//velocitySocket 0: Calibrate
	//velocitySocket 1: Drive
	//velocitySocket 2: Rotate
	//velocitySocket 3: CenterRobot;
	//velocitySocket 4: teleOP;

    public void robotInit(){
    	oi = new OI();
    	chooser = new SendableChooser<Command>();
    //	chooser.addDefault("Default Auto", new Auto());
    //	chooser.addObject("My Auto", new Auto());
    	 SmartDashboard.putData("Auto mode", chooser);
    
    	for(int i = 0; i < stickCount; i++){stick[i] = new Joystick(stickPorts[i]);}
    	
    	//gyro = new AnalogGyro(gyroPort);
    	driveTrain = new DriveTrain(RMap.TALON, 4); 
    	climb =  new Climb(RMap.CLIMBTALON,2);
    	
    }
	 
    public void disabledInit(){
    	/**
         * This function is called once each time the robot enters Disabled mode.
         * You can use it to reset any subsystem information you want to clear when
    	 * the robot is disabled.
         */
    }
	
	public void disabledPeriodic(){
		
		Scheduler.getInstance().run();
	}
	
    public void autonomousInit(){
    	Scheduler.getInstance().add(new Auto("10.28.54.31"));
    	
    }

    public void autonomousPeriodic(){
    	Scheduler.getInstance().run();

    	//Scheduler.getInstance().add(updateVelocity);  TODO uncomment
        //Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	//Scheduler.getInstance().add(new ResetRobot());
    	Scheduler.getInstance().add(new DriveCommand(driveTrain, oi));
    	//
    	Scheduler.getInstance().add(new SetClimb(climb,oi));
       // if (autonomousCommand != null) autonomousCommand.cancel();  
    }
    
    public void teleopPeriodic(){
    	
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic(){
    	System.out.println("Test test, Krystal is short");
    	LiveWindow.run();
    }
}
