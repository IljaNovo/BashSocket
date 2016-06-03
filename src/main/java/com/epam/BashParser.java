package com.epam;
public class BashParser {
	
	private String bashHtmlPage;
	
	public BashParser(String bashHtmlPage) {
		this.bashHtmlPage = bashHtmlPage;
	}
	
	public String getArticleText() {
		String pattern = "<div class=\"text\">";
		int startIndex = this.bashHtmlPage.lastIndexOf(pattern) +
			pattern.length();
		int endIndex = startIndex;
		
		while(endIndex != -1) {
			endIndex = this.bashHtmlPage.indexOf('<', endIndex) + 1;
			if (this.bashHtmlPage.charAt(endIndex + 1) == 'd') {
				endIndex = endIndex - 1;
				break;
			}
		}
		
		return this.bashHtmlPage
			.substring(startIndex, endIndex)
			.replace("<br>", "\n")
			.replace("&quot;", "\"")
			.replace("&lt;", "<")
			.replace("&gt;", ">");
	}
}