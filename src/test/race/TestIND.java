package test.race;

import static org.junit.Assert.*;
import java.time.LocalTime;
import org.junit.Test;

import race.IND;
import race.Racer;

public class TestIND {

	@Test
	public void testCancel() {
		IND ind = new IND();
		LocalTime time = LocalTime.now();

		Racer racer1 = new Racer(123);
		ind.num(racer1.getBib());

		ind.startRacer(time);

		ind.cancelRacer();

		// Make sure racer removed from pending Queue
		assertTrue(ind.getPendingRacers().isEmpty());
		assertEquals(racer1.getFinish(), null);
	}

	@Test
	public void testQueues() {
		LocalTime time = LocalTime.now();
		IND ind = new IND();

		Racer racer1 = new Racer(123);
		Racer racer2 = new Racer(234);
		ind.num(racer1.getBib());
		ind.num(racer2.getBib());

		ind.startRacer(time);

		// Pending queue shouldn't be empty yet, still 1 more racer.
		assertFalse(ind.getPendingRacers().isEmpty());
		ind.startRacer(time);
		// Now pending should be empty, but start shouldn't be.
		assertTrue(ind.getPendingRacers().isEmpty());

		assertFalse(ind.getStartedRacers().isEmpty());
	}

	@Test
	public void testClear() {
		LocalTime time = LocalTime.now();
		IND ind = new IND();

		Racer racer1 = new Racer(123);
		Racer racer2 = new Racer(234);
		ind.num(racer1.getBib());
		ind.num(racer2.getBib());

		ind.startRacer(time);

		assertFalse(ind.getPendingRacers().isEmpty());
		// Clear last racer from pending queue
		ind.clear(234);
		assertTrue(ind.getPendingRacers().isEmpty());

		assertFalse(ind.getStartedRacers().isEmpty());
	}

	@Test
	public void testFinish() {
		LocalTime time = LocalTime.now();
		IND ind = new IND();

		Racer racer1 = new Racer(123);
		ind.num(racer1.getBib());
		// No racers finished yet so finished list should be empty
		assertTrue(ind.getFinishedRacers().isEmpty());

		ind.startRacer(time);

		ind.finishRacer(time);
		// Now should be finished
		assertFalse(ind.getFinishedRacers().isEmpty());
	}
}
