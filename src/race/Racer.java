package race;

import java.time.Duration;
import java.time.LocalTime;

public class Racer {
	
	private int bib;
	private LocalTime start;
	private LocalTime finish;

	public Racer(int bib) {
		if(bib > 99999 || bib < 0) throw new IllegalArgumentException("Valid racer bib numbers range from 0 to 99,999");
		this.bib = bib;
	}

	public void start(LocalTime startTime) {
		start = startTime;
	}
	
	public void finish(LocalTime finishTime) {
		if (start == null)
			throw new IllegalStateException("Must start before finish");
		else
			finish = finishTime;
	}
	
	public void cancel() {
		start = null;
		finish = null;
	}
	
	public void DNF() {
		if (start == null)
			throw new IllegalStateException("Must start before DNF");
		else
			finish = null;
	}
	
	public LocalTime getStart() {
		return start;
	}

	public LocalTime getFinish() {
		return finish;
	}
	
	public int getBib() {
		return bib;
	}

	public Duration getDuration() {
		if (start == null || finish == null)
			throw new IllegalStateException("Must have a start and finish to get duration");
		else
			return Duration.between(start, finish);
	}

	public String toString() {
		if (start == null && finish == null) {
			return bib + " CANCEL"; 
		} else if (start != null && finish == null) {
			return bib + " DNF"; 
		} else {
			Duration duration = getDuration();
			return bib + " ELAPSED " + duration.getSeconds() + "." + duration.getNano();
		}
	}

}