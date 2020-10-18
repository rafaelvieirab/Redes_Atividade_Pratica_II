package br.ufc.crateus.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HttpRequest {
	private InputStream inputStream;
	private HttpMethod method;
	private String target; 
	private String version;  
	private ArrayList<Header> headers;
	private int bodyLength;
	private String body;

	public HttpRequest(InputStream inputStream) throws IOException {
		this.inputStream = inputStream;
		this.headers = new ArrayList<Header>();
		this.body = "";
		this.bodyLength = 0;
		build();
	}
	
	private void build() throws IOException {
		InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        
        readInitialLine(br.readLine());
        readHeader(br);
        
        if(this.bodyLength != 0)
        	readBody(br);
	}

	private HttpMethod returnMethod(String method) {
		HttpMethod enumMethod = null;
		
		switch (method.toUpperCase()) {
			case "GET":
				enumMethod = HttpMethod.GET;
				break;
			case "POST":
				enumMethod = HttpMethod.POST;
				break;
			case "PUT":
				enumMethod = HttpMethod.PUT;
				break;
			case "PATCH":
				enumMethod = HttpMethod.PATCH;
				break;
			case "HEAD":
				enumMethod = HttpMethod.HEAD;
				break;
			default:
				enumMethod = HttpMethod.OPTIONS;
		}
		return enumMethod;
	}
	
    private void readInitialLine(String line) {
    	String[] values = line.split(" ");
    	this.method = returnMethod(values[0]);
    	this.target = values[1];
    	this.version = values[2];
    }
    
    private void readHeader(BufferedReader br) throws IOException {
    	String line = "";
    	while((line = br.readLine()).length() != 0) {
    		String[] values = line.split(": ");
    		this.headers.add(new Header(values[0], values[1]));
    		
    		if(values[0].equals("Content-Length"))
    			this.bodyLength = Integer.parseInt(values[1]);
    	}
    }
    
    private void readBody(BufferedReader br) throws IOException {
    	for(int i = 0; i < this.bodyLength; i++)
    		this.body += (char) br.read();
    }

	public HttpMethod getMethod() {
		return method;
	}

	public String getTarget() {
		return target;
	}

	public String getVersion() {
		return version;
	}
	
	public ArrayList<Header> getHeaders() {
		return headers;
	}

	public int getBodyLength() {
		return bodyLength;
	}
	
	public String getBody() {
		return body;
	}
    
    @Override
    public String toString() {
    	StringBuilder msg = new StringBuilder();
    	String endLine = "\r\n";
    	
    	msg.append(method + " " + target + " " + version + endLine);
    	
    	for(Header header : headers) 
    		msg.append(header.toString() + endLine);
    	
    	if(body!="") {
    		msg.append(endLine);
    		msg.append(body + endLine);
    	}
    		
    	return msg.toString();
    }
}
