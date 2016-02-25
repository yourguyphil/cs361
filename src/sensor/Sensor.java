package sensor;

import java.time.Clock;
import java.time.LocalDateTime;
import static sensor.SensorType.*;

public class Sensor {
	
	private SensorType type = NONE;

	public LocalDateTime trigger(Clock clock) {
		return LocalDateTime.now(clock);
	}

	public SensorType getType() {
		return type;
	}

	public void setType(SensorType type) {
		this.type = type;
	}
	
}
