package br.ufc.crateus.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import br.ufc.crateus.dao.EmployeeDAO;
import br.ufc.crateus.http.HttpMethod;
import br.ufc.crateus.http.HttpRequest;
import br.ufc.crateus.http.HttpResponse;
import br.ufc.crateus.model.Employee;
import br.ufc.crateus.utils.BuildHtmlResponse;

public class Handle implements  Runnable {
	Socket socket;
	
	public Handle(Socket socket) {
		this.socket = socket;
	}
	
	private Employee StringToEmployee(String body) {
		String[] params = body.split("&");
		String name = params[0].split("=")[1];
		String office = params[1].split("=")[1];
		
		return EmployeeDAO.addEmployee(name, office);
	}
	
	public void process() throws IOException {
		HttpRequest request = new HttpRequest(socket.getInputStream());
		
		HttpResponse response = new HttpResponse();
		
		response.addHeader("Date", new Date(System.currentTimeMillis()).toString());
		response.addHeader("Connection", "Close");
		response.addHeader("Content-Type", "text/html; charset=utf-8");
		
		validation(request, response);
		
		if(response.getBodyLength() == 0) {
			String responseHtml = BuildHtmlResponse.toHtml(
					String.valueOf(response.getStatusCode()),
					response.getStatusText());
			response.setBody(responseHtml);
		}
		response.addHeader("Content-Length", String.valueOf(response.getBodyLength()));
		
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(response.toString().getBytes());
		outputStream.flush();
		socket.close();
	}
	
	private void validation(HttpRequest request, HttpResponse response) {
		
		if(request.getTarget().equals("/employees") && 
				request.getMethod() == HttpMethod.GET) {
			response.setStatusCode(200);
			response.setStatusText("OK");
			response.setBody(BuildHtmlResponse.toHtml(EmployeeDAO.getAllEmployees()));
		}
		else if(request.getTarget().equals("/employees") &&
				request.getMethod() == HttpMethod.POST) {
			
			if(request.getBodyLength() > 0) {
				Employee emp = StringToEmployee(request.getBody());
				response.setStatusCode(201);
				response.setStatusText("Created");
				response.setBody(BuildHtmlResponse.toHtml(emp));
			}
			else {
				response.setStatusCode(400);
				response.setStatusText("Bad Request");
			}
		}
		else {
			response.setStatusCode(404);
			response.setStatusText("Not Found");
		}
	}
	
	public void run() {
		try {
			process();
		} catch (Exception e) {
			System.out.println (e);
		}
	}
}
