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
package chronotimer;
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
public class Parser {
	private String [] buffer;
	private String [] cache;
	private String regex = "\\d+:\\d{2,2}:\\d{2,2}\\.\\d{1,3}";	//<------------------ DO NOT MODIFY
	
    public Parser(){
 
    }
    
    /**	reads all Strings from console
     *  @param input, the String to be parsed, calls readCommand() if legal String(format and contents)
     */
    public void parse(String input){
        if(input.isEmpty()){
        	System.out.println("No command found");
            return;
        }
        buffer = input.split("\\s+");
        if(buffer.length <= 1){
        	System.out.println("Illegal command format");
        	cache = null;
        	return;
        }
        if(!checkTime(buffer[0])){
        	System.out.println("Illegal time format");
        	cache = null;
        	return;
        }
        readCommand();
    }
    
   /**
    * @return current command stored in cache
    * */
    public String Current(){
    	if(cache == null){
    		return "BAD";
    	}
    	String c = "";
    	for(String i : cache){
    		c += i + " ";
    	}
    	return c;
      
    }
  
    
    
    /**	parses commands 
     *	@param command, an array of String splits by space: <TimeStamp> <Command> <EOL>
     *	matches command[1] to command cases, executes command if legal
     *	calls readFile() in case 'File'
     */
    private void readCommand(){
    	switch (buffer[1]){
    		case "FILE":
    			cache = buffer;
    			break;
    		case "FINISH":
    			cache = buffer;
    			//finish 
    			break;
    		case "START":	
    			cache = buffer;
    			//start 
    			break;
    		case "CANCEL":    		
    			cache = buffer;
    			//cancel
    			break;
    		case "ON":   	
    			cache = buffer;
    			//on 
    			break;
    		case "OFF":    		
    			cache = buffer;
    			//off
    			break;
    		case "DNF":   	
    			cache = buffer;
    			//DNF
    			break;
    		case "RESET":    			
    			cache = buffer;
    			//reset 
    			break;
    		case "PRINT":
    			cache = buffer;
    			//print
    			break;
    		case "TRIG":
    			if(!trigNumToggle()){
    				cache = null;
    				break;
    			}
    			cache = buffer;
    			//trig @param buffer[2]
    			break;
    		case "NUM" :
    			if(!trigNumToggle()){
    				cache = null;
    				break;
    			}
    			cache = buffer;
    			//num @param buffer[2]
    			break;
    		case "ENDRUN":
    			//endrun
    			cache = buffer;
    			break;
    		case "NEWRUN":
    			cache = buffer;
    			//newrun
    			break;
    		case "EXIT":
    			cache = buffer;
    			System.out.println("------EXITING------");
    			System.exit(0);
    			//exit
    			break;
    		case "EVENT":
    			if(!EVENT()){
    				cache = null;
    				break;
    			}
    			cache = buffer;
    			//event @param buffer[2]
    			break;
    		case "TIME":
    			if(!TIME()){
    				cache = null;
    				break;
    			}
    			cache = buffer;
    			//TIME pass @param buffer[2]
    			break;
    		case "TOGGLE":
    			if(!trigNumToggle()){
    				cache = null;
    				break;
    			}
    			cache = buffer;
    			//toggle @param buffer[2]
    			break;
    		case "CONN":
    			if(!CONN()){
    				cache = null;
    				break;
    			}
    			cache = buffer;
    			//conn @param buffer[2] and buffer[3]
   				break;
    		default:
    			System.out.println("Unrecognized buffer. Please try again");
    			cache = null;
    			break;
    	}
    }
    
    /**	reads a file from given address
     * 	@param input: address of file location
     * 	Reads commands from the file, one line at a time
     * 	calls readCommand() on lines with correctly formatted commands
     */
    private void readFile(String input){
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(Paths.get(input));
        } catch (IOException e) {
            System.out.println("File not found");
            return;
        }
        while(fileReader.hasNextLine()){
            String token = fileReader.nextLine();
            if(token.isEmpty()){
                continue;
            }
            buffer = token.split("\\s+");
            if(buffer.length <= 1){
            	System.out.println("Illegal command format in FILE");
            	continue;
            }
            if(!checkTime(buffer[0])){
            	System.out.println("Illegal time format");
            	continue;
            }
            if(buffer[1].equals("File")){System.out.println("Illegal command in FILE: File"); continue;}
            readCommand();
            fileReader.close();
        }
    }
    
    /**
     * @param time: time to check for correct format
     */
    private boolean checkTime(String time){
    	return time.matches(regex);
    }
    
    /**@param command [] : check format
     * 
     */
    private boolean trigNumToggle(){
    	if(!checkSize3()){
    		return false;
    	}
		if(!(buffer[2].matches("\\d+"))){
			System.out.println("Illegal command format: Number: NaN");
			return false;
		}
		return true;
    }
    
    /**@param command [] : check format
     * 
     */
    private boolean TIME(){
    	if(!checkSize3()){
    		return false;
    	}
		if(!checkTime(buffer[2])){
			System.out.println("Time format illegal");
			return false;
		}
		return true;
    }
    
    /**@param command [] : check format
     * 
     */
    private boolean CONN(){
    	if(!(buffer.length == 4)){
			System.out.println("Illegal command format: CONN");
			return false;
		}
		if(!(buffer[2].equals("GATE") || buffer[2].equals("EYE"))){
			System.out.println("Illegal CONN command: (must be GATE OR EYE)"); 
			return false;
		}
		if(!(buffer[3].matches("\\d+"))){
			System.out.println("Illegal command format: Number: NaN");
			return false;
		}
		return true;
    }
    
    /**@param command [] : check format
     * 
     */
    private boolean EVENT(){
    	if(!checkSize3()){
    		return false;
    	}
    	if(!(buffer[2].equals("IND"))){
    		System.out.println("Illegal command: EVENT : EVENT TYPE");
    		return false;
    	}
    	return true;
    }
    
    /**@param command [] : check format
     * 
     */
    private boolean checkSize3(){
    	if(!(buffer.length == 3)){
			System.out.println("Illegal command format: TIME");
			return false;
		}
    	return true;
    }
}