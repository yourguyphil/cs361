package io;

import java.time.LocalTime;

import race.EventType;
import sensor.SensorType;
import chronotimer.ChronoTimer;

public class Command {

	private LocalTime timeStamp;
	private String commandName;
	private String[] args;

	public Command(LocalTime timeStamp, String cmdName, String[] args) {
		this.timeStamp = timeStamp;
		this.commandName = cmdName;
		this.args = args;
	}
	
	/**
	 * @param chronoTimer
	 * @return true if the command successfully executed, false otherwise
	 */
	public boolean execute( ) {
		try {
			ChronoTimer chronoTimer = ChronoTimer.getInstance();
			
			switch (commandName){
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
				chronoTimer.connectSensor(SensorType.valueOf(args[0]), Integer.parseInt(args[1]));
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
				chronoTimer.setNextCompetitor(Integer.parseInt(args[0]));
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
		} catch (Exception e) {
			System.out.println("Illegal argument format");
			return false;
		}
		return true;
	}

}
