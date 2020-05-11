package sample;

import Connector.connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {


    public TextField usernamelogin;
    public PasswordField passlogin;
    public Label loginlabel;
    public AnchorPane parentPane;
    public ChoiceBox userChoice;

    ObservableList<String> userChoices = FXCollections.observableArrayList("admin", "teacher", "student", "librarian");

    public void initialize(){

        userChoice.setValue("admin");
        userChoice.setItems(userChoices);

    }


    public void login() throws SQLException {

        String sql = "select * FROM "+userChoice.getValue()+" WHERE id = '"+usernamelogin.getText()+"' and password = '"+passlogin.getText()+"'";

        connection connection = new connection();

        Connection con = connection.createConnection();



        Statement stat = con.createStatement();

        ResultSet resultSet = stat.executeQuery(sql);

        if (resultSet.next()){


            if (userChoice.getValue() == "admin") {

                adminDashboard();
            }else if (userChoice.getValue() == "teacher"){

                String course = resultSet.getString("course");

                try {

                    FXMLLoader fxmlLoader = new FXMLLoader();

                    AnchorPane childPane = fxmlLoader.load(getClass().getResource("teacherDashboard.fxml").openStream());

                    TeacherDashboard td = fxmlLoader.getController();

                    td.welcome.setText(course);

                    parentPane.getChildren().setAll(childPane);

                }catch (IOException e){

                    System.out.println("IO Exception!");
                }


            }else if (userChoice.getValue() == "student"){

                studentDashboard();
            }else if (userChoice.getValue() == "librarian"){

                libraryDashboard();
            }

        }
        else {

            loginlabel.setText("Wrong Credentials!");
        }

    }

    public void adminDashboard(){


        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            AnchorPane childPane = fxmlLoader.load(getClass().getResource("adminDashboard.fxml"));

            parentPane.getChildren().setAll(childPane);

        }catch (IOException e){

            System.out.println("IO Exception!");
        }


    }


    public void teacherDashboard(){

    }

    public void studentDashboard(){


        String name = String.valueOf(usernamelogin.getText());
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            AnchorPane childPane = fxmlLoader.load(getClass().getResource("studentDashboard.fxml").openStream());

            StudentDashboard sd = fxmlLoader.getController();

            sd.welcome.setText(name);

            parentPane.getChildren().setAll(childPane);


        }catch (IOException e){

            System.out.println("IO Exception!");
        }


    }

    public void libraryDashboard(){


        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            AnchorPane childPane = fxmlLoader.load(getClass().getResource("/Library/libraryDashboard.fxml").openStream());

            parentPane.getChildren().setAll(childPane);

        }catch (IOException e){

            System.out.println("IO Exception1!");
        }


    }

}
