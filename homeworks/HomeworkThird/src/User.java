public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String avatarUrl;

//    public User() {
//        System.out.println("Constructor of User has been worked!");
//    }

    public User(int id, String firstName, String lastName, String email, String avatarUrl) {
//        this();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.avatarUrl = avatarUrl;

        System.out.println("Constructor of User has been worked!");
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        System.out.println("Your firstname has set as " + firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        System.out.println("Your lastname has set as " + lastName);
    }

    public void setEmail(String email) {
        this.email = email;
        System.out.println("Your email has set as " + email);
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        System.out.println("Your avatar's Url has set as " + avatarUrl);
    }
}