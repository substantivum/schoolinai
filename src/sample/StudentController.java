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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tables.*;

public class StudentController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane buttons_dir;

    @FXML
    private Button subjects_dirbutton1;

    @FXML
    private Button subjects_dirbutton;

    @FXML
    private AnchorPane grades_anch;

    @FXML
    private TableView<gradesStudent> grades_table;

    @FXML
    private TableColumn<gradesStudent, String> grades_subname;

    @FXML
    private TableColumn<gradesStudent, Double> grades_1c;

    @FXML
    private TableColumn<gradesStudent, Double> grades_2c;

    @FXML
    private TableColumn<gradesStudent, Double> grades_3c;

    @FXML
    private TableColumn<gradesStudent, Double> grades_exam;

    @FXML
    private TableColumn<gradesStudent, Double> grades_offset;

    @FXML
    private TableColumn<gradesStudent, Double> grades_fscore;

    @FXML
    private AnchorPane home_work_anch;

    @FXML
    private TableView<home_tasks> home_work;

    @FXML
    private TableColumn<home_tasks, String> hw_subject;

    @FXML
    private TableColumn<home_tasks, String> hw_descr;

    @FXML
    private TableColumn<home_tasks, String> hw_deadline;

    @FXML
    private TableColumn<home_tasks, String> hw_status;

    @FXML
    private TableView<examsStudent> exams_table;

    @FXML
    private TableColumn<examsStudent, String> exams_sub;

    @FXML
    private TableColumn<examsStudent, String> exam_date;
    @FXML
    private TableColumn<examsStudent, String> exam_consult;

    @FXML
    private TableColumn<examsStudent, Double> exam_score;

    @FXML
    private TableView<subject_students> subjects_table;

    @FXML
    private TableColumn<subject_students, String> sub_name;

    @FXML
    private TableView<offsetStudents> offset_table;
    @FXML
    private TableColumn<offsetStudents, String> offset_date;

    @FXML
    private TableColumn<examsStudent, String> offset_sub;

    @FXML
    private TableColumn<examsStudent, Double> offset_score;

    @FXML
    private AnchorPane anch_foranch;

    @FXML
    private AnchorPane manual;

    @FXML
    private AnchorPane hide_anch;

    @FXML
    private MenuButton status_button;

    @FXML
    private MenuItem done_status;

    @FXML
    private MenuItem undone_status;

    @FXML
    private Label needthis;

    @FXML
    private Label hello;

    @FXML
    private Button exit;


    ObservableList<subject_students> subject_list = FXCollections.observableArrayList();
    ObservableList<home_tasks> hw_list = FXCollections.observableArrayList();
    ObservableList<gradesStudent> grades_list = FXCollections.observableArrayList();
    ObservableList<examsStudent> exams_list = FXCollections.observableArrayList();
    ObservableList<offsetStudents> offset_list = FXCollections.observableArrayList();
    int index = -1;


    @FXML
    void hello() {

        String select = "select concat(first_name, \" \", last_name) as name from coursework.students where user_name = \"" + students.id + "\"";
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



    @FXML
    void showManual(ActionEvent event) {

        anch_foranch.setVisible(false);
        home_work_anch.setVisible(false);
        grades_anch.setVisible(false);
        hide_anch.setVisible(false);
        manual.setVisible(true);
        System.out.println("true");

    }

    @FXML
    void changeDone(ActionEvent event) {
        try {
            Connection con = DataBaseHandler.getConnection();
            String sql = "update home_tasks_has_students set status = 'done' where hw_num = (select num from coursework.home_tasks where descr = '" + needthis.getText() + "') and students_id = (select id from coursework.students where user_name = '" + students.id + "')";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);

        }
        homeworkTable();
    }

    @FXML
    void changeUnDone(ActionEvent event) {
        try {
            Connection con = DataBaseHandler.getConnection();
            String sql = "update home_tasks_has_students set status = 'undone' where hw_num = (select num from coursework.home_tasks where descr = '" + needthis.getText() + "') and students_id = (select id from coursework.students where user_name = '" + students.id + "')";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);

        }

        homeworkTable();
    }

    @FXML
    void getSelectedHW(javafx.scene.input.MouseEvent mouseEvent) {
        index = home_work.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        needthis.setText(hw_descr.getCellData(index));
        System.out.println(needthis.getText());

    }

    @FXML
    void showExams(ActionEvent event) throws SQLException {
        anch_foranch.setVisible(true);
        subjects_table.setVisible(false);
        exams_table.setVisible(true);
        offset_table.setVisible(false);
        home_work_anch.setVisible(false);
        grades_anch.setVisible(false);
        hide_anch.setVisible(false);
        manual.setVisible(false);

        exams_table.getItems().clear();

        Connection con = DataBaseHandler.getConnection();
        ResultSet rs = con.createStatement().executeQuery("select subjects.name_s, grades.exam_consultdate, grades.exam_date, grades.exam from coursework.grades\n" +
                "join coursework.subjects on subjects.id = grades.subject_id\n" +
                "where grades.student_id = (select id from coursework.students where user_name = \"" + students.id + "\")");

        while (rs.next()) {
            exams_list.add(new examsStudent(rs.getString("name_s"), rs.getString("exam_consultdate"), rs.getString("exam_date"), Double.parseDouble(rs.getString("exam"))));
        }
        exams_sub.setCellValueFactory(new PropertyValueFactory<examsStudent, String>("name_s"));
        exam_consult.setCellValueFactory(new PropertyValueFactory<examsStudent, String>("exam_consultdate"));
        exam_date.setCellValueFactory(new PropertyValueFactory<examsStudent, String>("exam_date"));
        exam_score.setCellValueFactory(new PropertyValueFactory<examsStudent, Double>("exam"));
        exams_table.setItems(exams_list);


    }

    @FXML
    void showOffsets(ActionEvent event) throws SQLException {
        anch_foranch.setVisible(true);
        subjects_table.setVisible(false);
        exams_table.setVisible(false);
        offset_table.setVisible(true);
        home_work_anch.setVisible(false);
        grades_anch.setVisible(false);
        hide_anch.setVisible(false);
        manual.setVisible(false);

        offset_table.getItems().clear();

        Connection con = DataBaseHandler.getConnection();
        ResultSet rs = con.createStatement().executeQuery("select subjects.name_s, grades.offset_date, grades.offset from coursework.grades\n" +
                "join coursework.subjects on subjects.id = grades.subject_id\n" +
                "where grades.student_id = (select id from coursework.students where user_name = \"" + students.id + "\")");

        while (rs.next()) {
            offset_list.add(new offsetStudents(rs.getString("name_s"), rs.getString("offset_date"), Double.parseDouble(rs.getString("offset"))));
        }
        offset_sub.setCellValueFactory(new PropertyValueFactory<examsStudent, String>("name_s"));
        offset_score.setCellValueFactory(new PropertyValueFactory<examsStudent, Double>("offset"));
        offset_date.setCellValueFactory(new PropertyValueFactory<offsetStudents, String>("offset_date"));
        offset_table.setItems(offset_list);
    }

    @FXML
    void showGrades(ActionEvent event) throws SQLException {
        anch_foranch.setVisible(false);
        home_work_anch.setVisible(false);
        grades_anch.setVisible(true);
        hide_anch.setVisible(false);
        manual.setVisible(false);

        grades_table.getItems().clear();

        Connection con = DataBaseHandler.getConnection();
        ResultSet rs = con.createStatement().executeQuery("select subjects.name_s, grades.first_colloqium, grades.second_colloqium, grades.third_colloqium, grades.exam, grades.offset, ROUND(((grades.first_colloqium+ grades.second_colloqium + grades.third_colloqium + grades.exam + grades.offset) / 5), 2) as final_score from coursework.grades\n" +
                "join coursework.subjects on subjects.id = grades.subject_id\n" +
                "where grades.student_id = (select id from coursework.students where user_name = \"" + students.id + "\")");
        while (rs.next()) {
            grades_list.add(new gradesStudent(rs.getString("name_s"), Double.parseDouble(rs.getString("first_colloqium")), Double.parseDouble(rs.getString("second_colloqium")),
                    Double.parseDouble(rs.getString("third_colloqium")), Double.parseDouble(rs.getString("exam")), Double.parseDouble(rs.getString("offset")), Double.parseDouble(rs.getString("final_score"))));
        }

        grades_subname.setCellValueFactory(new PropertyValueFactory<gradesStudent, String>("name_s"));
        grades_1c.setCellValueFactory(new PropertyValueFactory<gradesStudent, Double>("first_colloqium"));
        grades_2c.setCellValueFactory(new PropertyValueFactory<gradesStudent, Double>("second_colloqium"));
        grades_3c.setCellValueFactory(new PropertyValueFactory<gradesStudent, Double>("third_colloqium"));
        grades_exam.setCellValueFactory(new PropertyValueFactory<gradesStudent, Double>("exam"));
        grades_offset.setCellValueFactory(new PropertyValueFactory<gradesStudent, Double>("offset"));
        grades_fscore.setCellValueFactory(new PropertyValueFactory<gradesStudent, Double>("final_score"));

        grades_table.setItems(grades_list);


    }

    @FXML
    void showHomeTasks(ActionEvent event) {
        anch_foranch.setVisible(false);
        home_work_anch.setVisible(true);
        grades_anch.setVisible(false);
        hide_anch.setVisible(false);
        manual.setVisible(false);

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


    @FXML
    public void homeworkTable() {
        home_work.getItems().clear();

        Connection con = null;
        try {
            con = DataBaseHandler.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT subjects.name_s, home_tasks.descr, home_tasks.deadline_date, home_tasks_has_students.status from coursework.home_tasks\n" +
                    "JOIN coursework.subjects on subjects.id = home_tasks.subject_id\n" +
                    "JOIN coursework.home_tasks_has_students on subjects.id = home_tasks_has_students.home_tasks_subject_id\n" +
                    "WHERE home_tasks.group = (select students.group from coursework.students where students.user_name = \"" + students.id + "\")");

            while(rs.next()) {
                hw_list.add(new home_tasks(rs.getString("name_s"), rs.getString("descr"), rs.getString("deadline_date"), rs.getString("status")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        hw_subject.setCellValueFactory(new PropertyValueFactory<home_tasks, String>("name_s"));
        hw_descr.setCellValueFactory(new PropertyValueFactory<home_tasks, String>("deadline_date"));
        hw_deadline.setCellValueFactory(new PropertyValueFactory<home_tasks, String>("descr"));
        hw_status.setCellValueFactory(new PropertyValueFactory<home_tasks, String>("status"));

        home_work.setItems(hw_list);

    }

    @FXML
    void showSubjects(ActionEvent event) {

        anch_foranch.setVisible(true);
        subjects_table.setVisible(true);
        exams_table.setVisible(false);
        offset_table.setVisible(false);
        home_work_anch.setVisible(false);
        grades_anch.setVisible(false);
        hide_anch.setVisible(false);
        manual.setVisible(false);

        subjects_table.getItems().clear();

        Connection con = null;
        try {
            con = DataBaseHandler.getConnection();
        ResultSet rs = con.createStatement().executeQuery("select subjects.name_s from coursework.subjects \n" +
                "join coursework.students_has_subjects on subjects.id = students_has_subjects.subjects_id\n" +
                "where students_has_subjects.students_id = (select id from coursework.students where user_name = \"" + students.id + "\")");

        while (rs.next()) {

            subject_list.add(new subject_students(rs.getString("name_s")));
        }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        sub_name.setCellValueFactory(new PropertyValueFactory<subject_students, String>("name_s"));
        subjects_table.setItems(subject_list);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeworkTable();
        hello();

    }
}