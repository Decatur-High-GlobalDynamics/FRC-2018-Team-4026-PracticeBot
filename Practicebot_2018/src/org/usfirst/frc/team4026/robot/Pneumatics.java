package org.usfirst.frc.team4026.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Pneumatics extends Subsystem{
	
	DoubleSolenoid grabber;
	Compressor compressor;
	
	boolean isInitialized = false;

	@Override
	public int init() {
		if(!isInitialized){
		grabber = new DoubleSolenoid(0,3);
		compressor = new Compressor();
		compressor.setClosedLoopControl(true);
		grabber.set(Value.kReverse);
		isInitialized = true;
		return 0;
		}
		//Return 1 if tries to reinit
		return 1;
	}
	
	void grabberPiston(int extend, int retract,Controller driveGamepad)
	{
		if (driveGamepad.getRawButton(extend))
		{
			grabber.set(DoubleSolenoid.Value.kForward);
		}
		else if (driveGamepad.getRawButton(retract))
		{
			grabber.set(DoubleSolenoid.Value.kReverse);
		}
	}
	@Override
	public int shutdown() {
		grabber.set(Value.kReverse);
		return 1;
	}
	

}
