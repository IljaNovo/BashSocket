package com.epam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class BinderManager {
	private String host;
	private int port;
	
	public BinderManager(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public String getHeadPage(String idPage) throws UnknownHostException, IOException{
		String querry = "GET /quote/" + idPage + " HTTP/1.1\nHost:" + this.host + "\n\n";
		String answer = "";
		
		try (Socket connection = new Socket(InetAddress.getByName(this.host), this.port);
				  PrintWriter out = new PrintWriter(connection.getOutputStream());
					BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()))
				) {
				out.print(querry);
				out.flush();
				
				answer = this.readQueryHead(input);
				
			} catch(UnknownHostException e) {
				throw new UnknownHostException("Хоста не существует");
			
			} catch(IOException e) {
				throw new IOException("Невозможно подключиться");
			}
		
		return answer;
	}
	
	public String readQueryHead(BufferedReader input) throws IOException {
		int count = 0;
		String header = "";
		while (count < 12) {
			header += input.readLine() + "\n";
			++count;
		}	
		return header;
	}
	
	public String getHtmlPage(String idPage, String encoding) throws UnknownHostException, IOException {
		String querry = "GET /quote/" + idPage + " HTTP/1.1\nHost:" + this.host + "\n\n";
		String answer = "";
		
		try (Socket connection = new Socket(InetAddress.getByName(this.host), this.port);
				  PrintWriter out = new PrintWriter(connection.getOutputStream());
					BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding))
				) {
				out.print(querry);
				out.flush();
				
				String buffer = "";
				 
				while ((buffer = input.readLine()) != null) { 
					answer += buffer + "\n";
				}
				
			} catch(UnknownHostException e) {
				throw new UnknownHostException("Хоста не существует");
			
			} catch(IOException e) {
				throw new IOException("Невозможно подключиться");
			}
		
		return answer;
	}
}
