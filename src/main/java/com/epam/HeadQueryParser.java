package com.epam;

public class HeadQueryParser {
	private String headQuery;
	
	public HeadQueryParser(String headQuery) {
		this.headQuery = headQuery;
	}
	
	public String getStatusLoading() {
		int index = this.headQuery.indexOf("HTTP/1.1");
		index = index + 9;
		String answer = "";
		while (this.headQuery.charAt(index) != ' ') {
			answer += this.headQuery.charAt(index);
			++index;
		}
		return answer;
	}
	
	public String getContentType() {
		int index = this.headQuery.indexOf("Content-Type");
		index = index + 14;
		String answer = "";
		while (this.headQuery.charAt(index) != ';') {
			answer += this.headQuery.charAt(index);
			++index;
		}
		return answer;
	}
	
	public String getTransferEncoding() {
		int index = this.headQuery.indexOf("Transfer-Encoding");
		index = index + 19;
		String answer = "";
		while (this.headQuery.charAt(index) != '\n') {
			answer += this.headQuery.charAt(index);
			++index;
		}
		return answer;
	}
	
	public String getCharset() {
		int index = this.headQuery.indexOf("charset");
		index = index + 8;
		String answer = "";
		while (this.headQuery.charAt(index) != '\n') {
			answer += this.headQuery.charAt(index);
			++index;
		}
		return answer;
	}
}
