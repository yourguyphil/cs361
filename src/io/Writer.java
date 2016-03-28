package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Writes to output 
 */
public class Writer {

	/** Writes contents to 'output.txt' in the 'Test' package
	 * @param contents the contents to write to output
	 */
	public static void write(String contents) {
		// write contents to 'output.txt' in the 'test.files' package
		Path file = Paths.get("src/test.files/output.txt");
		try (BufferedWriter writer = Files.newBufferedWriter(file)) {
		    writer.write(contents);
		} catch (IOException e) {
			System.out.println("Could not print contents to output.txt");
			e.printStackTrace();
		}
	}
	
}
