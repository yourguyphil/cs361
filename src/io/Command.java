package io;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import race.EventType;
import chronotimer.ChronoTimer;
import chronotimer.Sensor;

/** A command that can be executed
 */
public class Command {
	
	/** Attempts to execute a specified command
	 * @param timeStamp command time stamp
	 * @param cmdName command name
	 * @param args command arguments
	 * @return true if the command successfully executed, false otherwise
	 */
	public static boolean execute(LocalTime timeStamp, String cmdName, String[] args) {
		try {
			ChronoTimer chronoTimer = ChronoTimer.getInstance();
			
			// Set the chronotimer time to that of the command's timestamp
			chronoTimer.setTime(timeStamp);
			
			switch (cmdName){
			case "ON":
				chronoTimer.turnOn();
				break;

			case "OFF":
				chronoTimer.turnOff();
				break;

			case "EXIT":
				chronoTimer.exit();
				break;

			case "RESET":
				chronoTimer.reset();
				break;

			case "TIME":
				chronoTimer.setTime(LocalTime.parse(args[0]));
				break;

			case "TOGGLE":
				chronoTimer.toggleChannel(Integer.parseInt(args[0]));
				break;

			case "CONN":
				chronoTimer.connectSensor(Sensor.valueOf(args[0]), Integer.parseInt(args[1]));
				break;

			case "DISC":
				chronoTimer.disconnectSensor(Integer.parseInt(args[0]));
				break;
				
			case "EVENT":
				chronoTimer.setEvent(EventType.valueOf(args[0]));
				break;

			case "NEWRUN" :
				chronoTimer.newRun();
				break;

			case "ENDRUN":
				chronoTimer.endRun();
				break;

			case "PRINT":
				chronoTimer.printRun(Integer.parseInt(args[0]));
				break;

			case "EXPORT":
				chronoTimer.exportRun(Integer.parseInt(args[0]));
				break;

			case "NUM":
				chronoTimer.num(Integer.parseInt(args[0]));
				break;

			case "CLR":
				chronoTimer.clearNextCompetitor(Integer.parseInt(args[0]));
				break;

			case "SWAP":
				chronoTimer.swap();
				break;

			case "DNF":
				chronoTimer.DNF();
				break;
				
			case "TRIG":
				chronoTimer.trigger(Integer.parseInt(args[0]));
				break;
				
			case "START":
				chronoTimer.start();
				break;
				
			case "FINISH":
				chronoTimer.finish();
				break;

			default:
				System.out.println("Illegal command");
				return false;
			}
		} catch (IndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
			System.out.println("Illegal argument format");
			return false;
		}
		return true;
	}

}
