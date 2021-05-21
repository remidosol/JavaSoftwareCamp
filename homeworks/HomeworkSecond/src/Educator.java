
public class Educator {
    public Educator(int id, String fullName, String about, String avatarUrl) {
        this.id = id;
        this.fullName = fullName;
        this.about = about;
        this.avatarUrl = avatarUrl;
    }

    int id;
    String fullName;
    String about;
    String avatarUrl;
    Course[] coursesOfEducator; // hasMany
}
