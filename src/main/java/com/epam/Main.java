package com.epam;
import java.io.IOException;
import java.net.UnknownHostException;

public class Main {
	
	public static void main(String[] args) {
		BinderManager bm = new BinderManager("bash.im", 80);
		
		try {
			HeadQueryParser hqp = new HeadQueryParser(bm.getHeadPage(args[0]));
			if (hqp.getStatusLoading().equals("200")) {
				BashParser bp = new BashParser(bm.getHtmlPage(args[0], hqp.getCharset()));
				System.out.println(bp.getArticleText());
			} else {
				System.out.println("Статьи под номером: " + args[0] + " не существует.");
			}
		} catch (UnknownHostException e) {
		e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
