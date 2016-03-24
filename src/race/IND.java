package race;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

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
	
	/** Sets the next competitor to start
	 * @param bibNumber the bib number of the next competitor to start
	 */
	public void num(int bibNumber) {
		pendingRacers.add(new Racer(bibNumber));
	}
	
	/** Clear the next racer waiting to start
	 * @param bibNumber the bib number of the competitor to clear
	 */
	public void clear(int bibNumber) {		
		for (Racer r : pendingRacers) {
			if (r.getBib() == bibNumber)
				pendingRacers.remove(r);
		}
	}
	
	/** Marks the next active racer waiting to finish as canceled
	 */
	public void cancelRacer() {
		startedRacers.element().cancel();
		finishedRacers.add(startedRacers.remove());
	}

	/** Starts the next racer waiting to start
	 * @param start the time the racer started at
	 */
	public void startRacer(LocalTime start) {
		pendingRacers.element().setStart(start);
		startedRacers.add(pendingRacers.remove());
	}

	/** Finishes the next active racer waiting to finish
	 * @param finish the time the racer finished at
	 */
	public void finishRacer(LocalTime finish) {
		startedRacers.element().setFinish(finish);
		finishedRacers.add(startedRacers.remove());
	}

	/** Marks the next active racer waiting to finish as DNF
	 */
	public void DNFRacer() {
		startedRacers.element().DNF();
		finishedRacers.add(startedRacers.remove());
	}
	
	/** Swap the next two racers to finish
	 */
	public void swap() {
		if(startedRacers.size() < 2)
			throw new IllegalStateException("Need at least 2 racers to perform swap!");

		Collections.swap(startedRacers, 0, 1);
	}
	
	

}