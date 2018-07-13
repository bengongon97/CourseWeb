package omerkaramanli.sabanciuniv.edu.courseweb;

/**
 * Created by okuyucu on 5.05.2018.
 */

public class Student {
    int sid;
    String username;
    String major;
    int totalcredits;
    String sname;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getTotalcredits() {
        return totalcredits;
    }

    public void setTotalcredits(int totalcredits) {
        this.totalcredits = totalcredits;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Student(int sid, String username, String major, int totalcredits, String sname) {
        this.sid = sid;
        this.username = username;
        this.major = major;
        this.totalcredits = totalcredits;
        this.sname = sname;
    }
}
