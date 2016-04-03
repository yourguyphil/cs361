package race;

import java.time.LocalTime;
import java.util.LinkedList;

/**
 * Parallel events (e.g. skiing) with individual starts In the PARIND events,
 * racers queue up for parallel timed “runs” of the race. Each racer has a start
 * event and end event. By convention the start is on channel 1 and channel 3,
 * the finish is on channel 2 and channel 4, but there is nothing particularly
 * special about the events on each channel. When a start event is received, it
 * is associated with the next racer in the start queue. When a finish event is
 * received, it is associated with the next racer that is to finish. If there is
 * more than one racer active, the finish event is associated with racers in a
 * FIFO basis.
 */
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
