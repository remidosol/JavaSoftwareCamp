import java.util.ArrayList;

public class Student extends User {

    private ArrayList<String> takenCourses = new ArrayList<String>();

    public Student(int id, String firstName, String lastName, String email, String avatarUrl) {
        super(id, firstName, lastName, email, avatarUrl);
        System.out.println("Constructor of Student has been worked!");
    }

    public ArrayList<String> getTakenCourses() {
        return takenCourses;
    }

    public void setTakenCourses(ArrayList<String> takenCourses) {
        this.takenCourses = takenCourses;
    }
}
