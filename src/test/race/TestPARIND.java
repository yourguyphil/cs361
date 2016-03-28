package test.race;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import race.IND;
import race.PARIND;


public class TestPARIND {
	LocalTime time;
	@Before
	public void before() {
		time = LocalTime.now();
	}
	
	@Test
	public void testStart() {
		PARIND parind = new PARIND();
		// 3 racers, 123 and 234 are in Lane 0 while 345 will be in lane 1
		parind.num(123);
		parind.num(234);
		parind.num(345);
		parind.start(time, 12);
		//Bad lane, shouldn't work
		assertNull(parind.getLane(12));
		
		parind.start(time,0);
		//Started one racer so start queue should have 1 
		assertEquals(1,parind.getLane(0).getStartedRacers().size());
		assertEquals(0,parind.getLane(1).getStartedRacers().size());
		//Started another so start queue should now have 2
		parind.start(time, 0);
		assertEquals(2,parind.getLane(0).getStartedRacers().size());
		parind.start(time,1);
		//Lane 1 should now have 1 startedRacer
		assertEquals(1,parind.getLane(1).getStartedRacers().size());
	}
}
