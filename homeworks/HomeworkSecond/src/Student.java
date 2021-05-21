
public class Student {
    public Student(int id, String fullName, String email, String about, String avatarUrl) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.about = about;
        this.avatarUrl = avatarUrl;
    }

    int id;
    String fullName;
    String email;
    String about;
    String avatarUrl;
    Course[] coursesOfStudent; // manyToMany (polymorphic)
}
