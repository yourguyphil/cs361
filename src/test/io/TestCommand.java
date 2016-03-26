package test.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.Command;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import race.EventType;
import chronotimer.ChronoTimer;

/* IMPORTANT NOTE!!!!!!!!!!
 * 
 * The goal of this class is NOT to test the functionality of the
 * methods called by different commands. That should be tested
 * in the test class from which the called method belongs to.
 * 
 * RATHER this tests the parsing of each command name, and
 * it's respective arguments
 */
public class TestCommand {
	
	private ChronoTimer chronotimer;
	private LocalTime time;
	private String[] emptyArgs = {};
	private String[] badArgs = {"bad", "args"};
	
	@Before
	public void before() {
		chronotimer = new ChronoTimer();
		time = LocalTime.now();
	}

	@Test
	public void testExecuteCommand() {
		String[] args = {"1"};

		String badCmd = "BAD";
		String cmdWithoutArgs = "ON";
		String cmdWithArgs = "TOGGLE";

		// Fail without chronotimer
		assertFalse(Command.executeCommand(null, time, cmdWithoutArgs, emptyArgs));
		
		// Fail without time
		assertFalse(Command.executeCommand(chronotimer, null, cmdWithoutArgs, emptyArgs));
		
		// null, null
		assertFalse(Command.executeCommand(chronotimer, time, null, null));
		
		// null, args
		assertFalse(Command.executeCommand(chronotimer, time, null, emptyArgs));
		assertFalse(Command.executeCommand(chronotimer, time, null, args));
		
		// cmd name, null
		assertFalse(Command.executeCommand(chronotimer, time, badCmd, null));
		assertTrue(Command.executeCommand(chronotimer, time, cmdWithoutArgs, null));
		assertFalse(Command.executeCommand(chronotimer, time, cmdWithArgs, null));
		
		// cmd name, args
		assertFalse(Command.executeCommand(chronotimer, time, badCmd, emptyArgs));
		assertFalse(Command.executeCommand(chronotimer, time, badCmd, args));
		
		assertTrue(Command.executeCommand(chronotimer, time, cmdWithoutArgs, emptyArgs));
		assertTrue(Command.executeCommand(chronotimer, time, cmdWithoutArgs, args));
		
		assertFalse(Command.executeCommand(chronotimer, time, cmdWithArgs, emptyArgs));
		assertTrue(Command.executeCommand(chronotimer, time, cmdWithArgs, args));
	}
	
	@Test
	public void testON(){
		assertTrue(Command.executeCommand(chronotimer, time, "ON", null));
		assertTrue(Command.executeCommand(chronotimer, time, "ON", emptyArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "ON", badArgs));
	}
	
	@Test
	public void testOFF(){
		assertTrue(Command.executeCommand(chronotimer, time, "OFF", null));
		assertTrue(Command.executeCommand(chronotimer, time, "OFF", emptyArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "OFF", badArgs));
	}
	
	@Test
	public void testEXIT(){
		// Cannot be tested, system exit's before assertion
	}
	
	@Test
	public void testRESET(){
		assertTrue(Command.executeCommand(chronotimer, time, "RESET", null));
		assertTrue(Command.executeCommand(chronotimer, time, "RESET", emptyArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "RESET", badArgs));
	}
	
	@Test
	public void testTIME(){
		assertFalse(Command.executeCommand(chronotimer, time, "TIME", null));
		assertFalse(Command.executeCommand(chronotimer, time, "TIME", emptyArgs));
		assertFalse(Command.executeCommand(chronotimer, time, "TIME", badArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "TIME", new String[] {"00:00:00.00"}));
	}
	
	@Test
	public void testTOGGLE(){
		assertFalse(Command.executeCommand(chronotimer, time, "TOGGLE", null));
		assertFalse(Command.executeCommand(chronotimer, time, "TOGGLE", emptyArgs));
		assertFalse(Command.executeCommand(chronotimer, time, "TOGGLE", badArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "TOGGLE", new String[] {"1"}));
	}
	
	@Test
	public void testCONN(){
		assertFalse(Command.executeCommand(chronotimer, time, "CONN", null));
		assertFalse(Command.executeCommand(chronotimer, time, "CONN", emptyArgs));
		assertFalse(Command.executeCommand(chronotimer, time, "CONN", badArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "CONN", new String[] {"EYE", "1"}));
	}
	
