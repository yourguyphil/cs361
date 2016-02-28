//         											 PLEASE READ
    /**	Commands follow the format: <TimeStamp> <Command> <Argument List><EOL>{separated by spaces} <-- when read from the console, the TimeStamp is prepended to the input line.
     *  This class assumes that this will always occur, otherwise it is an illegal format.
    	When the readCommand() matches a 'File' command, it will treat the rest of the input as an address to fetch the file from.
    	All commands inside a File must be formated exactly like the console format <TimeStamp> <Command> <Argument List> <EOL>, the only difference is that the File must have TimeStamp written into each line.
    	This class will not prepend TimeStamps to any commands, and the Main class should only do so for input read through the console.  
    	The 'File' command read from a file is treated as illegal.
    	All commands are matched by readCommand() and executed <--- I can't be more specific about this since we haven't written a main or decided how we're going to generate events and store information.
 		------------- Black Ranger
     */
package io;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Parser {
    
    /**	reads all Strings from console
     *  @param input, the String to be parsed, calls readCommand() if legal String(format and contents)
     */
	public ArrayList<Command> parse(String input) {
        try {
        	String[] buffer = input.split("\\s+");
    		
    		LocalDateTime timeStamp = LocalDateTime.parse(buffer[0], DateTimeFormatter.ofPattern("hh:mm:ss.s"));
    		String cmdName = buffer[1];
    		String[] args = Arrays.copyOfRange(buffer, 2, buffer.length + 1);
    		
    		if (cmdName.equals("FILE")) {
				return parseFile(args[0]);
    		} else {
    			ArrayList<Command> commands = new ArrayList<>();
    			commands.add(new Command(timeStamp, cmdName, args));
    			return commands;
    		}
		} catch (Exception e) { }
        System.out.println("Error parsing command");
    	return null;
	}
    
    /**	reads a file from given address
     * 	@param input: address of file location
     * 	Reads commands from the file, one line at a time
     * 	calls readCommand() on lines with correctly formatted commands
     */
	private ArrayList<Command> parseFile(String path) {
        try {
        	ArrayList<Command> commands = new ArrayList<>();
            Scanner fileReader = new Scanner(Paths.get(path));
            
            while(fileReader.hasNextLine()){
                ArrayList<Command> c = parse(fileReader.nextLine());
                
                if (c != null) commands.addAll(c);
            }
            fileReader.close();
            return commands;
        } catch (Exception e) { }
        System.out.println("Error parsing command file");
        return null;
	}
}