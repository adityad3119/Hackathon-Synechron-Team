package com.hackathon.synechron.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	// private static GpioPinDigitalOutput pin;
	//public static final int DIGITAL_OUTPUT_PIN = 4;

	@GetMapping("/welcome/{name}")
	public String welcome(@PathVariable String name) {
		return "Hello " + name;
	}

	@GetMapping("/AIDA/{number}")
	public String light(@PathVariable String number) {
		try {

			// -- Linux --
			Process process = null;
			// Run a shell command
			// Process process = Runtime.getRuntime().exec("ls /home/mkyong/");
			
			String num = number;
	        switch (num) {
	            case "1":
	            	//process = Runtime.getRuntime().exec(
	            		//	"cmd /c on.bat", null, new File("C:\\workspace\\"));
	            	process = Runtime.getRuntime().exec("/home/conor/synechron/on.sh");
	                System.out.println("powering ON your devices");
	                break;
	            case "0":
	            	//process = Runtime.getRuntime().exec(
	            		//	"cmd /c off.bat", null, new File("C:\\workspace\\"));
	            	process = Runtime.getRuntime().exec("/home/conor/synechron/off.sh");
	                System.out.println("powering OFF your devices");
	                break;
	            default:
	                System.out.println("Unknown");
	                return "Unknown choice";
	        }

			// -- Windows --

			// Run a command
			// Process process = Runtime.getRuntime().exec("cmd /c dir C:\\Users\\mkyong");

			// Run a bat file
			// Process process = 

			StringBuilder output = new StringBuilder();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				//System.out.println("Success!");
				System.out.println(output);
				return output.toString();
				// System.exit(0);
			} else {
				return "failure!!";
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return "failure!!";
	}

}
