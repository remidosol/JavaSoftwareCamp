import java.util.ArrayList;

public class Instructor extends User {

    private String about;
    private ArrayList<String> givenCourses = new ArrayList<String>();

    public Instructor(int id, String firstName, String lastName, String email, String about, String avatarUrl) {
        super(id, firstName, lastName, email, avatarUrl);
        this.about = about;

        System.out.println("Constructor of Instructor has been worked!");
    }

    public String getAbout() {
        return about;
    }

    public ArrayList<String> getGivenCourses() {
        return givenCourses;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setGivenCourses(ArrayList<String> givenCourses) {
        this.givenCourses = givenCourses;
    }
}