	@Test
	public void testDISC(){
		assertFalse(Command.executeCommand(chronotimer, time, "DISC", null));
		assertFalse(Command.executeCommand(chronotimer, time, "DISC", emptyArgs));
		assertFalse(Command.executeCommand(chronotimer, time, "DISC", badArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "DISC", new String[] {"1"}));
	}
	
	@Test
	public void testEVENT(){
		assertFalse(Command.executeCommand(chronotimer, time, "EVENT", null));
		assertFalse(Command.executeCommand(chronotimer, time, "EVENT", emptyArgs));
		assertFalse(Command.executeCommand(chronotimer, time, "EVENT", badArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "EVENT", new String[] {"PARIND"}));
	}
	
	@Test
	public void testNEWRUN(){
		assertTrue(Command.executeCommand(chronotimer, time, "NEWRUN", null));
		assertTrue(Command.executeCommand(chronotimer, time, "NEWRUN", emptyArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "NEWRUN", badArgs));
	}
	
	@Test
	public void testENDRUN(){
		assertTrue(Command.executeCommand(chronotimer, time, "ENDRUN", null));
		assertTrue(Command.executeCommand(chronotimer, time, "ENDRUN", emptyArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "ENDRUN", badArgs));
	}
	
	@Test
	public void testPRINT(){
		assertFalse(Command.executeCommand(chronotimer, time, "PRINT", null));
		assertFalse(Command.executeCommand(chronotimer, time, "PRINT", emptyArgs));
		assertFalse(Command.executeCommand(chronotimer, time, "PRINT", badArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "PRINT", new String[] {"1"}));
	}
	
	@Test
	public void testEXPORT(){
		assertFalse(Command.executeCommand(chronotimer, time, "EXPORT", null));
		assertFalse(Command.executeCommand(chronotimer, time, "EXPORT", emptyArgs));
		assertFalse(Command.executeCommand(chronotimer, time, "EXPORT", badArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "EXPORT", new String[] {"1"}));
	}
	
	@Test
	public void testNUM(){
		assertFalse(Command.executeCommand(chronotimer, time, "NUM", null));
		assertFalse(Command.executeCommand(chronotimer, time, "NUM", emptyArgs));
		assertFalse(Command.executeCommand(chronotimer, time, "NUM", badArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "NUM", new String[] {"1"}));
	}
	
	@Test
	public void testCLR(){
		assertFalse(Command.executeCommand(chronotimer, time, "CLR", null));
		assertFalse(Command.executeCommand(chronotimer, time, "CLR", emptyArgs));
		assertFalse(Command.executeCommand(chronotimer, time, "CLR", badArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "CLR", new String[] {"1"}));
	}
	
	@Test
	public void testSWAP(){
		assertTrue(Command.executeCommand(chronotimer, time, "SWAP", null));
		assertTrue(Command.executeCommand(chronotimer, time, "SWAP", emptyArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "SWAP", badArgs));
	}
	
	@Test
	public void testDNF(){
		assertTrue(Command.executeCommand(chronotimer, time, "DNF", null));
		assertTrue(Command.executeCommand(chronotimer, time, "DNF", emptyArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "DNF", badArgs));
	}
	
	@Test
	public void testTRIG(){
		assertFalse(Command.executeCommand(chronotimer, time, "TRIG", null));
		assertFalse(Command.executeCommand(chronotimer, time, "TRIG", emptyArgs));
		assertFalse(Command.executeCommand(chronotimer, time, "TRIG", badArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "TRIG", new String[] {"1"}));
	}
	
	@Test
	public void testSTART(){
		assertTrue(Command.executeCommand(chronotimer, time, "START", null));
		assertTrue(Command.executeCommand(chronotimer, time, "START", emptyArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "START", badArgs));
	}
	
	@Test
	public void testFINISH(){
		assertTrue(Command.executeCommand(chronotimer, time, "FINISH", null));
		assertTrue(Command.executeCommand(chronotimer, time, "FINISH", emptyArgs));
		assertTrue(Command.executeCommand(chronotimer, time, "FINISH", badArgs));
	}
	
}
