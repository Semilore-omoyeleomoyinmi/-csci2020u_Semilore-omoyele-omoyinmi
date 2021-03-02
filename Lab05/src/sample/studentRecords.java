package sample;

public class studentRecords {
    private String studentId;
    private float midterm;
    private float assignment;
    private float finalExam;
    private float finalMark;
    private String letterGrade;

    //studentRecords constructor
    public studentRecords(String sid,  float ass, float midT, float finalE){
        studentId = sid;
        midterm = midT;
        assignment = ass;
        finalExam = finalE;

    }
    // get functions
    public String getStudentId(){
        return studentId;
    }

    public float getAssignment() {
        return assignment;
    }

    public float getFinalExam() {
        return finalExam;
    }

    public float getMidterm() {
        return midterm;
    }

    public float getFinalMark() {
        //calculate final mark
        float aGrade = (assignment/100f)*20f;
        float mGrade = (midterm/100f)*30f;
        float eGrade = (finalExam/100f)*50f;
        return finalMark = aGrade + mGrade + eGrade;
    }

    // Assign letter grade
    public String getLetterGrade() {
        if(finalMark >= 80 && finalMark <= 100){letterGrade = "A";}
        if(finalMark >= 70 && finalMark <= 79){letterGrade = "B";}
        if(finalMark >= 60 && finalMark <= 69){letterGrade = "C";}
        if(finalMark >= 50 && finalMark <= 59){letterGrade = "D";}
        if(finalMark >= 0 && finalMark <= 49){letterGrade = "F";}

        return letterGrade;
    }
}
