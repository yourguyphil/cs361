package io;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;



public class Shell {
	// use the parser written by Adarsh to parse live commands from the console
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		Parser n = new Parser();
		Date currentTime = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss.s");
		ft.format(currentTime);
		String command = "";
		System.out.println(ft.format(currentTime));
		while(true) {
			command = ft.format(currentTime) + " " + input.nextLine();
            n.parse(command);
		}
	}
}
