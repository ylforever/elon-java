package com.elon.dds.common;

import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

public class ClearIldleTimerTask extends TimerTask {
	@Autowired
	private DDSHolder holder;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		holder.clearIdleDDS();
	}
}
