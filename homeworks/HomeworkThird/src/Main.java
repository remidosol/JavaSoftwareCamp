public class Main {

    public static void main(String[] args) {


        System.out.println("------------------------------------------");
        System.out.println("********************************************************************");
        System.out.println("------------------------------------------");

        User newUser = new User(1, "John", "Doe",
                "john@doe.com",  "https://picsum.photos/200/300");

        System.out.println(newUser.getFullName());
        System.out.println(newUser.getFirstName());
        System.out.println(newUser.getLastName());
        System.out.println(newUser.getEmail());
        System.out.println(newUser.getId());
        System.out.println(newUser.getAvatarUrl());

        System.out.println("------------------------------------------");

        UserManager um = new UserManager();
        um.updateAccount(newUser, 2, "Mary", "Jane",
                "mary@jane.com", "https://picsum.photos/200/300");

        System.out.println(newUser.getFullName());

        um.deleteAccount(newUser);

        System.out.println("------------------------------------------");
        System.out.println("********************************************************************");
        System.out.println("------------------------------------------");

        Instructor newInstructor = new Instructor(3, "John", "Wick",
                "john@wick.com", "Hello world!", "https://picsum.photos/200/300");


        System.out.println(newInstructor.getFullName());
        System.out.println(newInstructor.getFirstName());
        System.out.println(newInstructor.getLastName());
        System.out.println(newInstructor.getEmail());
        System.out.println(newInstructor.getId());
        System.out.println(newInstructor.getAvatarUrl());

        System.out.println("------------------------------------------");

        InstructorManager im = new InstructorManager();
        im.updateAccount(newInstructor, 4, "Mary", "Mag",
                "mary@mag.com","Goodbye world!", "https://picsum.photos/200/300");

        System.out.println(newInstructor.getFullName());
        System.out.println(newInstructor.getFirstName());
        System.out.println(newInstructor.getLastName());
        System.out.println(newInstructor.getEmail());
        System.out.println(newInstructor.getId());
        System.out.println(newInstructor.getAvatarUrl());

        System.out.println("------------------------------------------");

        im.addCourse(newInstructor, "Javascript");
        im.addCourse(newInstructor, "Typescript");

        System.out.println(newInstructor.getGivenCourses());

        im.removeCourse(newInstructor, "Javascript");
        System.out.println(newInstructor.getGivenCourses());

        System.out.println("------------------------------------------");
        System.out.println("********************************************************************");
        System.out.println("------------------------------------------");

        Student newStudent = new Student(5, "Harry", "Potter",
                "john@wick.com", "https://picsum.photos/200/300");


        System.out.println(newStudent.getFullName());
        System.out.println(newStudent.getFirstName());
        System.out.println(newStudent.getLastName());
        System.out.println(newStudent.getEmail());
        System.out.println(newStudent.getId());
        System.out.println(newStudent.getAvatarUrl());

        System.out.println("------------------------------------------");

        StudentManager sm = new StudentManager();
        sm.updateAccount(newStudent, 6, "Hermonie", "Granger",
                "mary@mag.com", "https://picsum.photos/200/300");

        System.out.println(newStudent.getFullName());
        System.out.println(newStudent.getFirstName());
        System.out.println(newStudent.getLastName());
        System.out.println(newStudent.getEmail());
        System.out.println(newStudent.getId());
        System.out.println(newStudent.getAvatarUrl());

        System.out.println("------------------------------------------");

        sm.addCourse(newStudent, "React");
        sm.addCourse(newStudent, "React Native");

        System.out.println(newStudent.getTakenCourses());

        sm.removeCourse(newStudent, "React");
        System.out.println(newStudent.getTakenCourses());

        System.out.println("------------------------------------------");
        System.out.println("********************************************************************");
        System.out.println("------------------------------------------");
    }

}