package test.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.Parser;

import org.junit.Before;
import org.junit.Test;

import chronotimer.ChronoTimer;

public class TestParser {
	
	private Parser parser;
	private ChronoTimer chronotimer;
	
	@Before
	public void before() {
		chronotimer = new ChronoTimer();
		parser = new Parser(chronotimer);
	}
	
	@Test
	public void testParse(){
		assertFalse(parser.parse("")); // No timestamp or cmd name
		assertFalse(parser.parse("00:00:00.00")); // no cmd name
		assertFalse(parser.parse("badTimeStamp cmdName")); // bad timestamp
		
		assertTrue(parser.parse("00:00:00.00 cmdName")); // timestamp and cmd name
		assertTrue(parser.parse("00:00:00.00 cmdName arg1")); // timestamp, cmd name, and arg
		assertTrue(parser.parse("00:00:00.00 cmdName arg1 arg2")); // timestamp, cmd name, and multiple args
	}

	@Test
	public void testParseFile(){
		assertFalse(parser.parseFile("badPath")); // bad path
		assertTrue(parser.parseFile("output.txt")); // good path
	}
}
