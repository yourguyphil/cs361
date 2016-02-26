package chronotimer;

import java.time.Clock;
import java.time.LocalDateTime;

import sensor.SensorType;

public class ChronoTimer {
	
	boolean isOn;
	
	public ChronoTimer() {
		
	}

	// ON(if off) Turn system on, enter quiescent state 
	public void turnOn() {
		System.out.println("ON");
		if (!isOn) isOn = true;
	}
	// OFF(if on) Turn system off (but stay in simulator) 
	public void turnOff() {
		System.out.println("OFF");
		if (isOn) isOn = false;
	}

	// EXIT Exit the simulator 
	public void exit() {
		System.out.println("EXIT");
		System.exit(0);
	}

	// RESET Resets the System to initial state 
	public void reset() {
		System.out.println("RESET");
		if (isOn) {
			// TODO
		}
	}

	// TIME <hour>:<min>:<sec> Set the current time 
	public void setTime(LocalDateTime time) {
		System.out.println("TIME");
		if (isOn) {
			
		}
	}

	// TOG <channel> Toggle the state of the channel <CHANNEL> 
	public void toggleChannel(int channel) {
		System.out.println("TOG");
		if (isOn) {
			
		}
	}

	// CONN <sensor> <NUM> Connect a type of sensor to channel <NUM> <sensor> = {EYE, GATE, PAD} 
	public void connectSensor(int channel, SensorType type) {
		System.out.println("CONN");
		if (isOn) {
			
		}
	}

	// DISC <NUM> Disconnect a sensor from channel <NUM> 
	public void disconnectSensor(int channel) {
		System.out.println("DISC");
		if (isOn) {
			
		}
	}

	// EVENT <TYPE> IND | PARIND | GRP | PARGRP 
	public void setEvent(String eventType) {
		System.out.println("EVENT");
		if (isOn) {
			
		}
	}

	// NEWRUN Create a new Run (must end a run first) 
	public void newRun() {
		System.out.println("NEWRUN");
		if (isOn) {
			
		}
	}

	// ENDRUN Done with a Run 
	public void endRun() {
		System.out.println("ENDRUN");
		if (isOn) {
			
		}
	}

	// PRINT <RUN> Print the run on stdout 
	public void printRun(int runNumber) {
		System.out.println("PRINT");
		if (isOn) {
			
		}
	}

	// EXPORT <RUN> Export run in XML to file “RUN<RUN>” 
	public void exportRun(int runNumber) {
		System.out.println("EXPORT");
		if (isOn) {
			
		}
	}

	// NUM <NUMBER> Set <NUMBER> as the next competitor to start. 
	public void num(int bibNumber) {
		System.out.println("NUM");
		if (isOn) {
			
		}
	}

	// CLR <NUMBER> Clear <NUMBER> as the next competitor 
	public void clear(int bibNumber) {
		System.out.println("");
		if (isOn) {
			
		}
	}

	// SWAP Exchange next two competitors to finish in IND 
	public void swap() {
		System.out.println("");
		if (isOn) {
			
		}
	}

	// DNF The next competitor to finish will not finish 
	public void DNF() {
		System.out.println("");
		if (isOn) {
			
		}
	}

	// TRIG <NUM> Trigger channel <NUM>
	public void trigger(int channel) {
		System.out.println("");
		if (isOn) {
			
		}
	}

	// START Start trigger channel 1 (shorthand for TRIG 1) 
	public void start() {
		System.out.println("");
		if (isOn) {
			
		}
	}

	// FINISH Finish trigger channel 2 (shorthand for TRIG 2)
	public void finish() {
		System.out.println("");
		if (isOn) {
			
		}
	}
	

	

}