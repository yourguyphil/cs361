package chronotimer;

import io.Writer;

import java.time.LocalTime;
import java.util.ArrayList;

import race.EventType;
import race.IND;

/** Used to time sports events.
 */
public class ChronoTimer {

	private boolean isOn;
	ArrayList<IND> runs;
	Channel[] channels;
	LocalTime time;
	
	private static ChronoTimer singleton = new ChronoTimer();

	/** Private default constructor that prevents any other class from instantiating.
	 */
	private ChronoTimer() {
		runs = new ArrayList<IND>();
		runs.add(new IND());

		channels = new Channel[12];
		for (int i = 0; i < channels.length; i++)
			channels[i] = new Channel();
		
		time = LocalTime.now();
	}
	
	/** Gets a singleton instance of the chronotimer
	 * @return singleton instance of the chronotimer
	 */
	public static ChronoTimer getInstance() {
		return singleton;
	}

	/** Turn system on (if off), enter quiescent state
	 */
	public void turnOn() {
		if (!isOn) {
			isOn = true;
		}
	}
	
	/** Turn system off (if on), but stay in simulator
	 */
	public void turnOff() {
		if (isOn) {
			isOn = false;
		}
	}

	/** Exit the simulator 
	 */
	public void exit() {
		System.exit(0);
	}

	/** Resets the System to initial state 
	 */
	public void reset() {
		if (isOn) {
			singleton = new ChronoTimer();
		}
	}

	/** Set the current time 
	 * @param time time to set the current time to
	 */
	public void setTime(LocalTime time) {
		if (isOn) {
			this.time = time;
		}
	}

	/** Toggle the state of the specified channel
	 * @param channel channel number to toggle
	 */
	public void toggleChannel(int channel) {
		if (isOn) {
			channels[channel-1].toggle();
		}
	}

	/** Connect a type of sensor to a channel 
	 * @param sensor sensor type to connect
	 * @param channel channel number to connect to
	 */
	public void connectSensor(Sensor sensor, int channel) {
		if (isOn) {
			channels[channel-1].connect(sensor);
		}
	}

	/** Disconnect a sensor from channel
	 * @param channel channel number to disconnect from
	 */
	public void disconnectSensor(int channel) {
		if (isOn) {
			channels[channel-1].disconnect();
		}
	}

	/** Sets the event type of the current run
	 * @param type type of event (IND | PARIND | GRP | PARGRP)
	 */
	public void setEvent(EventType type) {
		if (isOn) {
			switch (type) {
			case IND:
				runs.set(runs.size() - 1, new IND());
				break;

			default:
				throw new IllegalArgumentException("Event type not yet implemented");
			}
		}
	}

	/** Ends the current run and creates a new run
	 */
	public void newRun() {
		if (isOn) {
			endRun();
			runs.add(new IND());
		}
	}

	/** Ends the current run
	 */
	public void endRun() {
		if (isOn) {
			getCurrentRun().end();
		}
	}

	/** Prints a run on stdout 
	 * @param runNumber run number to print out
	 */
	public void printRun(int runNumber) {
		if (isOn) {
			System.out.println(runs.get(runNumber - 1));
		}
	}

	/** Exports run in XML to file
	 * @param runNumber run number to export
	 */
	public void exportRun(int runNumber) {
		if (isOn) {
			// TODO Implement the 'Writer' class in the 'io' package
			Writer.write(runs.get(runNumber - 1).toString());
		}
	}

	/** Sets the next competitor to start
	 * @param bibNumber the bib number of the next competitor to start
	 */
	public void num(int bibNumber) {
		if (isOn) {
			getCurrentRun().num(bibNumber);
		}
	}

	/** Clears the next competitor
	 * @param bibNumber the bib number of the next competitor to clear
	 */
	public void clearNextCompetitor(int bibNumber) {
		if (isOn) {
			// TODO Unsure of what they want us to do. Ask TA on Wednesday
		}
	}

	/** Exchange next two competitors to finish in an IND event
	 */
	public void swap() {
		if (isOn) {
			// Project Description states : If there is more than one racer active, the finish event is associated with racers	
			// in a	FIFO (first	in,	first out) basis. 
			// This means we just swap runs(0) with runs(1) since these two will be next two to finish according to FIFO rules.
			
			// Need at least two runners to perform a swap.
			if(runs.size() < 2) throw new IllegalStateException("Not enough runners to perform a swap.");
			IND swapSecondToFirst = runs.get(1);
			// Add previous second racer to beginning of list to swap next two finishing racers
			runs.remove(1);
			runs.add(0,swapSecondToFirst);
		}
	}

	/** Set the next competitor to finish to DNF
	 */
	public void DNF() {
		if (isOn) {
			getCurrentRun().DNFRacer();
		}
	}

	/** Triggers a channels sensor
	 * @param channel channel number to trigger
	 */
	public void trigger(int channel) {
		if (isOn && channels[channel - 1].trigger()) {
			if (channel % 2 == 1) {
				getCurrentRun().startRacer(time);
			} else {
				getCurrentRun().finishRacer(time);
			}
		}
	}

	/** Shorthand for trigger channel 1
	 */
	public void start() {
		if (isOn) {
			trigger(1);
		}
	}

	/** Shorthand for trigger channel 2
	 */
	public void finish() {
		if (isOn) {
			trigger(2);
		}
	}
	
	/** Gets the current run
	 * @return the current run
	 */
	private IND getCurrentRun() {
		return runs.get(runs.size() - 1);
	}

}