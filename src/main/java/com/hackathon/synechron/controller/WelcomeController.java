package com.hackathon.synechron.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RestController
public class WelcomeController {

	private static GpioPinDigitalOutput pin;

	@GetMapping("/welcome/{name}")
	public String welcome(@PathVariable String name) {
		return "Hello " + name;
	}

	@GetMapping("/light")
	public String light() {
		try {
			if (pin == null) {
				GpioController gpio = GpioFactory.getInstance();
				pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
			}

			pin.toggle();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "OK";
	}

}
