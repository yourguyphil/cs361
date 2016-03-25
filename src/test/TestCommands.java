package test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.Parser;
import race.IND;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import race.Racer;
import chronotimer.ChronoTimer;

public class TestCommands {

	private Parser parser;
	private ChronoTimer chronotimer;
	private LocalTime time;
	
	@Before
	public void before() {
		time = LocalTime.now();
		chronotimer = new ChronoTimer();
		parser = new Parser(chronotimer);
	}
	
	@Test
	public void testOn() {
		chronotimer.executeCommand(time,"ON",null);
	}
	
	@Test
	public void testFinish() {
		chronotimer.executeCommand(time,"ON",null);
		assertFalse(parser.parse("23.232:23 FINISH"));
		assertFalse(parser.parse("03.23:23 FINISH"));
		assertTrue(parser.parse("23:23:23.23 FINISH"));
	}
	
	@Test
	public void testReset() {
		String[] args = {"123"};
		// TURN TIMER ON
		assertTrue(chronotimer.executeCommand(time, "ON", null));
		assertTrue(chronotimer.executeCommand(time, "RESET", args));
	}
	
	@Test
	public void testClear() {
		String[] args = {"1"};
		String[] args2 = {"123"};
		// TURN TIMER ON
		assertTrue(chronotimer.executeCommand(time, "ON", null));
		assertTrue(chronotimer.executeCommand(time, "CLR", args2));
		assertTrue(chronotimer.executeCommand(time, "DISC", args));
	}
	
	@Test
	public void testNum() {
		String[] args = { "1" };
		String[] args2 = { "123" };
		String[] args3 = { "" };
		// TURN TIMER ON
		assertTrue(chronotimer.executeCommand(time, "ON", null));
		assertTrue(chronotimer.executeCommand(time, "NUM", args));
				
	}
	
	@Test
	public void testSwap() {
		String[] args = {"123"};
		String[] args2 = {"234"};
		String[] args3 = {""};
		
		// TURN TIMER ON
		assertTrue(chronotimer.executeCommand(time, "ON", null));
		assertTrue(chronotimer.executeCommand(time, "NUM", args));
		assertTrue(chronotimer.executeCommand(time, "NUM", args2));
		assertTrue(chronotimer.executeCommand(time, "START", args3));
		assertTrue(chronotimer.executeCommand(time, "START", args3));
		
		assertTrue(chronotimer.executeCommand(time, "SWAP", args2));
	}
	
	@Test
	public void testDNF() {
		String[] args = {"1"};
		String[] args2 = {"123"};
		String[] args3 = {""};
		// TURN TIMER ON
		assertTrue(chronotimer.executeCommand(time, "ON", null));
		assertTrue(chronotimer.executeCommand(time, "NUM", args));
		assertTrue(chronotimer.executeCommand(time, "START", null));
		
		assertTrue(chronotimer.executeCommand(time, "DNF", args2));
	}
	
	@Test
	public void testNewRun() {
		String[] args = {"ABCDE"};
		
		// TURN TIMER ON
		assertTrue(chronotimer.executeCommand(time, "ON", null));
		
		assertTrue(chronotimer.executeCommand(time, "NEWRUN", args));
		assertTrue(chronotimer.executeCommand(time, "NEWRUN", null));
	}
	
	@Test
	public void testDisc() {
		String[] args = {"1"};
		String[] args2 = {"123"};
		// TURN TIMER ON
		assertTrue(chronotimer.executeCommand(time, "ON", null));
		
		assertFalse(chronotimer.executeCommand(time, "DISC", args2));
		assertTrue(chronotimer.executeCommand(time, "DISC", args));
	}

}