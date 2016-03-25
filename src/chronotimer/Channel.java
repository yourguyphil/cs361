package chronotimer;
import static chronotimer.Sensor.NONE;

/** Controls access of a sensor to the chronotimer 
 */
public class Channel {
	
	private boolean isArmed;
	private Sensor sensor;
	
	/** Default constructor for channel
	 */
	public Channel() {
		isArmed = false;
		sensor = NONE;
	}
	
	/** Connects a sensor to the channel
	 * @param sensor the type of sensor to be connected
	 */
	public void connect(Sensor sensor) {
		this.sensor = sensor;
	}
	
	/** Disconnects any sensor from the channel
	 */
	public void disconnect() {
		sensor = NONE;
	}
	
	/** Toggles the armed state of the channel
	 */
	public void toggle() {
		isArmed = !isArmed;
	}
	
	/** Triggers the sensor if the channel is armed and has a sensor connected
	 * @return true if the sensor was triggered, false otherwise
	 */
	public boolean trigger() {
		return isArmed && sensor != NONE;
	}

	/** Gets the armed state of the channel
	 * @return the armed state of the channel
	 */
	public boolean isArmed() {
		return isArmed;
	}
	
	/** Gets the sensor connected to the channel
	 * @return the sensor connected to the channel
	 */
	public Sensor getSensor() {
		return sensor;
	}
	
	

}