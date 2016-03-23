package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/** Writes to output 
 */
public class Writer {

	/** Writes contents to 'output.txt' in the 'Test' package
	 * @param contents the contents to write to output
	 */
	static BufferedWriter outputWriter;
	public static void write(String contents) {
		
		//Try and open connection to file
		try {
			 outputWriter = new BufferedWriter(new FileWriter("output.txt"));
		} catch (IOException e) {
			System.out.println(" Could not open file output.txt because: " + e.getMessage());
			e.printStackTrace();
		}
		
		// write contents to 'output.txt' in the 'Test' package
		try {
			outputWriter.write(contents);
			outputWriter.flush();
			outputWriter.close();
		} catch (IOException e) {
			System.out.println("Coult not write contents to the file " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			//need to close
			
		}
		
	}
	
}
