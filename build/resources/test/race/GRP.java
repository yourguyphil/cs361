package race;

import java.time.LocalTime;
import java.util.LinkedList;

/**
 * Group events (cross country skiing or running) with individual finishes In
 * the GRP events, racers all start at the same time (such as a gun or horn) so
 * all have the same start time. By convention the start is on channel 1 and
 * there are multiple finishes on channel 2, but there is nothing particularly
 * special about the events on each channel. When a start event is received, it
 * is associated with the start of the event. When a finish event is received,
 * it is associated with the next “place” – for example, the first finish is
 * entered in the list of finishers with the first time, the next time with 2nd
 * place, etc. There may be up to 9,999 places. Bib numbers may be associated
 * with places by entering the bib numbers in the order of finishing (such as a
 * cross country race).
 */
public class GRP extends AbstractEvent{
	
	private int numRacersReassigned;
	private LocalTime start;

	public GRP() {
		super(1);
	}
	
	@Override
	public void num(int bibNumber) {
		if (numRacersReassigned < getLane(0).getFinishedRacers().size()) {
			getLane(0).getFinishedRacers().get(numRacersReassigned).setBib(bibNumber);
			numRacersReassigned++;
		} else {
			System.out.println("Cannot manually add racers in GRP, only reassign them after finishing");
		}
	}

	@Override
	public void start(LocalTime start, int lane) {
		if (start == null) {
			System.out.println("Start cannot be null");
		} else if (lane < 0 || lane >= lanes.length) {
			System.out.println("GRP only has lanes 1-" + lanes.length);
		} else {
			this.start = start;
		}
	}
	
	@Override
	public void finish(LocalTime finish, int lane) {
		if (finish == null) {
			System.out.println("Finish cannot be null");
		} else if (lane < 0 || lane >= lanes.length) {
			String event = getClass().getSimpleName();
			System.out.println(event + " only has lanes 1-" + lanes.length);
		} else {
			numRacersAdded++;
			Racer racer = new Racer(numRacersAdded);
			racer.setStart(start);
			racer.setFinish(finish);
			
			if (nextFinishDNF) {
				racer.DNF();
				nextFinishDNF = false;
			}
			
			getLane(lane).getFinishedRacers().add(racer);
		}
	}

}
