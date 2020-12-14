package sample;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;

import javafx.scene.control.*;

import java.awt.event.ItemEvent;
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
    public JFXTextField username;
    public JFXPasswordField password;
    public Label wrong_password;


    public void exit(ActionEvent ae)
    {
        System.exit(0);
    }
    public void create_account(ActionEvent ae) throws Exception
    {
        create();
    }

    public void login(ActionEvent ae)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mini_project", "root", "password");
            Statement stmt = conn.createStatement();
            String sql = "SELECT `password` FROM `user` WHERE `password` LIKE ";
            sql=sql+"'"+ password.getText()+"' AND `username` LIKE '"+username.getText()+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()==false)
            {
                password.setText("");
                wrong_password.setText("Wrong  username or password");
            }
            else
            {
                Stage primaryStage=new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("user_details.fxml"));
                primaryStage.initStyle(StageStyle.UTILITY);
                primaryStage.setTitle("User details");
                primaryStage.setScene(new Scene(root, 700, 320));
                primaryStage.show();
            }

        }
        catch(Exception e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("ERROR");
            errorAlert.setContentText("DB Access error occurred "+e);
            errorAlert.showAndWait();
        }
    }
    public void save_to_db(ActionEvent ae)
    {
        if(password_create!=password2_create)
        {
            password2_create.setText("");
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("ERROR");
            errorAlert.setContentText("Password mismatch");
            errorAlert.showAndWait();
        }
        else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mini_project", "root", "password");
                String sql = "INSERT INTO `user` (`name`, `email`, `password`,`username`) VALUES ( ?, ?, ?,?);";
                PreparedStatement pr = conn.prepareStatement(sql);
                pr.setString(1, name_create.getText());
                pr.setString(2, email_create.getText());
                pr.setString(3, password_create.getText());
                pr.setString(4, username_create.getText());
                pr.executeUpdate();
            } catch (Exception e) {

                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("ERROR");
                errorAlert.setContentText("Account creation unsuccessful "+e);
                errorAlert.showAndWait();

            }
        }
    }

    public void create()throws Exception
    {
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("create_account.fxml"));
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle("Create Account");
        primaryStage.setScene(new Scene(root, 700, 545));
        primaryStage.show();
    }
}