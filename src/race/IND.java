package race;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class IND {

	private Queue<Racer> pendingRacers;
	private Queue<Racer> startedRacers;
	private ArrayList<Racer> finishedRacers;
	private boolean isOngoing;
    

	public IND() {
		startedRacers = new LinkedList<Racer>();
		pendingRacers = new LinkedList<Racer>();
		finishedRacers = new ArrayList<Racer>();
		isOngoing = true;
	}
    /**
     * 
     * @param bibNumber assign a bib number
     */
	public void num(int bibNumber) {
		if (isOngoing) {
			pendingRacers.add(new Racer(bibNumber));
		} else {
			throw new IllegalStateException("This event is ended");
		}
	}
    /**
     * cancels the next racer
     */
	public void cancelRacer() {
		if (isOngoing) {
			startedRacers.peek().cancel();
			finishedRacers.add(startedRacers.poll());
		} else {
			throw new IllegalStateException("This event is ended");
		}
	}
    /**
     *  Starts the next racer
     * @param start The time the next racer starts
     */
	public void startRacer(LocalTime start) {
		if (isOngoing) {
			pendingRacers.peek().start(start);
			startedRacers.add(pendingRacers.poll());
		} else {
			throw new IllegalStateException("This event is ended");
		}
	}
/**
 * 
 * @param finish the time the next racer finishes
 */
	public void finishRacer(LocalTime finish) {
		if (isOngoing) {
			startedRacers.peek().finish(finish);
			finishedRacers.add(startedRacers.poll());
		} else {
			throw new IllegalStateException("This event is ended");
		}
	}
/**
 * Assign the next racer a DNF
 */
	public void DNFRacer() {
		if (isOngoing) {
			startedRacers.peek().DNF();
			finishedRacers.add(startedRacers.poll());
		} else {
			throw new IllegalStateException("This event is ended");
		}
	}
	/**
	 * returns true if IND is happening
	 * @return
	 */
	public boolean isOngoing() {
		return isOngoing;
	}
	/**
	 * set isOngoing to false;
	 */
	public void end() {
		isOngoing = false;
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

}