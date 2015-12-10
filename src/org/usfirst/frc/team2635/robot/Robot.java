
package org.usfirst.frc.team2635.robot;

import com.lakemonsters2635.actuator.interfaces.BaseActuator;
import com.lakemonsters2635.actuator.interfaces.BaseDrive;
import com.lakemonsters2635.actuator.modules.ActuatorSimple;
import com.lakemonsters2635.actuator.modules.DriveArcade;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {
	final int REAR_RIGHT_CHANNEL = 1;
	final int FRONT_RIGHT_CHANNEL = 2;
	final int REAR_LEFT_CHANNEL = 3;
	final int FRONT_LEFT_CHANNEL = 4;
	final int GRABBER_MOTOR_CHANNEL = 5;
	
	final int JOYSTICK_CHANNEL = 0;
	final int MOVE_AXIS = 1;
	final int ROTATE_AXIS = 2;
	final int GRAB_UP_BUTTON = 1;
	final int GRAB_DOWN_BUTTON = 4;
	final double GRAB_UP_MAGNITUDE = 1.0;
	final double GRAB_DOWN_MAGNITUDE = -1.0;
	
	CANTalon grabberMotor;
	
	//Grabber doesn't need a wrapper because it only does a single thing.
	BaseActuator<Double> grabberMethod;
	
	RobotDrive driveTrain;
	BaseDrive driveMethod;
	
	Joystick joystick;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	grabberMotor = new CANTalon(GRABBER_MOTOR_CHANNEL);
    	grabberMethod = new ActuatorSimple(grabberMotor);
    	
    	driveTrain = new RobotDrive(FRONT_LEFT_CHANNEL, REAR_LEFT_CHANNEL, FRONT_RIGHT_CHANNEL, REAR_RIGHT_CHANNEL);
    	driveMethod = new DriveArcade(driveTrain);
    	
    	joystick = new Joystick(JOYSTICK_CHANNEL);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        double X = joystick.getRawAxis(ROTATE_AXIS);
        double Y = joystick.getRawAxis(MOVE_AXIS);
        boolean grabUp = joystick.getRawButton(GRAB_UP_BUTTON);
        boolean grabDown = joystick.getRawButton(GRAB_DOWN_BUTTON);
        
        driveMethod.drive(X, Y);
        
        if(grabUp){
        	grabberMethod.actuate(GRAB_UP_MAGNITUDE);
        }
        else if(grabDown){
        	grabberMethod.actuate(GRAB_DOWN_MAGNITUDE);
        }
        else{
        	grabberMethod.actuate(0.0);
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
