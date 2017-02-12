package org.usfirst.frc.team2854.robot.oi;

import edu.wpi.first.wpilibj.Joystick;


/**
 * Created by Kevin on 10/23/2015.
 */
public class Button extends Control{
  private final int id;
  private boolean held = false;

  public Button(Joystick aJoystick, ButtonType aButtonType){
    super(aJoystick);
    id = evaluateButtonType(aButtonType);
  }
  
  public boolean getHold(){ //stops repeated button presses
	  if(!held && joystick.getRawButton(id)){
		  held = true;
		  return true;
	  }else if(!joystick.getRawButton(id)){
		  held = false;
	  }
	  return false; //button is held or not pressed
  }

  public boolean get(){
    return joystick.getRawButton(id);
  }

}
