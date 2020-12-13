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
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

public class SampleController {
    public JFXTextField name_create;
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
        System.out.println(name_create.getText());
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