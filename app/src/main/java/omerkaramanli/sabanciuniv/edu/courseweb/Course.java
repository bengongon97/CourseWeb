package omerkaramanli.sabanciuniv.edu.courseweb;

/**
 * it is a class to store course
 * Created by okuyucu on 5.05.2018.
 */

public class Course {
    String crn;
    String cname;
    String ccode;
    String subject;
    String semester;
    String time;
    String room;
    String time1;
    String room1;
    int credit;
    int creditreq;
    String faculty;
    String slevel;
    String instr;
    int capacity;
    int reg;
    String cinfo;

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getRoom1() {
        return room1;
    }

    public void setRoom1(String room1) {
        this.room1 = room1;
    }

    public String getCreditStr() {
        return "" +  credit;
    }

    public int getCredit() {
        return credit;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getCreditreq() {
        return creditreq;
    }

    public void setCreditreq(int creditreq) {
        this.creditreq = creditreq;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSlevel() {
        return slevel;
    }

    public void setSlevel(String slevel) {
        this.slevel = slevel;
    }

    public String getInstr() {
        return instr;
    }

    public void setInstr(String instr) {
        this.instr = instr;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public String getCinfo() {
        return cinfo;
    }

    public void setCinfo(String cinfo) {
        this.cinfo = cinfo;
    }

    public Course(String crn, String cname, String ccode, String subject, String semester, String time, String room, String time1, String room1, int credit, int creditreq, String faculty, String slevel, String instr, int capacity, int reg, String cinfo) {
        this.crn = crn;
        this.cname = cname;
        this.ccode = ccode;
        this.subject = subject;
        this.semester = semester;
        this.time = time;
        this.room = room;
        this.time1 = time1;
        this.room1 = room1;
        this.credit = credit;
        this.creditreq = creditreq;
        this.faculty = faculty;
        this.slevel = slevel;
        this.instr = instr;
        this.capacity = capacity;
        this.reg = reg;
        this.cinfo = cinfo;
    }

    public Course(Course other) {
        this.crn = other.crn;
        this.cname = other.cname;
        this.ccode = other.ccode;
        this.subject = other.subject;
        this.semester = other.semester;
        this.time = other.time;
        this.room = other.room;
        this.time1 = other.time1;
        this.room1 = other.room1;
        this.credit = other.credit;
        this.creditreq = other.creditreq;
        this.faculty = other.faculty;
        this.slevel = other.slevel;
        this.instr = other.instr;
        this.capacity = other.capacity;
        this.reg = other.reg;
        this.cinfo = other.cinfo;
    }
}
