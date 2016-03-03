package Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import chronotimer.ChronoTimer;
import io.Parser;

public class TestParser {
	private Parser parser = new Parser(new ChronoTimer());
	
	@org.junit.Test
	public void testBadFormat(){
		assertTrue(parser.parse("08:24:19.96 START"));
		assertFalse(parser.parse("08:24:19.96START"));
		assertFalse(parser.parse("START 08:24:19.96"));
		assertFalse(parser.parse("START"));
		assertFalse(parser.parse("08:24:19.96"));	
		assertTrue(parser.parse("08:24:19.96         START"));
	}
	
	@org.junit.Test
	public void testBadTimeFormat(){
		assertFalse(parser.parse("08:24:193.96 START"));
		assertFalse(parser.parse("008:24:19.96 START"));
		assertTrue(parser.parse("08:24:19.960 START"));
		assertFalse(parser.parse("08:245:19.96 START"));
		assertFalse(parser.parse("08:24:19.96 TIME 9:15:19.94"));
		assertFalse(parser.parse("08:24:19.96 TIME 9:15:193.94"));
		assertTrue(parser.parse("08:24:19.96 TIME 09:15:19.94"));
		assertFalse(parser.parse("08:24:19.96 TIME 9:15:19.940"));
		assertFalse(parser.parse("08:24:19.96 TIME 9:153:19.94"));
		assertFalse(parser.parse("08:24:19.96 TIME 9:15:19.9405"));
	}
	
	@org.junit.Test
	public void testIllegalCommand(){
		assertFalse(parser.parse("08:24:19.96 Phillips"));
		assertFalse(parser.parse("08:24:19.96"));
		assertFalse(parser.parse("Black Ranger"));	
	}
	
	@org.junit.Test
	public void testToggle(){
		assertFalse(parser.parse("08:24:19.96 TOGGLE"));
		assertFalse(parser.parse("08:24:19.96 TOGGLE Phillips"));
		assertFalse(parser.parse("08:24:19.96TOGGLE TOGGLE 3 "));
		assertTrue(parser.parse("08:24:19.96 TOGGLE 1"));
	}
	
	@org.junit.Test
	public void testConn(){
		
		assertFalse(parser.parse("08:24:19.96 CONN"));
		assertFalse(parser.parse("08:24:19.96 CONN 3"));
		assertFalse(parser.parse("08:24:19.96 CONN GATE"));
		assertFalse(parser.parse("08:24:19.96 CONN EYE"));
		assertFalse(parser.parse("08:24:19.96 CONN EYE Phillips"));
		assertTrue(parser.parse("08:24:19.96 CONN EYE 1"));
		assertTrue(parser.parse("08:24:19.96 CONN GATE 1"));
		
	}
	
	@org.junit.Test
	public void testNum(){
		assertFalse(parser.parse("08:24:19.96 NUM"));
		assertFalse(parser.parse("08:24:19.96 NUM Phillips"));
		assertFalse(parser.parse("08:24:19.96 NUM Phillips 2"));
		assertTrue(parser.parse("08:24:19.96 NUM 234"));
	}
	
	@org.junit.Test
	public void testTrig(){
		assertFalse(parser.parse("08:24:19.96 TRIG"));
		assertFalse(parser.parse("08:24:19.96 TRIG Phillips"));
		assertFalse(parser.parse("08:24:19.96 TRIG Phillips 2"));
		assertFalse(parser.parse("08:24:19.96TRIG TRIG 1"));
		assertTrue(parser.parse("08:24:19.96 TRIG 1"));
	}

	@org.junit.Test
	public void testFile(){
		// It works 
	}
}
