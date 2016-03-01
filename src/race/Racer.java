package race;

import java.time.Duration;
import java.time.LocalTime;

/** Racer who runs in a race
 */
public class Racer {
	
	private int bibNumber;
	private LocalTime startTime;
	private LocalTime finishTime;

	/** Constructor for a racer with a specified bib number
	 * @param bibNumber the bib number of the racer
	 */
	public Racer(int bibNumber) {
		if(bibNumber > 99999 || bibNumber < 0) throw new IllegalArgumentException("Valid racer bib numbers range from 0 to 99,999");
		this.bibNumber = bibNumber;
	}

	/** Sets the start time of the racer
	 * @param time the start time of the racer
	 */
	public void setStart(LocalTime time) {
		startTime = time;
	}
	
	/** Sets the finish time of the racer
	 * @param time the finish time of the racer
	 */
	public void setFinish(LocalTime time) {
		if (startTime == null)
			throw new IllegalStateException("Must start before finish");
		else
			finishTime = time;
	}
	
	/** Marks the racer as canceled
	 */
	public void cancel() {
		startTime = null;
		finishTime = null;
	}
	
	/** Marks the racer as DNF
	 */
	public void DNF() {
		if (startTime == null)
			throw new IllegalStateException("Must start before DNF");
		else
			finishTime = null;
	}
	
	/** Gets the start time of the racer
	 * @return the start time of the racer
	 */
	public LocalTime getStart() {
		return startTime;
	}

	/** Gets the finish time of the racer
	 * @return the finish time of the racer
	 */
	public LocalTime getFinish() {
		return finishTime;
	}
	
	/** Gets the bib of the racer
	 * @return the bib of the racer
	 */
	public int getBib() {
		return bibNumber;
	}

	/** Gets the duration time of the racer
	 * @return the duration time of the racer
	 */
	public Duration getDuration() {
		if (startTime == null || finishTime == null)
			throw new IllegalStateException("Must have a start and finish to get duration");
		else
			return Duration.between(startTime, finishTime);
	}

	public String toString() {
		if (startTime == null && finishTime == null) {
			return bibNumber + " CANCEL"; 
		} else if (startTime != null && finishTime == null) {
			return bibNumber + " DNF"; 
		} else {
			Duration duration = getDuration();
			return bibNumber + " ELAPSED " + duration.getSeconds() + "." + duration.getNano();
		}
	}

}