package tables;

import sample.students;

public class studentsSubjectsTable extends students {
    String id;
    String first_name;
    String last_name;
    String user_name;
    String password;
    String group;
    String date_of_birth;

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    int num;


    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }


    //getters

    public String getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public studentsSubjectsTable(int num, String id, String first_name, String last_name, String user_name, String password, String group, String date_of_birth) {
        this.num = num;
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_name = user_name;
        this.password = password;
        this.date_of_birth = date_of_birth;
        this.group = group;
    }


}