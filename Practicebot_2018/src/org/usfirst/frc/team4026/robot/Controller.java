package org.usfirst.frc.team4026.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controller extends Subsystem{
	
	Joystick driveGamepad;
	boolean isInitialized = false;

	public int init(){
		if(!isInitialized){
			driveGamepad = new Joystick(0);
			isInitialized = true;
		}
	//Return 1 if tries to reinit
	return 1;
	}

	public double getLeft(){
		return driveGamepad.getY();
		
		
	}
	public double getRight(){
		return driveGamepad.getThrottle();
	}
	public boolean getRawButton(int button) {
		return driveGamepad.getRawButton(button);
	}
	@Override
	public int shutdown() {
		
		return 1;
	}

}
