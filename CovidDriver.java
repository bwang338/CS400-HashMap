
// --== CS400 File Header Information ==--
// Name: Neel Murthy
// Email: nmurthy@wisc.edu
// Team: BA
// TA: Bri
// Lecturer: Gary Dahl
// Notes to Grader: none
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.*;
import java.nio.file.*;
import java.util.InputMismatchException;
public class CovidDriver {
	public static void main(String[] args)
	{
		Scanner input=new Scanner(System.in);
		HashTableMap m=new HashTableMap();
		try {
			run(input, m);
		}
		catch(InputMismatchException e1)
		{
			System.out.println("Invalid Input");
		}
	}
	public static void run(Scanner input, HashTableMap m) throws InputMismatchException{
		System.out.println("*****************Welcome to the UW Covid DataBase*****************");
		System.out.println("              Please Select From The Following Options:               ");
		System.out.println("**********************************************************************");
		boolean exit = false;
		while (!exit) {
			System.out.println(
					"----------------------------------------------------------------------------------------------------------");
			System.out.println("Enter 0 to exit the application.");
			System.out.println("Enter 1 to search for a student.");
			System.out.println("Enter 2 to remove a student from the database.");
			System.out.println("Enter 3 to add a new student to the database.");
			System.out.println("Enter 4 to clear the database.");
			System.out.println("Enter 5 to enter a file of students into the database.");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------");
			int choice = input.nextInt();
			switch (choice) {
				case 0:
					exit = true;
					break;
				case 1:
					System.out.println("Enter an ID to search for a student: ");
					int id=input.nextInt();
					try {
						System.out.println(m.get(id).toString());
					}
					catch(NoSuchElementException e1)
					{
						System.out.println("Invalid ID");
						continue;
					}
					break;
				case 2:
					System.out.println("Enter an ID to remove a student: ");
					int rmID=input.nextInt();
					try {
						System.out.println(m.remove(rmID).toString()+" has been removed.");
					}
					catch(NullPointerException e1)
					{
						System.out.println("Invalid ID");
						continue;
					}
					break;
				case 3:
					System.out.println("Enter the student ID: ");
					int addID=input.nextInt();
					System.out.print("Enter the student's dorm: ");
					String dorm=input.next();
					System.out.print("Enter the student's Covid Status");
					String status=input.next();
					Student s1=new Student(addID, dorm, status);
					m.put(addID, s1);
					String studentInfo=s1.toString();
					System.out.println(studentInfo+" has been added to the database");
					break;
				case 4:
					m.clear();
					System.out.println("The database has been cleared");
					break;
				case 5:
					System.out.println("Enter the file path name: ");
					String fileIn=input.next();
					File file=new File(fileIn);
					if(file.exists()) {
						try {
							FileReader read=new FileReader(m, file);
						}
						catch(FileNotFoundException e1)
						{
							System.out.println("File is not in directory");
						}
					}
					else {
						System.out.println("Invalid file name");
					}
					break;
			}

		}
		System.out.println("Thanks for checking out the Covid database! Please come again.");
	}
}
