
//--== CS400 File Header Information ==--
//Name: Ryan Szymanski
//Email: rpszymanski@wisc.edu
//Team: BA
//TA: BRIANNA COCHRAN
//
//Lecturer: Florian
//Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
/**
* 
* @author rpszy
*
* @param <KeyType>
* @param <ValueType>
*/
public class HashTableMap  implements MapADT{
  private LinkedList<Student>[] array;
  private int size;
  private int length;
  
  /**
   * An argument constructor that makes an initial array of size capacity
   * 
   * @param capacity
   */
  public HashTableMap(int capacity) {
    this.array = new LinkedList[capacity];
  }

  /**
   * A no argument constructor that makes an initial array of size 10
   */
  public HashTableMap() {// with default capacity = 10
    this.array = new LinkedList[10];
  }
  // This is the fileRader class that reads through a file and makes a Student Object out of it
  // The method assumes that the first slot is for the student id, second slot is the residence
  // and the third is the test results
  // The method also assumes the file info is split by commas
 public void fileReader(File file) throws FileNotFoundException {
  
   Scanner sc = new Scanner(file);
   while(sc.hasNextLine()) {
     String line = sc.nextLine();
    String[] arrOfStu = line.split(",");
    arrOfStu[0] = arrOfStu[0].trim();
    arrOfStu[1] = arrOfStu[1].trim();
    arrOfStu[2] = arrOfStu[2].trim();
    if(arrOfStu.length > 3 && arrOfStu.length<2) {
      continue;
    }
    try {
      int IDNum = Integer.parseInt(arrOfStu[0].trim());
      Student stu = new Student(IDNum,arrOfStu[1],arrOfStu[2]);
      put(stu.getID(), stu);
    }
    catch (NumberFormatException e){
      continue;
    }
    
    
   }
   
   sc.close();
 }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return this.size;
  }
  
  public int length() {
    
    return this.length;
  }
  
  @Override
  public boolean containsKey(Integer key) {
    int index = Math.abs(key.hashCode() % array.length);
    if(array[index] == null) {
      return false;
    }
    for (int i = 0; i < array[index].size(); i++) {
      if (array[index].get(i).getID().equals(key)) {
        return true;
      }
      }
    
    return false;
  }

 

  @Override
  public void clear() {
    for (int i = 0; i < array.length; i++) {
      array[i] = null;
      size = 0;
    }
  }

  @Override
  public boolean put(Integer key, Student value) {
    if (((double) (size + 1) / (double) array.length) >= .8) {
      array =  resize(array);

}
length = array.length;
//KeyValueTypes o = new KeyValueTypes(key, value);
int index = Math.abs(key.hashCode() % array.length);

if (array[index] == null) {
array[index] = new LinkedList();

for (int i = 0; i < array[index].size(); i++) {
if (array[index].get(i).getID().equals(key)) {
  return false;
}

}
array[index].add(value);
this.size++;
return true;
} else {
array[index].add(value);
this.size++;
return true;
}

}

  

  @Override
  public String get(Integer key) throws NoSuchElementException {
    int index = key.hashCode() % array.length;
    if (array[index] == null) {
      throw new NoSuchElementException();
    }
    for (int i = 0; i < array[index].size(); i++) {
      if (array[index].get(i).getID().equals(key)) {
        return  array[index].get(i).getName();
      }

    }
    throw new NoSuchElementException();
  
  }

  @Override
  public String remove(Integer key) {
    int index = key.hashCode() % array.length;

    if (array[index] == null) {
      return null;
    }
    for (int i = 0; i < array[index].size(); i++) {
      if (array[index].get(i).getID().equals(key)) {
       
        String value =  array[index].get(i).getName();
        array[index].remove(i);
        this.size--;
        return value;

      }

    }
    return null;
  }
  private LinkedList[] resize(LinkedList[] array) {
    LinkedList<Student>[] newArray = new LinkedList[array.length*2];
 for(int i = 0; i< array.length; i++) {
   if(array[i] == null) {
     continue;
   }
 for(int j = 0; j< array[i].size(); j ++) {
   Student o = (Student) array[i].get(j);
   int index = Math.abs(o.getID().hashCode() % newArray.length);
   if (newArray[index] == null) {
     newArray[index] = new LinkedList();
  }
   newArray[index].add(o);
   
 }
   
 }
     return newArray;
  }
}
