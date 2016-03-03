package chronotimer;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time {

	public static LocalTime fromString(String time) {
		return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss.SS"));
	}
	
	public static String toString(LocalTime time) {
		return time.format(DateTimeFormatter.ofPattern("HH:mm:ss.SS"));
	}
	
}
