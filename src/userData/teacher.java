package userData;

import javafx.beans.property.SimpleStringProperty;

public class teacher extends user{

    SimpleStringProperty course;
    SimpleStringProperty teacherId;

    public teacher(String name, String teacherId, String email, String password, int age, String course) {

        super(name, password, email, age);
        this.course = new SimpleStringProperty(course);
        this.teacherId = new SimpleStringProperty(teacherId);
    }

    public String getCourse() {
        return course.get();
    }

    public SimpleStringProperty courseProperty() {
        return course;
    }

    public void setCourse(String course) {
        this.course.set(course);
    }

    public String getTeacherId() {
        return teacherId.get();
    }

    public SimpleStringProperty teacherIdProperty() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId.set(teacherId);
    }
}
