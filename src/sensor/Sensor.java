package sensor;

import static sensor.SensorType.NONE;

import java.time.Clock;
import java.time.LocalTime;

public class Sensor {
	
	private SensorType type = NONE;

	public LocalTime trigger(Clock clock) {
		return LocalTime.now(clock);
	}

	public SensorType getType() {
		return type;
	}

	public void setType(SensorType type) {
		this.type = type;
	}
	
}
