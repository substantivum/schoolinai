package tables;

public class examsStudent {

    String name_s;
    String exam_date;
    Double exam;
    String exam_consultdate;

    public String getExam_consultdate() {
        return exam_consultdate;
    }

    public void setExam_consultdate(String exam_consultdate) {
        this.exam_consultdate = exam_consultdate;
    }

    public String getExam_date() {
        return exam_date;
    }

    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
    }

    public String getName_s() {
        return name_s;
    }

    public Double getExam() {
        return exam;
    }

    public void setName_s(String name_s) {
        this.name_s = name_s;
    }

    public void setExam(Double exam) {
        this.exam = exam;
    }

    public examsStudent(String name_s, String exam_consultdate, String exam_date, Double exam) {
        this.exam_consultdate = exam_consultdate;
        this.name_s = name_s;
        this.exam_date = exam_date;
        this.exam = exam;
    }
}
