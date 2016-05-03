package race;

import java.util.ArrayList;
import java.util.LinkedList;

public class Lane {
	
	private LinkedList<Racer> startedRacers;
	private LinkedList<Racer> finishedRacers;
	private LinkedList<Racer> pendingRacers;
	
	/**
	 * Default constructor
	 */
	public Lane() {
		startedRacers = new LinkedList<Racer>();
		finishedRacers = new LinkedList<Racer>();
		pendingRacers = new LinkedList<Racer>();
	}
	
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
	
	public ArrayList<Racer> getAllRacers(){
		ArrayList<Racer> res = new ArrayList<Racer>();
		//need to stop those still waiting
		for(Racer r: this.pendingRacers){
			//r.DNF();
		}
		for(Racer r: this.getStartedRacers()){
			//r.DNF();
		}
		res.addAll(this.getFinishedRacers());
		res.addAll(this.getPendingRacers());
		res.addAll(this.getStartedRacers());
		return res;
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
