package tables;

public class offsetStudents {
    String name_s;
    String offset_date;
    Double offset;

    public String getOffset_date() {
        return offset_date;
    }

    public void setOffset_date(String offset_date) {
        this.offset_date = offset_date;
    }

    public String getName_s() {
        return name_s;
    }

    public Double getOffset() {
        return offset;
    }

    public void setName_s(String name_s) {
        this.name_s = name_s;
    }

    public void setOffset(Double offset) {
        this.offset = offset;
    }

    public offsetStudents(String name_s, String offset_date, Double offset) {
        this.name_s = name_s;
        this.offset = offset;
        this.offset_date = offset_date;
    }
}
