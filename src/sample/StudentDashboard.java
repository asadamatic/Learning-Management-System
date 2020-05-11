package sample;

import Connector.connection;
import Library.issuedBook;
import com.mysql.cj.result.BinaryStreamValueFactory;
import exams.marks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class StudentDashboard implements Initializable {

    public AnchorPane parentPane;
    public Button showOS;
    public Button showOOP;
    public Button showDS;
    public Button showSNS;
    public Button showMNI;
    public Label welcome;
    public Label subject;
    public ImageView userImage;

    public TableView<marks> marksTable;
    public TableColumn<marks, Double> quiz1;
    public TableColumn<marks, Double> quiz2;
    public TableColumn<marks, Double> assignment1;
    public TableColumn<marks, Double> assignment2;
    public TableColumn<marks, Double> mids;
    public TableColumn<marks, Double> finals;
    public TableColumn<marks, Double> total;
    public TableColumn<marks, String> grade;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        quiz1.setCellValueFactory(new PropertyValueFactory<>("quiz1"));
        quiz2.setCellValueFactory(new PropertyValueFactory<>("quiz2"));
        assignment1.setCellValueFactory(new PropertyValueFactory<>("assignment1"));
        assignment2.setCellValueFactory(new PropertyValueFactory<>("assignment2"));
        mids.setCellValueFactory(new PropertyValueFactory<>("mids"));
        finals.setCellValueFactory(new PropertyValueFactory<>("finals"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
    }


    public void OS() {


        subject.setText("Operating System");

        marksTable.getItems().clear();

        display("osmarks");
    }

    public void OOP() {

            subject.setText("Object Oriented Programming");

            marksTable.getItems().clear();


            display("oopmarks");
    }

    public void DS() {


        subject.setText("Discrete Structures");

        marksTable.getItems().clear();

        display("dsmarks");
    }

    public void SNS() {

        subject.setText("Signals & Systems");

        marksTable.getItems().clear();

        display("snsmarks");
    }


        public void MNI () {

            subject.setText("Microprocessors & Interfacing");

            marksTable.getItems().clear();

            display("mnimarks");

        }

        public void display (String subjectName){

            connection connection = new connection();


            try {

                Connection con = connection.createConnection();


                String sql = "select * FROM "+subjectName+" WHERE id = '"+welcome.getText()+"'";


                Statement stat = con.createStatement();


                ResultSet resultSet = stat.executeQuery(sql);


                while (resultSet.next()) {

                    String id = resultSet.getString("id");

                    Double q1 = resultSet.getDouble("quiz1");

                    Double q2 = resultSet.getDouble("quiz2");

                    Double a1 = resultSet.getDouble("assignment1");

                    Double a2 = resultSet.getDouble("assignment2");

                    Double mids = resultSet.getDouble("mids");

                    Double finals = resultSet.getDouble("finals");

                    Double total = resultSet.getDouble("total");

                    String grade = resultSet.getString("grade");

                    marks markSheet = new marks(id, q1, q2, a1, a2, mids, finals, total, grade);


                    marksTable.getItems().add(markSheet);


                }

            } catch (SQLException e) {

                System.out.println("SQL Exception!");

            }

        }

    public void logout() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();

        AnchorPane childPane = fxmlLoader.load(getClass().getResource("sample.fxml"));

        parentPane.getChildren().setAll(childPane);

    }


    //Library Section

    //Issued books tab

    public  TableView<issuedBook> issuedBooks;
    public TableColumn<ObservableList<issuedBook>, String> issuedBookId;
    public TableColumn<ObservableList<issuedBook>, String> issuedBookName;
    public TableColumn<ObservableList<issuedBook>, Date> issuedBookDate;


   public void IssuedBooks() throws SQLException {

       issuedBookId.setCellValueFactory(new PropertyValueFactory<>("BookId"));
       issuedBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
       issuedBookDate.setCellValueFactory(new PropertyValueFactory<>("IssueDate"));

       displayIsuuedBooks();
   }


    public void displayIsuuedBooks() throws SQLException{

        issuedBooks.getItems().clear();

        connection connection = new connection();
        Connection con = connection.createConnection();


        String sql = "select * FROM issued WHERE studentid = '"+welcome.getText()+"'";

        Statement stat = con.createStatement();

        ResultSet resultSet = stat.executeQuery(sql);

        ObservableList bookRack = FXCollections.observableArrayList();

        while (resultSet.next()){

            String bookid = resultSet.getString("bookid");
            String studentid = resultSet.getString("studentid");
            String bookName = resultSet.getString("bookname");
            Date issuedate = resultSet.getDate("issuedate");
            Date returndate = resultSet.getDate("returndate");



            issuedBook books = new issuedBook(bookid, studentid, bookName, issuedate, returndate);

            bookRack.add(books);

        }
        issuedBooks.setItems(bookRack);

    }


    //Returned Books Tab

    public  TableView<issuedBook> returnedBooks;
    public TableColumn<ObservableList<issuedBook>, String> returnedBookId;
    public TableColumn<ObservableList<issuedBook>, String> returnedBookName;
    public TableColumn<ObservableList<issuedBook>, Date> returnedIssueDate;
    public TableColumn<ObservableList<issuedBook>, Date> returnedBookDate;

    //Returned Books tab
    public void ReturnedBooks() throws SQLException {

        returnedBookId.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        returnedBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        returnedIssueDate.setCellValueFactory(new PropertyValueFactory<>("IssueDate"));
        returnedBookDate.setCellValueFactory(new PropertyValueFactory<>("IssueDate"));


        displayReturnedBooks();

    }



    public void displayReturnedBooks() throws SQLException{

        returnedBooks.getItems().clear();

        connection connection = new connection();
        Connection con = connection.createConnection();


        String sql = "select * FROM returned WHERE studentId = '"+welcome.getText()+"'";

        Statement stat = con.createStatement();

        ResultSet resultSet = stat.executeQuery(sql);

        ObservableList bookRack = FXCollections.observableArrayList();

        while (resultSet.next()){

            String bookid = resultSet.getString("bookid");
            String studentid = resultSet.getString("studentid");
            String bookName = resultSet.getString("bookname");
            Date issuedate = resultSet.getDate("issuedate");
            Date returndate = resultSet.getDate("returndate");


            issuedBook books = new issuedBook(bookid, studentid,bookName, issuedate, returndate);

            bookRack.add(books);

        }
        returnedBooks.setItems(bookRack);

    }




}
