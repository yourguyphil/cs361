package race;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.gson.Gson;

public abstract class AbstractEvent {

	protected Lane[] lanes;
	private int numRacersAdded;
	private boolean nextFinishDNF;
	
	/**
	 * Creates an event with the specified number of lanes
	 * @param numLanes the number of lanes
	 */
	public AbstractEvent(int numLanes) {
		lanes = new Lane[numLanes];
		for (int i = 0; i < lanes.length; i++)
			lanes[i] = new Lane();
	}
	
	/** 
	 * Starts the next racer in the specified lane
	 * @param start the time the racer started at
	 * @param lane the lane to start the racer from (0-indexed)
	 */
	public abstract void start(LocalTime start, int lane);
	
	/** 
	 * Finishes the next racer in the specified lane
	 * @param finish the time the racer finished at
	 * @param lane the lane to finish the racer from (0-indexed)
	 */
	public void finish(LocalTime finish, int lane) {
		if (finish == null) {
			System.out.println("Finish cannot be null");
		} else if (lane < 0 || lane >= lanes.length) {
			String event = getClass().getSimpleName();
			System.out.println(event + " only has lanes 1-" + lanes.length);
		} else {
			LinkedList<Racer> startedRacers = getLane(lane).getStartedRacers();
			LinkedList<Racer> finishedRacers = getLane(lane).getFinishedRacers();
			
			if (!startedRacers.isEmpty()) {
				if (nextFinishDNF) {
					startedRacers.peek().DNF();
					finishedRacers.add(startedRacers.poll());
					nextFinishDNF = false;
				} else {
					startedRacers.peek().setFinish(finish);
					finishedRacers.add(startedRacers.poll());
				}
			} else {
				System.out.println("No started racers to finish");
			}
		}
	}
	
	/** 
	 * Marks the next racer to finish as DNF
	 */
	public void DNF() {
		nextFinishDNF = true;
	}
	
	/** 
	 * Notifies event that a channel was triggered
	 * @param channel the channel that was triggered (0-indexed)
	 * @param time the time the channel was triggered
	 */
	public void notifyChannelTriggered(LocalTime time, int channel) {
		if (time == null) {
			System.out.println("Time cannot be null");
		} else if (channel < 0 || channel >= numChannels) {
			String event = getClass().getSimpleName();
			int numChannels = lanes.length * 2;
			System.out.println(event + " event only accepts input on channels 1-" + numChannels);
		} else {
			int lane = channel / 2;
			if (channel % 2 == 0)
				start(time, lane);
			else
				finish(time, lane);
		}
	}
	
	/** 
	 * Swap the next two racers to finish in a IND event
	 */
	public void swap() {
		System.out.println("Cannot swap in a non-IND event");
	}
	
	/** 
	 * Sets the next competitor to start
	 * @param bibNumber the bib number of the next competitor to start
	 */
	public void num(int bibNumber) {
		int lane = numRacersAdded % lanes.length; 
		getLane(lane).getPendingRacers().add(new Racer(bibNumber));
		numRacersAdded++;
	}
	
	/** 
	 * Clear the next racer waiting to start
	 * @param bibNumber the bib number of the competitor to clear
	 */
	public void clear(int bibNumber) {
		for (Lane lane : lanes) 
			for (Racer r : lane.getPendingRacers())
				if (r.getBib() == bibNumber)
					lane.getPendingRacers().remove(r);
	}
	
	/**
	 * Gets the specified lane
	 * @param lane the specified lane (0-indexed)
	 */
	public Lane getLane(int lane) {
		if (lane < 0 || lane >= lanes.length) {
			String event = getClass().getSimpleName();
			System.out.println(event + " only has lanes 1-" + lanes.length);
			return null;
		} else {
			return lanes[lane];
		}
	}
	
	/**
	 * Gets the lanes in a JSON string
	 * @return the lanes in a JSON string
	 */
	public String toJSON() {
		return (new Gson()).toJson(lanes);
	}
	
	@Override
	public String toString() {
		String description = "";
		for (Lane lane : lanes) description += lane.toString();
		
		return description;
	}
	
}
