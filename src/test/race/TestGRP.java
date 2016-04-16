package test.race;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import race.GRP;

public class TestGRP {
	LocalTime time;
	
	@Before
	public void before() {
		time = LocalTime.now();
	}
	
	@Test
	public void testStart() {
		GRP group = new GRP();
		//Should have no started or pending racers yet.
		assertEquals(group.getLane(0).getStartedRacers().size(), 0);
		assertEquals(group.getLane(0).getPendingRacers().size(), 0);
		group.num(101);
		group.num(102);
		group.num(103);
		group.num(104);
		group.num(105);
		group.num(106);
		group.num(107);
		group.num(108);
		// Now should have 8 pending racers.
		assertEquals(group.getLane(0).getPendingRacers().size(), 8);
		group.start(time, 0);
		// Now 8 started racers since they all start at the same time.
		assertEquals(group.getLane(0).getStartedRacers().size(), 8);
		time = LocalTime.now();
		group.finish(time, 0);
		// Make sure finish works as well
		assertEquals(group.getLane(0).getStartedRacers().size(), 7);
	}
}
