import org.json.JSONArray;
import org.json.JSONObject;

public class EducatorManager {

    public void getAttributesAsJSON(Educator educator) {
        JSONObject obj = new JSONObject();
        obj.put("id", educator.id);
        obj.put("fullName", educator.fullName);
        obj.put("about", educator.about);
        obj.put("avatarUrl", educator.avatarUrl);

        System.out.println(obj.toString(4));;
    }

    public void getWithCourses(Educator educator) {
        JSONObject obj = new JSONObject();
        //obj.put(educator.coursesOfEducator);
        obj.put("id", educator.id);
        obj.put("fullName", educator.fullName);
        obj.put("about", educator.about);
        obj.put("avatarUrl", educator.avatarUrl);

        JSONArray courseArray = new JSONArray();
        for (int i = 0; i < educator.coursesOfEducator.length; i++){
            JSONObject courObj = new JSONObject();
            courObj.put("courseName", educator.coursesOfEducator[i].courseName);
            courObj.put("courseProgram", educator.coursesOfEducator[i].courseProgram);
            courObj.put("shortDescription", educator.coursesOfEducator[i].shortDescription);
            JSONArray sectionArray = new JSONArray();
            for (int j = 0; j < educator.coursesOfEducator[i].sectionsOfCourse.length; j++){
                JSONObject secObj = new JSONObject();
                secObj.put("description", educator.coursesOfEducator[i].sectionsOfCourse[j].description);
                secObj.put("imageUrl", educator.coursesOfEducator[i].sectionsOfCourse[j].imageUrl);
                sectionArray.put(secObj);
            }
            courObj.put("sectionsOfCourse", sectionArray);
            courseArray.put(courObj);
        }

        obj.put("coursesOfEducator", courseArray);

        System.out.println(obj.toString(4));;
    }

    public void setCourses(Educator educator, Course[] courses) {
        educator.coursesOfEducator = courses;
    }
}
