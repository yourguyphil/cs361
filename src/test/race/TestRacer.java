package test.race;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import race.Racer;

public class TestRacer {
	Racer racer1;
	LocalTime startTime;
	@Before
	public void before() {
	}
	
	@Test
	public void test(){
	}
	
	//Test if cancel race will work properly
	public void testCancel() {
		startTime = LocalTime.now();
		racer1.setStart(startTime);
		racer1.cancel();
		assertTrue(racer1.getFinish() == null);
		assertTrue(racer1.getStart() == null);
	}
	//Test if Did not finish will work properly
	public void testDNF() {
		startTime = LocalTime.now();
		racer1.setStart(startTime);
		racer1.DNF();
		assertTrue(racer1.getFinish() == null);
		//Start time should still be there
		assertFalse(racer1.getStart() == null);
	}
	/*
	 * Make sure that the total time of racer is correct
	 */
	public void testGetDuration() {
		startTime = LocalTime.now();
		racer1.setStart(startTime);
		LocalTime finishTime = LocalTime.now();
		racer1.setFinish(finishTime);
		assertTrue(racer1.getDuration() == Duration.between(startTime, finishTime));
	}
}
