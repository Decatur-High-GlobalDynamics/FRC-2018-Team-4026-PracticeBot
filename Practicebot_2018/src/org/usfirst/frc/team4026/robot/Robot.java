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

import java.util.logging.Logger;
import java.util.logging.Level;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	
	//SonarLint: Use Logger instead of System.out.println
	private static final Logger LOGGER = Logger.getLogger(Robot.class.getName());
	
	private static final String K_DEFAULT_AUTO = "Default";
	private static final String K_CUSTOM_AUTO = "My Auto";
	private String mAutoSelected;
	private SendableChooser<String> mChooser = new SendableChooser<>();
	Drivetrain drivetrain = new Drivetrain();
	Controller joystick = new Controller();
	Pneumatics pneumatics = new Pneumatics();


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		mChooser.addDefault("Default Auto", K_DEFAULT_AUTO);
		mChooser.addObject(K_CUSTOM_AUTO, K_CUSTOM_AUTO);
		SmartDashboard.putData("Auto choices", mChooser);
		drivetrain.init();
		joystick.init();
		pneumatics.init();

		
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
		// autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		LOGGER.log(Level.ALL, "Auto selected: {0}", mAutoSelected); //Replace System.out with logger.log
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		switch (mAutoSelected) {
			case K_CUSTOM_AUTO:
				// Put custom auto code here
				break;
			case K_DEFAULT_AUTO:
			default:
				// Put default auto code here
				break;
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() { 
	drivetrain.tankDrive(joystick);
	pneumatics.grabberPiston(1,3,joystick);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		//not needed yet
	}
	
}
