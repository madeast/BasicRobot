package bot.controller;

import lejos.hardware.lcd.*;
import lejos.utility.*;

import bot.model.EV3Bot;

public class BotController
{
	private String message;
	private int xPosition, yPosition;
	private long waitTime;
	
	private EV3Bot Android;
	
	public BotController()
	{
		this.xPosition = 50;
		this.yPosition = 100;
		this.waitTime = 4000;
		this.message = "Do androids dream of electronic sheep.";
		Android = new EV3Bot();
	}
	public void start()
	{
		//Android.driveRoom();
		Android.driveAllRoom();
	}
	
	
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	public int getxPosition()
	{
		return xPosition;
	}
	public void setxPosition(int xPosition)
	{
		this.xPosition = xPosition;
	}
	public int getyPosition()
	{
		return yPosition;
	}
	public void setyPosition(int yPosition)
	{
		this.yPosition = yPosition;
	}
	public long getWaitTime()
	{
		return waitTime;
	}
	public void setWaitTime(long waitTime)
	{
		this.waitTime = waitTime;
	}
}
