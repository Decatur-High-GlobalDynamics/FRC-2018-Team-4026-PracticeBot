/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4026.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	
	private static final String K_AUTO_DEFAULT = "Default";
	private static final String K_AUTO_CUSTOM = "My Auto";
	boolean isGyroresetTelop;
	private String mAutoSelected;
	private SendableChooser<String> mChooser = new SendableChooser<>();
	Drivetrain drivetrain = new Drivetrain();
	Controller joystick = new Controller();
	Pneumatics pneumatics = new Pneumatics();
	Sensors sensors = new Sensors();


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		mChooser.addDefault("Default Auto", K_AUTO_DEFAULT);
		mChooser.addObject(K_AUTO_CUSTOM, K_AUTO_CUSTOM);
		SmartDashboard.putData("Auto choices", mChooser);
		drivetrain.init();
		joystick.init();
		pneumatics.init();
		sensors.init();
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		mAutoSelected = mChooser.getSelected();
		System.out.println("Auto selected: " + mAutoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		switch (mAutoSelected) {
			case K_AUTO_CUSTOM:
				autoCustom();// Put custom auto code here
				break;
			case K_AUTO_DEFAULT:
			default:
				autoDefault();// Put default auto code here
				break;
		}
	}
	
	public void autoCustom() {
	//Placeholder for autoCustom	
	}
	public void autoDefault() {
	//Placeholder for autoDefault	
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() { 

		if(!joystick.getRawButton(8) && !drivetrain.shouldIHelpDriverDriveStraight())
		{
			drivetrain.tankDrive(joystick);
			pneumatics.grabberPiston(1,3,joystick);
			isGyroresetTelop = false;
		}
		else {
			if(!isGyroresetTelop)
			{
				sensors.gyro.reset();
				isGyroresetTelop = true;
			}
		drivetrain.keepDriveStraight(joystick, 0 , sensors.gyro);
		}
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		//not needed yet
	}
	
}
