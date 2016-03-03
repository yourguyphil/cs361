package io;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import chronotimer.ChronoTimer;
import chronotimer.Time;

/** The main driver for the chronotimer. Reads in commands from the console including the FILE command
 */
public class Driver {
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		Parser parser = new Parser(new ChronoTimer());
		DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm:ss.SS");
		
		System.out.println("CHRONOTIMER");
		
		// Use the parser to read commands from the console
		while(true){
			
			// Retrieves input command and prepends time label to command passed to parser
			// For testing from file use command: FILE followed by address---> location of testCommands.txt
			parser.parse(Time.toString(LocalTime.now()) + " " + input.nextLine());
		}																			
	}
}
