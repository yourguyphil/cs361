package test.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Scanner;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;
import io.Writer;

public class TestWriter {
	private Scanner reader;
	private String [] match = {"Go, go Power Rangers!", "Go, go Power Rangers!", "Go, go Power Rangers!", "Mighty Morphin Power Rangers!", "Black Ranger Adarsh" , "Green Ranger Noah" , "Yellow Ranger Phillips", "Red Ranger Kyle" , "Blue Ranger Mitch"};
	
	@Before
	public void before() {
		try{
			reader = new Scanner(Paths.get("src\\test\\files\\output.txt"));
		}
		catch(IOException e){
			System.out.println("No file");
		}
	}
	
	@After
	public void after() {
		reader.close();
	}
		
	
	@Test
	public void testWriteMethod0(){
		assertFalse(Writer.write(null));
		String c = "";
		for(String i : match){
			c+= i;
		}
		Writer.write(c);
		assertEquals(c, reader.nextLine());
	}
	
	@Test
	public void testWriteMethod1(){
		String c = "";
		for(int i= match.length -1; i> -1; --i ){
			c+= match[i];
		}
		Writer.write(c);
		String line = reader.nextLine();
		assertEquals(c.length(), line.length());
		assertEquals(c, line);
		
	}
	
	@Test
	public void testWriteCorrect0(){		
		Writer.write(match[0]);
		while(reader.hasNextLine()){
			assertEquals(match[0], reader.nextLine());
		}
		Writer.write(match[3]);
		while(reader.hasNextLine()){
			assertEquals(match[3], reader.nextLine());
		}
	}
	
	@Test
	public void testWriteCorrect1(){
		Writer.write(match[5]);
		while(reader.hasNextLine()){
			assertEquals(match[5], reader.nextLine());
		}
		Writer.write(match[0] + " " + match[4]);
		while(reader.hasNextLine()){
			assertEquals(match[0] + " " + match[4], reader.nextLine());
		}
	}
	
	@Test
	public void testWriteCorrect2(){
		Writer.write(match[6] + "BREAK" + match [4]);
		while(reader.hasNextLine()){
			assertEquals(match[6] + "BREAK" + match [4], reader.nextLine());
		}
		Writer.write(match[0] + match[0]);
		while(reader.hasNextLine()){
			assertEquals(match[0] + match[0] + " " + match[4], reader.nextLine());
		}
	}
}
