package sample;


import Connector.connection;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.accessibility.AccessibleValue;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class addNew {


    public TextField addname;
    public TextField addid;
    public TextField addemail;
    public TextField addpassword;
    public TextField addage;
    public TextField addcourse;
    public TextField addsession;
    public Button add;
    public AnchorPane parentPane;


    public void addData() throws SQLException {


        String sql = "INSERT INTO student VALUES('"+addname.getText()+"', '"+addid.getText()+"', '"+addemail.getText()+"', '"+addpassword.getText()+"', "+addage.getText()+", '"+addcourse.getText()+"', "+addsession.getText()+")";

        connection connection= new connection();

        Connection con = connection.createConnection();

        Statement stat = con.createStatement();

        stat.execute(sql);

        Alert added = new Alert(Alert.AlertType.INFORMATION);
        added.setTitle("Info");
        added.setContentText("Student has been added successfully!");
        added.showAndWait();


        sql = "INSERT INTO osmarks VALUES('"+addid.getText()+"', 0, 0, 0, 0, 0, 0, 0, 'null')";

        stat.execute(sql);



        sql = "INSERT INTO oopmarks VALUES('"+addid.getText()+"', 0, 0, 0, 0, 0, 0, 0, 'null')";

        stat.execute(sql);



        sql = "INSERT INTO dsmarks VALUES('"+addid.getText()+"', 0, 0, 0, 0, 0, 0, 0, 'null')";

        stat.execute(sql);



        sql = "INSERT INTO snsmarks VALUES('"+addid.getText()+"', 0, 0, 0, 0, 0, 0, 0, 'null')";

        stat.execute(sql);



        sql = "INSERT INTO mnimarks VALUES('"+addid.getText()+"', 0, 0, 0, 0, 0, 0, 0, 'null')";

        stat.execute(sql);



    }


    public void back() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();

        AnchorPane childPane = fxmlLoader.load(getClass().getResource("adminDashboard.fxml"));

        parentPane.getChildren().setAll(childPane);

    }


}
