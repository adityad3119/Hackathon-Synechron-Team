package com.hackathon.synechron.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.Pi4J;
import com.pi4j.io.IOType;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfig;
import com.pi4j.io.gpio.digital.DigitalOutputProvider;
import com.pi4j.io.gpio.digital.DigitalStateChangeListener;
import com.pi4j.platform.Platform;

@RestController
public class WelcomeController {

	//private static GpioPinDigitalOutput pin;
	public static final int DIGITAL_OUTPUT_PIN = 4;
	
	@GetMapping("/welcome/{name}")
	public String welcome(@PathVariable String name) {
		return "Hello " + name;
	}

	@GetMapping("/light")
	public String light() {
		try {
			var pi4j = Pi4J.newAutoContext();

	        // get default runtime platform
	        Platform platform = pi4j.platforms().getDefault();

	        // get default digital output provide for this platform
	        DigitalOutputProvider provider = platform.provider(IOType.DIGITAL_OUTPUT);

	        // create I/O instance configuration using the config builder
	        DigitalOutputConfig config = DigitalOutputConfig.newBuilder(pi4j).address(3).build();

	        // use factory to create/register  I/O instance
	        DigitalOutput output = provider.create(config);

	        // setup a digital output listener to listen for any state changes on the digital output
	        output.addListener((DigitalStateChangeListener) event -> {
	            System.out.println(event);
	        });

	        // lets toggle the digital output state a few times
	        output.toggle()
	              .toggle()
	              .toggle();

	        // another friendly method of setting output state
	        output.high()
	              .low();

	        // blink the output for 10 seconds
	        output.blink(1, 10, TimeUnit.SECONDS);

	        // shutdown Pi4J
	        pi4j.shutdown();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "OK";
	}

}
