package Library;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

public class issuedBook {

    SimpleStringProperty bookId;
    SimpleStringProperty recieverId;
    SimpleStringProperty bookName;
    Date issueDate;
    Date returnDate;

    public issuedBook(String bookId, String recieverId, String bookName, Date issueDate, Date returnDate) {
        this.bookId = new SimpleStringProperty(bookId);
        this.recieverId = new SimpleStringProperty(recieverId);
        this.bookName = new SimpleStringProperty(bookName);
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public String getBookId() {
        return bookId.get();
    }

    public SimpleStringProperty bookIdProperty() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId.set(bookId);
    }

    public String getRecieverId() {
        return recieverId.get();
    }

    public SimpleStringProperty recieverIdProperty() {
        return recieverId;
    }

    public void setRecieverId(String recieverId) {
        this.recieverId.set(recieverId);
    }

    public String getBookName() {
        return bookName.get();
    }

    public SimpleStringProperty bookNameProperty() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName.set(bookName);
    }


    public Date getIssueDate() {

        return issueDate;
    }

    public void setIssueDate(Date issueDate) {

        this.issueDate = issueDate;
    }

    public Date getReturnDate() {

        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


}
