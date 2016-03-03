package bot.model;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;


public class EV3Bot
{
	private String botMessage;
	private int xPosition;
	private int yPosition;
	private long waitTime;
	private double [] roomDistance;
	private float [] ultrasonicSamples;
	private float [] touchSamples;
	
	private MovePilot botPilot;
	private EV3UltrasonicSensor distanceSensor;
	private EV3TouchSensor backTouch;
	
	public EV3Bot()
	{
		this.botMessage = "Do androids dream of electronic sheep.";
		this.xPosition = 50;
		this.yPosition = 50;
		this.waitTime = 4000;
		
		distanceSensor = new EV3UltrasonicSensor(LocalEV3.get().getPort("S1"));
		backTouch = new EV3TouchSensor(LocalEV3.get().getPort("S2"));
		
		setupPilot();
		displayMessage();
		
	}
	private void setupPilot()
	{
		Wheel leftWheel = WheeledChassis.modelWheel(Motor.A, 43.3).offset(-72);
		Wheel rightWheel = WheeledChassis.modelWheel(Motor.B, 43.3).offset(72);
		WheeledChassis chassis = new WheeledChassis(new Wheel[]{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
		botPilot = new MovePilot(chassis);
	}
	public void driveRoom()
	{
		ultrasonicSamples = new float [distanceSensor.sampleSize()];
		distanceSensor.fetchSample(ultrasonicSamples, 0);
		if(ultrasonicSamples[0] < 20 )
		{
			driveShort();
		}
		else
		{
			driveLong();
		}
		displayMessage("I am at the other door");
	}
	
	public void driveShort()
	{
		botPilot.travel(3810);
		botPilot.rotate(-60);
		botPilot.travel(5588);
		botPilot.rotate(60);
		botPilot.travel(3048);
		botPilot.rotate(-60);
		botPilot.travel(1048);
	}
	
	public void driveLong()
	{
		
	}
	
	private void displayMessage()
	{
		LCD.drawString(botMessage, xPosition, yPosition);
		Delay.msDelay(waitTime);
	}
	
	public void displayMessage(String message)
	{
		LCD.drawString(message, xPosition, yPosition);
		Delay.msDelay(waitTime);
	}
	
}
