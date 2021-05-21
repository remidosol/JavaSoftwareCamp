
public class Course {
    public Course(String shortDescription, String courseName, String courseProgram, Section[] sectionsOfCourse, Educator educatorOfCourse) {
        this.shortDescription = shortDescription;
        this.courseName = courseName;
        this.courseProgram = courseProgram;
        this.sectionsOfCourse = sectionsOfCourse;
        this.educatorOfCourse = educatorOfCourse;
    }

    String shortDescription;
    String courseName;
    String courseProgram;
    Section[] sectionsOfCourse; // hasMany
    Educator educatorOfCourse; // belongsTo
}


class Section {
    public Section(String description, String imageUrl) {
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String description;
    public String imageUrl;

//    public void getAttributesAsJSON() {
//        JSONObject obj = new JSONObject();
//        obj.put("description", description);
//        obj.put("imageUrl", imageUrl);
//
//        System.out.println(obj.toString(4));;
//    }
}