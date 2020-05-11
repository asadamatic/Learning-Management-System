package Library;

import Connector.connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import userData.student;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import java.time.LocalDate;

public class SelectStudent implements Initializable {

    public TextField bookId;
    public TextField bookName;
    public TextField recieverID;

    public TableView<student> students;
    public TableColumn<student, String> studentName;
    public TableColumn<student, String> studentId;
    public TableColumn<student, String> studentEmail;
    public TableColumn<student, String> studentCourse;

    public AnchorPane parentPane;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        studentCourse.setCellValueFactory(new PropertyValueFactory<>("course"));


        try {

            displayStudents();

        }catch (SQLException e){

            System.out.println("SQL Exception!");

        }
    }

    public void displayStudents() throws SQLException {


        students.getItems().clear();


        connection connection = new connection();

        Connection con = connection.createConnection();

        String sql = "select * FROM student";

        Statement stat = con.createStatement();

        ResultSet resultSet = stat.executeQuery(sql);

        ObservableList student = FXCollections.observableArrayList();

        while (resultSet.next()){

            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String course = resultSet.getString("course");
            String password = resultSet.getString("password");
            int age = resultSet.getInt("age");
            int session = resultSet.getInt("session");

            student studentList = new student(name, id, email, password, age, course, session);

            student.add(studentList);

        }
        students.setItems(student);

        stat.close();
        con.close();

    }
    public void back() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();

        AnchorPane childPane = fxmlLoader.load(getClass().getResource("libraryDashboard.fxml"));

        parentPane.getChildren().setAll(childPane);

    }


    public void selectReciever(){


        student selectStudent = students.getSelectionModel().getSelectedItem();

        String reciever = selectStudent.getStudentId();

        recieverID.setText(reciever);

    }

    public void displayIssued() throws SQLException {

        selectReciever();

        connection connection = new connection();

        Connection con = connection.createConnection();

        java.util.Date date=new java.util.Date();

        java.sql.Date issueDate=new java.sql.Date(date.getTime());


        Statement stat =con.createStatement();

        String sql = "INSERT INTO issued values('"+bookId.getText()+"', '"+recieverID.getText()+"', '"+bookName.getText()+"','"+issueDate+"', "+null+")";

        stat.execute(sql);

        Alert issued = new Alert(Alert.AlertType.INFORMATION);
        issued.setTitle("Book Allowance Confirmation");
        issued.setContentText("Book has been allotted  to the selected student!");
        issued.showAndWait();
    }


}
