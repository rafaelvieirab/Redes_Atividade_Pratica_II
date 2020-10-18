package br.ufc.crateus.utils;

import java.util.List;

import br.ufc.crateus.model.Employee;

public class BuildHtmlResponse {
	private final static String INITIAL_HTML = "<!DOCTYPE html>\n" + 
			"<html lang=\"pt-br\">\n" + 
			"<head>\n" + 
			"    <meta charset=\"UTF-8\">\n" + 
			"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + 
			"    <title>Employees</title>\n" + 
			"</head>\n" + 
			"<body>";
	
	private final static String FINAL_HTML = "</body></html>";
	
	public static String toHtml(List<Employee> list) {
		StringBuilder listStr = new StringBuilder();
		
		listStr.append(INITIAL_HTML);
		listStr.append("<ul>");
		
		for(Employee emp : list) {
			listStr.append("<li>");
			listStr.append("<p>Id: "+ emp.getId() + "</p>");
			listStr.append("<p>Nome: "+ emp.getName() + "</p>");
			listStr.append("<p>Cargo: "+ emp.getOffice() + "</p>");
			listStr.append("</li>");
		}
		listStr.append("</ul>");
		listStr.append(FINAL_HTML);
		
		return listStr.toString();
	}
	
	public static String toHtml(Employee emp) {
		StringBuilder listStr = new StringBuilder();
		
		listStr.append(INITIAL_HTML);
		
		listStr.append("<h3> Empregado Cadastrado com Sucesso </h3>");
		listStr.append("<p>Id: "+ emp.getId() + "</p>");
		listStr.append("<p>Nome: "+ emp.getName() + "</p>");
		listStr.append("<p>Cargo: "+ emp.getOffice() + "</p>");
		
		listStr.append(FINAL_HTML);
		
		return listStr.toString();
	}
	
	public static String toHtml(String statusErro, String msgErro) {
		StringBuilder listStr = new StringBuilder();
		
		listStr.append(INITIAL_HTML);
		listStr.append("<h1>Erro " + statusErro + "<br>"+ msgErro +"</h1>");
		listStr.append(FINAL_HTML);
		
		return listStr.toString();
	}
}
