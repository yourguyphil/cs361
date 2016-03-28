package test.race;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import race.Racer;

public class TestRacer {
	Racer racer1;
	LocalTime startTime;
	@Before
	public void before() {
		racer1 = new Racer(123);
		startTime = LocalTime.now();
	}
	
	@Test
	public void testSetStart() {
		//Racer1 hasn't started yet so startTime shouldn't exist
		assertTrue(racer1.getStart() == null);
		startTime = LocalTime.now();
		racer1.setStart(startTime);
		//Racer1 started so startTime should exist
		assertFalse(racer1.getStart() == null);
		//Local startTime should equal racer1s start time
		assertEquals(startTime,racer1.getStart());
	}
	
	@Test
	public void testSetFinish() {
		LocalTime finishTime = LocalTime.now();
		racer1.setFinish(finishTime);
		//Racer1 hasn't started so there should be no finish time
		assertTrue(racer1.getFinish() == null);
		racer1.setStart(startTime);
		racer1.setFinish(finishTime);
		// Now racer1 has started and then finished so there should be a finish time
		assertFalse(racer1.getFinish() == null);
		//Local finishTime should equal racer1s finish time
		assertEquals(finishTime,racer1.getFinish());
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
	
	// Make sure that the total time of racer is correct
	public void testGetDuration() {
		startTime = LocalTime.now();
		racer1.setStart(startTime);
		LocalTime finishTime = LocalTime.now();
		racer1.setFinish(finishTime);
		assertTrue(racer1.getDuration() == Duration.between(startTime, finishTime));
	}
}
