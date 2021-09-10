// --== CS400 File Header Information ==--
// Name: Elaina Timmerman
// Email: eltimmerman@wisc.edu
// Team: BA
// TA: Bri
// Lecturer: Gary Dahl
// Notes to Grader: none
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CovidTracker extends HashTableMap {

  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);
    HashTableMap map = new HashTableMap(10);
    run(scnr, map);
  }
  public static void run(Scanner scnr, HashTableMap map) {
    System.out.println(
        "Welcome to UW-Madison COVID Tracker!\nCommands:\nF: Insert File\nI: Insert\nG: Get object\nS: Size \nL: Lookup \nR: Remove object\nC: Clear\nQ: Quit");
    String commands = scnr.nextLine().trim().toLowerCase();
    boolean exit = false;
    while (!exit) {
      System.out.print("Enter a command:");
      commands = scnr.nextLine().trim().toLowerCase();
      switch (commands) {
        case "s":
          System.out.println(map.size());
          break;
        case "i": // insert
          System.out.println("Enter student ID:");
          int id1 = scnr.nextInt();
          System.out.println("Enter student location:");
          String location = scnr.next();
          System.out.println("Enter student status:");
          String status = scnr.next();
          Student object = new Student(id1, location, status);
          map.put(id1, object);
          System.out.println("Student is added");
          break;
        case "r": // remove
          System.out.println("Enter student ID:");
          int id = scnr.nextInt();
          try {
            System.out.print(map.remove(id) + "is removed");
          } catch (NullPointerException e) {
            System.out.println("ID is not in map");
            continue;
          }
          break;
        case "f": //file input
          try {
          System.out.println("Type file name:");
          String file = scnr.next();
          File input = new File(file);
          FileReader readFile = new FileReader(map, input);
          }catch (FileNotFoundException e) {
            System.out.println("File cannot be found.");
          }
          break;
        case "m": // menu
          System.out.println(
              "Commands:\nI: Insert\nG: Get object\nS: Size\nF: Insert File\nL: Lookup \nR: Remove object\nC: Clear\nQ: Quit");
          break;
        case "c": // clear
          map.clear();
          System.out.println("Map is cleared");
          break;
        case "g": // get student object
          System.out.println("Enter student ID:");
          int id2 = scnr.nextInt();
          try {
            System.out.print(map.get(id2));
          } catch (NoSuchElementException e) {
            System.out.println("ID is not in map");
            continue;
          }
          break;
        case "l": // lookup
          System.out.println("Enter student ID:");
          int id3 = scnr.nextInt();
          try {
            System.out.println(map.containsKey(id3));
          } catch (NoSuchElementException e) {
            System.out.println("ID is not in map");
            continue;
          }
          break;
        case "q": // quit
          System.out.println("Bye!");
          exit = true;
          break;
      }

    }
    scnr.close();
  }
}
