package com.epam;
import java.io.IOException;
import java.net.UnknownHostException;

public class Main {
	
	public static void main(String[] args) {
		BinderManager bm = new BinderManager("bash.im", 80);
			
		try {
			BashParser bp = new BashParser(bm.executeQuery("GET", args[0]));
			System.out.println(bp.getArticleText());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
