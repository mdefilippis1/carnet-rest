package carnet.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ClientCarnet {
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String[] params = str.split(";");
		handleRequest(params);
		sc.close();
	}
	
	private static void printResponse(Response response) {
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
		System.out.println();
	}
	
	private static void get(WebTarget target) {
		Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_PLAIN_TYPE);
		Response response = invocationBuilder.get();
		printResponse(response);
	}
	
	private static void post(WebTarget target, String nom, String adresse) {
		System.out.println("Posting data");
		target = target.queryParam("nom", nom);
		target = target.queryParam("adresse", adresse);
		Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_PLAIN_TYPE);
		Response response = invocationBuilder.post(Entity.text(""));
		printResponse(response);
	}
	
	private static void put(WebTarget target, String nom, String adresse) {
		target = target.queryParam("nom", nom);
		target = target.queryParam("adresse", adresse);
		Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_PLAIN_TYPE);
		Response response = invocationBuilder.put(Entity.text(""));
		printResponse(response);
	}
	
	private static void delete(WebTarget target) {
		System.out.println("Deleting data");
		Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_PLAIN_TYPE);
		Response response = invocationBuilder.delete();
		printResponse(response);
	}
	
	private static void handleRequest(String[] args) throws Exception {
		String ip;
		String port;
		
		BufferedReader br = new BufferedReader(new FileReader("options.conf")); 
		ip = br.readLine();
		port = br.readLine();
		
		Client client = ClientBuilder.newClient();
		System.out.println("Connecting to "+ip+":"+port+"...");
		WebTarget webTarget = client.target("http://"+ip+":"+port+"/carnet-rest/carnet");
		
		switch(args[0]) {
		case "GET":
			if(args.length == 1) {
				get(webTarget.path("personnes"));
			}
			else if(args.length == 3) {
				if (args[1].equals("adresse")) {
					get(webTarget.path("adresses").path(args[2]));
				}
				else if (args[1].equals("personne")) {
					get(webTarget.path("personnes").path(args[2]));
				}
			}
			break;
		case "POST":
			if (args.length == 3) {
				post(webTarget.path("personnes"), args[1], args[2]);
			}
			else {
				System.out.print("argument error");
			}
			break;
		case "PUT":
			if (args.length == 3) {
				put(webTarget.path("personnes"), args[1], args[2]);
			}
			break;
		case "DELETE":
			if (args.length == 2) {
				delete(webTarget.path("personnes").path(args[1]));
			}
			break;
		}
	}
}
