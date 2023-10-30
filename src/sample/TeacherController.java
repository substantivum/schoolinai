package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.DataBaseHandler;
import tables.gradesTable;
import tables.homeworkTable;
import sample.teachers;

public class TeacherController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField filter_text;

    @FXML
    private AnchorPane buttons_dir;

    @FXML
    private Button subjects_dirbutton;

    @FXML
    private Button update_button;

    @FXML
    private AnchorPane grades_anch;

    @FXML
    private TableView<gradesTable> table_grades;

    @FXML
    private TableColumn<gradesTable, String> grades_id;

    @FXML
    private TableColumn<gradesTable, String> grades_student;

    @FXML
    private TableColumn<gradesTable, Double> grades_1c;

    @FXML
    private TableColumn<gradesTable, Double> grade_2c;

    @FXML
    private TableColumn<gradesTable, Double> grade_3c;

    @FXML
    private TableColumn<gradesTable, Double> grade_exam;

    @FXML
    private TableColumn<gradesTable, Double> grade_offset;

    @FXML
    private ComboBox<String> selectgroup;

    @FXML
    private TextField edit_1c;

    @FXML
    private TextField edit_2c;

    @FXML
    private TextField edit_3c;

    @FXML
    private TextField edit_exam;

    @FXML
    private TextField edit_offset;

    @FXML
    private AnchorPane hide_anch;

    @FXML
    private AnchorPane manual;

    @FXML
    private Label label_name;

    @FXML
    private TextField deadline_text;

    @FXML
    private TextArea add_descr;

    @FXML
    private Button hw_button;

    @FXML
    private Button hw_change;

    @FXML
    private Label needthis;

    @FXML
    private TableView<homeworkTable> hw_table;
    @FXML
    private TableColumn<homeworkTable, Integer> num;

    @FXML
    private TableColumn<homeworkTable, String> hw_group;

    @FXML
    private TableColumn<homeworkTable, String> hw_descr;

    @FXML
    private TableColumn<homeworkTable, String> hw_deadline;

    @FXML
    private TableColumn<gradesTable, Double> grade_fscore;

    @FXML
    private AnchorPane hw_anch;

    @FXML
    private Button exit;

    @FXML
    private Label hello;

    ObservableList<gradesTable> grades_table = FXCollections.observableArrayList();
    ObservableList<homeworkTable> hw_list = FXCollections.observableArrayList();

    ObservableList<String> list = FXCollections.observableArrayList();
    int index = -1;

    @FXML
    void hello() {

        String select = "select concat(first_name, \" \", last_name) as name from coursework.teachers where user_name = \"" + teachers.id + "\"";
        Connection con = null;
        try {
            con = DataBaseHandler.getConnection();
            ResultSet rs = con.createStatement().executeQuery(select);

            while(rs.next()) {
                hello.setText("Hello, " + rs.getString("name") + "!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void changeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Educational Center");
        stage.setScene(scene);
        stage.show();
        exit.getScene().getWindow().hide();

        Image image = new Image("/sample/assets/book-open-flat.png");
        stage.getIcons().add(image);
    }


    public void showManual(ActionEvent event) {

        hide_anch.setVisible(false);
        grades_anch.setVisible(false);
        manual.setVisible(true);
        hw_anch.setVisible(false);

    }


    public void showGrades(ActionEvent event) {
        hide_anch.setVisible(false);
        grades_anch.setVisible(true);
        manual.setVisible(false);
        hw_anch.setVisible(false);


    }

    @FXML
    public void updateGrades() {
        table_grades.getItems().clear();
        try {
            Connection con = DataBaseHandler.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT students.id, CONCAT(students.first_name, ' ', students.last_name) as name, grades.first_colloqium, grades.second_colloqium, grades.third_colloqium, grades.exam, grades.offset, ROUND(((grades.first_colloqium+ grades.second_colloqium + grades.third_colloqium + grades.exam + grades.offset) / 5), 2) as final_score FROM coursework.grades\n" +
                    "JOIN coursework.subjects on subjects.id = grades.subject_id\n" +
                    "JOIN coursework.students on grades.student_id = students.id\n" +
                    "JOIN coursework.teachers on teachers.subjects_id = subjects.id\n" +
                    "where teachers.user_name = \"" + teachers.id + "\" ORDER BY final_score");

            while (rs.next()) {
                grades_table.add(new gradesTable(rs.getString("id"), rs.getString("name"), Double.parseDouble(rs.getString("first_colloqium")),
                        Double.parseDouble(rs.getString("second_colloqium")),
                        Double.parseDouble(rs.getString("third_colloqium")), Double.parseDouble(rs.getString("exam")),
                        Double.parseDouble(rs.getString("offset")), Double.parseDouble(rs.getString("final_score"))));
            }

        } catch (Exception e) {
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);
        }

        grades_id.setCellValueFactory(new PropertyValueFactory<gradesTable, String>("id"));
        grades_student.setCellValueFactory(new PropertyValueFactory<gradesTable, String>("name"));
        grades_1c.setCellValueFactory(new PropertyValueFactory<gradesTable, Double>("first_colloqium"));
        grade_2c.setCellValueFactory(new PropertyValueFactory<gradesTable, Double>("second_colloqium"));
        grade_3c.setCellValueFactory(new PropertyValueFactory<gradesTable, Double>("third_colloqium"));
        grade_exam.setCellValueFactory(new PropertyValueFactory<gradesTable, Double>("exam"));
        grade_offset.setCellValueFactory(new PropertyValueFactory<gradesTable, Double>("offset"));
        grade_fscore.setCellValueFactory(new PropertyValueFactory<gradesTable, Double>("final_score"));

        table_grades.setItems(grades_table);

    }


    @FXML
    public void homeTasksTable() {

        hw_table.getItems().clear();
        Connection con = null;
        try {
            con = DataBaseHandler.getConnection();
        String select = "SELECT home_tasks.num, home_tasks.group, home_tasks.descr, home_tasks.deadline_date FROM coursework.home_tasks\n" +
                "JOIN coursework.teachers on teachers.subjects_id = home_tasks.subject_id\n" +
                "WHERE subject_id = (select subjects_id from coursework.teachers where user_name = \"" + teachers.id + "\")";

        ResultSet rs = con.createStatement().executeQuery(select);
        while (rs.next()) {
            hw_list.add(new homeworkTable(Integer.parseInt(rs.getString("num")), rs.getString("group"), rs.getString("descr"), rs.getString("deadline_date")));
        }

        } catch (Exception e) {
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);
        }

        num.setCellValueFactory(new PropertyValueFactory<homeworkTable, Integer>("num"));
        hw_group.setCellValueFactory(new PropertyValueFactory<homeworkTable, String>("group"));
        hw_descr.setCellValueFactory(new PropertyValueFactory<homeworkTable, String>("descr"));
        hw_deadline.setCellValueFactory(new PropertyValueFactory<homeworkTable, String>("deadline_date"));
        hw_table.setItems(hw_list);


        try {
            ResultSet rs = con.createStatement().executeQuery("select name from coursework.groups");

            while(rs.next()) {
                list.add(rs.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        selectgroup.setItems(list);




    }

    public void showHW(ActionEvent event) {

        hide_anch.setVisible(false);
        grades_anch.setVisible(false);
        manual.setVisible(false);
        hw_anch.setVisible(true);

    }

    public void addHomeTask(ActionEvent event) {
        String group = selectgroup.getValue();
        String descr = add_descr.getText().trim();
        String deadline = deadline_text.getText();

                String insert = "INSERT INTO coursework.home_tasks(home_tasks.group, home_tasks.descr, home_tasks.subject_id, deadline_date) VALUES (?, ?, (select teachers.subjects_id from coursework.teachers where user_name = \"" + teachers.id + "\"), ?)";

        try {
            PreparedStatement prSt = DataBaseHandler.getConnection().prepareStatement(insert);
            prSt.setString(1, selectgroup.getValue());
            prSt.setString(2, add_descr.getText());
            prSt.setString(3, deadline_text.getText());

            prSt.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);
        }
        homeTasksTable();
    }

    @FXML
    public void getSelectedHW(javafx.scene.input.MouseEvent mouseEvent){
        index = hw_table.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        selectgroup.setValue(hw_group.getCellData(index).toString());
        add_descr.setText(hw_descr.getCellData(index).toString());
        deadline_text.setText(hw_deadline.getCellData(index).toString());
        needthis.setText(String.valueOf(Integer.parseInt(num.getCellData(index).toString())));

    }

    @FXML
    public void editHW(Event event) {
        Connection con = null;
        try {
            con = DataBaseHandler.getConnection();

        String group = selectgroup.getValue();
        String descr = add_descr.getText();
        String deadline = deadline_text.getText();
        String num = needthis.getText();

        String sql = "UPDATE coursework.home_tasks set home_tasks.group = \"" + group + "\", home_tasks.descr = \"" + descr + "\", home_tasks.deadline_date = \"" + deadline + "\" where home_tasks.num = " + num;

        PreparedStatement pst = con.prepareStatement(sql);
        pst.execute();

    }catch (Exception e){
        Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null,e);
    }
    homeTasksTable();
    }

    @FXML
    public void getSelected_score(javafx.scene.input.MouseEvent mouseEvent) {
        index = table_grades.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        edit_1c.setText(grades_1c.getCellData(index).toString());
        edit_2c.setText(grade_2c.getCellData(index).toString());
        edit_3c.setText(grade_3c.getCellData(index).toString());
        edit_exam.setText(grade_exam.getCellData(index).toString());
        edit_offset.setText(grade_offset.getCellData(index).toString());
        label_name.setText(grades_id.getCellData(index).toString());
        System.out.println(label_name.getText());

    }

    @FXML
    public void Edit_score(ActionEvent event) {
        try {
            Connection con = DataBaseHandler.getConnection();

            String value1 = edit_1c.getText();
            String value2 = edit_2c.getText();
            String value3 = edit_3c.getText();
            String value4 = edit_exam.getText();
            String value5 = edit_offset.getText();
            String value6 = label_name.getText();


            String sql = "UPDATE coursework.grades set grades.first_colloqium= " + value1 + ", grades.second_colloqium = " + value2 + ", grades.third_colloqium= " + value3 + ", grades.exam= " + value4 + ", grades.offset= " + value5 + " where grades.student_id = \"" + value6 + "\" and grades.subject_id = (select subjects_id from coursework.teachers where teachers.user_name = \"" + teachers.id + "\")";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);
        }
        updateGrades();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateGrades();
        homeTasksTable();
        hello();

    }
}
