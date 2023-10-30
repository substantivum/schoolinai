package tables;

public class home_tasks {

    String name_s, deadline_date, descr, status;

    public String getName_s() {
        return name_s;
    }

    public String getDeadline_date() {
        return deadline_date;
    }

    public String getDescr() {
        return descr;
    }

    public String getStatus() {
        return status;
    }

    public void setName_s(String name_s) {
        this.name_s = name_s;
    }

    public void setDeadline_date(String deadline_date) {
        this.deadline_date = deadline_date;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public home_tasks(String name_s, String deadline_date, String descr, String status) {
        this.name_s = name_s;
        this.deadline_date = deadline_date;
        this.descr = descr;
        this.status = status;
    }
}
