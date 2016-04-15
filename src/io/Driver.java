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
		new ChronoTimerEmulator();
	}
}
