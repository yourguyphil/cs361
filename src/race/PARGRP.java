package race;

import java.time.LocalTime;
import java.util.LinkedList;

public class PARGRP extends AbstractEvent {

	public PARGRP() {
		super(4);
	}

	@Override
	public void start(LocalTime start, int lane) {
		if (start == null) {
			System.out.println("Start cannot be null");
		} else if (lane < 0 || lane >= lanes.length) {
			System.out.println("GRP only has lanes 1-" + lanes.length);
		} else {
			LinkedList<Racer> pendingRacers = getLane(lane).getPendingRacers();
			LinkedList<Racer> startedRacers = getLane(lane).getStartedRacers();
			
			if (!pendingRacers.isEmpty()) {
				for (Racer racer : pendingRacers)
					racer.setStart(start);
				
				startedRacers.addAll(pendingRacers);
				pendingRacers.clear();
			} else {
				System.out.println("No pending racers to start");
			}
		}
	}

}
