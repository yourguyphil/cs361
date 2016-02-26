package chronotimer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class Shell {
	// use the parser to read commands from the console
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		Parser parser = new Parser();
		SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss.s");
		while(true){
            parser.parse(ft.format(new Date().getTime()) + " " + input.nextLine());		//retrieves input command and prepends time label to command
		}																				//passed to parser 
																						// for testing from file use command: FILE followed by address---> location of testCommands.txt
	}
}
