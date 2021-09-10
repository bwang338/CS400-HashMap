import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Tests the Covid Application
 * 
 * @author mkornely
 *
 */
public class CovidTest {

  public static void main(String[] args) {
    System.out.println(testStudent());
    System.out.println(testStudentHash());
    System.out.println(testCovidTracker());

  }

  /**
   * Tests the Student class
   * 
   * @returns boolean if it passes the tests
   *
   */

  public static boolean testStudent() {
    Student test1;
    Student test2;
    Student test3;

    try {

      test1 = new Student();
      test2 = new Student(123, "Witte", "Positive");
      test3 = new Student(456, "Smith", "Negative");


    } catch (Exception ex) {
      return false;
    }

    if (test1.getID() == 0)
      if (test1.getLocation() == "NA")
        if (test1.getStatus() == "NA")
          test1.setID(909);
    test1.setLocation("Sellery");
    test1.setStatus("Negative");

    if (test1.getID() == 909)
      if (test1.getLocation().equals("Sellery"))
        if (test1.getStatus().equals("Negative"))

          if (test2.getID() == 123)
            if (test2.getLocation() == "Witte")
              if (test2.getStatus() == "Positive")
                if (test3.getID() == 456)
                  if (test3.getLocation() == "Smith")
                    if (test3.getStatus() == "Negative")
                      return true;
    return false;



  }

  /**
   * Tests the Student class in the hashmap
   * 
   * @returns boolean if it passes the tests
   *
   */
  public static boolean testStudentHash() {
    if (testStudent()) {
      Student test1;
      Student test2;
      Student test3;
      Student test4;
      Student test5;



      test1 = new Student();
      test2 = new Student(123, "Witte", "Positive");
      test3 = new Student(456, "Smith", "Negative");
      test4 = new Student(789, "Sellery", "Positive");
      test5 = new Student(101, "Off Campus", "Negative");


      HashTableMap<Integer, Student> m = new HashTableMap<>();


      if (m.put(test1.getID(), test1))
        if (m.put(test2.getID(), test2))
          if (m.put(test3.getID(), test3))
            if (m.put(test4.getID(), test4))
              if (m.put(test5.getID(), test5))



                try {
                  if (m.get(test1.getID()) != test1)
                    return false;
                  if (m.get(test2.getID()) != test2)
                    return false;
                  if (m.get(test3.getID()) != test3)
                    return false;
                  if (m.get(test4.getID()) != test4)
                    return false;
                  if (m.get(test5.getID()) != test5)
                    return false;

                } catch (NoSuchElementException e) {
                  return false;
                }


      try {
        m.get(5);
      } catch (NoSuchElementException e) {
        return true;

      }
    }
    return false;

  }


  /**
   * Tests functionality of FileReader()
   * 
   * @return true if data from provided .txt file is correctly transformed into Student objects and
   *         properly added to Hash Table; false otherwise
   */
  public static boolean testFileReader() {

    HashTableMap<Integer, Student> table = new HashTableMap<>();
    File inputs = new File("FileReaderTestInputs.txt");

    String[] arr = {"NA", "A", "B", "C", "X", "Y", "Z"};

    try {
      FileReader reader = new FileReader(table, inputs);

    } catch (FileNotFoundException e) {
      return false;
    }

    if (table.size() != arr.length)
      return false;

    for (int i = 0; i < arr.length; i++) {
      try {
        if (!table.remove(i).getLocation().contentEquals(arr[i]))
          return false;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    if (table.size() != 0)
      return false;

    return true;
  }

  /**
   * Tests the CovidTracker class
   * 
   * @returns boolean if it passes the tests
   *
   */
  public static boolean testCovidTracker() {

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

      CovidTracker.run(sc, t);

      System.setOut(old);
      System.out.flush();

      if (t.size() != 4)
        return false;
      if (!t.get(101).getLocation().contentEquals("A")
          || !t.get(102).getLocation().contentEquals("B")
          || !t.get(103).getLocation().contentEquals("C")
          || !t.get(104).getLocation().contentEquals("D"))
        return false;


    }

    { // remove students 102 and 104
      driverOutput = new ByteArrayOutputStream();
      p = new PrintStream(driverOutput);
      System.setOut(p);

      CovidTracker.run(sc, t);

      System.setOut(old);
      System.out.flush();

      if (t.size() != 2)
        return false;
      try {
        t.get(102);
        t.get(104);
        return false;
      } catch (NoSuchElementException e) {
      }


    }

    { // clear table
      driverOutput = new ByteArrayOutputStream();
      p = new PrintStream(driverOutput);
      System.setOut(p);

      CovidTracker.run(sc, t);

      System.setOut(old);
      System.out.flush();

      if (t.size() != 0)
        return false;

    }

    { // add 9 students from file
      driverOutput = new ByteArrayOutputStream();
      p = new PrintStream(driverOutput);
      System.setOut(p);

      CovidTracker.run(sc, t);

      System.setOut(old);
      System.out.flush();

      String[] arr = {"NA", "A", "B", "C", "X", "Y", "Z"};

      if (t.size() != arr.length)
        return false;

      for (int i = 0; i < arr.length; i++) {
        try {
          if (!t.remove(i).getLocation().contentEquals(arr[i]))
            return false;
        } catch (NoSuchElementException e) {
          return false;
        }
      }

      if (t.size() != 0)
        return false;

    }

    { // erroneous input
      driverOutput = new ByteArrayOutputStream();
      p = new PrintStream(driverOutput);
      System.setOut(p);

      try {
        CovidTracker.run(sc, t);
      } catch (Exception e) {
        return false;
      }

      System.setOut(old);
      System.out.flush();


    }

    sc.close();
    return true;
  }



}
