package io;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

import race.EventType;
import sensor.SensorType;
import chronotimer.ChronoTimer;

public class Command {

	private LocalDateTime timeStamp;
	private String commandName;
	private String[] args;

	public Command(LocalDateTime timeStamp, String cmdName, String[] args) {
		this.timeStamp = timeStamp;
		this.commandName = cmdName;
		this.args = args;
	}

	
	/**
	 * @param chronoTimer
	 * @return true if the command successfully executed, false otherwise
	 */
	public boolean execute(ChronoTimer chronoTimer) {
		switch (commandName){
		case "ON":
			chronoTimer.turnOn();
			return true;

		case "OFF":
			chronoTimer.turnOff();
			return true;

		case "EXIT":
			chronoTimer.exit();
			return true;

		case "RESET":
			chronoTimer.reset();
			return true;

		case "TIME":
			return isValid(1, () -> {
				LocalDateTime time = LocalDateTime.parse(args[0], DateTimeFormatter.ofPattern("hh:mm:ss.s"));
				chronoTimer.setTime(time);
				return null;
			});

		case "TOGGLE":
			return isValid(1, () -> {
				int channel = Integer.parseInt(args[0]);
				chronoTimer.toggleChannel(channel);
				return null;
			});

		case "CONN":
			return isValid(2, () -> {
				SensorType type = SensorType.valueOf(args[0]);
				int channel = Integer.parseInt(args[1]);
				chronoTimer.connectSensor(channel, type);
				return null;
			});

		case "DISC":
			return isValid(1, () -> {
				int channel = Integer.parseInt(args[0]);
				chronoTimer.disconnectSensor(channel);
				return null;
			});
			
		case "EVENT":
			return isValid(1, () -> {
				EventType type = EventType.valueOf(args[0]);
				chronoTimer.setEvent(type);
				return null;
			});

		case "NEWRUN" :
			chronoTimer.newRun();
			return true;

		case "ENDRUN":
			chronoTimer.endRun();
			return true;

		case "PRINT":
			return isValid(1, () -> {
				int runNumber = Integer.parseInt(args[0]);
				chronoTimer.printRun(runNumber);
				return null;
			});

		case "EXPORT":
			return isValid(1, () -> {
				int runNumber = Integer.parseInt(args[0]);
				chronoTimer.exportRun(runNumber);
				return null;
			});

		case "NUM":
			return isValid(1, () -> {
				int bibNumber = Integer.parseInt(args[0]);
				chronoTimer.setNextCompetitor(bibNumber);
				return null;
			});

		case "CLR":
			return isValid(1, () -> {
				int bibNumber = Integer.parseInt(args[0]);
				chronoTimer.clearNextCompetitor(bibNumber);
				return null;
			});

		case "SWAP":
			chronoTimer.swap();
			return true;

		case "DNF":
			chronoTimer.DNF();
			return true;
			
		case "TRIG":
			return isValid(1, () -> {
				int channel = Integer.parseInt(args[0]);
				chronoTimer.trigger(channel);
				return null;
			});
			
		case "START":
			chronoTimer.start();
			return true;
			
		case "FINISH":
			chronoTimer.finish();
			return true;

		default:
			System.out.println("Unrecognized command");
			return false;
		}
	}
	
	private boolean isValid(int requiredNumArgs, Callable<Void> c) {
		if (args.length == requiredNumArgs) {
			try {
				c.call();
				return true;
			} catch (Exception e) {
				System.out.println("Illegal argument format");
			}
		} else {
			System.out.println("Illegal number of arguments");
		}
		return false;
	}

}
