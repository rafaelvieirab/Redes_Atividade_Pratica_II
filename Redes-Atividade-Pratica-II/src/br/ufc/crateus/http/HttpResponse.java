package br.ufc.crateus.http;

import java.util.ArrayList;

public class HttpResponse {
	private String version;
	private int statusCode;
	private String statusText;
	private ArrayList<Header> headers;
	private String body;
	
	public HttpResponse() {
		this.version = "HTTP/1.0";
		this.headers = new ArrayList<Header>();
	}

	public void addHeader(String type, String value) {
		this.headers.add(new Header(type, value));
	}
	
	public int getBodyLength() {
		return body.length();
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
    public String toString() {
    	StringBuilder msg = new StringBuilder();
    	String endLine = "\r\n";
    	
    	msg.append(version + " " + statusCode + " " + statusText + endLine);
    	
    	for(Header header : headers) 
    		msg.append(header.toString() + endLine);
    	
    	if(body!="") {
    		msg.append(endLine);
    		msg.append(body + endLine);
    	}
    	return msg.toString();
    }
	
}
