package race;

import java.time.LocalTime;
import java.util.LinkedList;

import com.google.gson.Gson;

public abstract class AbstractEvent {

	protected LinkedList<Racer> pendingRacers;
	protected LinkedList<Racer> startedRacers;
	protected LinkedList<Racer> finishedRacers;
	
	public AbstractEvent() {
		startedRacers = new LinkedList<Racer>();
		pendingRacers = new LinkedList<Racer>();
		finishedRacers = new LinkedList<Racer>();
	}
	
	public abstract void num(int bibNumber);
	public abstract void clear(int bibNumber);
	public abstract void cancelRacer();
	public abstract void startRacer(LocalTime start);
	public abstract void finishRacer(LocalTime finish);
	public abstract void DNFRacer();
	
	@Override
	public String toString() {
		String description = "";
		for (Racer r : finishedRacers) description += r + "\n";
		for (Racer r : startedRacers) description += r + "\n";
		for (Racer r : pendingRacers) description += r + "\n";
		
		return description;
	}
	
	public String toJSON() {
		LinkedList<Racer> racers = new LinkedList<Racer>();
		for(Racer r : pendingRacers) racers.add(r);
		for(Racer r : startedRacers) racers.add(r);
		for(Racer r : finishedRacers) racers.add(r);

		Gson g = new Gson();
		return g.toJson(racers);
	}
	
	public LinkedList<Racer> getPendingRacers() { return pendingRacers; }
	public LinkedList<Racer> getStartedRacers() { return startedRacers; }
	public LinkedList<Racer> getFinishedRacers() { return finishedRacers; }
	
}
