package io;
public class Time {

	// All different units of time (hunds = hundredth of a second)
	private long hunds, secs, mins, hours;
	
	//Might not need..
	private long endTime, totalTime;
	
	// long millis is our start time
	public Time(long millis) {
		// We will pass in the start time as parameter, then assign time values from there.		
		assignTimeValues(millis);
	}
	
	// Getters, used to return string of time.
	public long getHunds() { return this.hunds; }
	public long getSecs() { return this.secs; }
	public long getMins() { return this.mins; }
	public long getHours() { return this.hours; }
	public long getTotalTime() { return this.totalTime; }
	
	private void assignTimeValues(long millis) {
		// Just converting miliseconds into different units of time
		hunds = (millis / 10) % 100; 
		secs = (millis/1000) % 60;
		mins = (millis/1000 * 60) % 60;
		hours = (millis/1000 * 3600) % 60;
	}
	
	// Will be read from console or file, states our end time of race
	public void end(long stopTime) {
		endTime = stopTime;
		assignTimeValues(stopTime);
	}
	
	// Calculates total time of run
	public void totalTime(long start, long stop) {
		totalTime = stop - start;
	}
	
	public String toString() {
		return "<" + hours + ":" + mins + ":" + secs + "." + hunds + ">";
	}
	
	/*public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("Enter 'start' to begin race..");
		String input = stdIn.nextLine();
		if(input.equalsIgnoreCase("start")) {
			long start = System.currentTimeMillis();
			Time t = new Time(start);
			System.out.println( "START : " +t.toString());
			System.out.println("Enter 'stop' to end race");
			input = stdIn.nextLine();
				long stopTime = System.currentTimeMillis();
				t.end(stopTime);
				System.out.println("STOP : " + t.toString());
				t.totalTime(start,stopTime);
				t.assignTimeValues(t.getTotalTime());
				System.out.println("TOTAL TIME: " + t.toString());
		}
		stdIn.close();
	}*/
	
}
