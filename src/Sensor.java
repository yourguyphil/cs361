import java.util.ArrayList;
import java.util.EventListener;


/**
 * Represents a sensor that can be connected to a ChronoTimer in order
 * to sense when a racer starts or finishes.
 */
public class Sensor {

	private ArrayList<SensorListener> listeners;
	private boolean isArmed;
	private SensorType type;

	public enum SensorType {
		PUSH_BUTTON, ELECTRIC_EYE, GATE, PAD
	}

	public Sensor(SensorType type){
		listeners = new ArrayList<SensorListener>();
		this.type = type;
	}

	/**
	 * Sets whether or not the sensor is armed. If the sensor is not armed,
	 * it cannot fire events.
	 * @param isArmed if true, the sensor will be armed
	 */
	public void setArmed(boolean isArmed){
		this.isArmed = isArmed;
	}

	/**
	 * Checks is the sensor is armed
	 * @return true if the sensor is armed
	 */
	public boolean isArmed(){
		return isArmed;
	}
	
	/**
	 * Gets the sensor type. Either PUSH_BUTTON, ELECTRIC_EYE, GATE, or PAD
	 * @return sensor type
	 */
	public SensorType getType() {
		return type;
	}

	/**
	 * Sets the sensor type to PUSH_BUTTON, ELECTRIC_EYE, GATE, or PAD
	 * @param type the type to set the sensor to
	 */
	public void setType(SensorType type) {
		this.type = type;
	}

	/**
	 * Add a listener to the sensor
	 * @param l the listener to be added
	 */
	public void addListener(SensorListener l){
		listeners.add(l);
	}

	/**
	 * Remove a listener from the sensor
	 * @param l the listener to be removed
	 */
	synchronized public void removeListener(SensorListener l){
		listeners.remove(l);
	}

	/**
	 * Manually trip the sensor
	 */
	public void trip(){
		// Call sensorTripped() on all listeners associated with this Sensor
		for(SensorListener l : listeners)
			l.sensorTripped();
	}
	
	public interface SensorListener extends EventListener{
		public void sensorTripped();
	}
}
