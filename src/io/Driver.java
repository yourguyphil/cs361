package io;

import chronotimer.ChronoTimerEmulator;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import chronotimer.ChronoTimer;
import chronotimer.Time;

/** The main driver for the chronotimer. Reads in commands from the console including the FILE command
 */
public class Driver {
	
	public static void main(String[] args){
		//to use GUI launch: ChronoTimerEmulator Applet

		Scanner input = new Scanner(System.in);
		Parser parser = new Parser(new ChronoTimer());
		
		System.out.println("CHRONOTIMER");
		
		// Use the parser to read commands from the console
		while(input.hasNextLine()){
			
			// Retrieves input command and prepends time label to command passed to parser
			// For testing from file use command: FILE followed by filename found in the test/files directory
			parser.parse(Time.toString(LocalTime.now()) + " " + input.nextLine());
		}
		
		input.close();

	}
}
