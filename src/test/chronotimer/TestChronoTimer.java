package test.chronotimer;

import static org.junit.Assert.*;

import java.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import chronotimer.ChronoTimer;


public class TestChronoTimer {
	
	private ChronoTimer ct;
	private LocalTime time;
	
	@Before
	public void before() {
		ct = new ChronoTimer();
		time = LocalTime.now();
	}
	
	@Test
	public void testON(){
		
	}
	
	@Test
	public void testOFF(){
		
	}
	
	@Test
	public void testEXIT(){
		
	}
	
	@Test
	public void testRESET(){
		
	}
	
	@Test
	public void testTIME(){
		
	}
	
	@Test
	public void testTOGGLE(){
		
	}
	
	@Test
	public void testCONN(){
		
	}
	
	@Test
	public void testDISC(){
		
	}
	
	@Test
	public void testEVENT(){
		
	}
	
	@Test
	public void testNEWRUN(){
		
	}
	
	@Test
	public void testENDRUN(){
		
	}
	
	@Test
	public void testPRINT(){
		
	}
	
	@Test
	public void testEXPORT(){
		
	}
	
	@Test
	public void testNUM(){
		
	}
	
	@Test
	public void testCLR(){
		
	}
	
	@Test
	public void testSWAP(){
		
	}
	
	@Test
	public void testDNF(){
		
	}
	
	@Test
	public void testTRIG(){
		
	}
	
	@Test
	public void testSTART(){
		
	}
	
	@Test
	public void testFINISH(){
		
	}
	
	/* IMPORTANT NOTE!!!!!!!!!!
	 * 
	 * The goal of this is NOT to test the functionality of the
	 * methods called by different commands. That should be tested
	 * in the test class from which the called method belongs to.
	 * 
	 * RATHER this will test the parsing of each command name, and
	 * it's respective arguments
	 */
	@Test
	public void testExecuteCommand() {
		assertFalse(ct.executeCommand(null, "ON", null)); // Bad timestamp 
		assertTrue(ct.executeCommand(time, "ON", null)); // Good timestamp
	}
}
