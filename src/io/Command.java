package io;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import race.EventType;
import chronotimer.ChronoTimer;
import chronotimer.Sensor;
import chronotimer.Time;

/**
 * Command that can be executed on a chronotimer
 *
 */
public class Command {

	/**
	 * Attempts to execute a specified command
	 * 
	 * @param chronotimer to execute upon
	 * @param timeStamp when command is executed
	 * @param cmdName name of command
	 * @param args command arguments
	 * @return true if the command and arguments were parsed, false otherwise
	 */
	public static boolean executeCommand(ChronoTimer chronotimer, LocalTime timeStamp, String cmdName, String[] args) {
		try {
			// Set the chronotimer time to that of the command's timestamp
			chronotimer.TIME(timeStamp);

			// Check for commands independent of power state
			switch (cmdName) {
			case "ON":
				chronotimer.ON();
				return true;
				
			case "EXIT":
				chronotimer.EXIT();
				return true;

			case "TIME":
				chronotimer.TIME(Time.fromString(args[0]));
				return true;
			}

			// All other commands are dependent upon an ON state
			if (!chronotimer.isOn()) {
				System.out.println("ChronoTimer is OFF. Valid commands: [ON, TIME, EXIT]");
				
			} else {
				switch (cmdName) {
				case "OFF":
					chronotimer.OFF();
					return true;

				case "RESET":
					chronotimer.RESET();
					return true;
					
				case "TOGGLE":
					chronotimer.TOGGLE(Integer.parseInt(args[0]) - 1);
					return true;

				case "CONN":
					chronotimer.CONN(Sensor.valueOf(args[0]), Integer.parseInt(args[1]) - 1);
					return true;

				case "DISC":
					chronotimer.DISC(Integer.parseInt(args[0]) - 1);
					return true;

				case "EVENT":
					chronotimer.EVENT(EventType.valueOf(args[0]));
					return true;

				case "NEWRUN":
					chronotimer.NEWRUN();
					return true;

				case "ENDRUN":
					chronotimer.ENDRUN();
					return true;

				case "PRINT":
					chronotimer.PRINT(Integer.parseInt(args[0]));
					return true;

				case "EXPORT":
					chronotimer.EXPORT(Integer.parseInt(args[0]));
					return true;

				case "NUM":
					chronotimer.NUM(Integer.parseInt(args[0]));
					return true;

				case "CLR":
					chronotimer.CLR(Integer.parseInt(args[0]));
					return true;

				case "SWAP":
					chronotimer.SWAP();
					return true;

				case "DNF":
					chronotimer.DNF();
					return true;

				case "TRIG":
					chronotimer.TRIG(Integer.parseInt(args[0]) - 1);
					return true;

				case "START":
					chronotimer.START();
					return true;

				case "FINISH":
					chronotimer.FINISH();
					return true;

				default:
					System.out.println("Illegal command");
					break;
				}
			}

		} catch (IndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
			System.out.print("Illegal argument(s): ");
			System.out.println(cmdName + " " + Arrays.toString(args));
		}
		return false;
	}
	
}
