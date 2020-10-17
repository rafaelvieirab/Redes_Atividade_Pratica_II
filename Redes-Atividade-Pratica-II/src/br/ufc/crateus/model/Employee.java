package br.ufc.crateus.model;

public class Employee {
	private int id;
	private String name;
	private String office;
	
	public Employee(int id, String name, String office) {
		this.id = id;
		this.name = name;
		this.office = office;
	}

	public Employee(String name, String office) {
		this.name = name;
		this.office = office;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", office=" + office + "]";
	}
}
