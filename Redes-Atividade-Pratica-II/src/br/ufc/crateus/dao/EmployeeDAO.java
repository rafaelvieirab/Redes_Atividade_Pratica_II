package br.ufc.crateus.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.ufc.crateus.model.Employee;

public class EmployeeDAO {
	
	private static Map<Integer,Employee> employeeMap = new HashMap<Integer, Employee>();
	private static int nextId;
	
	static {
		initEmployees();
	}

	private static void initEmployees() {
		Employee emp1 = new Employee(0, "Rafael", "Eletricista");
		Employee emp2 = new Employee(1, "Jean", "Funcionário");
		Employee emp3 = new Employee(2, "Julia", "Secretaria");

		employeeMap.put(emp1.getId(), emp1);
		employeeMap.put(emp2.getId(), emp2);
		employeeMap.put(emp3.getId(), emp3);
		nextId = 3;
	}

	public static Employee addEmployee(String name, String office) {
		Employee emp = new Employee(nextId, name, office);
		employeeMap.put(emp.getId(), emp);
		nextId++;
		return emp;
	}

	public static List<Employee> getAllEmployees() {
		return new ArrayList<Employee>(employeeMap.values());
	}
	
}