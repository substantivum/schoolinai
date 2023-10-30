package tables;

public class gradesTable {

    String id, name;
    Double first_colloqium, second_colloqium, third_colloqium, exam, offset, final_score;


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirst_colloqium(Double first_colloqium) {
        this.first_colloqium = first_colloqium;
    }

    public void setSecond_colloqium(Double second_colloqium) {
        this.second_colloqium = second_colloqium;
    }

    public void setThird_colloqium(Double third_colloqium) {
        this.third_colloqium = third_colloqium;
    }

    public void setExam(Double exam) {
        this.exam = exam;
    }

    public void setOffset(Double offset) {
        this.offset = offset;
    }

    public void setFinal_score(Double final_score) {
        this.final_score = final_score;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getFirst_colloqium() {
        return first_colloqium;
    }

    public Double getSecond_colloqium() {
        return second_colloqium;
    }

    public Double getThird_colloqium() {
        return third_colloqium;
    }

    public Double getExam() {
        return exam;
    }

    public Double getOffset() {
        return offset;
    }

    public Double getFinal_score() {
        return final_score;
    }

    public gradesTable(String id, String name, Double first_colloqium, Double second_colloqium, Double third_colloqium, Double exam, Double offset, Double final_score) {
        this.id = id;
        this.name = name;
        this.first_colloqium = first_colloqium;
        this.second_colloqium = second_colloqium;
        this.third_colloqium = third_colloqium;
        this.exam = exam;
        this.offset = offset;
        this.final_score = final_score;
    }
}