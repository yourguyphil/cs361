package io;
import java.util.*;
public class Time {
			
	public double start() {
		
		// Gives current time in milliseconds and then converts to seconds
		return (System.currentTimeMillis() / 1000) % 60;
	}
	
	public double end() {
		// same as above
		return (System.currentTimeMillis() / 1000) % 60;
	}
	
	public String totalTimeToString(double start, double end) {
		// return total time
		return "Total Time : " + (end-start) + " seconds.";
	}
}
