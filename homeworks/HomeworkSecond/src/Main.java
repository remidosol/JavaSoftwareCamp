import com.github.javafaker.*;

public class Main{

    public static void main(String []args){

        Faker faker = new Faker();

        EducatorManager edcManager = new EducatorManager();
        StudentManager stdManager = new StudentManager();
        CourseManager crsManager = new CourseManager();

        // Educator, Course and Section Creation
        Educator[] educators = new Educator[5];
        Course[] courses = new Course[3];
        for (int i = 0; i < 5; i++){
            Educator edc = new Educator(i+1, faker.name().fullName(), faker.lorem().sentence(15), faker.internet().image());
            educators[i] = edc;

            for (int j = 0; j < 3; j++){
                Section[] sections = new Section[5];
                for (int k = 0; k < 5; k++){
                    Section sct = new Section(faker.lorem().sentence(15), faker.internet().image());
                    sections[k] = sct;
                }
                Course crs = new Course(faker.lorem().sentence(5), faker.lorem().sentence(4), faker.lorem().sentence(25), sections, edc);
                courses[j] = crs;
            }
            edcManager.setCourses(edc, courses);
        }

        // Student Creating
        Student[] students = new Student[15];
        for (int x = 0; x < 15; x++){
            Student std = new Student(x+1, faker.name().fullName(), faker.internet().emailAddress(), faker.lorem().sentence(15), faker.internet().image());
            int randCourseCount = faker.random().nextInt(1,3);

            Course[] studentCourses = new Course[randCourseCount];
            for (int y = 0; y < randCourseCount; y++){
                studentCourses[y] = courses[y];
            }

            stdManager.setCourses(std, studentCourses);
            students[x] = std;
        }


        // Printing Educators

        for (Educator edc : educators){
//            edcManager.getAttributesAsJSON(edc);
            edcManager.getWithCourses(edc);
        }

        // Printing Courses

        for (Course crs : courses){
            crsManager.getAttributesAsJSON(crs);
        }

        // Printing Students

        for (Student std : students){
//            stdManager.getAttributesAsJSON(std);
            stdManager.getWithCourses(std);
        }

    }
}