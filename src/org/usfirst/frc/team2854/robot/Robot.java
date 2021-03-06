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

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//ports
	int stickPorts[] = {0,1};
	public static int gyroPort = 2;
	//Things
	private static int stickCount = 2;
	public static Joystick stick[] = new Joystick[stickCount];//stick[0] would be the teleop, and stick[1] would be something else I suppose?
	public static OI oi;
	public static AnalogGyro gyro;
	//subsystems
	public static DriveTrain driveTrain;
	public static ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	
    Command autonomousCommand;
    SendableChooser chooser;

    public void robotInit(){
    	oi = new OI();
    	chooser = new SendableChooser();
    	chooser.addDefault("Default Auto", new ExampleCommand());
//      chooser.addObject("My Auto", new MyAutoCommand());
    	SmartDashboard.putData("Auto mode", chooser);
    	for(int i = 0; i < stickCount; i++){stick[i] = new Joystick(stickPorts[i]);}
    	
    	
    	gyro = new AnalogGyro(gyroPort);
    	
    	driveTrain = new DriveTrain();
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
    	/**
    	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
    	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
    	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
    	 * below the Gyro
    	 *
    	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
    	 * or additional comparisons to the switch structure below with additional strings & commands.
    	 */
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic(){    
    	/**
         * This function is called periodically during autonomous
         */
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();  
    }
    public void teleopPeriodic(){
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {   
    	/**
         * This function is called periodically during test mode
         */
        LiveWindow.run();
    }
}
