package sample;

import Connector.connection;
import com.sun.jdi.connect.Connector;
import exams.marks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TeacherDashboard implements Initializable {

    public Label welcome;
    public AnchorPane parentPane;
    public TableView<marks> markSheet;
    public TableColumn<ObservableList<marks>, String> id;
    public TableColumn<ObservableList<marks>, Double> quiz1;
    public TableColumn<ObservableList<marks>, Double> quiz2;
    public TableColumn<ObservableList<marks>, Double> assignment1;
    public TableColumn<ObservableList<marks>, Double> assignment2;
    public TableColumn<ObservableList<marks>, Double> mids;
    public TableColumn<ObservableList<marks>, Double> finals;
    public TableColumn<ObservableList<marks>, Double> total;
    public TableColumn<ObservableList<marks>, String> grade;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        markSheet.setEditable(true);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));


        quiz1.setCellValueFactory(new PropertyValueFactory<>("quiz1"));
        quiz1.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        quiz2.setCellValueFactory(new PropertyValueFactory<>("quiz2"));
        quiz2.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        assignment1.setCellValueFactory(new PropertyValueFactory<>("assignment1"));
        assignment1.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        assignment2.setCellValueFactory(new PropertyValueFactory<>("assignment2"));
        assignment2.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        mids.setCellValueFactory(new PropertyValueFactory<>("mids"));
        mids.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        finals.setCellValueFactory(new PropertyValueFactory<>("finals"));
        finals.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
    }


    public void displayStudentsData() throws SQLException {


        markSheet.getItems().clear();


        connection connection = new connection();

        Connection con = connection.createConnection();

        String sql = "select * FROM "+welcome.getText()+"marks";

        Statement stat = con.createStatement();

        ResultSet resultSet = stat.executeQuery(sql);

        ObservableList<marks> scoreBoard = FXCollections.observableArrayList();

        while (resultSet.next()){

            String id = resultSet.getString("id");
            Double quiz1 = resultSet.getDouble("quiz1");
            Double quiz2 = resultSet.getDouble("quiz2");
            Double assignment1 = resultSet.getDouble("assignment1");
            Double assignment2 = resultSet.getDouble("assignment2");
            Double mids = resultSet.getDouble("mids");
            Double finals = resultSet.getDouble("finals");
            Double total = resultSet.getDouble("total");
            String grade = resultSet.getString("grade");

            marks scores = new marks (id, quiz1, quiz2, assignment1, assignment2, mids, finals, total, grade);

            scoreBoard.add(scores);
        }

        markSheet.setItems(scoreBoard);

        stat.close();
        con.close();


    }


    public void editQuiz1(TableColumn.CellEditEvent editCell){

        marks marksSelected = markSheet.getSelectionModel().getSelectedItem();
        marksSelected.setQuiz1(Double.parseDouble((String.valueOf(editCell.getNewValue()))));

        String selectedID = marksSelected.getId();

        update(selectedID, marksSelected);
    }
    public void editQuiz2(TableColumn.CellEditEvent editCell){

        marks marksSelected = markSheet.getSelectionModel().getSelectedItem();
        marksSelected.setQuiz2(Double.parseDouble(String.valueOf(editCell.getNewValue())));
        String selectedID = marksSelected.getId();

        update(selectedID, marksSelected);
    }
    public void editAssignment1(TableColumn.CellEditEvent editCell){

        marks marksSelected = markSheet.getSelectionModel().getSelectedItem();
        marksSelected.setAssignment1(Double.parseDouble((String.valueOf(editCell.getNewValue()))));
        String selectedID = marksSelected.getId();

        update(selectedID, marksSelected);
    }
    public void editAssignment2(TableColumn.CellEditEvent editCell){

        marks marksSelected = markSheet.getSelectionModel().getSelectedItem();
        marksSelected.setAssignment2(Double.parseDouble((String.valueOf(editCell.getNewValue()))));
        String selectedID = marksSelected.getId();

        update(selectedID, marksSelected);
    }
    public void editMids(TableColumn.CellEditEvent editCell){

        marks marksSelected = markSheet.getSelectionModel().getSelectedItem();
        marksSelected.setMids(Double.parseDouble((String.valueOf(editCell.getNewValue()))));
        String selectedID = marksSelected.getId();
        update(selectedID, marksSelected);
    }
    public void editFinals(TableColumn.CellEditEvent editCell){

        marks marksSelected = markSheet.getSelectionModel().getSelectedItem();
        marksSelected.setFinals(Double.parseDouble((String.valueOf(editCell.getNewValue()))));
        String selectedID = marksSelected.getId();
        update(selectedID, marksSelected);

    }

    public void update(String selectedID, marks marksSelected){



        try {

            connection connection = new connection();

            Connection con = connection.createConnection();

            String sql = "UPDATE "+welcome.getText()+"marks SET quiz1 = "+marksSelected.getQuiz1()+", quiz2 = "+marksSelected.getQuiz2()+", assignment1 = "+marksSelected.getAssignment1()+", assignment2 = "+marksSelected.getAssignment2()+", mids = "+marksSelected.getMids()+", finals = "+marksSelected.getFinals()+", total = ((quiz1 + quiz2)/2 + (assignment1 + assignment2)/2 + mids + finals)  WHERE id = '"+selectedID+"'";

            Statement stat = con.createStatement();

            stat.execute(sql);

            sql = "UPDATE "+welcome.getText()+"marks SET  grade = CASE WHEN total >= 90 AND total <= 100 THEN 'A+' WHEN total >= 80 AND total < 90 THEN 'A' WHEN total >= 70 AND total < 80 THEN 'A-' WHEN total >= 60 AND total < 70 THEN 'B+' WHEN total >= 55 AND total < 60 THEN 'B' WHEN total >= 50 AND total < 55 THEN 'B-' WHEN total >= 45 AND total < 50 THEN 'C+' WHEN total >= 40 AND total < 45 THEN 'C' WHEN total >= 35 AND total < 40 THEN 'C-' WHEN total >= 30 AND total < 35 THEN 'D' WHEN total >= 0  AND total< 30 THEN 'F' ELSE 'null' END WHERE id = '"+selectedID+"';";

            stat.execute(sql);
            displayStudentsData();

        } catch (SQLException e) {

            System.out.println("SQL Exception!");

        }
    }

    public void logout() {


        try {


            FXMLLoader fxmlLoader = new FXMLLoader();



            AnchorPane childPane = fxmlLoader.load(getClass().getResource("sample.fxml"));



            parentPane.getChildren().setAll(childPane);

        }catch (IOException e){

            System.out.println("IO Exception!");
        }

    }


}
