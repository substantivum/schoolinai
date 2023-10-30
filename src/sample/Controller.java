package sample;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Const;
import sample.DataBaseHandler;
import sample.students;
import sample.teachers;

public class Controller {
    @FXML
    public TextField user_login;

    @FXML
    private JFXButton login_button;

    @FXML
    private PasswordField pass_login;

    @FXML
    private ToggleGroup acctype;


    @FXML
    private Label res_text;


    public boolean loginStudent(String user, String password) {
        String select = "SELECT * FROM " + Const.STUDENT_TABLE + " WHERE " + Const.STUDENTS_LOGIN +
                " = \"" + user + "\" AND "+ Const.STUDENTS_PASSWORD + " = \"" + password + "\"";
        int count = 0;
        try {
            PreparedStatement rsSt = DataBaseHandler.getConnection().prepareStatement(select);
            ResultSet rs = rsSt.executeQuery();

            while (rs.next()) {
                count +=1;
                students.id = rs.getString(Const.STUDENTS_LOGIN);
                students.password = rs.getString(Const.STUDENTS_PASSWORD);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count == 1;
    }



    public boolean loginteacher(String user, String password) {
        String select = "SELECT * FROM " + Const.TEACHERS_TABLE + " WHERE " + Const.TEACHERS_LOGIN +
                " = \"" + user + "\" AND "+ Const.TEACHERS_PASSWORD + " = \"" + password + "\"";
        int count = 0;
        try {
            PreparedStatement rsSt = DataBaseHandler.getConnection().prepareStatement(select);
            ResultSet rs = rsSt.executeQuery();

            while (rs.next()) {
                count +=1;
                teachers.id = rs.getString(Const.TEACHERS_LOGIN);
                teachers.password = rs.getString(Const.TEACHERS_PASSWORD);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count == 1;

    }

    public void changeScene(String window, Button button) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(window));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Educational Center");
        stage.setScene(scene);
        stage.show();

        Image image = new Image("/sample/assets/book-open-flat.png");
        stage.getIcons().add(image);
    }


    @FXML
    void initialize() {


        login_button.setOnAction(event -> {
            String loginText = user_login.getText().trim();
            String passText = pass_login.getText().trim();


            RadioButton selectedRadioButton = (RadioButton) acctype.getSelectedToggle();
            String acc_log = selectedRadioButton.getText();




            if (acc_log.equals("Director")) {
                if (loginText.equals("director") && passText.equals("pass_dir")) {
                    try {
                        login_button.getScene().getWindow().hide();
                        changeScene("director.fxml", login_button);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else res_text.setText("Login and/or password is incorrect");
            }

            if (acc_log.equals(("Student"))) {
                if (loginStudent(loginText, passText)) {

                    try {
                        login_button.getScene().getWindow().hide();
                        changeScene("student.fxml", login_button);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else res_text.setText("Login and/or password is incorrect");

                }

            if (acc_log.equals("Teacher")) {
                if (loginteacher(loginText, passText)) {
                    try {
                        login_button.getScene().getWindow().hide();
                        changeScene("Teacher.fxml", login_button);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else res_text.setText("Login and/or password is incorrect");

            }

        });


    }


    }

