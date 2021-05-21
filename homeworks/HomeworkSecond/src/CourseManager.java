import org.json.JSONArray;
import org.json.JSONObject;

public class CourseManager {

    public void getAttributesAsJSON(Course course) {
        JSONObject obj = new JSONObject();
        obj.put("shortDescription", course.shortDescription);
        obj.put("courseName", course.courseName);
        obj.put("courseProgram", course.courseProgram);

        JSONArray sectionArray = new JSONArray();
        for (int i = 0; i < course.sectionsOfCourse.length; i++){
            JSONObject secObj = new JSONObject();
            secObj.put("description", course.sectionsOfCourse[i].description);
            secObj.put("imageUrl", course.sectionsOfCourse[i].imageUrl);
            sectionArray.put(secObj);
        }
        obj.put("sectionsOfCourse", sectionArray);

        JSONObject educatorObj = new JSONObject();
        educatorObj.put("id", course.educatorOfCourse.id);
        educatorObj.put("fullName", course.educatorOfCourse.fullName);
        educatorObj.put("about", course.educatorOfCourse.about);
        educatorObj.put("avatarUrl", course.educatorOfCourse.avatarUrl);

        obj.put("educatorOfCourse", educatorObj);

        System.out.println(obj.toString(4));;
    }
}
