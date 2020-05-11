package sample;


import Connector.connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Learning Management System");
        primaryStage.setScene(new Scene(root, 1124, 661));
        primaryStage.show();
    }


    public static void main(String[] args) {


        try {

            connection connection = new connection();
            Connection con = connection.createConnection();

            Statement stat = con.createStatement();

            String sql;

            sql = "CREATE SCHEMA IF NOT EXISTS `lms`";

            stat.execute(sql);


            //Admin table
            sql = "CREATE TABLE IF NOT EXISTS `lms`.`admin` (id VARCHAR(20), password VARCHAR(32))";

            stat.execute(sql);

            //Admin table
            sql = "CREATE TABLE IF NOT EXISTS `lms`.`librarian` (id VARCHAR(20), password VARCHAR(32))";

            stat.execute(sql);

            //Student table
            sql = "CREATE TABLE IF NOT EXISTS `lms`.`library` (id VARCHAR(20), name VARCHAR(255), category VARCHAR(100))";

            stat.execute(sql);

            //Student table
            sql = "CREATE TABLE IF NOT EXISTS `lms`.`student` (name VARCHAR(100), id VARCHAR(20), email VARCHAR(255), password VARCHAR(32), age INT(11), course VARCHAR(50), session INT(11))";

            stat.execute(sql);

            //Subject Tables
            sql = "CREATE TABLE IF NOT EXISTS `lms`.`osmarks` (`id` VARCHAR(16) NOT NULL,`quiz1` DOUBLE NULL,`quiz2` DOUBLE NULL,`assignment1` DOUBLE NULL,`assignment2` DOUBLE NULL,`mids` DOUBLE NULL,`finals` DOUBLE NULL,`total` DOUBLE NULL,`grade` VARCHAR(4))";

            stat.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS `lms`.`oopmarks` (`id` VARCHAR(16) NOT NULL,`quiz1` DOUBLE NULL,`quiz2` DOUBLE NULL,`assignment1` DOUBLE NULL,`assignment2` DOUBLE NULL,`mids` DOUBLE NULL,`finals` DOUBLE NULL,`total` DOUBLE NULL,`grade` VARCHAR(4))";

            stat.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS `lms`.`dsmarks` (`id` VARCHAR(16) NOT NULL,`quiz1` DOUBLE NULL,`quiz2` DOUBLE NULL,`assignment1` DOUBLE NULL,`assignment2` DOUBLE NULL,`mids` DOUBLE NULL,`finals` DOUBLE NULL,`total` DOUBLE NULL,`grade` VARCHAR(4))";

            stat.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS `lms`.`snsmarks` (`id` VARCHAR(16) NOT NULL,`quiz1` DOUBLE NULL,`quiz2` DOUBLE NULL,`assignment1` DOUBLE NULL,`assignment2` DOUBLE NULL,`mids` DOUBLE NULL,`finals` DOUBLE NULL,`total` DOUBLE NULL,`grade` VARCHAR(4))";

            stat.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS `lms`.`mnimarks` (`id` VARCHAR(16) NOT NULL,`quiz1` DOUBLE NULL,`quiz2` DOUBLE NULL,`assignment1` DOUBLE NULL,`assignment2` DOUBLE NULL,`mids` DOUBLE NULL,`finals` DOUBLE NULL,`total` DOUBLE NULL,`grade` VARCHAR(4))";

            stat.execute(sql);


            //Teacher table
            sql = "CREATE TABLE IF NOT EXISTS `lms`.`teacher` (name VARCHAR(100), id VARCHAR(20), email VARCHAR(255), password VARCHAR(32), age INT(11), course VARCHAR(50))";

            stat.execute(sql);


            //Issued Book table
            sql = "CREATE TABLE IF NOT EXISTS `lms`.`issued` (bookid VARCHAR(45), studentid VARCHAR(45), bookname VARCHAR(255), issuedate date, returndate date)";

            stat.execute(sql);

            //Returned Book table
            sql = "CREATE TABLE IF NOT EXISTS `lms`.`returned` (bookid VARCHAR(45), studentid VARCHAR(45), bookname VARCHAR(255), issuedate date, returndate date)";

            stat.execute(sql);

        } catch (SQLException e) {

            System.out.println("SQL Exception!");
        }


        launch(args);
    }
}
