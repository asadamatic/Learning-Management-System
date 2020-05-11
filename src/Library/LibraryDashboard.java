package Library;

import Connector.connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import userData.student;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.sql.Date;

public class LibraryDashboard {


    public AnchorPane parentPane;
    public AnchorPane booksTabPane;


    public TableView<book> books;
    public TableColumn<ObservableList<book>, String> bookId;
    public TableColumn<ObservableList<book>, String> bookName;
    public TableColumn<ObservableList<book>, String> bookCategory;


    public TextField inputId;
    public TextField inputName;
    public TextField inputCategory;
    public Button addBook;
    public Button issueBook;


    //Issued books tab

    public  TableView<issuedBook> issuedBooks;
    public TableColumn<ObservableList<issuedBook>, String> issuedBookId;
    public TableColumn<ObservableList<issuedBook>, String> issuedStudentId;
    public TableColumn<ObservableList<issuedBook>, Date> issueDate;

    public  TableView<issuedBook> returnedBooks;
    public TableColumn<ObservableList<issuedBook>, String> returnedBookId;
    public TableColumn<ObservableList<issuedBook>, String> returnedStudentId;
    public TableColumn<ObservableList<issuedBook>, Date>  issueDateReturn;
    public TableColumn<ObservableList<issuedBook>, Date> returnDate;





    public void initialize() {

        bookId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        bookName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        bookCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));


        try {

            displayBooks();
        }catch (Exception e){

            System.out.println("Exception!");
        }


    }

    public void displayBooks() throws SQLException {

        books.getItems().clear();

        connection connection = new connection();
        Connection con = connection.createConnection();


        String sql = "select * FROM library";

        Statement stat = con.createStatement();

        ResultSet resultSet = stat.executeQuery(sql);

        ObservableList bookRack = FXCollections.observableArrayList();

        while (resultSet.next()){

            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String category = resultSet.getString("category");


            book book = new book(id, name, category);

            bookRack.add(book);

        }
        books.setItems(bookRack);

    }

    public void addBook() throws SQLException{

        connection connection = new connection();
        Connection con = connection.createConnection();


        String sql = "INSERT INTO library VALUES('"+inputId.getText()+"', '"+inputName.getText()+"', '"+inputCategory.getText()+"');";

        Statement stat = con.createStatement();

        stat.execute(sql);

        displayBooks();

    }

    public void deleteBook() throws SQLException{

        book selectedBook = books.getSelectionModel().getSelectedItem();

        String id = selectedBook.getId();

        connection connection = new connection();
        Connection con = connection.createConnection();

        String sql = "DELETE FROM library WHERE id = '"+id+"'";

        Statement stat = con.createStatement();

        stat.execute(sql);

        displayBooks();
    }

    public void issueBook() throws IOException {

        book selectedBook = books.getSelectionModel().getSelectedItem();


        if (selectedBook != null) {


            String id = selectedBook.getId();
            String name = selectedBook.getName();

            FXMLLoader fxmlLoader = new FXMLLoader();

            AnchorPane childPane = fxmlLoader.load(getClass().getResource("/Library/selectStudent.fxml").openStream());

            SelectStudent select = fxmlLoader.getController();

            select.bookId.setText(id);
            select.bookName.setText(name);

            parentPane.getChildren().setAll(childPane);

        }else {

            Alert removed = new Alert(Alert.AlertType.INFORMATION);
            removed.setTitle("Info");
            removed.setContentText("Please select a Book first!!");
            removed.showAndWait();
        }

    }

    public void logout() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();

        AnchorPane childPane = fxmlLoader.load(getClass().getResource("/sample/sample.fxml").openStream());

        parentPane.getChildren().setAll(childPane);

    }


    //Issued Books Tab
    public void issuedBooks() throws SQLException {

        issuedBookId.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        issuedStudentId.setCellValueFactory(new PropertyValueFactory<>("RecieverId"));
        issueDate.setCellValueFactory(new PropertyValueFactory<>("IssueDate"));


        displayIsuuedBooks();

    }

    public void displayIsuuedBooks() throws SQLException{

        issuedBooks.getItems().clear();

        connection connection = new connection();
        Connection con = connection.createConnection();


        String sql = "select * FROM issued";

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



    public void ReturnBook() throws SQLException {

        issuedBook selectData = issuedBooks.getSelectionModel().getSelectedItem();

        String book = selectData.getBookId();

        String reciever = selectData.getRecieverId();

        Date issueDate = selectData.getIssueDate();


        connection connection = new connection();

        Connection con = connection.createConnection();

        java.util.Date date=new java.util.Date();

        java.sql.Date returnDate=new java.sql.Date(date.getTime());


        Statement stat =con.createStatement();

        String sql = "SELECT * FROM library WHERE id = '"+book+"' ";

        ResultSet resultSet = stat.executeQuery(sql);

        String name = "book name";

        while (resultSet.next()){
            name = resultSet.getString("name");

        }

        sql = "INSERT INTO returned values('"+book+"', '"+reciever+"', '"+name+"','"+issueDate+"', '"+returnDate+"')";

        stat.execute(sql);

        sql = "DELETE FROM issued WHERE bookid = '"+book+"' and studentid = '"+reciever+"' and issuedate = '"+issueDate+"'";

        stat.execute(sql);

        Alert issued = new Alert(Alert.AlertType.INFORMATION);
        issued.setTitle("Book Allowance Confirmation");
        issued.setContentText("Book return has been confirmed!");
        issued.showAndWait();

        displayIsuuedBooks();
    }

    //Returned Books tab
    public void ReturnedBooks() throws SQLException {

        returnedBookId.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        returnedStudentId.setCellValueFactory(new PropertyValueFactory<>("RecieverId"));
        issueDateReturn.setCellValueFactory(new PropertyValueFactory<>("IssueDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("IssueDate"));


        displayReturnedBooks();

    }

    public void displayReturnedBooks() throws SQLException{

        returnedBooks.getItems().clear();

        connection connection = new connection();
        Connection con = connection.createConnection();


        String sql = "select * FROM returned";

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
