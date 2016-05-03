package chronotimer;

import io.Writer;

import java.time.LocalTime;
import java.util.LinkedList;

import race.AbstractEvent;
import race.EventType;
import race.GRP;
import race.IND;
import race.PARGRP;
import race.PARIND;

/**
 * Used to time sports events.
 */
@SuppressWarnings("unused")
public class ChronoTimer {

	private boolean isOn;
	LinkedList<AbstractEvent> runs;
	Channel[] channels;
	LocalTime time;

	/**
	 * Default constructor
	 */
	public ChronoTimer() {
		ON();
		RESET();
		OFF();
	}

	/**
	 * Gets the current run
	 * @return the current run
	 */
	public AbstractEvent getCurrentRun() {
		return getRun(runs.size() - 1);
	}

	/**
	 * Gets the at run specified run number
	 * @param runNumber to get (0-indexed)
	 * @return the run with the specified run number
	 */
	public AbstractEvent getRun(int runNumber) {
		if (runNumber < 0 || runNumber >= runs.size()) {
			System.out.println("Chronotimer only has runs 1-" + runs.size());
			return null;
		} else {
			return runs.get(runNumber);
		}
	}


	/**
	 * Gets all the runs (events) that have been run 
	 * @return runs (events) that have been run 
	 */
	public LinkedList<AbstractEvent> getRuns() {
		return runs;
	}


	/**
	 * Gets all the channels of the chronotimer
	 * @return the channels of the chronotimer
	 */
	public Channel[] getChannels() {
		return channels;
	}

	/**
	 * Gets the chronotimer time
	 * @return the chronotimer time
	 */
	public LocalTime getTime() {
		return time;
	}

	/**
	 * Gets if the chronotimer is on
	 * @return true if on, false otherwise
	 */
	public boolean isOn() {
		if (!isOn)
			System.out.println("ChronoTimer is OFF. Valid commands: [ON, TIME, EXIT]");

		return isOn;
	}


	/**
	 * Turn the system on
	 */
	public void ON() {
		isOn = true;
	}

	/**
	 * Turn the system off
	 */
	public void OFF() {
		if (isOn()) {
			isOn = false;
		}
	}

	/**
	 * Exit the system
	 */
	public void EXIT() {
		System.exit(0);
	}

	/**
	 * Resets the chronotimer to its initial state
	 */
	public void RESET() {
		if (isOn()) {
			runs = new LinkedList<AbstractEvent>();
			runs.add(new IND());

			channels = new Channel[12];
			for (int i = 0; i < channels.length; i++)
				channels[i] = new Channel();

			time = LocalTime.now();
		}

	}

	/**
	 * Set the chronotimer time
	 * @param time the time to set to
	 */
	public void TIME(LocalTime time) {
		if (time == null) {
			System.out.println("Time cannot be null");
		} else {
			this.time = time;
		}
	}

	/**
	 * Toggle the state of the specified channel
	 * @param channel to toggle (0-indexed)
	 */
	public void TOGGLE(int channel) {
		if (channel < 0 || channel >= channels.length) {
			System.out.println("Chronotimer only has channels 1-" + channels.length);
		} else if (isOn()){
			channels[channel].toggle();
		}
	}

	/**
	 * Connect a type of sensor to a channel
	 * @param sensor to connect
	 * @param channel to connect to (0-indexed)
	 */
	public void CONN(Sensor sensor, int channel) {
		if (channel < 0 || channel >= channels.length) {
			System.out.println("Chronotimer only has channels 1-" + channels.length);
		} else if (sensor == null) {
			System.out.println("Sensor cannot be null");
		} else if (isOn()){
			channels[channel].connect(sensor);
		}
	}

	/**
	 * Disconnect a sensor from channel
	 * @param channel to disconnect from (0-indexed)
	 */
	public void DISC(int channel) { 
		if (channel < 0 || channel >= channels.length) {
			System.out.println("Chronotimer only has channels 1-" + channels.length);
		} else if (isOn()) {
			channels[channel].disconnect();
		}
	}

	/**
	 * Sets the event type of the current run
	 * @param eventType to change to
	 */
	public void EVENT(EventType eventType) {
		if (eventType == null) {
			System.out.println("EventType cannot be null");
		} else if (isOn()) {
			switch (eventType) {
			case IND:
				runs.set(runs.size() - 1, new IND());
				break;

			case PARIND:
				runs.set(runs.size() - 1, new PARIND());
				break;
				
			case GRP:
				runs.set(runs.size() - 1, new GRP());
				break;
				
			case PARGRP:
				runs.set(runs.size() - 1, new PARGRP());
				break;

			default:
				System.out.println("Event type not yet implemented");
			}
		}
	}

	/**
	 * Ends the current run and creates a new run of the same type as the previous run
	 */
	public void NEWRUN() {
		if (isOn()) {
			try {
				if (getCurrentRun() == null)
					runs.removeLast();

				runs.add(getCurrentRun().getClass().newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				System.out.println("Error creating new run");
			}
		}
	}

	/**
	 * Ends the current run
	 */
	public void ENDRUN() {
		if (isOn()) {
			if (getCurrentRun() != null)
				runs.add(null);
		}
	}

	/**
	 * Prints a run on stdout
	 * @param runNumber to print
	 */
	public String PRINT(int runNumber) {
		if (runNumber < 0 || runNumber >= runs.size()) {
			System.out.println("Chronotimer only has runs 1-" + runs.size());
		} else if (isOn()) {
			System.out.println(getRun(runNumber));
			return getRun(runNumber).toString();
		}
		return null;
	}
	/**
	 * Exports run in JSON to file
	 * @param runNumber to export
	 */
	public void EXPORT(int runNumber) {
		if (runNumber < 0 || runNumber >= runs.size()) {
			System.out.println("Chronotimer only has runs 1-" + runs.size());
		} else if (isOn()) {
			Writer.write(getRun(runNumber).toJSON());
		}
	}

	/**
	 * Sets the next pending competitor to start
	 * @param bibNumber to mark ready to start
	 */
	public void NUM(int bibNumber) {
		if (isOn()) {
			getCurrentRun().num(bibNumber);
		}
	}

	/**
	 * Clears the next competitor
	 * @param bibNumber to clear
	 */
	public void CLR(int bibNumber) {
		if (isOn()) {
			getCurrentRun().clear(bibNumber);
		}
	}

	/**
	 * Exchange next two competitors to finish in an IND event
	 */
	public void SWAP() {
		if (isOn()) {
			getCurrentRun().swap();
		}
	}

	/**
	 * Set the next competitor to finish to DNF
	 */
	public void DNF() {
		if (isOn()) {
			getCurrentRun().DNF();
		}
	}

	/**
	 * Triggers a channels sensor
	 * @param channel to trigger (0-indexed)
	 */
	public void TRIG(int channel) {
		if (channel < 0 || channel >= channels.length) {
			System.out.println("Chronotimer only has channels 1-" + channels.length);
		} else if (isOn() && channels[channel].trigger()) {
			getCurrentRun().notifyChannelTriggered(time, channel);
		}
	}

	/**
	 * Start a racer on the current run
	 */
	public void START() {
		TRIG(0);
	}

	/**
	 * Finish a racer on the current run
	 */
	public void FINISH() {
		TRIG(1);
	}

}