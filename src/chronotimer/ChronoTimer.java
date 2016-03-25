package chronotimer;

import io.Writer;

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
/**
 * @author Noah
 *
 */
public class ChronoTimer {

	private boolean isOn;
	LinkedList<AbstractEvent> runs;
	Channel[] channels;
	LocalTime time;

	/**
	 * Private default constructor that prevents any other class from
	 * instantiating.
	 */
	public ChronoTimer() {
		RESET();
	}

	/**
	 * Gets the current run
	 * @return the current run
	 */
	private AbstractEvent getCurrentRun() {
		return getRun(runs.size());
	}
	
	/**
	 * Gets the at run specified run number (1-indexed)
	 * @return the run with the specified run number
	 */
	private AbstractEvent getRun(int runNumber) {
			return runs.get(runNumber - 1);
	}

	
	/**
	 * Turn the system on
	 */
	private void ON(){
		isOn = true;
	}

	/**
	 * Turn the system off
	 */
	private void OFF(){
		isOn = false;
	}

	/**
	 * Exit the system
	 */
	private void EXIT(){
		System.exit(0);
	}

	/**
	 * Resets the chronotimer to its initial state
	 */
	private void RESET(){
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
	private void TIME(LocalTime time){
		if (time == null) throw new IllegalArgumentException();
		this.time = time;
	}

	/**
	 * Toggle the state of the specified channel
	 * @param channel to toggle
	 */
	private void TOGGLE(int channel){
		channels[channel].toggle();
	}

	/**
	 * Connect a type of sensor to a channel
	 * @param sensor to connect
	 * @param channel to connect to
	 */
	private void CONN(Sensor sensor, int channel){
		channels[channel].connect(sensor);
	}

	/**
	 * Disconnect a sensor from channel
	 * @param channel to disconnect from
	 */
	private void DISC(int channel){ 
		channels[channel].disconnect();
	}

	/**
	 * Sets the event type of the current run
	 * @param eventType to change to
	 */
	private void EVENT(EventType eventType){
		switch (eventType) {
		case IND:
			runs.set(runs.size() - 1, new IND());
			break;
			
		case PARIND:
			runs.set(runs.size() - 1, new PARIND());
			break;

		default:
			throw new IllegalArgumentException("Event type not yet implemented");
		}
	}

	/**
	 * Ends the current run and creates a new run of the same type as the previous run
	 */
	private void NEWRUN(){
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
	private void ENDRUN(){
		runs.add(null);
	}

	/**
	 * Prints a run on stdout
	 * @param runNumber to print
	 */
	private void PRINT(int runNumber){
		System.out.println(getRun(runNumber));
	}

	/**
	 * Exports run in JSON to file
	 * @param runNumber to export
	 */
	private void EXPORT(int runNumber){
		// TODO Implement the 'Writer' class in the 'io' package
		Writer.write(getRun(runNumber).toJSON());
	}

	/**
	 * Sets the next pending competitor to start
	 * @param bibNumber to mark ready to start
	 */
	private void NUM(int bibNumber){
		getCurrentRun().num(bibNumber);
	}

	/**
	 * Clears the next competitor
	 * @param bibNumber to clear
	 */
	private void CLR(int bibNumber){
		getCurrentRun().clear(bibNumber);
	}

	/**
	 * Exchange next two competitors to finish in an IND event
	 */
	private void SWAP(){
		if (getCurrentRun() instanceof IND) {
			((IND) getCurrentRun()).swap();
		} else {
			System.out.println("Cannot swap on a non-IND run");
		}
	}

	/**
	 * Set the next competitor to finish to DNF
	 */
	private void DNF(){
		getCurrentRun().DNFRacer();
	}

	/**
	 * Triggers a channels sensor
	 * @param channel to trigger
	 */
	private void TRIG(int channel){
		if (channels[channel - 1].trigger()) {
			if (channel % 2 == 1) {
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
	private void START(){
		TRIG(1);
	}

	/**
	 * Finish a racer on the current run
	 */
	private void FINISH(){
		TRIG(2);
	}
	
	/**
	 * Attempts to execute a specified command
	 * 
	 * @param timeStamp
	 *            command time stamp
	 * @param cmdName
	 *            command name
	 * @param args
	 *            command arguments
	 * @return true if the command and arguments were parsed, false otherwise
	 */
	public boolean executeCommand(LocalTime timeStamp, String cmdName, String[] args) {
		try {
			// Set the chronotimer time to that of the command's timestamp
			if (timeStamp != null) {
				TIME(timeStamp);
			} else
				return false;

			// Check for commands independent of power state
			switch (cmdName) {
			case "ON":
				ON();
				return true;
				
			case "EXIT":
				EXIT();
				return true;

			case "TIME": // Set the current time
				TIME(Time.fromString(args[0]));
				return true;
			}

			// All other commands are dependent upon an on state
			if (isOn) {
				switch (cmdName) {
				case "OFF":
					OFF();
					return true;
				
				case "RESET":
					RESET();
					return true;

				case "TOGGLE":
					TOGGLE(Integer.parseInt(args[0]) - 1);
					return true;

				case "CONN":
					CONN(Sensor.valueOf(args[0]), Integer.parseInt(args[1]) - 1);
					return true;

				case "DISC":
					DISC(Integer.parseInt(args[0]) - 1);
					return true;

				case "EVENT":
					EVENT(EventType.valueOf(args[0]));
					return true;

				case "NEWRUN":
					NEWRUN();
					return true;

				case "ENDRUN":
					ENDRUN();
					return true;

				case "PRINT":
					PRINT(Integer.parseInt(args[0]));
					return true;

				case "EXPORT":
					EXPORT(Integer.parseInt(args[0]));
					return true;

				case "NUM":
					NUM(Integer.parseInt(args[0]));
					return true;

				case "CLR":
					CLR(Integer.parseInt(args[0]));
					return true;

				case "SWAP":
					SWAP();
					return true;

				case "DNF":
					DNF();
					return true;

				case "TRIG":
					TRIG(Integer.parseInt(args[0]));
					return true;

				case "START":
					START();
					return true;

				case "FINISH":
					FINISH();
					return true;

				default:
					System.out.println("Illegal command");
					break;
				}
			} else {
				System.out.println("ChronoTimer is OFF. Valid commands: [ON, TIME, EXIT]");
			}

		} catch (IndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
			System.out.print("Illegal argument(s): ");
			System.out.println(cmdName + " " + Arrays.toString(args));
		} catch (NullPointerException e) {
			System.out.println("This event is ended");
		}
		return false;
	}

}