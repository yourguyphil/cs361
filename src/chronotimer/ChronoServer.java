package chronotimer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import race.Racer;


public class ChronoServer {
	static String sharedResponse = "";
	static boolean gotMessageFlag = false;
	public HttpServer server;
	public static HashMap<Integer, String> nameLookUp;

	public static void main(String[] args){
		ChronoServer s = new ChronoServer();
	}


	public ChronoServer(){
		try {
			nameLookUp = nameLoader();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpServer server = null;
		try {
			server = HttpServer.create(new InetSocketAddress(8000), 0);
		} catch (IOException e) {
			System.out.println("could not start server");
			e.printStackTrace();
		}
		server.createContext("/Results", new DisplayHandler());
		server.createContext("/sendresults",new PostHandler());
		//server.createContext("/myStyle", new CSSHandler());
		server.setExecutor(null); // creates a default executor
		System.out.println("Starting Server...");
		server.start();
	}

	static HashMap<Integer, String> nameLoader() throws IOException{
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(217, "Edward Snowden");
		map.put(314, "Bob Smith");
		map.put(211, "Carrol OCONNOR");
		map.put(17, "Prince");
		map.put(310, "Pekabo Street");
		map.put(500, "Lindesy Vaughn");
		return map;	
	}


	static class DisplayHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange t) throws IOException {
			t.getResponseHeaders().set("Content-Type", "text/html");
			String response ="<body> <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>"
            +"<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js'></script>"
			+"<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js'></script>";
         response += "<div class = 'container'>";
		 response += "<table class ='table table-hover'>"
					+ "<tr>"
					+ "<td> Place </td>"
					+ "<td>Bib Number</td>"
					+ "<td>Name</td>"
					+ "<td>Time</td>"
					+ "</tr>";
			Gson g = new Gson();
			if (!sharedResponse.isEmpty()) {
				System.out.println(response);
				ArrayList<Racer> fromJson = new ArrayList<Racer>();
				fromJson.clear();
				 fromJson = g.fromJson(sharedResponse,
						new TypeToken<Collection<Racer>>() {
				}.getType());
				int i = 1;
				Collections.sort(fromJson, Racer.Comparators.DURATION);
				
				for(Racer r: fromJson){
					
					
					if(nameLookUp.containsKey((r.getBib()))){
					 response += "<tr>"
								 +"<td>" +i+ "</td>";
					   response+="<td>" +r.getBib() + "</td>"
								+"<td>" +nameLookUp.get(r.getBib()) + "</td>";
					}
					else{
						response += "<tr>"
								+"<td>" +i+ "</td>"
								+"<td>" +r.getBib() + "</td>"
								+"<td>" + "NOT FOUND "+ "</td>";
					}
					if(r.getFinish() == null){
						response +="<td>" + "DNF" +"</td>" 
								+"</tr>";
					}
					else{
						response +="<td>" + Time.toString(LocalTime.MIDNIGHT.plus(r.getDuration())) +"</td>" 
								 +"</tr>";
					}
					
					++i;
				}
			}
			else{
				System.out.println("no data to update");
			}
			response += "</table>";
			response += "</div>";
			response += "</body>";
			System.out.println(response);
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}

	}

	static class PostHandler implements HttpHandler {
		public void handle(HttpExchange transmission) throws IOException {

			//  shared data that is used with other handlers
			sharedResponse = "";

			// set up a stream to read the body of the request
			InputStream inputStr = transmission.getRequestBody();

			// set up a stream to write out the body of the response
			OutputStream outputStream = transmission.getResponseBody();

			// string to hold the result of reading in the request
			StringBuilder sb = new StringBuilder();

			// read the characters from the request byte by byte and build up the sharedResponse
			int nextChar = inputStr.read();
			while (nextChar > -1) {
				sb=sb.append((char)nextChar);
				nextChar=inputStr.read();
			}

			// create our response String to use in other handler
			sharedResponse = sharedResponse+sb.toString();

			// respond to the POST with ROGER
			String postResponse = "ROGER JSON RECEIVED";

			System.out.println("response: " + sharedResponse);

			transmission.sendResponseHeaders(300, postResponse.length());

			outputStream.write(postResponse.getBytes());

			outputStream.close();
		}
	}
	
	static class CSSHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            t.getResponseHeaders().set("Content-Type", "text/html");
            String response = "";
            String fileName = "myStyle.css";
            String line = "";
            try{
            	FileReader fr = new FileReader(fileName);
            	 BufferedReader bufferedReader = 
                         new BufferedReader(fr);

                     while((line = bufferedReader.readLine()) != null) {
                         System.out.println(line);
                         response += line;
                     } 
                     
                     bufferedReader.close();
            }
            catch(IOException e){
            	System.out.println("something bad happened");
            	e.printStackTrace();
            }finally{
            	
            }
            
  
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}


