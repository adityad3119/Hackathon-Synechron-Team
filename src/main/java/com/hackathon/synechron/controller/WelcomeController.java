package com.hackathon.synechron.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.DigitalState;

@RestController
public class WelcomeController {

	//private static GpioPinDigitalOutput pin;
		private static int DIGITAL_OUTPUT_PIN = 1;
	@GetMapping("/welcome/{name}")
	public String welcome(@PathVariable String name) {
		return "Hello " + name;
	}

	@GetMapping("/light")
	public String light() {
		try {
			/*
			 * if (pin == null) { GpioController gpio = GpioFactory.getInstance(); pin =
			 * gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW); }
			 */
			
			var pi4j = Pi4J.newAutoContext();

			// create a digital output instance using the default digital output provider
			var output = pi4j.dout().create(DIGITAL_OUTPUT_PIN);
			output.config().shutdownState(DigitalState.HIGH);

			// setup a digital output listener to listen for any state changes on the digital output
			output.addListener(System.out::println);

			// lets invoke some changes on the digital output
			output.state(DigitalState.LOW);

			// lets toggle the digital output state a few times
			output.toggle();
			

			//pin.toggle();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "OK";
	}

}
