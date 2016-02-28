package chronotimer;
import static chronotimer.Sensor.NONE;

public class Channel {
	
	private boolean isArmed;
	private Sensor sensor;
	
	public Channel() {
		isArmed = false;
		sensor = NONE;
	}
	
	private boolean hasSensor() {
		return sensor != NONE;
	}
	
	public void connect(Sensor sensor) {
		this.sensor = sensor;
	}
	
	public void disconnect() {
		sensor = NONE;
	}
	
	public void toggle() {
		isArmed = !isArmed;
	}
	
	public boolean trigger() {
		// sensor is successfully triggered if the channel
		// is armed and the sensor type isn't NONE
		return isArmed && hasSensor();
	}

}