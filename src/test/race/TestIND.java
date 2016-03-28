package test.race;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import race.IND;

public class TestIND {
	LocalTime time;
	@Before
	public void before() {
		time = LocalTime.now();
	}
	
	@Test
	public void testStart() {
		IND ind = new IND();
		ind.num(123);
		ind.num(234);
		ind.start(time, 12);
		//Bad lanes, shouldn't work
		assertNull(ind.getLane(12));
		assertNull(ind.getLane(1));
		
		ind.start(time,0);
		//Started one racer so start queue should have 1 
		assertEquals(1,ind.getLane(0).getStartedRacers().size());
		//Started another so start queue should now have 2
		ind.start(time, 0);
		assertEquals(2,ind.getLane(0).getStartedRacers().size());
	}
	
	@Test
	public void testSwap() {
		//Put 3 racers into pendingRacers queue
		IND ind = new IND();
		ind.num(123);
		ind.num(234);
		ind.num(345);

		//Start 3 racers 
		time = LocalTime.now();
		ind.start(time, 0);
		assertTrue(ind.getLane(0).getStartedRacers().peek().getBib() == 123);
		time = LocalTime.now();
		ind.start(time, 0);
		ind.start(time, 0);
		ind.clear(123);
		//swap 1st two racers, 1st place should now be 234 and second should be 123
		ind.swap();

		assertFalse(ind.getLane(0).getStartedRacers().peek().getBib() == 123);
		assertTrue(ind.getLane(0).getStartedRacers().peek().getBib() == 234);
	}
	
}
