package sample;

import Connector.connection;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class addNewTeacher {

    public TextField addname;
    public TextField addid;
    public TextField addemail;
    public TextField addpassword;
    public TextField addage;
    public TextField addcourse;
    public Button add;
    public AnchorPane parentPane;


    public void addData() throws SQLException {


        String sql = "INSERT INTO teacher VALUES('"+addname.getText()+"', '"+addid.getText()+"', '"+addemail.getText()+"', '"+addpassword.getText()+"', "+addage.getText()+", '"+addcourse.getText()+"')";

        connection connection= new connection();

        Connection con = connection.createConnection();

        Statement stat = con.createStatement();

        stat.execute(sql);

        Alert added = new Alert(Alert.AlertType.INFORMATION);
        added.setTitle("Info");
        added.setContentText("Teacher has been added successfully!");
        added.showAndWait();

    }


    public void back() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();

        AnchorPane childPane = fxmlLoader.load(getClass().getResource("adminDashboard.fxml"));

        parentPane.getChildren().setAll(childPane);

    }

}
