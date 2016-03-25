package chronotimer;

import io.Writer;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import race.AbstractEvent;
import race.EventType;
import race.IND;
import race.PARIND;

/**
 * Used to time sports events.
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
		reset();
	}
	
	/**
	 * Resets the ChronoTimer to its initial state
	 */
	private void reset() {
		runs = new LinkedList<AbstractEvent>();
		runs.add(new IND());

		channels = new Channel[12];
		for (int i = 0; i < channels.length; i++)
			channels[i] = new Channel();

		time = LocalTime.now();
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
	 * Attempts to execute a specified command
	 * 
	 * @param timeStamp
	 *            command time stamp
	 * @param cmdName
	 *            command name
	 * @param args
	 *            command arguments
	 * @return true if the command was executed, false otherwise
	 */
	public boolean executeCommand(LocalTime timeStamp, String cmdName,
			String[] args) {
		try {
			// Set the chronotimer time to that of the command's timestamp
			if (timeStamp != null)
				time = timeStamp;
			else
				return false;

			// Check for commands independent of power state
			switch (cmdName) {
			case "ON": // Turn the system on
				isOn = true;
				return true;
				
			case "EXIT": // Exit the system
				System.exit(0);
				return true;

			case "TIME": // Set the current time
				time = Time.fromString(args[0]);
				return true;
			}

			// All other commands are dependent upon an on state
			if (isOn) {
				switch (cmdName) {
				case "OFF": // Turn the systen off
					isOn = false;
					return true;
				
				case "RESET": // Resets the System to initial state
					reset();
					return true;

				case "TOGGLE": // Toggle the state of the specified channel
					channels[Integer.parseInt(args[0]) - 1].toggle();
					return true;

				case "CONN": // Connect a type of sensor to a channel
					channels[Integer.parseInt(args[1]) - 1].connect(Sensor.valueOf(args[0]));
					return true;

				case "DISC": // Disconnect a sensor from channel
					channels[Integer.parseInt(args[0]) - 1].disconnect();
					return true;

				case "EVENT": // Sets the event type of the current run
					switch (EventType.valueOf(args[0])) {
					case IND:
						runs.set(runs.size() - 1, new IND());
						return true;
						
					case PARIND:
						runs.set(runs.size() - 1, new PARIND());
						return true;

					default:
						throw new IllegalArgumentException("Event type not yet implemented");
					}

				case "NEWRUN": // Ends the current run and creates a new run of the same type as the previous run
					try {
						if (getCurrentRun() == null)
							runs.removeLast();
						
						runs.add(getCurrentRun().getClass().newInstance());
						return true;
					} catch (InstantiationException | IllegalAccessException e) {
						System.out.println("Error creating new run");
					}

				case "ENDRUN": // Ends the current run
					runs.add(null);
					return true;

				case "PRINT": // Prints a run on stdout
					System.out.println(getRun(Integer.parseInt(args[0])));
					return true;

				case "EXPORT": // Exports run in XML to file
					// TODO Implement the 'Writer' class in the 'io' package
					Writer.write(getRun(Integer.parseInt(args[0])).toJSON());
					return true;

				case "NUM": // Sets the next competitor to start
					int bibNumber = Integer.parseInt(args[0]);
					getCurrentRun().num(bibNumber);
					return true;

				case "CLR": // Clears the next competitor
					getCurrentRun().clear(Integer.parseInt(args[0]));
					return true;

				case "SWAP": // Exchange next two competitors to finish in an IND event
					if (getCurrentRun() instanceof IND) {
						((IND) getCurrentRun()).swap();
						return true;
					} else {
						System.out.println("Cannot swap on a non-IND run");
					}
					break;

				case "DNF": // Set the next competitor to finish to DNF
					getCurrentRun().DNFRacer();
					return true;

				case "TRIG": // Triggers a channels sensor
					int channel = Integer.parseInt(args[0]);
					if (channels[channel - 1].trigger()) {
						if (channel % 2 == 1) {
							getCurrentRun().startRacer(time);
						} else {
							getCurrentRun().finishRacer(time);
						}
						return true;
					} else {
						System.out.println("Channel unarmed or no sensor connected");
					}
					break;

				case "START": // Start a racer on the current run
					executeCommand(time, "TRIG", new String[] {"1"});
					return true;

				case "FINISH": // Finish a racer on the current run
					executeCommand(time, "TRIG", new String[] {"2"});
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