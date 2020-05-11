package userData;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class student extends user {

    private SimpleStringProperty studentId;
    private SimpleIntegerProperty session;
    private SimpleStringProperty course;





    public student(String name,String studentId, String email, String password, int age, String course, int session) {

        super(name, password, email,age);

        this.studentId = new SimpleStringProperty(studentId);
        this.course = new SimpleStringProperty(course);
        this.session = new SimpleIntegerProperty(session);
    }

    public String getStudentId() {
        return studentId.get();
    }

    public SimpleStringProperty studentIdProperty() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId.set(studentId);
    }

    public int getSession() {
        return session.get();
    }

    public SimpleIntegerProperty sessionProperty() {
        return session;
    }

    public void setSession(int session) {
        this.session.set(session);
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
}
