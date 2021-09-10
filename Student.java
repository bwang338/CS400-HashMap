// --== CS400 File Header Information ==--
// Name: David Wissink
// Email: dwissink@wisc.edu
// Team: BA
// Role: Backend Developer 2
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: None
public class Student{
    private Integer studentID;
    private String location;
    private String status;   

    //Constructor for Student objects, takes as input the Integer student ID,
    //the student's location, and their status
    public Student(Integer sID, String loc, String stat) {
        this.studentID = sID;
        this.location = loc;
        this.status = stat;
    }

    //Default constructor for Student object that takes no arguments, sets ID to 0,
    //and both location and status to NA
    public Student() {
        this.studentID = 0;
        this.location = "NA";
        this.status = "NA";
    }

    //Updates ID field to newID
    public void setID(Integer newID) {
        studentID = newID;
        return;
    }

    //Updates location field to newLoc
    public void setLocation(String newLoc) {
        location = newLoc;
        return;
    }

    //Updates status field to newStat
    public void setStatus(String newStat) {
        status = newStat;
        return;
    }

    //Returns Students ID field
    public Integer getID() {
        return studentID;
    }

    //Returns Students location field
    public String getLocation() {
        return location;
    }

    //Returns Students status field
    public String getStatus() {
        return status;
    }
    
    //Returns all fields of the Student object in a comma separated string
    @Override
    public String toString() {
        return ("ID: " + studentID + ", Location: " + location + ", Status: " + status);
    }
}
