package io;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Driver {
	public static void main(String[] args) {
		double endTime = 0;
		Scanner stdIn = new Scanner(System.in);
		
		Time racerTime = new Time();
		
		System.out.println("Enter start to begin race:");
		String input = stdIn.nextLine();
		
		// User input loop to start and stop race
		if(input.equalsIgnoreCase("Start")) {
			// Call start and print startTime
			double startTime = racerTime.start();
			System.out.println("Start: " + startTime + " seconds.");
			
			// User input for ending race
			while(!(input.equalsIgnoreCase("end"))) {
				System.out.println("Enter end to stop race...");
				input = stdIn.nextLine();
				endTime = racerTime.end();
				System.out.println("end: "+ endTime + " seconds.");
			}
			System.out.println(racerTime.totalTimeToString(startTime,endTime));
		}
		stdIn.close();
	}

}
