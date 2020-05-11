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

public class EditTeacher {

    public TextField editName;
    public TextField editId;
    public TextField editEmail;
    public TextField editPassword;
    public TextField editAge;
    public TextField editCourse;
    public Button updateDone;
    public AnchorPane parentPane;

    public void update(String newName, String newId, String newEmail, String newPassword, int newAge, String newCourse) {

        editName.setText(newName);
        editId.setText(newId);
        editEmail.setText(newEmail);
        editPassword.setText(newPassword);
        editAge.setText(String.valueOf(newAge));
        editCourse.setText(newCourse);



    }

    public void done() throws SQLException {

        connection connection = new connection();
        Connection con = connection.createConnection();

        String sql = "UPDATE teacher SET name = '"+editName.getText()+"', email = '"+editEmail.getText()+"', password = '"+editPassword.getText()+"', age = "+editAge.getText()+", course = '"+editCourse.getText()+"' WHERE id = '"+editId.getText()+"'";

        Statement stat = con.createStatement();

        stat.execute(sql);


        Alert updated = new Alert(Alert.AlertType.INFORMATION);
        updated.setTitle("Info");
        updated.setContentText("Teacher's data has been updated successfully!");
        updated.showAndWait();



    }

    public void back() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();

        AnchorPane childPane = fxmlLoader.load(getClass().getResource("adminDashboard.fxml"));

        parentPane.getChildren().setAll(childPane);

    }


}
