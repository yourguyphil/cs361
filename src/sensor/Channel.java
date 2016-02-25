package sensor;
import java.time.Clock;
import java.time.LocalDateTime;

import sensor.Sensor;
import static sensor.SensorType.*;

public class Channel {
	
	private Sensor sensor;
	private boolean isArmed;
	
	public Channel() {
		isArmed =false;
	}
	
	private boolean hasSensor() {
		return sensor.getType() != NONE;
	}
	
	public void connect(String sensorType) {
		if(sensorType.equals("EYE"))
			sensor.setType(EYE);
		else if(sensorType.equals("GATE"))
			sensor.setType(GATE);
		else if(sensorType.equals("PAD"))
			sensor.setType(PAD);
		else {
			sensor.setType(NONE);
			throw new IllegalArgumentException("Invalid sensor type");
		}
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
	
	public LocalDateTime trigger(Clock clock) {
		if(isArmed && hasSensor())
			return sensor.trigger(clock);
		else
			throw new IllegalStateException("Channel is not armed");
	}

}