package tables;

public class homeworkTable {

    int num;
    String group, descr, deadline_date;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getDeadline_date() {
        return deadline_date;
    }

    public void setDeadline_date(String deadline_date) {
        this.deadline_date = deadline_date;
    }

    public homeworkTable(int num, String group, String descr, String deadline_date) {
        this.num = num;
        this.group = group;
        this.descr = descr;
        this.deadline_date = deadline_date;
    }
}
