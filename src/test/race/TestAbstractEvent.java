package test.race;

import static org.junit.Assert.*;

import java.awt.Event;
import java.time.LocalTime;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import race.AbstractEvent;
import race.Lane;
import race.PARIND;
import race.Racer;
import race.IND;

public class TestAbstractEvent {
	
	Racer racer1, racer2, racer3;
	LocalTime time;
	AbstractEvent event;
	IND ind;
	PARIND parind;
	
	@Before
	public void before() {
		time = LocalTime.now();		
		racer1 = new Racer(0);
		racer2 = new Racer(1);
		racer3 = new Racer(2);
		ind = new IND();
		parind = new PARIND();
	}
	
	@Test
	public void testFinish() {
		IND ind = new IND();
		ind.num(123);
		ind.start(time, 0);
		// No racers finished yet so finishedRacers size == 0
		assertEquals(0,ind.getLane(0).getFinishedRacers().size());
		// 1 racer finished so finishedRacers size == 1
		ind.finish(time, 0);
		assertEquals(1,ind.getLane(0).getFinishedRacers().size());
		
		PARIND parind = new PARIND();
		parind.num(123);
		parind.num(234);
		
		//Start both racers, both lanes finihsedRacers queue should be empty
		parind.start(time, 0);
		time = LocalTime.now();
		parind.start(time, 1);
		assertEquals(0,parind.getLane(0).getFinishedRacers().size());
		assertEquals(0,parind.getLane(1).getFinishedRacers().size());
		
		// Finish lane 0, only lane 1's finishedQueue shold be empty
		parind.finish(time, 0);
		assertEquals(1,parind.getLane(0).getFinishedRacers().size());
		assertEquals(0,parind.getLane(1).getFinishedRacers().size());
		//Finish lane 1, both lanes finishedQueue shuold be 1
		parind.finish(time, 1);
		assertEquals(1,parind.getLane(0).getFinishedRacers().size());
		assertEquals(1,parind.getLane(1).getFinishedRacers().size());
	}
	
	@Test
	public void testDNF() {
		IND ind = new IND();
		ind.num(123);
		ind.start(time, 0);
		// only queue thats not empty should be startedRacers and its size should == 1
		assertEquals(0,ind.getLane(0).getPendingRacers().size());
		assertEquals(0,ind.getLane(0).getFinishedRacers().size());
		assertEquals(1,ind.getLane(0).getStartedRacers().size());		
		
		//Racer didn't finish so only nonempty queue now should still be startedRacers
		ind.DNF();
		assertEquals(0,ind.getLane(0).getPendingRacers().size());
		assertEquals(0,ind.getLane(0).getFinishedRacers().size());
		assertEquals(1,ind.getLane(0).getStartedRacers().size());	
		
		//PARIND 
		PARIND parind = new PARIND();
		parind.num(123);
		parind.num(234);
		parind.num(345);
		parind.start(time, 0);
		parind.start(time, 1);
		time = LocalTime.now();
		parind.start(time, 2);
		//For lane 0 only queue thats not empty should be startedRacers and its size should == 1
		assertEquals(0,parind.getLane(0).getFinishedRacers().size());
		assertEquals(1,parind.getLane(0).getStartedRacers().size());		
		
		//For lane 1 only queue thats not empty should be startedRacers and its size should == 1
		assertEquals(0,parind.getLane(1).getPendingRacers().size());
		assertEquals(0,parind.getLane(1).getFinishedRacers().size());
		assertEquals(1,parind.getLane(1).getStartedRacers().size());				
		
		//Racer didn't finish so finishedRacers should be empty
		parind.DNF();
		assertEquals(1,parind.getLane(0).getPendingRacers().size());
		assertEquals(0,parind.getLane(0).getFinishedRacers().size());
		assertEquals(1,parind.getLane(0).getStartedRacers().size());		
	}
	
	@Test
	public void testNotifyChannelTriggered() {
		IND ind = new IND();
		ind.num(123);
		// Check that it starts and finishes racers properly
		//finish
		ind.notifyChannelTriggered(time, 1);
		assertEquals(1,ind.getLane(0).getPendingRacers().size());
		//start racer
		ind.notifyChannelTriggered(time, 0);
		assertEquals(0,ind.getLane(0).getPendingRacers().size());
		//finish racer
		ind.notifyChannelTriggered(time,1);
		assertEquals(1,ind.getLane(0).getFinishedRacers().size());
		
		PARIND parind = new PARIND();
		parind.num(123);
		// Check that it starts and finishes racers properly
		//finish
		parind.notifyChannelTriggered(time, 1);
		assertEquals(1,parind.getLane(0).getPendingRacers().size());
		//start racer
		parind.notifyChannelTriggered(time, 0);
		assertEquals(0,parind.getLane(0).getPendingRacers().size());
		//finish racer
		parind.notifyChannelTriggered(time,1);
		assertEquals(1,parind.getLane(0).getFinishedRacers().size());
	}
	
	@Test
	public void testSwap() {
		//Put 4 racers into pendingRacers queue
		IND ind = new IND();
		ind.num(123);
		ind.num(234);
		ind.num(345);
		ind.num(456);

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

		assertTrue(ind.getLane(0).getStartedRacers().peek().getBib() == 234);
		assertFalse(ind.getLane(0).getStartedRacers().peek().getBib() == 123);
	}
	
	@Test
	public void testNum() {
		IND ind = new IND();
		ind.num(1);
		assertFalse(ind.getLane(0).getPendingRacers().size() == 0);
		assertTrue(ind.getLane(0).getPendingRacers().size() == 1);
		
		PARIND parind = new PARIND();
		parind.num(1);
		parind.num(2);
		assertFalse(parind.getLane(0).getPendingRacers().size() == 0);
		assertFalse(parind.getLane(1).getPendingRacers().size() == 0);
	}
	
	@Test
	public void testClear() {
		IND ind = new IND();
		ind.num(1);
		assertFalse(ind.getLane(0).getPendingRacers().size() == 0);
		assertTrue(ind.getLane(0).getPendingRacers().size() == 1);
		ind.clear(1);
		assertTrue(ind.getLane(0).getPendingRacers().size() == 0);
		
		PARIND parind = new PARIND();
		parind.num(1);
		parind.num(2);
		assertFalse(parind.getLane(0).getPendingRacers().size() == 0);
		assertFalse(parind.getLane(1).getPendingRacers().size() == 0);
		parind.clear(1);
		assertTrue(parind.getLane(0).getPendingRacers().size() == 0);
		assertTrue(parind.getLane(1).getPendingRacers().size() == 1);
		
	}
	
	@Test
	public void testGetLane() {
		IND ind = new IND();
		assertNull(ind.getLane(-1));
		assertNotNull(ind.getLane(0));
		assertNull(ind.getLane(1));
		
		PARIND parind = new PARIND();
		assertNull(parind.getLane(-1));
		assertNotNull(parind.getLane(0));
		assertNotNull(parind.getLane(1));
		assertNull(parind.getLane(2));
	}
	
	@Test
	public void test(){
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
