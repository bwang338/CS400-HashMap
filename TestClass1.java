// --== CS400 File Header Information ==--
// Name: Ian Koh
// Email: iskoh@wisc.edu
// Team: BA
// Role: Test Engineer 2
// TA: Bri Cochran
// Lecturer: Florian Heiml
// Notes to Grader: <optional extra notes>

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class TestClass1 {

	public static void main(String[] args) {
		
		System.out.println("\nStudent object:");
		System.out.println("\ttestStudent:" + testStudent());
		System.out.println("\ttestStudentHash:" + testStudentHash());
		
		System.out.println("\nFile reader:");
		System.out.println("\ttestFileReader:" + testFileReader());
		
		System.out.println("\nDriver interface:");
		System.out.println("\ttestCovidDriver:" + testCovidDriver());
		
	}
	
	/**
	 * Tests functionality of constructors for Student class, as well as getters, setters, and toString(). 
	 * 
	 * @return true if Student objects are created with provided/default field values with no errors; 
	 * false otherwise
	 */
	public static boolean testStudent() {
		
		Student a;
		Student b;
		Student c;
		Student d;
		Student e;
		
		try {
			
			a = new Student();
			b = new Student(908, "Sellery", "N");
			c = new Student(907, "Witte", "P");
			d = new Student(906, "Off-campus", "N");
			e = new Student(910, "Chadborne", "N");
			
			//System.out.println("A");
			
		} catch (Exception ex) { return false; }
				
		if (a.getID() != 0
				|| !a.getLocation().equals("NA")
				|| !a.getStatus().contentEquals("NA"))
			return false;
		
		//System.out.println("B");
		
		if (b.getID() != 908
				|| !b.getLocation().equals("Sellery")
				|| !b.getStatus().contentEquals("N"))			
			return false;	
		
		//System.out.println("C");
		
		if (c.getID() != 907
				|| !c.getLocation().equals("Witte")
				|| !c.getStatus().contentEquals("P"))
			return false;
		
		//System.out.println("D");
		
		if (d.getID() != 906
				|| !d.getLocation().equals("Off-campus")
				|| !d.getStatus().contentEquals("N"))
			return false;
		
		//System.out.println("E");

		if (e.getID() != 910
				|| !e.getLocation().equals("Chadborne")
				|| !e.getStatus().contentEquals("N"))
			return false;
		
		//System.out.println("F");

		a.setID(203);
		b.setLocation("Dejope");
		c.setStatus("N");
		
		//System.out.println("G");

		if (a.getID() != 203
				|| !b.getLocation().contentEquals("Dejope")
				|| !c.getStatus().equals("N"))
			return false;
		
		//System.out.println("H");

		return true;
	}

	/**
	 * Tests if Student.java integrates correctly with HashTableMap implementation. 
	 * 
	 * @return true if Student objects are correctly stored; false otherwise
	 */
	public static boolean testStudentHash() {
		
		Student a = new Student();
		Student b = new Student(908, "Sellery", "N");;
		Student c = new Student(907, "Witte", "P");;
		Student d = new Student(906, "Off-campus", "N");;
		Student e = new Student(910, "Chadborne", "N");;
		
		HashTableMap<Integer, Student> table = new HashTableMap<>();
		
		try {
			
			if (!table.put(a.getID(), a) 
					|| !table.put(b.getID(), b) 
					|| !table.put(c.getID(), c) 
					|| !table.put(d.getID(), d)
					|| !table.put(e.getID(), e))
				return false;

		} catch (Exception ex) {
			return false;
		}
		
		try {
			
			if (table.get(0) != a)
				return false;
			if (table.get(908) != b)
				return false;
			if (table.get(907) != c)
				return false;
			if (table.get(d.getID()) != d)
				return false;
			if (table.get(e.getID()) != e)
				return false;
			
		} catch (NoSuchElementException ex) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Tests functionality of FileReader() 
	 * 
	 * @return true if data from provided .txt file is correctly transformed into Student objects 
	 * and properly added to Hash Table; false otherwise
	 */
	public static boolean testFileReader() {
		
		HashTableMap<Integer, Student> table = new HashTableMap<>();
		File inputs = new File("FileReaderTestInputs.txt");
		
		String[] arr = {"NA", 
				"A", 
				"B", 
				"C", 
				"X", 
				"Y", 
				"Z"};
		
		try {
			FileReader reader = new FileReader(table, inputs);
			
		} catch (FileNotFoundException e) {
			return false;
		}
			
		if (table.size() != arr.length) return false;
		
		for (int i = 0; i < arr.length; i++) {
			try {
				if (!table.remove(i).getLocation().contentEquals(arr[i])) return false;
			} catch (NoSuchElementException e) { return false; }
		}
		
		if (table.size() != 0) return false;
		
		return true;
	}
	
	public static boolean testCovidDriver() {
		
		ByteArrayOutputStream driverOutput = new ByteArrayOutputStream();
		PrintStream p = new PrintStream(driverOutput);
		
		PrintStream old = System.out;

		File inputs = new File("DriverTestInputs.txt");
		Scanner sc;
		try {
			sc = new Scanner(inputs);
		} catch (FileNotFoundException e) {
			System.out.println("Test file not found");
			return false; 
		}

		HashTableMap<Integer, Student> t = new HashTableMap<>();
		
		{ // add students 101-104

		System.setOut(p); // prevents driver from flooding console
		
		CovidDriver.run(sc, t);

		System.setOut(old);
		System.out.flush();
		
		if (t.size() != 4) return false;
		if (!t.get(101).getLocation().contentEquals("A") 
				|| !t.get(102).getLocation().contentEquals("B") 
				|| !t.get(103).getLocation().contentEquals("C")
				|| !t.get(104).getLocation().contentEquals("D")) 
			return false;
		
		//System.out.println("1: " + driverOutput.toString());
		
		}
		
		{ // remove students 102 and 104
		driverOutput = new ByteArrayOutputStream();
		p = new PrintStream(driverOutput);
		System.setOut(p);
			
		CovidDriver.run(sc, t);

		System.setOut(old);
		System.out.flush();
			
		if (t.size() != 2) return false;
		try {
			t.get(102);
			t.get(104);
			return false;
		} catch (NoSuchElementException e) { }
		
		//System.out.println("2: " + driverOutput.toString());
			
		}
		
		{ // clear table
			driverOutput = new ByteArrayOutputStream();
			p = new PrintStream(driverOutput);
			System.setOut(p);
				
			CovidDriver.run(sc, t);

			System.setOut(old);
			System.out.flush();
				
			if (t.size() != 0) return false;
			
			//System.out.println("3: " + driverOutput.toString()); 
		}
		
		{ // add 9 students from file
			driverOutput = new ByteArrayOutputStream();
			p = new PrintStream(driverOutput);
			System.setOut(p);
				
			CovidDriver.run(sc, t);

			System.setOut(old);
			System.out.flush();
				
			String[] arr = {"NA", 
					"A", 
					"B", 
					"C", 
					"X", 
					"Y", 
					"Z"};
			
			if (t.size() != arr.length) return false;
			
			for (int i = 0; i < arr.length; i++) {
				try {
					if (!t.remove(i).getLocation().contentEquals(arr[i])) return false;
				} catch (NoSuchElementException e) { return false; }
			}
			
			if (t.size() != 0) return false;
			
			//System.out.println("4: " + driverOutput.toString());
		}
		
		{ // erroneous input
			driverOutput = new ByteArrayOutputStream();
			p = new PrintStream(driverOutput);
			System.setOut(p);
			
			try {
				CovidDriver.run(sc, t);
				return false;
			} catch (InputMismatchException e) { sc.next(); }
			//System.out.println("1");
			
			try {
				CovidDriver.run(sc, t);
			} catch (Exception e) {	return false; }
			//System.out.println("2");

			try {
				CovidDriver.run(sc, t);
				return false;
			} catch (InputMismatchException e) { sc.next(); }
			//System.out.println("3");

			try {
				CovidDriver.run(sc, t);
			} catch (Exception e) {	return false; }
			//System.out.println("4");

			try {
				CovidDriver.run(sc, t);
				return false;
			} catch (InputMismatchException e) { sc.next(); }
			//System.out.println("5");

			try {
				CovidDriver.run(sc, t);
			} catch (Exception e) {	return false; }
			//System.out.println("6");

			System.setOut(old);
			System.out.flush();
				
			
			//System.out.println("5: " + driverOutput.toString()); 
		}

		sc.close();
		return true;
	}
	
}
