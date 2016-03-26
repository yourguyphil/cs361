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

		// Fail with any null input
		assertFalse(Command.execute(null, time, cmdWithoutArgs, emptyArgs));
		assertFalse(Command.execute(chronotimer, null, cmdWithoutArgs, emptyArgs));
		assertFalse(Command.execute(chronotimer, time, null, emptyArgs));
		assertFalse(Command.execute(chronotimer, time, cmdWithoutArgs, null));
		
		// cmd name, args
		assertFalse(Command.execute(chronotimer, time, badCmd, emptyArgs));
		assertFalse(Command.execute(chronotimer, time, badCmd, args));
		
		assertTrue(Command.execute(chronotimer, time, cmdWithoutArgs, emptyArgs));
		assertFalse(Command.execute(chronotimer, time, cmdWithoutArgs, args));
		
		assertFalse(Command.execute(chronotimer, time, cmdWithArgs, emptyArgs));
		assertTrue(Command.execute(chronotimer, time, cmdWithArgs, args));
	}
	
	@Test
	public void testON(){
		assertFalse(Command.execute(chronotimer, time, "ON", badArgs));
		assertTrue(Command.execute(chronotimer, time, "ON", emptyArgs));
	}
	
	@Test
	public void testOFF(){
		assertFalse(Command.execute(chronotimer, time, "OFF", badArgs));
		assertTrue(Command.execute(chronotimer, time, "OFF", emptyArgs));
	}
	
	@Test
	public void testEXIT(){
		// Cannot be tested, system exit's before assertion
	}
	
	@Test
	public void testRESET(){
		assertFalse(Command.execute(chronotimer, time, "RESET", badArgs));
		assertTrue(Command.execute(chronotimer, time, "RESET", emptyArgs));
	}
	
	@Test
	public void testTIME(){
		assertFalse(Command.execute(chronotimer, time, "TIME", emptyArgs));
		assertFalse(Command.execute(chronotimer, time, "TIME", badArgs));
		assertTrue(Command.execute(chronotimer, time, "TIME", new String[] {"00:00:00.00"}));
	}
	
	@Test
	public void testTOGGLE(){
		assertFalse(Command.execute(chronotimer, time, "TOGGLE", emptyArgs));
		assertFalse(Command.execute(chronotimer, time, "TOGGLE", badArgs));
		assertTrue(Command.execute(chronotimer, time, "TOGGLE", new String[] {"1"}));
	}
	
	@Test
	public void testCONN(){
		assertFalse(Command.execute(chronotimer, time, "CONN", emptyArgs));
		assertFalse(Command.execute(chronotimer, time, "CONN", badArgs));
		assertTrue(Command.execute(chronotimer, time, "CONN", new String[] {"EYE", "1"}));
	}
	
	@Test
	public void testDISC(){
		assertFalse(Command.execute(chronotimer, time, "DISC", emptyArgs));
		assertFalse(Command.execute(chronotimer, time, "DISC", badArgs));
		assertTrue(Command.execute(chronotimer, time, "DISC", new String[] {"1"}));
	}
	
	@Test
	public void testEVENT(){
		assertFalse(Command.execute(chronotimer, time, "EVENT", emptyArgs));
		assertFalse(Command.execute(chronotimer, time, "EVENT", badArgs));
		assertTrue(Command.execute(chronotimer, time, "EVENT", new String[] {"PARIND"}));
	}
	
	@Test
	public void testNEWRUN(){
		assertFalse(Command.execute(chronotimer, time, "NEWRUN", badArgs));
		assertTrue(Command.execute(chronotimer, time, "NEWRUN", emptyArgs));
	}
	
	@Test
	public void testENDRUN(){
		assertFalse(Command.execute(chronotimer, time, "ENDRUN", badArgs));
		assertTrue(Command.execute(chronotimer, time, "ENDRUN", emptyArgs));
	}
	
	@Test
	public void testPRINT(){
		assertFalse(Command.execute(chronotimer, time, "PRINT", emptyArgs));
		assertFalse(Command.execute(chronotimer, time, "PRINT", badArgs));
		assertTrue(Command.execute(chronotimer, time, "PRINT", new String[] {"1"}));
	}
	
	@Test
	public void testEXPORT(){
		assertFalse(Command.execute(chronotimer, time, "EXPORT", emptyArgs));
		assertFalse(Command.execute(chronotimer, time, "EXPORT", badArgs));
		assertTrue(Command.execute(chronotimer, time, "EXPORT", new String[] {"1"}));
	}
	
	@Test
	public void testNUM(){
		assertFalse(Command.execute(chronotimer, time, "NUM", emptyArgs));
		assertFalse(Command.execute(chronotimer, time, "NUM", badArgs));
		assertTrue(Command.execute(chronotimer, time, "NUM", new String[] {"1"}));
	}
	
	@Test
	public void testCLR(){
		assertFalse(Command.execute(chronotimer, time, "CLR", emptyArgs));
		assertFalse(Command.execute(chronotimer, time, "CLR", badArgs));
		assertTrue(Command.execute(chronotimer, time, "CLR", new String[] {"1"}));
	}
	
	@Test
	public void testSWAP(){
		assertFalse(Command.execute(chronotimer, time, "SWAP", badArgs));
		assertTrue(Command.execute(chronotimer, time, "SWAP", emptyArgs));
	}
	
	@Test
	public void testDNF(){
		assertFalse(Command.execute(chronotimer, time, "DNF", badArgs));
		assertTrue(Command.execute(chronotimer, time, "DNF", emptyArgs));
	}
	
	@Test
	public void testTRIG(){
		assertFalse(Command.execute(chronotimer, time, "TRIG", emptyArgs));
		assertFalse(Command.execute(chronotimer, time, "TRIG", badArgs));
		assertTrue(Command.execute(chronotimer, time, "TRIG", new String[] {"1"}));
	}
	
	@Test
	public void testSTART(){
		assertFalse(Command.execute(chronotimer, time, "START", badArgs));
		assertTrue(Command.execute(chronotimer, time, "START", emptyArgs));
	}
	
	@Test
	public void testFINISH(){
		assertFalse(Command.execute(chronotimer, time, "FINISH", badArgs));
		assertTrue(Command.execute(chronotimer, time, "FINISH", emptyArgs));
	}
	
}
