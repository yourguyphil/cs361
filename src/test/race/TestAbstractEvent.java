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
	AbstractEvent evnt;
	
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
		evnt = ind;
		racer1.setStart(time);
		ind.startRacer(time);
		assertFalse(evnt.getStartedRacers() == null);
		assertFalse(evnt.toJSON() == null);
		assertTrue(evnt.getPendingRacers().size() == 0);
		racer1.cancel();
		ind.clear(0);
		assertFalse(evnt.getStartedRacers() == null);
		assertFalse(evnt.toJSON() == null);
		assertFalse(evnt.getPendingRacers() == null);
		assertFalse(evnt.getFinishedRacers() == null);
		assertFalse(evnt.getStartedRacers() == null);
		evnt.clear(0);
		assertTrue(evnt.getPendingRacers().size() == 0);
		
		time = LocalTime.now();
		racer1.setStart(time);
		ind.startRacer(time);
		time = LocalTime.now();
		racer3.setStart(time);
		
		assertTrue(evnt.getPendingRacers().size() == 0);
		assertFalse(evnt.toJSON() == null);
		assertFalse(evnt.toString() == null);
		racer1 = racer2 = racer3 = null;
		assertFalse(evnt.toJSON() == null);
		assertTrue(evnt.getPendingRacers().toString() == "[]");
	}
	
}
