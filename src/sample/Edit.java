package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import userData.student;
import Connector.connection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Edit {

    public TextField editName;
    public TextField editId;
    public TextField editEmail;
    public TextField editPassword;
    public TextField editAge;
    public TextField editCourse;
    public TextField editSession;
    public Button updateDone;
    public AnchorPane parentPane;



    public void update(String newName, String newId, String newEmail, String newPassword, int newAge, String newCourse, int newSession) {

       editName.setText(newName);
       editId.setText(newId);
       editEmail.setText(newEmail);
       editPassword.setText(newPassword);
       editAge.setText(String.valueOf(newAge));
       editCourse.setText(newCourse);
       editSession.setText(String.valueOf(newSession));


    }

    public void done() throws SQLException {

        connection connection = new connection();
        Connection con = connection.createConnection();

        String sql = "UPDATE student SET name = '"+editName.getText()+"', email = '"+editEmail.getText()+"', password = '"+editPassword.getText()+"'," +
                " age = "+editAge.getText()+", course = '"+editCourse.getText()+"', session = "+editSession.getText()+" WHERE id = '"+editId.getText()+"'";

        Statement stat = con.createStatement();

        stat.execute(sql);


        Alert uodated = new Alert(Alert.AlertType.INFORMATION);
        uodated.setTitle("Info");
        uodated.setContentText("Student's data has been updated successfully!");
        uodated.showAndWait();
        
       

    }

    public void back() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();

        AnchorPane childPane = fxmlLoader.load(getClass().getResource("adminDashboard.fxml"));

        parentPane.getChildren().setAll(childPane);

    }




}
