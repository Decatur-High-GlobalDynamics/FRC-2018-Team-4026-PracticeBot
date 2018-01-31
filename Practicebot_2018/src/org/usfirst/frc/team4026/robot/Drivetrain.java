package org.usfirst.frc.team4026.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Talon;

public class Drivetrain implements Subsystem{
	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	static final double MAX_BATTERY = 12.3;
	boolean isGyroresetTelop = false;
	
	boolean isInitialized = false;

	Talon rightDriveMotor;
	Talon leftDriveMotor;
	AnalogGyro gyro;
	
	public int init(){
		if(!isInitialized){
		
		rightDriveMotor = new Talon (RIGHT);
		leftDriveMotor = new Talon (LEFT);
		gyro = new AnalogGyro(0);
		gyro.calibrate();

		
		isInitialized = true;
		return 0;
		}
		//Return 1 if tries to reinit
		return 1;
	}
	void tankDrive(Controller driveGamepad)
	{
		double left  = driveGamepad.getLeft();
		double right = driveGamepad.getRight();

		//Cut speed in half
		if(driveGamepad.getRawButton(7))
		{
			right /= 2.0;
			left /= 2.0;
		}
		double avgStick = (right + left) / 2.0;
		if(!driveGamepad.getRawButton(8) && !shouldIHelpDriverDriveStraight())
		{
		setDriveMotors(left, right);
		isGyroresetTelop = false;
		}
		else 
		{
			if(isGyroresetTelop == false)
			{
				gyro.reset();
				isGyroresetTelop = true;
			}
			keepDriveStraight(driveGamepad, avgStick, avgStick, 0);
		}
	}
	void setDriveMotors(double leftPower2, double rightPower2)
	{
			leftDriveMotor.set(-leftPower2);
			rightDriveMotor.set(rightPower2);
	}
	public boolean shouldIHelpDriverDriveStraight() {
		return false;
	}
	double batteryCompensationPct()
	{
		return MAX_BATTERY / RobotController.getBatteryVoltage();
	}

	public void keepDriveStraight(Controller driveGamepad, double leftDriveVel, double rightDriveVel, double targetAngle) 
	{	
		double error = 0, correctionFactor;
		error = targetAngle - gyro.getAngle();
		correctionFactor = (error/75.0);

		if(leftDriveVel > 0.9)
			leftDriveVel = 0.9;
		else if(leftDriveVel < -0.9)
			leftDriveVel = -0.9;

		if(rightDriveVel > 0.9)
			rightDriveVel = 0.9;
		else if(rightDriveVel < -0.9)
			rightDriveVel = -0.9;

		if(targetAngle > (gyro.getAngle() - 0.5) || targetAngle < (gyro.getAngle() + 0.5))
		{
			leftDriveMotor.set(((-leftDriveVel) + correctionFactor) * batteryCompensationPct());
			rightDriveMotor.set((rightDriveVel + correctionFactor) * batteryCompensationPct());
		}
		else
		{
			leftDriveMotor.set(-leftDriveVel * batteryCompensationPct());
			rightDriveMotor.set(rightDriveVel * batteryCompensationPct());
		}
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
