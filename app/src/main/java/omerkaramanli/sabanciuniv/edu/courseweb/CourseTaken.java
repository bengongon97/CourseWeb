package omerkaramanli.sabanciuniv.edu.courseweb;
// it is a class to be used in courses page of users
//it has grade variable addition to course class variables
public class CourseTaken extends Course{

    String grade;
    public CourseTaken(String crn, String cname, String ccode, String subject, String semester, String time, String room, String time1, String room1, int credit, int creditreq, String faculty, String slevel, String instr, int capacity, int reg, String cinfo,String grade) {
        super(crn, cname, ccode, subject, semester, time, room, time1, room1, credit, creditreq, faculty, slevel, instr, capacity, reg, cinfo);
        this.grade=grade;
    }

    public  CourseTaken(Course c)
    {
        super(c);
        this.grade = null;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {

        return grade;
    }

    public boolean equals(CourseTaken obj) {
        if(this.crn.equals(obj.crn))
            return true;
        return false;
    }
}
