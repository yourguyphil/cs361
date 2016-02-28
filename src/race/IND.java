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
	}

	public void num(int bibNumber) {
		if (isOngoing) {
			pendingRacers.add(new Racer(bibNumber));
		} else {
			throw new IllegalStateException("This event is ended");
		}
	}

	public void cancelRacer() {
		if (isOngoing) {
			startedRacers.peek().cancel();
			finishedRacers.add(startedRacers.poll());
		} else {
			throw new IllegalStateException("This event is ended");
		}
	}

	public void startRacer(LocalTime start) {
		if (isOngoing) {
			pendingRacers.peek().start(start);
			startedRacers.add(pendingRacers.poll());
		} else {
			throw new IllegalStateException("This event is ended");
		}
	}

	public void finishRacer(LocalTime finish) {
		if (isOngoing) {
			startedRacers.peek().finish(finish);
			finishedRacers.add(startedRacers.poll());
		} else {
			throw new IllegalStateException("This event is ended");
		}
	}

	public void DNFRacer() {
		if (isOngoing) {
			startedRacers.peek().DNF();
			finishedRacers.add(startedRacers.poll());
		} else {
			throw new IllegalStateException("This event is ended");
		}
	}
	
	public boolean isOngoing() {
		return isOngoing;
	}
	
	public void end() {
		isOngoing = false;
	}

	@Override
	public String toString() {
		String description = "";
		for (Racer racer : finishedRacers)
			description += racer + "\n";
		
		return description;
	}

}