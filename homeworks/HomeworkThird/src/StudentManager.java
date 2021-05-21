public class StudentManager {

    public void addCourse(Student user, String courseName) {
        user.getTakenCourses().add(courseName);
        System.out.println(courseName + " has been added to your taken courses.");
    }

    public void removeCourse(Student user, String courseName) {
        user.getTakenCourses().remove(user.getTakenCourses().indexOf(courseName));
        System.out.println(courseName + " has been removed to your taken courses.");
    }

    public void updateAccount(Student user, int id, String firstName, String lastName,
                              String email, String avatarUrl) {
        UserManager userManager = new UserManager();
        userManager.updateAccount(user, id, firstName, lastName, email, avatarUrl);
        System.out.println(user.getFirstName() + " your student account has been updated.");
    }
}
