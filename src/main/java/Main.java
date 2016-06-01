import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
	
	public static void main(String[] args) {
		
		String url = "bash.im";
		String idStr = "439569";
		String querry = "GET /quote/" + idStr + " HTTP/1.1\nHost:" + url + "\n\n";		
		Socket connection;
		
		BufferedReader input;
		PrintWriter out = null;
		
		try {
			connection = new Socket(InetAddress.getByName(url), 80);
			out = new PrintWriter(connection.getOutputStream());
			out.print(querry);
			out.flush();
			input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String st = "";
			
			while ((st = input.readLine()) != null) { 
				System.out.println(st); 
			}
		
		} catch(UnknownHostException e) {
			System.out.println(0);
		
		} catch(IOException e) {
			System.out.println(1);
		}
	}
}
