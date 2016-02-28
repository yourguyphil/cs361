package sensor;
import static sensor.SensorType.*;

import java.time.Clock;
import java.time.LocalTime;

public class Channel {
	
	private Sensor sensor;
	private boolean isArmed;
	
	public Channel() {
		isArmed =false;
	}
	
	private boolean hasSensor() {
		return sensor.getType() != NONE;
	}
	
	public void connect(SensorType type) {
		sensor.setType(type);
	}
	
	public void disconnect() {
		if(hasSensor())
			sensor.setType(NONE);
		else 
			throw new IllegalStateException("Cannot disconnect when nothing is connected");
	}
	
	public void toggle() {
		if (hasSensor())
			isArmed = !isArmed;
		else 
			throw new IllegalStateException("Must connect a sensor before arming");
	}
	
	public LocalTime trigger(Clock clock) {
		if(isArmed && hasSensor())
			return sensor.trigger(clock);
		else
			throw new IllegalStateException("Channel is not armed");
	}

}