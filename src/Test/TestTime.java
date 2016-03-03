package Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.concurrent.Callable;



import javax.crypto.ExemptionMechanismException;

import junit.framework.Assert;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import chronotimer.Time;

public class TestTime {
	
	LocalTime[] times = {
			LocalTime.of(23, 0, 0, 0),
			LocalTime.of(0, 59, 0, 0),
			LocalTime.of(0, 0, 59, 0),
			LocalTime.of(0, 0, 0, 990000000),
			LocalTime.of(0, 0, 0, 0),
			LocalTime.of(23, 59, 59, 990000000)};

	@Test
	public void testFromStringHours() {
		assertEquals(times[0], Time.fromString("23:00:00.00"));
		assertThrows(DateTimeParseException.class, () -> Time.fromString("0:00:00.00"));
		assertThrows(DateTimeParseException.class, () -> Time.fromString("000:00:00.00"));
		assertThrows(DateTimeParseException.class, () -> Time.fromString("25:00:00.00"));
	}
	
	@Test
	public void testFromStringMinutes() {
		assertEquals(times[1], Time.fromString("00:59:00.00"));
		assertThrows(DateTimeParseException.class, () -> Time.fromString("00:0:00.00"));
		assertThrows(DateTimeParseException.class, () -> Time.fromString("00:000:00.00"));
		assertThrows(DateTimeParseException.class, () -> Time.fromString("00:60:00.00"));
	}
	
	@Test
	public void testFromStringSeconds() {
		assertEquals(times[2], Time.fromString("00:00:59.00"));
		assertThrows(DateTimeParseException.class, () -> Time.fromString("00:00:0.00"));
		assertThrows(DateTimeParseException.class, () -> Time.fromString("00:00:000.00"));
		assertThrows(DateTimeParseException.class, () -> Time.fromString("00:00:60.00"));
	}
	
	@Test
	public void testFromStringHundreths() {
		assertEquals(times[3], Time.fromString("00:00:00.99"));
		assertThrows(DateTimeParseException.class, () -> Time.fromString("00:00:00.0"));
		assertThrows(DateTimeParseException.class, () -> Time.fromString("00:00:00.000"));
	}

	@Test
	public void testToString() {
		assertEquals("23:00:00.00", Time.toString(times[0]));
		assertEquals("00:59:00.00", Time.toString(times[1]));
		assertEquals("00:00:59.00", Time.toString(times[2]));
		assertEquals("00:00:00.99", Time.toString(times[3]));
		assertEquals("00:00:00.00", Time.toString(times[4]));
		assertEquals("23:59:59.99", Time.toString(times[5]));
	}
	
	private static void assertThrows(Class<?> throwable, Runnable runnable) {
		boolean fail = false;
		try {
			runnable.run();
			fail = true;
		} catch (Throwable t) {
			if (!throwable.isInstance(t))
				throw new AssertionError("Bad exception type", t);
		}

		if (fail)
			fail("No exception was thrown");
	}
}
