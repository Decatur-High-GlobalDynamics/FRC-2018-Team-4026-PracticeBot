package org.usfirst.frc.team4026.robot;

import edu.wpi.first.wpilibj.AnalogGyro;

public class Sensors implements Subsystem{
	boolean isInitialized = false;
	Gyro gyro;
	
	public int init(){
		if(!isInitialized){
		gyro = new Gyro();
		gyro.init(0);
		
		
		isInitialized = true;
		return 0;
		}
		//Return 1 if tries to reinit
		return 1;
	}
	public int shutdown() {
		return 1;
	}
	class Gyro {
		AnalogGyro gyro;

		public void init(int slot){
			gyro = new AnalogGyro(slot);
			gyro.calibrate();
		}
		public double getAngle() {
			return gyro.getAngle();
		}
		public void reset() {
			gyro.reset();
		}
	}
}
