package Test;

import static org.junit.Assert.*;
import java.time.LocalTime;
import io.Parser;

import org.junit.Before;
import org.junit.Test;
import chronotimer.ChronoTimer;

public class TestInit {
   Parser p = new Parser();
   ChronoTimer chronoTimer; 
   LocalTime timeStamp; 
   
   /**
    * need to init a Chrontimer before each test so state is not persisted between jUnits
    */
   @Before 
   public void initialize() {
	   chronoTimer = ChronoTimer.getInstance();
	   timeStamp = LocalTime.now();
   }
   
	
	/**
	 * Test calling TRIG before on		
	 */
   @Test
	public void testStartBeforeOn() {
		assertFalse(chronoTimer.executeCommand(timeStamp, "TRIG", new String[]{ "I"}));
		
	}
	/**
	 * Test calling on works
	 */
	@Test
	public void testStartAfterOn(){
		assertTrue(chronoTimer.executeCommand(timeStamp, "ON", new String[]{ "DOES NOT EVER TOUCH THESE"}));
	}
	
	/**
	 * Test calling a DNF before ON has been called throws nullPointer
	 */
	@Test(expected=NullPointerException.class)
	public void testDNFBeforeOn(){
	    assertFalse(chronoTimer.executeCommand(timeStamp, "DNF", new String[]{ "1"}));	
	}
	
	/**
	 * Calling stop before start should not work
	 */
    @Test
    public void stopBeforeStart(){
    	assertFalse(chronoTimer.executeCommand(timeStamp, "STOP", new String[]{"1"}));
    }
    /**
     *  call on and off in that order should work
     */
    @Test
    public void testOnOff(){
    	assertTrue(chronoTimer.executeCommand(timeStamp, "ON", new String[]{"1"}));
    	assertTrue(chronoTimer.executeCommand(timeStamp, "OFF", new String[]{"1"}));
    	
    }
    
    /**
     * call on off off
     */
    @Test()
    public void testOnOffCallCommand(){
    	assertTrue(chronoTimer.executeCommand(timeStamp, "ON", new String[]{"1"}));
    	assertTrue(chronoTimer.executeCommand(timeStamp, "OFF", new String[]{"1"}));
    	assertFalse(chronoTimer.executeCommand(timeStamp, "START", new String[]{"1"}));
    }
	
}
