public class InstructorManager {

    public void addCourse(Instructor user, String courseName) {
        user.getGivenCourses().add(courseName);
        System.out.println(courseName + " has been added to your given courses.");
    }

    public void removeCourse(Instructor user, String courseName) {
        user.getGivenCourses().remove(user.getGivenCourses().indexOf(courseName));
        System.out.println(courseName + " has been removed to your given courses.");
    }

    public void updateAccount(Instructor user, int id, String firstName, String lastName,
                              String email, String avatarUrl, String about) {
        UserManager userManager = new UserManager();
        userManager.updateAccount(user, id, firstName, lastName, email, avatarUrl);
        user.setAbout(about);
        System.out.println(user.getFirstName() + " your instructor account has been updated.");
    }
}
