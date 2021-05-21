import org.json.JSONArray;
import org.json.JSONObject;

public class StudentManager {

    public void getAttributesAsJSON(Student student) {
        JSONObject obj = new JSONObject();
        obj.put("id", student.id);
        obj.put("fullName", student.fullName);
        obj.put("email", student.email);
        obj.put("about", student.about);
        obj.put("avatarUrl", student.avatarUrl);
        obj.put("coursesOfStudent", student.coursesOfStudent);

        System.out.println(obj.toString(4));;
    }

    public void getWithCourses(Student student) {
        JSONObject obj = new JSONObject();

        obj.put("id", student.id);
        obj.put("fullName", student.fullName);
        obj.put("email", student.email);
        obj.put("about", student.about);
        obj.put("avatarUrl", student.avatarUrl);

        JSONArray courseArray = new JSONArray();
        for (int i = 0; i < student.coursesOfStudent.length; i++){
            JSONObject courObj = new JSONObject();
            courObj.put("courseName", student.coursesOfStudent[i].courseName);
            courObj.put("courseProgram", student.coursesOfStudent[i].courseProgram);
            courObj.put("shortDescription", student.coursesOfStudent[i].shortDescription);
            JSONArray sectionArray = new JSONArray();
            for (int j = 0; j < student.coursesOfStudent[i].sectionsOfCourse.length; j++){
                JSONObject secObj = new JSONObject();
                secObj.put("description", student.coursesOfStudent[i].sectionsOfCourse[j].description);
                secObj.put("imageUrl", student.coursesOfStudent[i].sectionsOfCourse[j].imageUrl);
                sectionArray.put(secObj);
            }
            courObj.put("sectionsOfCourse", sectionArray);

            JSONObject educatorObj = new JSONObject();
            educatorObj.put("id", student.coursesOfStudent[i].educatorOfCourse.id);
            educatorObj.put("fullName", student.coursesOfStudent[i].educatorOfCourse.fullName);
            educatorObj.put("about", student.coursesOfStudent[i].educatorOfCourse.about);
            educatorObj.put("avatarUrl", student.coursesOfStudent[i].educatorOfCourse.avatarUrl);

            courObj.put("educatorOfCourse", educatorObj);
            courseArray.put(courObj);
        }

        obj.put("coursesOfStudent", courseArray);

        System.out.println(obj.toString(4));;
    }

    public void setCourses(Student student, Course[] courses) {
        student.coursesOfStudent = courses;
    }
}
