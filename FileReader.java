//--== CS400 File Header Information ==--
//Name: Ryan Szymanski
//Email: rpszymanski@wisc.edu
//Team: BA
//TA: BRIANNA COCHRAN
//
//Lecturer: Florian
//Notes to Grader: 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that creates a new HashTable of student based on an input of a text file that has student
 * data on it
 * 
 * @author rpszy
 *
 */
public class FileReader {
  public FileReader(HashTableMap map, File file) throws FileNotFoundException {

    Scanner sc = new Scanner(file);
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      String[] arrOfStu = line.split(",");
      if (arrOfStu.length > 3 || arrOfStu.length < 2) { // NOTE: typo? Should be ||. 
                                                        //Also, put this BEFORE the previous 3 lines to prevent index out of bounds exception
        continue;
      }
      arrOfStu[0] = arrOfStu[0].trim();
      arrOfStu[1] = arrOfStu[1].trim();
      arrOfStu[2] = arrOfStu[2].trim();

      try {
        int IDNum = Integer.parseInt(arrOfStu[0].trim());
        Student stu = new Student(IDNum, arrOfStu[1], arrOfStu[2]);
        map.put(stu.getID(), stu);
      } catch (NumberFormatException e) {
        continue;
      }


    }

    sc.close();
  }
}
