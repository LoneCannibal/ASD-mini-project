package sample;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;

import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

public class SampleController {
    public JFXTextField name_create;
    public JFXTextField username_create;
    public JFXTextField email_create;
    public JFXPasswordField password_create;
    public JFXPasswordField password2_create;



    public void exit(ActionEvent ae)
    {
        System.exit(0);
    }
    public void create_account(ActionEvent ae) throws Exception
    {
        create();
    }
    public void save_to_db(ActionEvent ae)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mini_project","root","password");
            String sql = "INSERT INTO `user` (`name`, `email`, `password`,`username`) VALUES ( ?, ?, ?,?);";
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1,name_create.getText());
            pr.setString(2,email_create.getText());
            pr.setString(3,password_create.getText());
            pr.setString(4,username_create.getText());
            pr.executeUpdate();
        }
        catch(Exception e)
        {

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Account creation unsuccessful");

        }
    }
    public void create()throws Exception
    {
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("create_account.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setTitle("User ID System");
        primaryStage.setScene(new Scene(root, 700, 545));
        primaryStage.show();
    }
}