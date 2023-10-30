package tables;

import sample.teachers;

public class teachersTable extends teachers {

    int num;
    String id;
    String first_name;
    String last_name;
    String user_name;



    String password;
    String date_of_birth;
    String phone;
    String name_s;

    //getters
    public int getNum() {
        return num;
    }

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


    public String getPhone() {
        return phone;
    }

    public String getName_s() {
        return name_s;
    }



    //setters
    public void setNum(int num) {
        this.num = num;
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


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName_s(String name_s) {
        this.name_s = name_s;
    }
    public teachersTable(int num, String id1, String first_name1, String last_name1, String user_name1, String password1, String date_of_birth1, String phone1, String name_s) {
        this.num = num;
        this.id = id1;
        this.first_name = first_name1;
        this.last_name = last_name1;
        this.user_name = user_name1;
        this.password = password1;
        this.date_of_birth = date_of_birth1;
        this.phone = phone1;
        this.name_s = name_s;
    }
}
