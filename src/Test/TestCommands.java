package Test;
import io.Parser;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestCommands {

	@Test
	public void testOn() {
		Parser parser = new Parser();
		assertTrue(parser.parse("08:24:19.96 ON"));
		assertFalse(parser.parse("08:24:19.96ON"));
		assertFalse(parser.parse("ON 08:24:19.96"));
		assertFalse(parser.parse("ON"));
		assertTrue(parser.parse("08:24:19.96  ON"));
	}
	
	@Test
	public void testFinish() {
		Parser parser = new Parser();
		assertFalse(parser.parse("23.232:23 FINISH"));
		assertFalse(parser.parse("03.23:23 FINISH"));
		assertTrue(parser.parse("23:23:23.23 FINISH"));
	}
	
	@Test
	public void testReset() {
		Parser parser = new Parser();
		assertFalse(parser.parse("23.232:23 RESET"));
		assertFalse(parser.parse("03.23:23 RESET"));
		assertFalse(parser.parse("122:23:23 RESET"));
		assertTrue(parser.parse("23:23:23.23 RESET"));
	}
	
	@Test
	public void testClear() {
		Parser parser = new Parser();
		assertFalse(parser.parse("12:23:23 CLR"));
		assertTrue(parser.parse("08:24:19.96 CLR 1"));
		assertTrue(parser.parse("08:24:19.96 CLR 2"));
		assertFalse(parser.parse("08:24:19.96 CLR1"));
	}
	
	@Test
	public void testNum() {
		Parser parser = new Parser();
		assertFalse(parser.parse("12:23:23 NUM"));
		assertTrue(parser.parse("08:24:19.96 NUM 1"));
		assertTrue(parser.parse("08:24:19.96 NUM 12"));
		assertFalse(parser.parse("08:24:19.96 NUM1"));
	}
	
	@Test
	public void testSwap() {
		Parser parser = new Parser();
		assertTrue(parser.parse("12:23:23 SWAP"));
		assertTrue(parser.parse("08:24:19.96 SWAP 1"));
		assertTrue(parser.parse("08:24:19.96 SWAP 12"));
		assertFalse(parser.parse("08:24:19.96 SWAP1"));
	}
	
	@Test
	public void testDNF() {
		Parser parser = new Parser();
		assertTrue(parser.parse("12:23:23 DNF"));
		assertTrue(parser.parse("08:24:19.96 DNF 1"));
		assertTrue(parser.parse("08:24:19.96 DNF 12"));
		assertFalse(parser.parse("08:24:19.96 DNF1"));
	}
	
	@Test
	public void testDisc() {
		Parser parser = new Parser();
		assertFalse(parser.parse("12:23:23.63 DISC"));
		assertTrue(parser.parse("08:24:19.96 DISC 1"));
		assertFalse(parser.parse("08:24:19.96 DISC AFDA"));
		assertTrue(parser.parse("08:24:19.96 DISC 12"));
		assertFalse(parser.parse("08:24:19.96 DISC1"));
	}

}