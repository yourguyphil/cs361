package race;

import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedList;

/** Individual timed events (such as ski races, bobsled runs) In the IND events,
 * racers queue up for single “runs” of the race. Each racer has a start event
 * and end event. By convention the start is on channel 1, the finish is on
 * channel 2, but there is nothing particularly special about the events on each
 * channel. When a start event is received, it is associated with the next racer
 * in the start queue. When a finish event is received, it is associated with
 * the next racer that is to finish. If there is more than one racer active, the
 * finish event is associated with racers in a FIFO (first in, first out) basis
 */
public class IND extends AbstractEvent{
	
	/**
	 * Creates an IND event
	 */
	public IND() {
		super(1);
	}

	@Override
	public void start(LocalTime start, int lane) {
		if (start == null) {
			System.out.println("Start cannot be null");
		} else if (lane < 0 || lane >= lanes.length) {
			System.out.println("IND only has lanes 1-" + lanes.length);
		} else {
			LinkedList<Racer> pendingRacers = getLane(lane).getPendingRacers();
			LinkedList<Racer> startedRacers = getLane(lane).getStartedRacers();
			
			if (!pendingRacers.isEmpty()) {
				pendingRacers.peek().setStart(start);
				startedRacers.add(pendingRacers.poll());
			} else {
				System.out.println("No pending racers to start");
			}
		}
	}
	
	@Override
	public void swap() {
		LinkedList<Racer> startedRacers = getLane(0).getStartedRacers();
		
		if(startedRacers.size() >= 2)
			Collections.swap(startedRacers, 0, 1);
		else
			System.out.println("Need at least 2 started racers to swap");
	}
	
}