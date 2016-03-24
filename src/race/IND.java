package race;

import java.time.LocalTime;
import java.util.ArrayList;
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

	private Queue<Racer> pendingRacers;
	private Queue<Racer> startedRacers;
	private ArrayList<Racer> finishedRacers;

	/** Default constructor for IND
	 */
	public IND() {
		startedRacers = new LinkedList<Racer>();
		pendingRacers = new LinkedList<Racer>();
		finishedRacers = new ArrayList<Racer>();
	}
	
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
		// We need to clear the racer whose bibId matches from pending
		Queue<Racer> replacePending = new LinkedList<Racer>();
		
		// If the next racers bib != bibId add it to new queue; don't add racer we want to clear.
		while(!pendingRacers.isEmpty()) {
			if(pendingRacers.peek().getBib() != bibNumber) 
				replacePending.add(pendingRacers.poll());
			else pendingRacers.poll();
		}
		
		//set pending queue to new queue that doesn't include cleared racers
		pendingRacers = replacePending;
	}
	
	/** Marks the next active racer waiting to finish as canceled
	 */
	public void cancelRacer() {
		if (startedRacers.isEmpty())
			throw new IllegalStateException("No started racers to cancel");

		startedRacers.peek().cancel();
		finishedRacers.add(startedRacers.poll());
	}

	/** Starts the next racer waiting to start
	 * @param start the time the racer started at
	 */
	public void startRacer(LocalTime start) {
		if (pendingRacers.isEmpty())
			throw new IllegalStateException("No pending racers to start");

		pendingRacers.peek().setStart(start);
		startedRacers.add(pendingRacers.poll());
	}

	/** Finishes the next active racer waiting to finish
	 * @param finish the time the racer finished at
	 */
	public void finishRacer(LocalTime finish) {
		if (startedRacers.isEmpty())
			throw new IllegalStateException("No started racers to finish");

		startedRacers.peek().setFinish(finish);
		finishedRacers.add(startedRacers.poll());
	}

	/** Marks the next active racer waiting to finish as DNF
	 */
	public void DNFRacer() {
		if (startedRacers.isEmpty())
			throw new IllegalStateException("No started racers to DNF");

		startedRacers.peek().DNF();
		finishedRacers.add(startedRacers.poll());
	}
	
	/** Swap the next two racers to finish
	 */
	public void swap() {
		if(startedRacers.size() < 2)
			throw new IllegalStateException("Need at least 2 racers to perform swap!");

		// Need a temporary queue to replace current queue with swapped racers
		Queue<Racer> newQueue = new LinkedList<Racer>();
		
		//swap first two racers
		Racer swapWithSecond = startedRacers.poll();
		newQueue.add(startedRacers.poll());
		newQueue.add(swapWithSecond);
		
		// Add rest of racers to temp queue
		newQueue.addAll(startedRacers);
		
		// Relpace original Queue with temp queue
		startedRacers = newQueue;
	}
	
	@Override
	public String toString() {
		String description = "";
		for (Racer racer : finishedRacers)
			description += racer + "\n";
		for (Racer racer : startedRacers)
			description += racer + "\n";
		for (Racer racer : pendingRacers)
			description += racer + "\n";
		
		return description;
	}
	
	// TESTING PURPOSES
	public Queue<Racer> getStartedRacerQueue() { return startedRacers; }
	public Queue<Racer> getPendingRacerQueue() { return pendingRacers; }
	public ArrayList<Racer> getFinishedRacers() { return finishedRacers; }

	@Override
	public String toJSON() {
		// TODO Auto-generated method stub
		return null;
	}
}