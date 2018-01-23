package org.usfirst.frc.team4026.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controller{
	Joystick driveGamepad;
	public void init(){
		driveGamepad = new Joystick(0);

	}
	public double getLeft(){
		return driveGamepad.getY();
		
		
	}
	public double getRight(){
		return driveGamepad.getThrottle();
	}
	public boolean getRawButton(int button) {
		if (driveGamepad.getRawButton(button)){
			return true;
		}
		else {
		
		return false;
		}
	}

}
