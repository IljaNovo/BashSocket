package com.epam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
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
	
	public String executeQuery(String queryParam, String idPage) {
		String query = queryParam + " /quote/" + idPage + " HTTP/1.1\nHost:" + this.host + "\n\n";
		return this.getPage(query);
	}
	
	private String getPage(String query) {
		String answer = "";
		
		try (Socket connection = new Socket(InetAddress.getByName(this.host), this.port);
			PrintWriter out = new PrintWriter(connection.getOutputStream());
				
			) {
				out.print(query);
				out.flush();
						
				answer = this.readHead(connection.getInputStream());			
				HeadQueryParser hqp = new HeadQueryParser(answer);
				
				answer += this.readBody(connection.getInputStream(), hqp.getCharset());
				
			} catch(UnknownHostException e) {
				return "Хоста не существует";
			} catch(IOException e) {
				return "Невозможно подключиться";
			}
		return answer;
	}
	
	private String readBody(InputStream input, String encoding) throws IOException {
		String header = "";
		String buf = "";
		BufferedReader bodyReader = new BufferedReader(new InputStreamReader(input, encoding));
		while ((buf = bodyReader.readLine()) != null) {
			header += buf + '\n';
		}	
		return header;
	}
	
	private String readHead(InputStream input) throws IOException {
		String header = "";
		char buf = ' ';
		while (!this.validateEnd(header)) {
			buf = (char)input.read();
			header += buf;
		}	
		return header;
	}
	
	private boolean validateEnd(String header) {
		if (header.length() < 4) {
			return false;
		}
		if ((header.charAt(header.length() - 1) == '\n') &&
			(header.charAt(header.length() - 2) == '\r') &&
			(header.charAt(header.length() - 3) == '\n') &&
			(header.charAt(header.length() - 4) == '\r')) {
			return true;
		}
		return false;
	}
}
