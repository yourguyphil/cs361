package race;

import java.time.LocalTime;

public abstract class AbstractEvent {

	public abstract void num(int bibNumber);
	public abstract void clear(int bibNumber);
	public abstract void cancelRacer();
	public abstract void startRacer(LocalTime start);
	public abstract void finishRacer(LocalTime finish);
	public abstract void DNFRacer();
	public abstract String toString();
	public abstract String toJSON();
	
}
