//         											 PLEASE READ
/**	Commands follow the format: <TimeStamp> <Command> <Argument List><EOL>{separated by spaces} <-- when read from the console, the TimeStamp is prepended to the input line.
 *  This class assumes that this will always occur, otherwise it is an illegal format.
	When the readCommand() matches a 'File' command, it will treat the rest of the input as an address to fetch the file from.
	All commands inside a File must be formated exactly like the console format <TimeStamp> <Command> <Argument List> <EOL>, the only difference is that the File must have TimeStamp written into each line.
	This class will not prepend TimeStamps to any commands, and the Main class should only do so for input read through the console.
	All commands are matched and executed in the command class
 */
package io;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

/** Parses strings and files into executable commands
 */
public class Parser {

	/** Attempts to parse the input into a command to be executed
	 * @param input the string to be parsed
	 */
	public boolean parse(String input) {
		try {
			String[] buffer = input.split("\\s+");

			LocalTime timeStamp = LocalTime.parse(buffer[0]);
			String cmdName = buffer[1];
			String[] args = Arrays.copyOfRange(buffer, 2, buffer.length);

			if (cmdName.equals("FILE")) {
				parseFile(args[0]);
			} else {
				return Command.execute(timeStamp, cmdName, args);
			}
		} catch (DateTimeParseException | IndexOutOfBoundsException e) {
			System.out.println("Error parsing command");
		}
		return false;
	}

	/** Reads a file from the specified path and attempts to parse each line into a command
	 * @param path the path of the file to read relative to the src\test\ directory
	 */
	private void parseFile(String path) {
		try {
			Scanner fileReader = new Scanner(new File("src\\Test\\" + path));
			while (fileReader.hasNextLine())
				parse(fileReader.nextLine());
			
			fileReader.close();
		} catch (IOException e) {
			System.out.println("Error parsing command file");
		}
	}
}