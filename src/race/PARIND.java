package race;

import java.time.LocalTime;
import java.util.LinkedList;

public class PARIND extends AbstractEvent{

	/**
	 * Creates a PARIND event
	 */
	public PARIND() {
		super(2);
	}
	
	@Override
	public void start(LocalTime start, int lane) {
		if (start == null) {
			System.out.println("Start cannot be null");
		} else if (lane < 0 || lane >= lanes.length) {
			System.out.println("PARIND only has lanes 1-" + lanes.length);
		} else {
			LinkedList<Racer> pendingRacers = getLane(lane).getPendingRacers();
			LinkedList<Racer> startedRacers = getLane(lane).getStartedRacers();
			
			if (!pendingRacers.isEmpty()) {
				pendingRacers.peek().setStart(start);
				startedRacers.add(pendingRacers.poll());
			} else {
				System.out.println("No pending racers to start");
			}
		}
	}

}
