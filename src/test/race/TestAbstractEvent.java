package test.race;

import static org.junit.Assert.*;
import java.awt.Event;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import race.AbstractEvent;
import race.Racer;
import race.IND;

public class TestAbstractEvent {
	
	Racer racer1, racer2, racer3;
	LocalTime time;
	AbstractEvent event;
	
	@Before
	public void before() {
		time = LocalTime.now();
	}
	
	@Test
	// Testing methods from AbstractEvent class.
	public void testAll(){		
		racer1 = new Racer(0);
		racer2 = new Racer(1);
		racer3 = new Racer(2);
		assertFalse(racer1.getBib() == 1);
		assertTrue(racer1.getBib() == 0);
		IND ind = new IND();
		event = ind;
		racer1.setStart(time);
		ind.start(time, 0);
		assertFalse(event.getLane(0).getStartedRacers() == null);
		assertFalse(event.toJSON() == null);
		assertTrue(event.getLane(0).getPendingRacers().size() == 0);
		ind.clear(0);
		assertFalse(event.getLane(0).getStartedRacers() == null);
		assertFalse(event.toJSON() == null);
		assertFalse(event.getLane(0).getPendingRacers() == null);
		assertFalse(event.getLane(0).getFinishedRacers() == null);
		assertFalse(event.getLane(0).getStartedRacers() == null);
		event.clear(0);
		assertTrue(event.getLane(0).getPendingRacers().size() == 0);
		
		time = LocalTime.now();
		racer1.setStart(time);
		ind.start(time, 0);
		time = LocalTime.now();
		racer3.setStart(time);
		
		assertTrue(event.getLane(0).getPendingRacers().size() == 0);
		assertFalse(event.toJSON() == null);
		assertFalse(event.toString() == null);
		racer1 = racer2 = racer3 = null;
		assertFalse(event.toJSON() == null);
		assertTrue(event.getLane(0).getPendingRacers().toString() == "[]");
	}
	
}
