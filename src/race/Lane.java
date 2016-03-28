package race;

import java.util.LinkedList;

public class Lane {
	
	private LinkedList<Racer> startedRacers;
	private LinkedList<Racer> finishedRacers;
	private LinkedList<Racer> pendingRacers;
	
	/**
	 * Gets the pending racers
	 * @return the pending racers
	 */
	public LinkedList<Racer> getPendingRacers() {
		return pendingRacers;
	}
	
	/**
	 * Gets the started racers
	 * @return the started racers
	 */
	public LinkedList<Racer> getStartedRacers() {
		return startedRacers;
	}
	
	/**
	 * Gets the finished racers
	 * @return the finished racers
	 */
	public LinkedList<Racer> getFinishedRacers() {
		return finishedRacers;
	}
	
	@Override
	public String toString() {
		String description = "";
		for (Racer r : finishedRacers) description += r + "\n";
		for (Racer r : startedRacers) description += r + "\n";
		for (Racer r : pendingRacers) description += r + "\n";
		
		return description;
	}
}
