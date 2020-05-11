package exams;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class marks {

    SimpleStringProperty id;
    SimpleDoubleProperty quiz1;
    SimpleDoubleProperty quiz2;
    SimpleDoubleProperty assignment1;
    SimpleDoubleProperty assignment2;
    SimpleDoubleProperty mids;
    SimpleDoubleProperty finals;
    SimpleDoubleProperty total;
    SimpleStringProperty grade;

    public marks(String id, Double quiz1, Double quiz2, Double assignment1, Double assignment2, Double mids, Double finals, Double total, String grade) {

        this.id = new SimpleStringProperty(id);
        this.quiz1 = new SimpleDoubleProperty(quiz1);
        this.quiz2 = new SimpleDoubleProperty(quiz2);
        this.assignment1 = new SimpleDoubleProperty(assignment1);
        this.assignment2 = new SimpleDoubleProperty(assignment2);
        this.mids = new SimpleDoubleProperty(mids);
        this.finals = new SimpleDoubleProperty(finals);
        this.total = new SimpleDoubleProperty(total);
        this.grade = new SimpleStringProperty(grade);

    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public double getQuiz1() {

        return quiz1.get();
    }

    public SimpleDoubleProperty quiz1Property() {

        return quiz1;
    }

    public void setQuiz1(double quiz1) {

        this.quiz1.set(quiz1);
    }

    public double getQuiz2()
    {
        return quiz2.get();
    }

    public SimpleDoubleProperty quiz2Property()
    {
        return quiz2;
    }

    public void setQuiz2(double quiz2)
    {
        this.quiz2.set(quiz2);
    }

    public double getAssignment1() {

        return assignment1.get();
    }

    public SimpleDoubleProperty assignment1Property() {

        return assignment1;
    }

    public void setAssignment1(double assignment1) {

        this.assignment1.set(assignment1);
    }

    public double getAssignment2()
    {
        return assignment2.get();
    }

    public SimpleDoubleProperty assignment2Property() {

        return assignment2;
    }

    public void setAssignment2(double assignment2)
    {
        this.assignment2.set(assignment2);
    }

    public double getMids()
    {
        return mids.get();
    }

    public SimpleDoubleProperty midsProperty() {

        return mids;
    }

    public void setMids(double mids) {

        this.mids.set(mids);
    }

    public double getFinals() {

        return finals.get();
    }

    public SimpleDoubleProperty finalsProperty()
    {
        return finals;
    }

    public void setFinals(double finals) {
        this.finals.set(finals);
    }

    public double getTotal() {
        return total.get();
    }

    public SimpleDoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    public String getGrade() {
        return grade.get();
    }

    public SimpleStringProperty gradeProperty() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade.set(grade);
    }
}
