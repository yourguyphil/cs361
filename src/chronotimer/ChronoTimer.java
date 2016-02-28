package chronotimer;

import java.time.LocalDateTime;

import race.EventType;
import sensor.SensorType;

public class ChronoTimer {
	
	
	private boolean isOn;
	private static ChronoTimer singleton = new ChronoTimer();
	
	// A private Constructor prevents any other class from instantiating.
	private ChronoTimer() { }
	
	// Static 'instance' method
	public static ChronoTimer getInstance() {
		return singleton;
	}

	// ON(if off) Turn system on, enter quiescent state 
	public void turnOn() {
		if (!isOn) {
			System.out.println("ON");
			isOn = true;
		}
	}
	// OFF(if on) Turn system off (but stay in simulator) 
	public void turnOff() {
		if (isOn) {
			System.out.println("OFF");
			isOn = false;
		}
	}

	// EXIT Exit the simulator 
	public void exit() {
		System.out.println("EXIT");
		System.exit(0);
	}

	// RESET Resets the System to initial state 
	public void reset() {
		if (isOn) {
			System.out.println("RESET");
		}
	}

	// TIME <hour>:<min>:<sec> Set the current time 
	public void setTime(LocalDateTime time) {
		if (isOn) {
			System.out.println("TIME");
		}
	}

	// TOGGLE <channel> Toggle the state of the channel <CHANNEL> 
	public void toggleChannel(int channel) {
		if (isOn) {
			System.out.println("TOGGLE");
		}
	}

	// CONN <sensor> <NUM> Connect a type of sensor to channel <NUM> <sensor> = {EYE, GATE, PAD} 
	public void connectSensor(SensorType type, int channel) {
		if (isOn) {
			System.out.println("CONN");
		}
	}

	// DISC <NUM> Disconnect a sensor from channel <NUM> 
	public void disconnectSensor(int channel) {
		if (isOn) {
			System.out.println("DISC");
		}
	}

	// EVENT <TYPE> IND | PARIND | GRP | PARGRP 
	public void setEvent(EventType type) {
		if (isOn) {
			System.out.println("EVENT");
		}
	}

	// NEWRUN Create a new Run (must end a run first) 
	public void newRun() {
		if (isOn) {
			System.out.println("NEWRUN");
		}
	}

	// ENDRUN Done with a Run 
	public void endRun() {
		if (isOn) {
			System.out.println("ENDRUN");
		}
	}

	// PRINT <RUN> Print the run on stdout 
	public void printRun(int runNumber) {
		if (isOn) {
			System.out.println("PRINT");
		}
	}

	// EXPORT <RUN> Export run in XML to file “RUN<RUN>” 
	public void exportRun(int runNumber) {
		if (isOn) {
			System.out.println("EXPORT");
		}
	}

	// NUM <NUMBER> Set <NUMBER> as the next competitor to start. 
	public void setNextCompetitor(int bibNumber) {
		if (isOn) {
			System.out.println("NUM");
		}
	}

	// CLR <NUMBER> Clear <NUMBER> as the next competitor 
	public void clearNextCompetitor(int bibNumber) {
		if (isOn) {
			System.out.println("CLR");
		}
	}

	// SWAP Exchange next two competitors to finish in IND 
	public void swap() {
		if (isOn) {
			System.out.println("SWAP");
		}
	}

	// DNF The next competitor to finish will not finish 
	public void DNF() {
		if (isOn) {
			System.out.println("DNF");
		}
	}

	// TRIG <NUM> Trigger channel <NUM>
	public void trigger(int channel) {
		if (isOn) {
			System.out.println("TRIG");
		}
	}

	// START Start trigger channel 1 (shorthand for TRIG 1) 
	public void start() {
		if (isOn) {
			System.out.println("START");
		}
	}

	// FINISH Finish trigger channel 2 (shorthand for TRIG 2)
	public void finish() {
		if (isOn) {
			System.out.println("FINISH");
		}
	}

}