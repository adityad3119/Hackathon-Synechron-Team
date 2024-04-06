package com.hackathon.synechron.service.impl;

import com.hackathon.synechron.service.HomeAutomation;
import com.pi4j.Pi4J;
import com.pi4j.platform.Platforms;

public class HomeAutomationImpl implements HomeAutomation {

	@Override
	public String turnLightOff() {
		// TODO Auto-generated method stub
		
		var pi4j = Pi4J.newAutoContext();
		Platforms platforms = pi4j.platforms();

		console.box("Pi4J PLATFORMS");
		console.println();
		platforms.describe().print(System.out);
		console.println();
		return null;
	}

}
