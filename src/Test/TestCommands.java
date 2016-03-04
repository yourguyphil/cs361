package Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.Parser;
import race.IND;

import java.time.LocalTime;

import org.junit.Test;

import race.Racer;
import chronotimer.ChronoTimer;

public class TestCommands {

	private Parser parser = new Parser(new ChronoTimer());
	
	@Test
	public void testOn() {
		// Need to set environment up to test
		LocalTime time = LocalTime.now();
		ChronoTimer ct = new ChronoTimer();
		ct.executeCommand(time,"ON",null);
	}
	
	@Test
	public void testFinish() {
		// Need to set environment up to test
		LocalTime time = LocalTime.now();
		String[] args = {""};
		ChronoTimer ct = new ChronoTimer();
		ct.executeCommand(time,"ON",null);
		assertFalse(parser.parse("23.232:23 FINISH"));
		assertFalse(parser.parse("03.23:23 FINISH"));
		assertTrue(parser.parse("23:23:23.23 FINISH"));
	}
	
	@Test
	public void testReset() {
		ChronoTimer ct = new ChronoTimer();
		LocalTime time = LocalTime.now();
		String[] args = {"1"};
		String[] args2 = {"123"};
		// TURN TIMER ON
		assertTrue(ct.executeCommand(time, "ON", null));
		assertTrue(ct.executeCommand(time, "RESET", args2));
	}
	
	@Test
	public void testClear() {
		ChronoTimer ct = new ChronoTimer();
		LocalTime time = LocalTime.now();
		String[] args = {"1"};
		String[] args2 = {"123"};
		// TURN TIMER ON
		assertTrue(ct.executeCommand(time, "ON", null));
		
		assertTrue(ct.executeCommand(time, "CLR", args2));
		assertTrue(ct.executeCommand(time, "DISC", args));
	}
	
	@Test
	public void testNum() {
		// Need to set environment up to test
				ChronoTimer ct = new ChronoTimer();
				LocalTime time = LocalTime.now();
				String[] args = {"1"};
				String[] args2 = {"123"};
				String[] args3 = {""};
				// TURN TIMER ON
				assertTrue(ct.executeCommand(time, "ON", null));
				assertTrue(ct.executeCommand(time, "NUM", args));
				
	}
	
	@Test
	public void testSwap() {
		// Need to set environment up to test
		ChronoTimer ct = new ChronoTimer();
		LocalTime time = LocalTime.now();
		String[] args = {"123"};
		String[] args2 = {"234"};
		String[] args3 = {""};
		
		Racer racer1 = new Racer(123);
		Racer racer2 = new Racer(234);
		// TURN TIMER ON
		assertTrue(ct.executeCommand(time, "ON", null));
		assertTrue(ct.executeCommand(time, "NUM", args));
		assertTrue(ct.executeCommand(time, "NUM", args2));
		
		assertTrue(ct.executeCommand(time, "START", args3));
		assertTrue(ct.executeCommand(time, "START", args3));
				
		assertTrue(ct.executeCommand(time, "SWAP", args2));
	}
	
	@Test
	public void testDNF() {
		// Need to set environment up to test
		ChronoTimer ct = new ChronoTimer();
		LocalTime time = LocalTime.now();
		String[] args = {"1"};
		String[] args2 = {"123"};
		String[] args3 = {""};
		// TURN TIMER ON
		assertTrue(ct.executeCommand(time, "ON", null));
		assertTrue(ct.executeCommand(time, "NUM", args));
		assertTrue(ct.executeCommand(time, "START", null));
		
		assertTrue(ct.executeCommand(time, "DNF", args2));
	}
	
	@Test
	public void testNewRun() {
		ChronoTimer ct = new ChronoTimer();
		LocalTime time = LocalTime.now();
		String[] args = {"ABCDE"};
		
		// TURN TIMER ON
		assertTrue(ct.executeCommand(time, "ON", null));
		
		assertTrue(ct.executeCommand(time, "NEWRUN", args));
		assertTrue(ct.executeCommand(time, "NEWRUN", null));
	}
	
	@Test
	public void testDisc() {
		ChronoTimer ct = new ChronoTimer();
		LocalTime time = LocalTime.now();
		String[] args = {"1"};
		String[] args2 = {"123"};
		// TURN TIMER ON
		assertTrue(ct.executeCommand(time, "ON", null));
		
		assertFalse(ct.executeCommand(time, "DISC", args2));
		assertTrue(ct.executeCommand(time, "DISC", args));
	}

}