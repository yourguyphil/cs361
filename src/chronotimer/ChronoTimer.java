package chronotimer;

import io.Writer;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import race.EventType;
import race.IND;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * Used to time sports events.
 */
public class ChronoTimer {

	private boolean isOn;
	ArrayList<IND> runs;
	Channel[] channels;
	LocalTime time;
	Gson g = new Gson();

	/**
	 * Private default constructor that prevents any other class from
	 * instantiating.
	 */
	public ChronoTimer() {
		reset();
	}
	
	/**
	 * Resets the chronotimer to its initial state
	 */
	private void reset() {
		runs = new ArrayList<IND>();
		runs.add(new IND());

		channels = new Channel[12];
		for (int i = 0; i < channels.length; i++)
			channels[i] = new Channel();

		time = LocalTime.now();
	}

	/**
	 * Gets the current run
	 * 
	 * @return the current run
	 */
	private IND getCurrentRun() {
		return runs.get(runs.size() - 1);
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
	 * @return true if the command successfully executed, false otherwise
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

			case "OFF": // Turn the systen off
				isOn = false;
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

					default:
						throw new IllegalArgumentException("Event type not yet implemented");
					}

				case "NEWRUN": // Ends the current run and creates a new run
					getCurrentRun().end();
					runs.add(new IND());
					return true;

				case "ENDRUN": // Ends the current run
					getCurrentRun().end();
					return true;

				case "PRINT": // Prints a run on stdout
					System.out.println(runs.get(Integer.parseInt(args[0]) - 1));
					return true;

				case "EXPORT": // Exports run in XML to file
					// TODO Implement the 'Writer' class in the 'io' package
					String out = g.toJson(runs.get(Integer.parseInt(args[0]) - 1).toString());
					//Writer.write(runs.get(Integer.parseInt(args[0]) - 1).toString());
					Writer.write(out);
					// Ask TA about output format
					return true;

				case "NUM": // Sets the next competitor to start
					int bibNumber = Integer.parseInt(args[0]);
					getCurrentRun().num(bibNumber);
					return true;

				case "CLR": // Clears the next competitor
					// Clear competitor from pending queue
					getCurrentRun().clear(Integer.parseInt(args[0]));
					return true;

				case "SWAP": // Exchange next two competitors to finish in an
					// IND event
					if (getCurrentRun() instanceof IND) {
						getCurrentRun().swap();
						return true;
					} else {
						throw new IllegalStateException("Cannot swap on a non-IND run");
					}

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
					}
					return false;

				case "START": // Start a racer on the current run
					getCurrentRun().startRacer(time);
					return true;

				case "FINISH": // Finish a racer on the current run
					getCurrentRun().finishRacer(time);
					return true;

				default:
					System.out.println("Illegal command");
				}
			} else {
				System.out.println("ChronoTimer is OFF. Can only take ON, OFF, TIME, and EXIT commands");
			}

		} catch (IndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
			System.out.print("Illegal argument format: ");
			System.out.println(cmdName + " " + Arrays.toString(args));
		}
		return false;
	}

}