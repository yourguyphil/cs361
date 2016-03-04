package Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import chronotimer.ChronoTimer;
import io.Parser;

public class TestParser {
	private Parser parser = new Parser(new ChronoTimer());
	
	
	/*@org.junit.Test
	public void testBadTimeFormat(){
		assertFalse(parser.parse("08:24:193.96 START"));
		assertFalse(parser.parse("008:24:19.96 START"));
		assertTrue(parser.parse("08:24:19.960 START"));
		assertFalse(parser.parse("08:245:19.96 START"));
		assertFalse(parser.parse("08:24:19.96 TIME 9:15:19.94"));
		assertFalse(parser.parse("08:24:19.96 TIME 9:15:193.94"));
		assertTrue(parser.parse("08:24:19.96 TIME 09:15:19.94"));
		assertFalse(parser.parse("08:24:19.96 TIME 9:15:19.940"));
		assertFalse(parser.parse("08:24:19.96 TIME 9:153:19.94"));
		assertFalse(parser.parse("08:24:19.96 TIME 9:15:19.9405"));
	}*/
	
	@org.junit.Test
	public void testIllegalCommand(){
		ChronoTimer ct = new ChronoTimer();
		LocalTime time = LocalTime.now();
		String[] args = {"ABCDE"};
		String[] args2 = {"1"};
		String[] args3 = {"GATE 1"};
		// TURN TIMER ON
		assertFalse(ct.executeCommand(time, "BLACH", args2));
		
		assertFalse(ct.executeCommand(time, "COZNN", args));
		assertFalse(ct.executeCommand(time, "CODNN", null));
		assertFalse(ct.executeCommand(time, "CAONN", args3));
	}
	
	@org.junit.Test
	public void testConn(){
		ChronoTimer ct = new ChronoTimer();
		LocalTime time = LocalTime.now();
		String[] args = {"ABCDE"};
		String[] args2 = {"1"};
		String[] args3 = {"GATE 1"};
		// TURN TIMER ON
		assertFalse(ct.executeCommand(time, "CONN", args2));
		
		assertFalse(ct.executeCommand(time, "CONN", args));
		assertFalse(ct.executeCommand(time, "CONN", null));
		assertFalse(ct.executeCommand(time, "CONN", args3));
		
	/*	assertFalse(parser.parse("08:24:19.96 CONN"));
		assertFalse(parser.parse("08:24:19.96 CONN 3"));
		assertFalse(parser.parse("08:24:19.96 CONN GATE"));
		assertFalse(parser.parse("08:24:19.96 CONN EYE"));
		assertFalse(parser.parse("08:24:19.96 CONN EYE Phillips"));
		assertTrue(parser.parse("08:24:19.96 CONN EYE 1"));
		assertTrue(parser.parse("08:24:19.96 CONN GATE 1"));*/
		
	}
	
	@org.junit.Test
	public void testTrig(){
		ChronoTimer ct = new ChronoTimer();
		LocalTime time = LocalTime.now();
		String[] args = {"ABCDE"};
		String[] args2 = {"1"};
		// TURN TIMER ON
		assertFalse(ct.executeCommand(time, "TRIG", args2));
		
		assertFalse(ct.executeCommand(time, "TRIG", args));
		assertFalse(ct.executeCommand(time, "NEWRUN", null));
	}

	@org.junit.Test
	public void testFile(){
		// It works 
	}
}
