package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chronotimer.Channel;
import chronotimer.Sensor;

public class TestChannel {
	
	private Channel channel;
	
	@Before
	public void before() {
		channel = new Channel();
	}
	
	/** Connects a sensor to the channel
	 * @param sensor the type of sensor to be connected
	 */
	@Test
	public void testConnect() {
		assertEquals(Sensor.NONE, channel.getSensor()); // Initial state, no sensor connected
		
		channel.connect(Sensor.NONE);
		assertEquals(Sensor.NONE, channel.getSensor()); // No sensor connected
		
		channel.connect(Sensor.EYE);
		assertEquals(Sensor.EYE, channel.getSensor()); // Eye sensor connected
		
		channel.connect(Sensor.GATE);
		assertEquals(Sensor.GATE, channel.getSensor()); // Gate sensor connected
		
		channel.connect(Sensor.PAD);
		assertEquals(Sensor.PAD, channel.getSensor()); // Pad sensor connected	
	}
	
	/** Disconnects any sensor from the channel
	 */
	@Test
	public void testDisconnect() {
		channel.connect(Sensor.NONE);
		channel.disconnect();
		assertEquals(Sensor.NONE, channel.getSensor()); // No sensor disconnected
		
		channel.connect(Sensor.EYE);
		channel.disconnect();
		assertEquals(Sensor.NONE, channel.getSensor()); // Eye sensor disconnected
		
		channel.connect(Sensor.GATE);
		channel.disconnect();
		assertEquals(Sensor.NONE, channel.getSensor()); // Gate sensor disconnected
		
		channel.connect(Sensor.PAD);
		channel.disconnect();
		assertEquals(Sensor.NONE, channel.getSensor()); // Pad sensor disconnected	
	}
	
	@Test
	public void testToggle() {
		assertFalse(channel.isArmed()); // Initial state, Unarmed
		
		channel.toggle();
		assertTrue(channel.isArmed()); // Armed state
		
		channel.toggle();
		assertFalse(channel.isArmed()); // Unarmed state
	}

	@Test
	public void testTriggerUnarmed() {
		assertFalse(channel.isArmed());
		
		assertFalse(channel.trigger()); // Initial state, no sensor connected
		
		channel.connect(Sensor.NONE);
		assertFalse(channel.trigger()); // No sensor connected
		
		channel.connect(Sensor.EYE);
		assertFalse(channel.trigger()); // Eye sensor connected
		
		channel.connect(Sensor.GATE);
		assertFalse(channel.trigger()); // Gate sensor connected
		
		channel.connect(Sensor.PAD);
		assertFalse(channel.trigger()); // Pad sensor connected	
	}
	
	@Test
	public void testTriggerArmed() {
		channel.toggle();
		assertTrue(channel.isArmed());
		
		assertFalse(channel.trigger()); // Initial state, no sensor connected
		
		channel.connect(Sensor.NONE);
		assertFalse(channel.trigger()); // No sensor connected
		
		channel.connect(Sensor.EYE);
		assertTrue(channel.trigger()); // Eye sensor connected
		
		channel.connect(Sensor.GATE);
		assertTrue(channel.trigger()); // Gate sensor connected
		
		channel.connect(Sensor.PAD);
		assertTrue(channel.trigger()); // Pad sensor connected
	}
}
