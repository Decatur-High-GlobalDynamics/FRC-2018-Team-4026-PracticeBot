package org.usfirst.frc.team4026.robot;

import edu.wpi.first.wpilibj.Talon;

public class Drivetrain implements Subsystem{
	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	
	boolean isInitialized = false;

	Talon rightDriveMotor;
	Talon leftDriveMotor;
	
	public int init(){
		if(!isInitialized){
		
		rightDriveMotor = new Talon (RIGHT);
		leftDriveMotor = new Talon (LEFT);
		
		isInitialized = true;
		return 0;
		}
		//Return 1 if tries to reinit
		return 1;
	}
	void tankDrive(Controller driveGamepad)
	{
		double right = driveGamepad.getRight();
		double left  = driveGamepad.getLeft();
		setDriveMotors(right, left);
	}
	void setDriveMotors(double rightPower2, double leftPower2)
	{
			leftDriveMotor.set(-rightPower2);
			rightDriveMotor.set(leftPower2);
	}
	private void stopDrive(){
		leftDriveMotor.set(0);
		rightDriveMotor.set(0);
	}
	@Override
	public int shutdown() {
		stopDrive();
		return 1;
	}
}
