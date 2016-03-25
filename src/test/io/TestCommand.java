package test.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.Command;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import chronotimer.ChronoTimer;

/* IMPORTANT NOTE!!!!!!!!!!
 * 
 * The goal of this class is NOT to test the functionality of the
 * methods called by different commands. That should be tested
 * in the test class from which the called method belongs to.
 * 
 * RATHER this will test the parsing of each command name, and
 * it's respective arguments
 */
public class TestCommand {
	
	private ChronoTimer chronotimer;
	private LocalTime time;
	
	@Before
	public void before() {
		chronotimer = new ChronoTimer();
		time = LocalTime.now();
	}

	@Test
	public void testExecuteCommand() {
		assertFalse(Command.executeCommand(chronotimer, null, "ON", null)); // Bad timestamp 
		assertTrue(Command.executeCommand(chronotimer, time, "ON", null)); // Good timestamp
	}
	
}
