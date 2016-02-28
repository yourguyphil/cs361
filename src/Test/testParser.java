package Test;
import static org.junit.Assert.*;
import io.Parser;

import org.junit.After;
import org.junit.Before;

public class testParser {
	private Parser parser;

	@Before
	public void before(){
		parser = new Parser();
	}
	
	@After
	public void after(){
		parser = null;
	}
	
	@org.junit.Test
	public void testBadFormat(){
		parser.parse("8:24:19.96 START");
		assertEquals("8:24:19.96 START ", parser.Current());
		
		parser.parse("8:24:19.96START");
		assertEquals("BAD", parser.Current());
		
		parser.parse("START 8:24:19.96");
		assertEquals("BAD", parser.Current());
		
		parser.parse("START");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96         START");
		assertEquals("8:24:19.96 START ", parser.Current());	
	}
	
	@org.junit.Test
	public void testBadTimeFormat(){
		parser.parse("8:24:193.96 START");
		assertEquals("BAD", parser.Current());
		
		parser.parse("08:24:19.96 START");
		assertEquals("08:24:19.96 START ", parser.Current());
		
		parser.parse("8:24:19.960 START");
		assertEquals("8:24:19.960 START ", parser.Current());
		
		parser.parse("8:245:19.96 START");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 TIME 9:15:19.94");
		assertEquals("8:24:19.96 TIME 9:15:19.94 ", parser.Current());
		
		parser.parse("8:24:19.96 TIME 9:15:193.94");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 TIME 09:15:19.94");
		assertEquals("8:24:19.96 TIME 09:15:19.94 ", parser.Current());
		
		parser.parse("8:24:19.96 TIME 9:15:19.940");
		assertEquals("8:24:19.96 TIME 9:15:19.940 ", parser.Current());
		
		parser.parse("8:24:19.96 TIME 9:153:19.94");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 TIME 9:15:19.9405");
		assertEquals("BAD", parser.Current());
		
	}
	
	@org.junit.Test
	public void testIllegalCommand(){
		parser.parse("8:24:19.96 Phillips");
		assertEquals("BAD", parser.Current());		
		
		parser.parse("8:24:19.96");
		assertEquals("BAD", parser.Current());	
		
		parser.parse("Black Ranger");
		assertEquals("BAD", parser.Current());	
	}
	
	@org.junit.Test
	public void testToggle(){
		parser.parse("8:24:19.96 TOGGLE");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 TOGGLE Phillips");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96TOGGLE TOGGLE 3 ");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 TOGGLE 1");
		assertEquals("8:24:19.96 TOGGLE 1 ", parser.Current());
		
	}
	
	@org.junit.Test
	public void testConn(){
		parser.parse("8:24:19.96 CONN");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 CONN 3");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 CONN GATE");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 CONN EYE");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 CONN EYE Phillips");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 CONN EYE 1");
		assertEquals("8:24:19.96 CONN EYE 1 ", parser.Current());
		
		parser.parse("8:24:19.96 CONN GATE 1");
		assertEquals("8:24:19.96 CONN GATE 1 ", parser.Current());
		
	}
	
	@org.junit.Test
	public void testNum(){
		parser.parse("8:24:19.96 NUM");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 NUM Phillips");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 NUM Phillips 2");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 NUM 234");
		assertEquals("8:24:19.96 NUM 234 ", parser.Current());
	}
	
	@org.junit.Test
	public void testTrig(){
		parser.parse("8:24:19.96 TRIG");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 TRIG Phillips");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 TRIG Phillips 2");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96TRIG TRIG 1");
		assertEquals("BAD", parser.Current());
		
		parser.parse("8:24:19.96 TRIG 1");
		assertEquals("8:24:19.96 TRIG 1 ", parser.Current());
	}

	@org.junit.Test
	public void testFile(){
		// It works 
	}
}
