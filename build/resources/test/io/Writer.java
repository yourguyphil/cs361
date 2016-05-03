package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/** Writes to output 
 */
public class Writer {

	/** Writes contents to 'output.txt' in the 'Test' package
	 * @param contents the contents to write to output
	 */
	public static boolean write(String contents) {
		if (contents != null) {
			Path file = Paths.get("src\\test\\files\\output.txt");
			try (BufferedWriter writer = Files.newBufferedWriter(file)) {
			    writer.write(contents);
			    return true;
			} catch (IOException e) {
				System.out.println("Error exporting contents");
			}
		}
		return false;
	}
		
}
