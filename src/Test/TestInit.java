package Test;

import static org.junit.Assert.*;

import java.time.LocalTime;

import io.Command;
import io.Parser;

import org.junit.Test;

public class TestInit {
   Parser p = new Parser();
   Command c = new Command();
   LocalTime timeStamp = LocalTime.now();
	
	/**
	 * Test calling TRIG before on		
	 */
   @Test
	public void testStartBeforeOn() {
		assertFalse(c.execute(timeStamp, "TRIG", new String[]{ "I"}));
	}
	/**
	 * Test calling on works
	 */
	@Test
	public void testStartAfterOn(){
		assertTrue(c.execute(timeStamp, "ON", new String[]{ "DOES NOT EVER TOUCH THESE"}));
	}
	
	/**
	 * Test calling a DNF before ON has been called throws nullPointer
	 */
	@Test(expected=NullPointerException.class)
	public void testDNFBeforeOn(){
	    assertFalse(c.execute(timeStamp, "DNF", new String[]{ "1"}));	
	}
	
	/**
	 * Calling stop before start should not work
	 */
    @Test
    public void stopBeforeStart(){
    	assertFalse(c.execute(timeStamp, "STOP", new String[]{"1"}));
    }
    /**
     *  call on and off in that order should work
     */
    @Test
    public void testOnOff(){
    	assertTrue(c.execute(timeStamp, "ON", new String[]{"1"}));
    	assertTrue(c.execute(timeStamp, "OFF", new String[]{"1"}));
    }
	
}
