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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.DataBaseHandler;
import tables.studentsSubjectsTable;
import tables.studentsTable;
import tables.teachersTable;

public class DirectorController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane for_director;

    @FXML
    private AnchorPane buttons_dir;

    @FXML
    private Button subjects_dirbutton;

    @FXML
    private AnchorPane teachers_anch;

    @FXML
    private TableView<studentsTable> table_students;
    @FXML
    private TableColumn<studentsTable, Integer> st_num;

    @FXML
    private TableColumn<studentsTable, String> st_id;

    @FXML
    private TableColumn<studentsTable, String> st_name;

    @FXML
    private TableColumn<studentsTable, String> st_lname;

    @FXML
    private TableColumn<studentsTable, String> st_usname;

    @FXML
    private TableColumn<studentsTable, String> st_pass;

    @FXML
    private TableColumn<studentsTable, String> st_group;

    @FXML
    private TableColumn<studentsTable, String> st_dob;

    @FXML
    private AnchorPane teach_edit;

    @FXML
    private TextField editteach_lname;

    @FXML
    private TextField editteach_fname;

    @FXML
    private TextField editteach_num;

    @FXML
    private TextField editteach_dob;

    @FXML
    private TextField editteach_login;

    @FXML
    private TextField editteach_pass;

    @FXML
    private TextField editteach_subject;

    @FXML
    private TextField editteach_phone;

    @FXML
    private AnchorPane button_sub;

    @FXML
    private ButtonBar choose_sub1;

    @FXML
    private Button sub_eng;

    @FXML
    private Button sub_germ;

    @FXML
    private Button sub_ipo;

    @FXML
    private ButtonBar choose_sub2;

    @FXML
    private Button sub_math;

    @FXML
    private Button sub_logic;

    @FXML
    private Button sub_pl;

    @FXML
    private AnchorPane studsub_anch;

    @FXML
    private TableView<teachersTable> table_teachers;

    @FXML
    private TableColumn<teachersTable, Integer> teach_num;

    @FXML
    private TableColumn<teachersTable, String> teach_id;

    @FXML
    private TableColumn<teachersTable, String> teach_name;

    @FXML
    private TableColumn<teachersTable, String> teach_lname;

    @FXML
    private TableColumn<teachersTable, String> teach_usname;

    @FXML
    private TableColumn<teachersTable, String> teach_pass;

    @FXML
    private TableColumn<teachersTable, String> teach_subject;

    @FXML
    private TableColumn<teachersTable, String> teach_dob;

    @FXML
    private TableColumn<teachersTable, String> teach_phone;


    @FXML
    private AnchorPane welcome;

    @FXML
    private AnchorPane stud_edit;

    @FXML
    private TextField editstudent_fname;

    @FXML
    private TextField editstudent_lname;

    @FXML
    private TextField editstudent_num;

    @FXML
    private TextField editstudent_group;

    @FXML
    private TextField editstudent_dob;

    @FXML
    private TextField editstudent_login;

    @FXML
    private TextField editstudent_pass;

    @FXML
    private Button addstudent_main;

    @FXML
    private AnchorPane stud_add;

    @FXML
    private TextField addstudent_fname;

    @FXML
    private TextField addstudent_lname;

    @FXML
    private TextField addstudent_num;

    @FXML
    private TextField addstudent_group;

    @FXML
    private TextField addstudent_dob;

    @FXML
    private TextField addstudent_login;

    @FXML
    private TextField addstudent_pass;

    @FXML
    private AnchorPane students_anch;

    @FXML
    private TableView<studentsSubjectsTable> table_studsub;

    @FXML
    private TableColumn<studentsSubjectsTable, Integer> stsub_num;

    @FXML
    private TableColumn<studentsSubjectsTable, String> stsub_id;

    @FXML
    private TableColumn<studentsSubjectsTable, String> stsub_name;

    @FXML
    private TableColumn<studentsSubjectsTable, String> stsub_lname;

    @FXML
    private TableColumn<studentsSubjectsTable, String> stsub_usname;

    @FXML
    private TableColumn<studentsSubjectsTable, String> stsub_pass;

    @FXML
    private TableColumn<studentsSubjectsTable, String> stsub_group;

    @FXML
    private TableColumn<studentsSubjectsTable, String> stsub_dob;

    @FXML
    private AnchorPane hide_anch;

    @FXML
    private AnchorPane teach_add;

    @FXML
    private TextField addteach_fname;

    @FXML
    private TextField addteach_lname;

    @FXML
    private TextField addteach_num;

    @FXML
    private TextField addteach_dob;

    @FXML
    private TextField addteach_login;

    @FXML
    private TextField addteach_pass;

    @FXML
    private TextField addteach_subject;

    @FXML
    private TextField addteach_phone;

    @FXML
    private AnchorPane manual;

    @FXML
    private TextField subadd_id;

    @FXML
    private TextField subadd_sub;


    @FXML
    private Button exit;


    ObservableList<studentsTable> student_list = FXCollections.observableArrayList();
    ObservableList<teachersTable> teachers_list = FXCollections.observableArrayList();
    ObservableList<studentsSubjectsTable> studentsSubjects = FXCollections.observableArrayList();

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

    public void showDirectorManual(ActionEvent event) {
        students_anch.setVisible(false);
        stud_edit.setVisible(false);
        hide_anch.setVisible(false);
        button_sub.setVisible(false);
        teachers_anch.setVisible(false);
        studsub_anch.setVisible(false);
        stud_add.setVisible(false);
        stud_edit.setVisible(false);
        teach_edit.setVisible(false);
        teach_add.setVisible(false);
        manual.setVisible(true);
    }

    public void Open_teachers_table(ActionEvent event){
        students_anch.setVisible(false);
        stud_edit.setVisible(false);
        hide_anch.setVisible(false);
        button_sub.setVisible(false);
        teachers_anch.setVisible(true);
        studsub_anch.setVisible(false);
        stud_add.setVisible(false);
        stud_edit.setVisible(false);
        teach_edit.setVisible(false);
        teach_add.setVisible(false);
        manual.setVisible(false);
    }

    int index = -1;


    @FXML
    void MathTable(ActionEvent event) {

            button_sub.setVisible(false);
            studsub_anch.setVisible(true);

            table_studsub.getItems().clear();
            try {
                Connection con = DataBaseHandler.getConnection();

                ResultSet rs = con.createStatement().executeQuery("SELECT students.num, students.id, students.first_name, students.last_name, students.user_name, students.password, students.group, students.date_of_birth FROM coursework.students \n" +
                        "JOIN students_has_subjects on students_has_subjects.students_id = students.id\n" +
                        "WHERE subjects_id = 1 ORDER BY students.num");

                while (rs.next()) {
                    studentsSubjects.add(new studentsSubjectsTable(Integer.parseInt(rs.getString("num")), rs.getString("id"), rs.getString("first_name"), rs.getString("last_name"),
                            rs.getString("user_name"), rs.getString("password"), rs.getString("group"), rs.getString("date_of_birth")));

                }

            } catch (Exception e) {
                Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);
            }

            stsub_num.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, Integer>("num"));
            stsub_id.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("id"));
            stsub_name.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("first_name"));
            stsub_lname.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("last_name"));
            stsub_usname.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("user_name"));
            stsub_pass.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("password"));
            stsub_group.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("group"));
            stsub_dob.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("date_of_birth"));

            table_studsub.setItems(studentsSubjects);

            subadd_sub.setText("Math");

    }

    @FXML
    void LogicTable(ActionEvent event) {

        button_sub.setVisible(false);
        studsub_anch.setVisible(true);

        table_studsub.getItems().clear();
        try {
            Connection con = DataBaseHandler.getConnection();

            ResultSet rs = con.createStatement().executeQuery("SELECT students.num, students.id, students.first_name, students.last_name, students.user_name, students.password, students.group, students.date_of_birth FROM coursework.students \n" +
                    "JOIN students_has_subjects on students_has_subjects.students_id = students.id\n" +
                    "WHERE subjects_id = 2 ORDER BY students.num");

            while (rs.next()) {
                studentsSubjects.add(new studentsSubjectsTable(Integer.parseInt(rs.getString("num")), rs.getString("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("user_name"), rs.getString("password"), rs.getString("group"), rs.getString("date_of_birth")));

            }

        } catch (Exception e) {
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);
        }

        stsub_num.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, Integer>("num"));
        stsub_id.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("id"));
        stsub_name.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("first_name"));
        stsub_lname.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("last_name"));
        stsub_usname.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("user_name"));
        stsub_pass.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("password"));
        stsub_group.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("group"));
        stsub_dob.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("date_of_birth"));

        table_studsub.setItems(studentsSubjects);

        subadd_sub.setText("Logic");

    }

    @FXML
    void PLTable(ActionEvent event) {

        button_sub.setVisible(false);
        studsub_anch.setVisible(true);

        table_studsub.getItems().clear();
        try {
            Connection con = DataBaseHandler.getConnection();

            ResultSet rs = con.createStatement().executeQuery("SELECT students.num, students.id, students.first_name, students.last_name, students.user_name, students.password, students.group, students.date_of_birth FROM coursework.students \n" +
                    "JOIN students_has_subjects on students_has_subjects.students_id = students.id\n" +
                    "WHERE subjects_id = 3 ORDER BY students.num");

            while (rs.next()) {
                studentsSubjects.add(new studentsSubjectsTable(Integer.parseInt(rs.getString("num")), rs.getString("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("user_name"), rs.getString("password"), rs.getString("group"), rs.getString("date_of_birth")));

            }

        } catch (Exception e) {
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);
        }

        stsub_num.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, Integer>("num"));
        stsub_id.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("id"));
        stsub_name.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("first_name"));
        stsub_lname.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("last_name"));
        stsub_usname.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("user_name"));
        stsub_pass.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("password"));
        stsub_group.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("group"));
        stsub_dob.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("date_of_birth"));

        table_studsub.setItems(studentsSubjects);

        subadd_sub.setText("Programming Languages");

    }

    @FXML
    void SETable(ActionEvent event) {

        button_sub.setVisible(false);
        studsub_anch.setVisible(true);

        table_studsub.getItems().clear();
        try {
            Connection con = DataBaseHandler.getConnection();

            ResultSet rs = con.createStatement().executeQuery("SELECT students.num, students.id, students.first_name, students.last_name, students.user_name, students.password, students.group, students.date_of_birth FROM coursework.students \n" +
                    "JOIN students_has_subjects on students_has_subjects.students_id = students.id\n" +
                    "WHERE subjects_id = 4 ORDER BY students.num");

            while (rs.next()) {
                studentsSubjects.add(new studentsSubjectsTable(Integer.parseInt(rs.getString("num")), rs.getString("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("user_name"), rs.getString("password"), rs.getString("group"), rs.getString("date_of_birth")));

            }

        } catch (Exception e) {
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);
        }

        stsub_num.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, Integer>("num"));
        stsub_id.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("id"));
        stsub_name.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("first_name"));
        stsub_lname.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("last_name"));
        stsub_usname.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("user_name"));
        stsub_pass.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("password"));
        stsub_group.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("group"));
        stsub_dob.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("date_of_birth"));

        table_studsub.setItems(studentsSubjects);

        subadd_sub.setText("Software Engineering");

    }

    public void closeEditStudent(ActionEvent event) {
        stud_edit.setVisible(false);
        studentsTable();
    }

    @FXML
    void EnglishTable(ActionEvent event) {

        button_sub.setVisible(false);
        studsub_anch.setVisible(true);

        table_studsub.getItems().clear();
        try {
            Connection con = DataBaseHandler.getConnection();

            ResultSet rs = con.createStatement().executeQuery("SELECT students.num, students.id, students.first_name, students.last_name, students.user_name, students.password, students.group, students.date_of_birth FROM coursework.students \n" +
                    "JOIN students_has_subjects on students_has_subjects.students_id = students.id\n" +
                    "WHERE subjects_id = 5 ORDER BY students.num");

            while (rs.next()) {
                studentsSubjects.add(new studentsSubjectsTable(Integer.parseInt(rs.getString("num")), rs.getString("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("user_name"), rs.getString("password"), rs.getString("group"), rs.getString("date_of_birth")));

            }

        } catch (Exception e) {
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);
        }

        stsub_num.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, Integer>("num"));
        stsub_id.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("id"));
        stsub_name.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("first_name"));
        stsub_lname.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("last_name"));
        stsub_usname.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("user_name"));
        stsub_pass.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("password"));
        stsub_group.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("group"));
        stsub_dob.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("date_of_birth"));

        table_studsub.setItems(studentsSubjects);

        subadd_sub.setText("English");

    }

    @FXML
    void GermanTable(ActionEvent event) {

        button_sub.setVisible(false);
        studsub_anch.setVisible(true);

        table_studsub.getItems().clear();
        try {
            Connection con = DataBaseHandler.getConnection();

            ResultSet rs = con.createStatement().executeQuery("SELECT students.num, students.id, students.first_name, students.last_name, students.user_name, students.password, students.group, students.date_of_birth FROM coursework.students \n" +
                    "JOIN students_has_subjects on students_has_subjects.students_id = students.id\n" +
                    "WHERE subjects_id = 6 ORDER BY students.num");

            while (rs.next()) {
                studentsSubjects.add(new studentsSubjectsTable(Integer.parseInt(rs.getString("num")), rs.getString("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("user_name"), rs.getString("password"), rs.getString("group"), rs.getString("date_of_birth")));

            }

        } catch (Exception e) {
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);
        }

        stsub_num.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, Integer>("num"));
        stsub_id.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("id"));
        stsub_name.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("first_name"));
        stsub_lname.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("last_name"));
        stsub_usname.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("user_name"));
        stsub_pass.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("password"));
        stsub_group.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("group"));
        stsub_dob.setCellValueFactory(new PropertyValueFactory<studentsSubjectsTable, String>("date_of_birth"));

        table_studsub.setItems(studentsSubjects);

        subadd_sub.setText("German");

    }

    public void addStudenttoSub(ActionEvent event) {
        String student = subadd_id.getText();
        String subject =  subadd_sub.getText();

        String insert = "INSERT INTO students_has_subjects (students_id, subjects_id) VALUES (?, (select id from coursework.subjects where name_s = \"" + subject + "\"))";

        try {
            PreparedStatement prSt = DataBaseHandler.getConnection().prepareStatement(insert);
            prSt.setString(1, student);

            prSt.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void closeAddStudent(ActionEvent event) {
        stud_add.setVisible(false);
        studentsTable();
    }

    public void showSubjects(ActionEvent event) {
        hide_anch.setVisible(false);
        button_sub.setVisible(true);
        students_anch.setVisible(false);
        teachers_anch.setVisible(false);
        studsub_anch.setVisible(false);
        stud_add.setVisible(false);
        stud_edit.setVisible(false);
        teach_add.setVisible(false);
        teach_edit.setVisible(false);
        manual.setVisible(false);

    }

    public void showUpdateStudent(ActionEvent event) {
        stud_edit.setVisible(true);
    }

    public void showAddStudent(ActionEvent event) {
        stud_add.setVisible(true);
    }

    public void showAddTeacher(ActionEvent event) {
        teach_add.setVisible(true);

    }

    public void closeAddTeacher(ActionEvent event) {
        teach_add.setVisible(false);
    }

    public void showEditTeacher(ActionEvent event) {
        teach_edit.setVisible(true);
    }

    public void closeEditTeacher(ActionEvent event) {
        teach_edit.setVisible(false);
    }

    public void showStudents(ActionEvent event) throws SQLException {
        students_anch.setVisible(true);
        stud_edit.setVisible(false);
        hide_anch.setVisible(false);
        button_sub.setVisible(false);
        teachers_anch.setVisible(false);
        studsub_anch.setVisible(false);
        stud_add.setVisible(false);
        stud_edit.setVisible(false);
        teach_edit.setVisible(false);
        teach_add.setVisible(false);
        manual.setVisible(false);
    }

    public void Delete_student_table(){
        index = table_students.getSelectionModel().getSelectedIndex();
        String value1 = st_num.getCellData(index).toString();
        if (index <= -1){
            return;
        }
        try {
            Connection con = DataBaseHandler.getConnection();

            String sql = "DELETE FROM students where num='"+value1+"'";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();

        }catch (Exception e){
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null,e);
        }
        studentsTable();
    }

    public void Delete_teacher_table(){
        index = table_teachers.getSelectionModel().getSelectedIndex();
        String value1 = teach_num.getCellData(index).toString();
        if (index <= -1){
            return;
        }
        try {
            Connection con = DataBaseHandler.getConnection();

            String sql = "DELETE FROM teachers where num='"+value1+"'";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();

        }catch (Exception e){
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null,e);
        }
        teachersTable();
    }

    @FXML
    public void getSelected_teacher(javafx.scene.input.MouseEvent mouseEvent) {

        index = table_teachers.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        editteach_num.setText(teach_num.getCellData(index).toString());
        editteach_fname.setText(teach_name.getCellData(index).toString());
        editteach_lname.setText(teach_lname.getCellData(index).toString());
        editteach_phone.setText(teach_phone.getCellData(index).toString());
        editteach_login.setText(teach_usname.getCellData(index).toString());
        editteach_pass.setText(teach_pass.getCellData(index).toString());
        editteach_dob.setText(teach_dob.getCellData(index).toString());
        editteach_subject.setText(teach_subject.getCellData(index).toString());
    }

    @FXML
    public void getSelected_student(javafx.scene.input.MouseEvent mouseEvent) {
        index = table_students.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        editstudent_num.setText(st_num.getCellData(index).toString());
        editstudent_fname.setText(st_name.getCellData(index).toString());
        editstudent_lname.setText(st_lname.getCellData(index).toString());
        editstudent_group.setText(st_group.getCellData(index).toString());
        editstudent_login.setText(st_usname.getCellData(index).toString());
        editstudent_pass.setText(st_pass.getCellData(index).toString());
        editstudent_dob.setText(st_dob.getCellData(index).toString());
    }

    @FXML
    public void Edit_student(Event event){
        try {
            Connection con = DataBaseHandler.getConnection();

            String value1 = editstudent_num.getText();
            String value2 = editstudent_group.getText();
            String value3 = editstudent_fname.getText();
            String value4 = editstudent_lname.getText();
            String value5 = editstudent_login.getText();
            String value6 = editstudent_pass.getText();
            String value7 = editstudent_dob.getText();

            String sql = "UPDATE students set num= '"+ value1 +"', id= '"+ value2+ "-" + value1 +"', first_name= '"+
                    value3 +"', last_name= '"+ value4 +"', user_name= '"+ value5 +"', password= '"+ value6 +"', date_of_birth= '"+
                    value7 +"' where num='"+value1+"'";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();

        }catch (Exception e){
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null,e);
        }
        studentsTable();
    }

    @FXML
    public void Edit_teacher(Event event){
        try {
            Connection con = DataBaseHandler.getConnection();

            String value1 = editteach_num.getText();
            String value2 = editteach_fname.getText();
            String value3 = editteach_lname.getText();
            String value4 = editteach_login.getText();
            String value5 = editteach_pass.getText();
            String value6 = editteach_subject.getText();
            String value7 = editteach_dob.getText();
            String value8 = editteach_phone.getText();

            String sql = "UPDATE teachers set num= '"+ value1 +"', id = \"t-" + value1 + "\", first_name= '" + value2 +"', last_name= '"+ value3 +"', user_name= '"+ value4 +"', password= '"+ value5 +"', phone = " + value8 + ", subjects_id= (select id from coursework.subjects where name_s = \"" + value6 + "\"), date_of_birth= '"+ value7 +"' where num='"+value1+"'";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();

        }catch (Exception e){
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null,e);
        }
        teachersTable();
    }


    public void addStudent(ActionEvent event) {
        String insert = "INSERT INTO students (students.num, students.id, students.first_name, students.last_name, " +
                "students.user_name, students.password, students.group, students.date_of_birth) VALUES (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = DataBaseHandler.getConnection().prepareStatement(insert);
            prSt.setString(1, addstudent_num.getText());
            prSt.setString(2, addstudent_group.getText() + "-" + editstudent_num.getText());
            prSt.setString(3, addstudent_fname.getText());
            prSt.setString(4, addstudent_lname.getText());
            prSt.setString(5, addstudent_login.getText());
            prSt.setString(6, addstudent_pass.getText());
            prSt.setString(7, addstudent_group.getText());
            prSt.setString(8, addstudent_dob.getText());

            prSt.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);
        }
        studentsTable();

    }

    public void addTeacher(ActionEvent event) {
        String insert = "INSERT INTO teachers (teachers.num, teachers.id, teachers.first_name, teachers.last_name, teachers.user_name, teachers.password, teachers.subjects_id, teachers.date_of_birth, teachers.phone) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = DataBaseHandler.getConnection().prepareStatement(insert);
            prSt.setString(1, addteach_num.getText());
            prSt.setString(2, "t-" + addteach_num.getText());
            prSt.setString(3, addteach_fname.getText());
            prSt.setString(4, addteach_lname.getText());
            prSt.setString(5, addteach_login.getText());
            prSt.setString(6, addteach_pass.getText());
            prSt.setString(7, addteach_subject.getText());
            prSt.setString(8, addteach_dob.getText());
            prSt.setString(9, addteach_phone.getText());

            prSt.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);
        }
        teachersTable();

    }



        public void teachersTable() {
            table_teachers.getItems().clear();
            Connection con = null;
            try {
                con = DataBaseHandler.getConnection();

                ResultSet rs = con.createStatement().executeQuery("select teachers.num, teachers.id, teachers.first_name, teachers.last_name, " +
                        "teachers.user_name, teachers.password, teachers.date_of_birth, teachers.phone, subjects.name_s from coursework.teachers\n" +
                        "join coursework.subjects on subjects.id = teachers.subjects_id where subjects_id = subjects.id order by teachers.id;");
                while (rs.next()) {
                    teachers_list.add(new teachersTable(Integer.parseInt(rs.getString("num")), rs.getString("id"), rs.getString("first_name"),
                            rs.getString("last_name"), rs.getString("user_name"), rs.getString("password"),
                            rs.getString("date_of_birth"), rs.getString("phone"), rs.getString("name_s")));

                }

            } catch (Exception e) {
                Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);
            }

            teach_num.setCellValueFactory(new PropertyValueFactory<teachersTable, Integer>("num"));
            teach_id.setCellValueFactory(new PropertyValueFactory<teachersTable, String>("id"));
            teach_name.setCellValueFactory(new PropertyValueFactory<teachersTable, String>("first_name"));
            teach_lname.setCellValueFactory(new PropertyValueFactory<teachersTable, String>("last_name"));
            teach_usname.setCellValueFactory(new PropertyValueFactory<teachersTable, String>("user_name"));
            teach_pass.setCellValueFactory(new PropertyValueFactory<teachersTable, String>("password"));
            teach_phone.setCellValueFactory(new PropertyValueFactory<teachersTable, String>("phone"));
            teach_dob.setCellValueFactory(new PropertyValueFactory<teachersTable, String>("date_of_birth"));
            teach_subject.setCellValueFactory(new PropertyValueFactory<teachersTable, String>("name_s"));

            table_teachers.setItems(teachers_list);
        }
            @FXML
        public void studentsTable() {
            table_students.getItems().clear();
                try {
                    Connection con = DataBaseHandler.getConnection();

                    ResultSet rs = con.createStatement().executeQuery("SELECT * FROM students ORDER BY num");

                        while (rs.next()) {
                            student_list.add(new studentsTable(Integer.parseInt(rs.getString("num")), rs.getString("id"), rs.getString("first_name"), rs.getString("last_name"),
                                    rs.getString("user_name"), rs.getString("password"), rs.getString("group"), rs.getString("date_of_birth")));

                        }

                    } catch (Exception e) {
                        Logger.getLogger(TableView.class.getName()).log(Level.SEVERE, null, e);
                    }

                    st_num.setCellValueFactory(new PropertyValueFactory<studentsTable, Integer>("num"));
                    st_id.setCellValueFactory(new PropertyValueFactory<studentsTable, String>("id"));
                    st_name.setCellValueFactory(new PropertyValueFactory<studentsTable, String>("first_name"));
                    st_lname.setCellValueFactory(new PropertyValueFactory<studentsTable, String>("last_name"));
                    st_usname.setCellValueFactory(new PropertyValueFactory<studentsTable, String>("user_name"));
                    st_pass.setCellValueFactory(new PropertyValueFactory<studentsTable, String>("password"));
                    st_group.setCellValueFactory(new PropertyValueFactory<studentsTable, String>("group"));
                    st_dob.setCellValueFactory(new PropertyValueFactory<studentsTable, String>("date_of_birth"));

                    table_students.setItems(student_list);
        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentsTable();
        teachersTable();

    }
}
