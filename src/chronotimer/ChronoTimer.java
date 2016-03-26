package chronotimer;

import io.Writer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.LinkedList;

import com.sun.istack.internal.NotNull;

import race.AbstractEvent;
import race.EventType;
import race.IND;
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
		RESET();
	}

	/**
	 * Gets the current run
	 * @return the current run
	 */
	private AbstractEvent getCurrentRun() {
		return getRun(runs.size() - 1);
	}
	
	/**
	 * Gets the at run specified run number
	 * @param runNumber to get (0-indexed)
	 * @return the run with the specified run number
	 */
	private AbstractEvent getRun(int runNumber) {
		return runs.get(runNumber);
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
	 * Turn the system on
	 */
	public void ON(){
		isOn = true;
	}

	/**
	 * Turn the system off
	 */
	public void OFF(){
		isOn = false;
	}

	/**
	 * Exit the system
	 */
	public void EXIT(){
		System.exit(0);
	}

	/**
	 * Resets the chronotimer to its initial state
	 */
	public void RESET(){
		runs = new LinkedList<AbstractEvent>();
		runs.add(new IND());

		channels = new Channel[12];
		for (int i = 0; i < channels.length; i++)
			channels[i] = new Channel();

		time = LocalTime.now();
	}

	/**
	 * Set the chronotimer time
	 * @param time the time to set to
	 */
	public void TIME(LocalTime time){
		if (time == null) throw new IllegalArgumentException();
		this.time = time;
	}

	/**
	 * Toggle the state of the specified channel
	 * @param channel to toggle (0-indexed)
	 */
	public void TOGGLE(int channel){
		channels[channel].toggle();
	}

	/**
	 * Connect a type of sensor to a channel
	 * @param sensor to connect
	 * @param channel to connect to (0-indexed)
	 */
	public void CONN(Sensor sensor, int channel){
		channels[channel].connect(sensor);
	}

	/**
	 * Disconnect a sensor from channel
	 * @param channel to disconnect from (0-indexed)
	 */
	public void DISC(int channel){ 
		channels[channel].disconnect();
	}

	/**
	 * Sets the event type of the current run
	 * @param eventType to change to
	 */
	public void EVENT(EventType eventType){
		switch (eventType) {
		case IND:
			runs.set(runs.size() - 1, new IND());
			break;
			
		case PARIND:
			runs.set(runs.size() - 1, new PARIND());
			break;

		default:
			System.out.println("Event type not yet implemented");
		}
	}

	/**
	 * Ends the current run and creates a new run of the same type as the previous run
	 */
	public void NEWRUN(){
		try {
			if (getCurrentRun() == null)
				runs.removeLast();
			
			runs.add(getCurrentRun().getClass().newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("Error creating new run");
		}
	}

	/**
	 * Ends the current run
	 */
	public void ENDRUN(){
		if (getCurrentRun() != null)
			runs.add(null);
	}

	/**
	 * Prints a run on stdout
	 * @param runNumber to print
	 */
	public void PRINT(int runNumber){
		System.out.println(getRun(runNumber));
	}

	/**
	 * Exports run in JSON to file
	 * @param runNumber to export
	 */
	public void EXPORT(int runNumber){
		// TODO Implement the 'Writer' class in the 'io' package
		Writer.write(getRun(runNumber).toJSON());
	}

	/**
	 * Sets the next pending competitor to start
	 * @param bibNumber to mark ready to start
	 */
	public void NUM(int bibNumber){
		getCurrentRun().num(bibNumber);
	}

	/**
	 * Clears the next competitor
	 * @param bibNumber to clear
	 */
	public void CLR(int bibNumber){
		getCurrentRun().clear(bibNumber);
	}

	/**
	 * Exchange next two competitors to finish in an IND event
	 */
	public void SWAP(){
		if (getCurrentRun() instanceof IND) {
			((IND) getCurrentRun()).swap();
		} else {
			System.out.println("Cannot swap on a non-IND run");
		}
	}

	/**
	 * Set the next competitor to finish to DNF
	 */
	public void DNF(){
		getCurrentRun().DNFRacer();
	}

	/**
	 * Triggers a channels sensor
	 * @param channel to trigger (0-indexed)
	 */
	public void TRIG(int channel){
		if (channels[channel].trigger()) {
			if (channel % 2 == 0) {
				getCurrentRun().startRacer(time);
			} else {
				getCurrentRun().finishRacer(time);
			}
		} else {
			System.out.println("Channel unarmed or no sensor connected");
		}
	}

	/**
	 * Start a racer on the current run
	 */
	public void START(){
		TRIG(0);
	}

	/**
	 * Finish a racer on the current run
	 */
	public void FINISH(){
		TRIG(1);
	}

}