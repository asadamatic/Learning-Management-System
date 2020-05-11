package sample;

import javafx.beans.binding.When;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import Connector.connection;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import userData.student;
import userData.teacher;


public class adminDashboard implements Initializable {


    public TableView<student> students;
    public TableColumn<student, String> name;
    public TableColumn<student, String> id;
    public TableColumn<student, String> email;
    public TableColumn<student, String> password;
    public TableColumn<student, Integer> age;
    public TableColumn<student, String> course;
    public TableColumn<student, Integer> session;




    public AnchorPane parentPane;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        course.setCellValueFactory(new PropertyValueFactory<>("course"));
        session.setCellValueFactory(new PropertyValueFactory<>("session"));


        try {

            displayData();

        }catch (SQLException e){

            System.out.println("SQL Exception!");
        }

    }


    public void displayData() throws SQLException {


        students.getItems().clear();


        connection connection = new connection();

        Connection con = connection.createConnection();

        String sql = "select * FROM student";

        Statement stat = con.createStatement();

        ResultSet resultSet = stat.executeQuery(sql);

        while (resultSet.next()){

            String name = resultSet.getString("name");
            String id = resultSet.getString("id");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            int age = resultSet.getInt("age");
            String course = resultSet.getString("course");
            int session = resultSet.getInt("session");

            student student = new student(name, id, email, password, age, course, session);

            students.getItems().add(student);

        }

        stat.close();
        con.close();


    }

    public void addNew() throws IOException{

            FXMLLoader fxmlLoader = new FXMLLoader();

            AnchorPane childPane = fxmlLoader.load(getClass().getResource("addNew.fxml").openStream());
            parentPane.getChildren().setAll(childPane);

    }

    public void editWindow() throws IOException {

        student student = students.getSelectionModel().getSelectedItem();

        if (student == null){

            Alert removed = new Alert(Alert.AlertType.INFORMATION);
            removed.setTitle("Info");
            removed.setContentText("Please select a student first!!");
            removed.showAndWait();

        }
        else {

            String newName = student.getName();
            String newId = student.getStudentId();
            String newEmail = student.getEmail();
            String newPassword = student.getPassword();
            int newAge = student.getAge();
            String newCourse = student.getCourse();
            int newSession = student.getSession();


            FXMLLoader fxmlLoader = new FXMLLoader();

            AnchorPane childPane = fxmlLoader.load(getClass().getResource("edit.fxml").openStream());

            Edit edit = fxmlLoader.getController();
            edit.update(newName, newId, newEmail, newPassword, newAge, newCourse, newSession);

            parentPane.getChildren().setAll(childPane);

        }
    }


    public void delete() throws SQLException{

        student student = students.getSelectionModel().getSelectedItem();

        if (student == null){

            Alert removed = new Alert(Alert.AlertType.INFORMATION);
            removed.setTitle("Info");
            removed.setContentText("Please select a student first!!");
            removed.showAndWait();

        }
        else {

            String id = student.getStudentId();

            connection connection = new connection();
            Connection con = connection.createConnection();

            String sql = "DELETE FROM student WHERE id = '"+id+"'";

            Statement stat = con.createStatement();

            stat.execute(sql);

            sql = "DELETE FROM osmarks WHERE id = '"+id+"'";
            stat.execute(sql);

            sql = "DELETE FROM oopmarks WHERE id = '"+id+"'";
            stat.execute(sql);

            sql = "DELETE FROM dsmarks WHERE id = '"+id+"'";
            stat.execute(sql);

            sql = "DELETE FROM snsmarks WHERE id = '"+id+"'";
            stat.execute(sql);

            sql = "DELETE FROM mnimarks WHERE id = '"+id+"'";
            stat.execute(sql);


            Alert removed = new Alert(Alert.AlertType.INFORMATION);
            removed.setTitle("Info");
            removed.setContentText("Student has been removed successfully!");
            removed.showAndWait();

            displayData();

        }
    }

    public void logout() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();

        AnchorPane childPane = fxmlLoader.load(getClass().getResource("sample.fxml"));

        parentPane.getChildren().setAll(childPane);

    }

    public void displayTeacherTab() {

        nameTeacher.setCellValueFactory(new PropertyValueFactory<>("name"));
        idTeacher.setCellValueFactory(new PropertyValueFactory<>("TeacherId"));
        emailTeacher.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordTeacher.setCellValueFactory(new PropertyValueFactory<>("password"));
        ageTeacher.setCellValueFactory(new PropertyValueFactory<>("age"));
        courseTeacher.setCellValueFactory(new PropertyValueFactory<>("course"));


        try {

            displayTeachersData();
        } catch (SQLException e) {

            System.out.println("SQL Exception!");
        }


    }

    //Teacher's tab

    public TableView<teacher> teachers;
    public TableColumn<teacher, String> nameTeacher;
    public TableColumn<teacher, String> idTeacher;
    public TableColumn<teacher, String> emailTeacher;
    public TableColumn<teacher, String> passwordTeacher;
    public TableColumn<teacher, Integer> ageTeacher;
    public TableColumn<teacher, String> courseTeacher;





    public void displayTeachersData() throws SQLException {


        teachers.getItems().clear();


        connection connection = new connection();

        Connection con = connection.createConnection();

        String sql = "select * FROM teacher";

        Statement stat = con.createStatement();

        ResultSet resultSet = stat.executeQuery(sql);

        while (resultSet.next()){

            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            int age = resultSet.getInt("age");
            String course = resultSet.getString("course");

            teacher teacher = new teacher(name, id, email, password, age, course);

            teachers.getItems().add(teacher);

        }

        stat.close();
        con.close();
    }

    public void addNewTeacher() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();

        AnchorPane childPane = fxmlLoader.load(getClass().getResource("addNewTeacher.fxml"));
        parentPane.getChildren().setAll(childPane);

    }

    public void editTeacherWindow() throws IOException {

        teacher teacher = teachers.getSelectionModel().getSelectedItem();

        if (teacher == null){

            Alert removed = new Alert(Alert.AlertType.INFORMATION);
            removed.setTitle("Info");
            removed.setContentText("Please select a teacher first!!");
            removed.showAndWait();

        }
        else {

            String newName = teacher.getName();
            String newId = teacher.getTeacherId();
            String newEmail = teacher.getEmail();
            String newPassword = teacher.getPassword();
            int newAge = teacher.getAge();
            String newCourse = teacher.getCourse();


            FXMLLoader fxmlLoader = new FXMLLoader();

            AnchorPane childPane = fxmlLoader.load(getClass().getResource("editTeacher.fxml").openStream());

            EditTeacher edit = fxmlLoader.getController();
            edit.update(newName, newId, newEmail, newPassword, newAge, newCourse);

            parentPane.getChildren().setAll(childPane);

        }
    }

    public void deleteTeacher() throws SQLException{

        teacher teacher = teachers.getSelectionModel().getSelectedItem();

        if (teacher == null){


        }
        else {

            String id = teacher.getTeacherId();

            connection connection = new connection();
            Connection con = connection.createConnection();

            String sql = "DELETE FROM teacher WHERE id = '"+id+"'";

            Statement stat = con.createStatement();

            stat.execute(sql);

            Alert removed = new Alert(Alert.AlertType.INFORMATION);
            removed.setTitle("Info");
            removed.setContentText("Teacher has been removed successfully!");
            removed.showAndWait();

            displayTeachersData();

        }
    }

}




