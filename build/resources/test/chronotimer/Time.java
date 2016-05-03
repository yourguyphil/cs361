package chronotimer;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time {

	public static LocalTime fromString(String time) {
		if (time == null) {
			System.out.println("Time cannot be null");
			return null;
		} else {
			return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss.SS"));
		}
	}
	
	public static String toString(LocalTime time) {
		if (time == null) {
			System.out.println("Time cannot be null");
			return null;
		} else {
			return time.format(DateTimeFormatter.ofPattern("HH:mm:ss.SS"));
		}
	}
	
}
