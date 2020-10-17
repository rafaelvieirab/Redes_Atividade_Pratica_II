package br.ufc.crateus.http;

public class Header {
	private String type;
	private String value;
	
	public Header(String type, String value) {
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return type + ": " + value;
	}
}
