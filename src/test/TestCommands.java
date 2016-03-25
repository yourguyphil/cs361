package test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.Command;
import io.Parser;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

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
		Command.executeCommand(chronotimer, time,"ON",null);
	}
	
	@Test
	public void testFinish() {
		Command.executeCommand(chronotimer, time,"ON",null);
		assertFalse(parser.parse("23.232:23 FINISH"));
		assertFalse(parser.parse("03.23:23 FINISH"));
		assertTrue(parser.parse("23:23:23.23 FINISH"));
	}
	
	@Test
	public void testReset() {
		String[] args = {"123"};
		// TURN TIMER ON
		assertTrue(Command.executeCommand(chronotimer, time, "ON", null));
		assertTrue(Command.executeCommand(chronotimer, time, "RESET", args));
	}
	
	@Test
	public void testClear() {
		String[] args = {"1"};
		String[] args2 = {"123"};
		// TURN TIMER ON
		assertTrue(Command.executeCommand(chronotimer, time, "ON", null));
		assertTrue(Command.executeCommand(chronotimer, time, "CLR", args2));
		assertTrue(Command.executeCommand(chronotimer, time, "DISC", args));
	}
	
	@Test
	public void testNum() {
		String[] args = { "1" };
		// TURN TIMER ON
		assertTrue(Command.executeCommand(chronotimer, time, "ON", null));
		assertTrue(Command.executeCommand(chronotimer, time, "NUM", args));
				
	}
	
	@Test
	public void testSwap() {
		String[] args = {"123"};
		String[] args2 = {"234"};
		String[] args3 = {""};
		
		// TURN TIMER ON
		assertTrue(Command.executeCommand(chronotimer, time, "ON", null));
		assertTrue(Command.executeCommand(chronotimer, time, "NUM", args));
		assertTrue(Command.executeCommand(chronotimer, time, "NUM", args2));
		assertTrue(Command.executeCommand(chronotimer, time, "START", args3));
		assertTrue(Command.executeCommand(chronotimer, time, "START", args3));
		
		assertTrue(Command.executeCommand(chronotimer, time, "SWAP", args2));
	}
	
	@Test
	public void testDNF() {
		String[] args = {"1"};
		String[] args2 = {"123"};
		// TURN TIMER ON
		assertTrue(Command.executeCommand(chronotimer, time, "ON", null));
		assertTrue(Command.executeCommand(chronotimer, time, "NUM", args));
		assertTrue(Command.executeCommand(chronotimer, time, "START", null));
		
		assertTrue(Command.executeCommand(chronotimer, time, "DNF", args2));
	}
	
	@Test
	public void testNewRun() {
		String[] args = {"ABCDE"};
		
		// TURN TIMER ON
		assertTrue(Command.executeCommand(chronotimer, time, "ON", null));
		
		assertTrue(Command.executeCommand(chronotimer, time, "NEWRUN", args));
		assertTrue(Command.executeCommand(chronotimer, time, "NEWRUN", null));
	}
	
	@Test
	public void testDisc() {
		String[] args = {"1"};
		String[] args2 = {"123"};
		// TURN TIMER ON
		assertTrue(Command.executeCommand(chronotimer, time, "ON", null));
		
		assertFalse(Command.executeCommand(chronotimer, time, "DISC", args2));
		assertTrue(Command.executeCommand(chronotimer, time, "DISC", args));
	}

}