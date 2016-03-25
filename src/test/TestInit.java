package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.Command;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import chronotimer.ChronoTimer;

public class TestInit {
   ChronoTimer chronotimer;
   LocalTime timeStamp;
   
   /**
    * Need to reset the chronotimer before each test so state is not persisted between jUnits
    */
   @Before 
   public void before() {
	   chronotimer = new ChronoTimer();
	   timeStamp = LocalTime.now(); 
   }
	
	/**
	 * Test calling TRIG before on		
	 */
   @Test
	public void testStartBeforeOn() {
		assertFalse(Command.executeCommand(chronotimer, timeStamp, "TRIG", new String[]{ "1"}));
		
	}
	/**
	 * Test calling on works
	 */
	@Test
	public void testStartAfterOn(){
		assertTrue(Command.executeCommand(chronotimer, timeStamp, "ON", null));
	}
	
	/**
	 * Test calling a DNF before ON
	 */
	@Test
	public void testDNFBeforeOn(){
	    assertFalse(Command.executeCommand(chronotimer, timeStamp, "DNF", new String[]{ "1"}));	
	}
	
	/**
	 * Calling stop before start should not work
	 */
    @Test
    public void stopBeforeStart(){
    	assertFalse(Command.executeCommand(chronotimer, timeStamp, "STOP", new String[]{"1"}));
    }
    /**
     *  call on and off in that order should work
     */
    @Test
    public void testOnOff(){
    	assertTrue(Command.executeCommand(chronotimer, timeStamp, "ON", new String[]{"1"}));
    	assertTrue(Command.executeCommand(chronotimer, timeStamp, "OFF", new String[]{"1"}));
    	
    }
    
    /**
     * call on off off
     */
    @Test()
    public void testOnOffCallCommand(){
    	assertTrue(Command.executeCommand(chronotimer, timeStamp, "ON", new String[]{"1"}));
    	assertTrue(Command.executeCommand(chronotimer, timeStamp, "OFF", new String[]{"1"}));
    	assertFalse(Command.executeCommand(chronotimer, timeStamp, "START", new String[]{"1"}));
    }
	
}
