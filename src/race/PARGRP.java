package race;

import java.time.LocalTime;
import java.util.LinkedList;

public class PARGRP extends AbstractEvent {

	public PARGRP() {
		super(8);
	}

	@Override
	public void start(LocalTime start, int lane) {

		if (start == null) {
			System.out.println("Start cannot be null");
		} else {
			for (Lane l : lanes) {
				LinkedList<Racer> pendingRacers = l.getPendingRacers();
				LinkedList<Racer> startedRacers = l.getStartedRacers();
				
				for (Racer racer : pendingRacers)
					racer.setStart(start);
				
				startedRacers.addAll(pendingRacers);
				pendingRacers.clear();
			}
		}
	}
	
	@Override
	public void num(int bibNumber) {
		int numPending = 0;
		for (Lane lane : lanes)
			numPending += lane.getPendingRacers().size();
		
		if (numPending < lanes.length) {
			super.num(bibNumber);
		} else {
			System.out.println("PARGRP event only takes " + lanes.length + " racers at once");
		}
	}
	
	@Override
	public void notifyChannelTriggered(LocalTime time, int channel) {
		int numChannels = lanes.length * 2;
		if (time == null) {
			System.out.println("Time cannot be null");
		} else if (channel < 0 || channel >= numChannels) {
			System.out.println("PARGRP event only accepts input on channels 1-" + numChannels);
		} else {
			int numStarted = 0;
			for (Lane lane : lanes)
				numStarted += lane.getStartedRacers().size();
			
			if (channel == 0 && numStarted == 0) {
				start(time, -1);
			} else {
				finish(time, channel);
			}
		}
	}

}
