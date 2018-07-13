package omerkaramanli.sabanciuniv.edu.courseweb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by okuyucu on 4.05.2018.
 */

class Database {
    SQLiteDatabase db;
    SQLClass bdb;
    SimpleDateFormat parser = new SimpleDateFormat("HH:mm");

    public Database(Context c) {
        bdb = new SQLClass(c);
    }

    public void open() {
        db = bdb.getWritableDatabase();
    }

    public void close() {
        bdb.close();
    }

    public List<user> getAllUserslist() {
        String[] columns = {"username", "password", "usertype", "name", "lastname"};
        String sortOrder = "username" + " ASC";
        List<user> userlist = new ArrayList<user>();

        Cursor c = db.query("users", columns, null, null, null, null, null);
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                String username = c.getString(0);
                String password = c.getString(1);
                String usertype = c.getString(2);
                String name = c.getString(3);
                String lastname = c.getString(4);
                user u = new user(username, password, usertype, name, lastname);
                userlist.add(u);
            } while (c.moveToNext());
        }
        c.close();
        return userlist;
    }

    public ArrayList<user> getAllStudentsList(){
        String[] columns = {"username", "password", "usertype", "name", "lastname"};
        String sortOrder = "username" + " ASC";
        ArrayList<user> userlist = new ArrayList<user>();

        Cursor c = db.query("users", columns, null, null, null, null, null);
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                String username = c.getString(0);
                String password = c.getString(1);
                String usertype = c.getString(2);
                String name = c.getString(3);
                String lastname = c.getString(4);
                if(usertype.equals("student")) {
                    user u = new user(username, password, usertype, name, lastname);
                    userlist.add(u);
                }
            } while (c.moveToNext());
        }
        c.close();
        return userlist;
    }

    public boolean loginCheck(String username, String password) {

        // array of columns to fetch
        String[] columns = {
                "username"
        };
        // selection criteria
        String selection = "username" + " = ?" + " AND " + "password" + " = ?";

        // selection arguments
        String[] selectionArgs = {username, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query("users", //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public user getUserInfo(String username) {

        // array of columns to fetch
        String[] columns = {"username", "password", "usertype", "name", "lastname"};
        // selection criteria
        String selection = "username" + " = ?";
        String[] selectionArgs = {username};
        user u = null;

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor c = db.query("users", //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        c.moveToFirst();
        if (c.moveToFirst()) {
            String usern = c.getString(0);
            String password = c.getString(1);
            String usertype = c.getString(2);
            String name = c.getString(3);
            String lastname = c.getString(4);
            u = new user(usern, password, usertype, name, lastname);

        }
        c.close();
        return u;

    }

    public Student getStudentInfo(String username) {

        // array of columns to fetch
        String[] columns = {"sid", "username", "major", "totalcredits", "sname"};
        // selection criteria
        String selection = "username" + " = ?";
        String[] selectionArgs = {username};
        Student u = null;

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor c = db.query("students", //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        c.moveToFirst();
        if (c.moveToFirst()) {
            int sid = Integer.parseInt(c.getString(0));
            String usern = c.getString(1);
            String major = c.getString(2);
            int totalcredits = Integer.parseInt(c.getString(3));
            String sname = c.getString(4);
            u = new Student(sid, usern, major, totalcredits, sname);
        }
        c.close();
        return u;

    }

    public Student getStudentInfoWithSid(int sid) {

        String sid1= Integer.toString(sid);
        // array of columns to fetch
        String[] columns = {"sid", "username", "major", "totalcredits", "sname"};
        // selection criteria
        String selection = "sid" + " = ?";
        String[] selectionArgs = {sid1};
        Student u = null;

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor c = db.query("students", //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        c.moveToFirst();
        if (c.moveToFirst()) {
            int sid3 = Integer.parseInt(c.getString(0));
            String usern = c.getString(1);
            String major = c.getString(2);
            int totalcredits = Integer.parseInt(c.getString(3));
            String sname = c.getString(4);
            u = new Student(sid3, usern, major, totalcredits, sname);
        }
        c.close();
        return u;

    }

    public Course getCourseInfo(String crn) {

        // array of columns to fetch
        String[] columns = {"crn", "cname", "ccode", "subject", "semester", "time", "room", "time1", "room1", "credit", "creditreq", "faculty", "slevel", "instr", "capacity", "reg", "cinfo"};
        String selection = "crn" + " = ?";
        String[] selectionArgs = {crn};
        Course t = null;

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor c = db.query("courses", //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        c.moveToFirst();
        if (c.moveToFirst()) {
            String crn2 = c.getString(0);
            String cname = c.getString(1);
            String ccode = c.getString(2);
            String subject = c.getString(3);
            String semester = c.getString(4);
            String time = c.getString(5);
            String room = c.getString(6);
            String time1 = c.getString(7);
            String room1 = c.getString(8);
            int credit = Integer.parseInt(c.getString(9));
            int creditreq = Integer.parseInt(c.getString(10));
            String faculty = c.getString(11);
            String slevel = c.getString(12);
            String instr = c.getString(13);
            int capacity = Integer.parseInt(c.getString(14));
            int reg = Integer.parseInt(c.getString(15));
            String cinfo = c.getString(16);
            t = new Course(crn2, cname, ccode, subject, semester, time, room, time1, room1, credit, creditreq, faculty, slevel, instr, capacity, reg, cinfo);

        }
        c.close();
        return t;

    }

    public String getCourseCrn (String ccode){
        // array of columns to fetch
        String[] columns = {"crn"};
        String selection = "ccode" + " = ?";
        String[] selectionArgs = {ccode};
        String crn="";
        //return the crn of the course with the given ccode
        Cursor c = db.query("courses", //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        c.moveToFirst();
        if (c.moveToFirst()) {
            crn = c.getString(0);
        }
        c.close();
        return crn;
    }

    public ArrayList<Course> getAllCourseslist() {
        String[] columns = {"crn", "cname", "ccode", "subject", "semester", "time", "room", "time1", "room1", "credit", "creditreq", "faculty", "slevel", "instr", "capacity", "reg", "cinfo"};
        ArrayList<Course> courseList = new ArrayList<Course>();

        Cursor c = db.query("courses", columns, null, null, null, null, null);
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                String crn = c.getString(0);
                String cname = c.getString(1);
                String ccode = c.getString(2);
                String subject = c.getString(3);
                String semester = c.getString(4);
                String time = c.getString(5);
                String room = c.getString(6);
                String time1 = c.getString(7);
                String room1 = c.getString(8);
                int credit = Integer.parseInt(c.getString(9));
                int creditreq = Integer.parseInt(c.getString(10));
                String faculty = c.getString(11);
                String slevel = c.getString(12);
                String instr = c.getString(13);
                int capacity = Integer.parseInt(c.getString(14));
                int reg = Integer.parseInt(c.getString(15));
                String cinfo = c.getString(16);
                Course x = new Course(crn, cname, ccode, subject, semester, time, room, time1, room1, credit, creditreq, faculty, slevel, instr, capacity, reg, cinfo);
                courseList.add(x);
            } while (c.moveToNext());
        }
        c.close();
        return courseList;
    }

    public HashMap<String, CourseTaken> getCoursesTaken(String username) {
        user x = getUserInfo(username);
        if (x.getUsertype().equals("student") ) {
            Student s = getStudentInfo(username);
            int sid = s.getSid();

            String[] columns = {"sid", "crn", "grade"};
            String selection = "sid" + " = ?";
            String[] selectionArgs = {String.valueOf(sid)};
            HashMap<String,CourseTaken> courseTakenList = new HashMap<>();

            Cursor c = db.query("takes", columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            if (c.moveToFirst()) {
                do {
                    int sid2 = Integer.parseInt(c.getString(0));
                    String crn = c.getString(1);
                    String grade = c.getString(2);

                    Course f = getCourseInfo(crn);

                    CourseTaken d = new CourseTaken(f);

                    d.setGrade(grade);
                    courseTakenList.put(d.crn, d);
                } while (c.moveToNext());
            }
            c.close();
            return courseTakenList;
        }
        return null;
    }

    public ArrayList<CourseTaken> getArrayCoursesGiven(String username) {
        user x = getUserInfo(username);
        if (x.getUsertype().equals("instructor") ) {
            String[] columns = {"username", "crn"};
            String selection = "username" + " = ?";
            String[] selectionArgs = {username};
            ArrayList<CourseTaken> courseTakenList = new ArrayList<>();

            Cursor c = db.query("gives", columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            if (c.moveToFirst()) {
                do {
                    String username2 = c.getString(0);
                    String crn = c.getString(1);

                    Course f = getCourseInfo(crn);

                    CourseTaken d = new CourseTaken(f);

                    courseTakenList.add(d);
                } while (c.moveToNext());
            }
            c.close();
            return courseTakenList;
        }
        return null;
    }

    public ArrayList<CourseTaken> getArrayCoursesTaken(String username) {
        user x = getUserInfo(username);
        if (x.getUsertype().equals("student") ) {
            Student s = getStudentInfo(username);
            int sid = s.getSid();

            String[] columns = {"sid", "crn", "grade"};
            String selection = "sid" + " = ?";
            String[] selectionArgs = {String.valueOf(sid)};
            ArrayList<CourseTaken> courseTakenList = new ArrayList<>();

            Cursor c = db.query("takes", columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            if (c.moveToFirst()) {
                do {
                    int sid2 = Integer.parseInt(c.getString(0));
                    String crn = c.getString(1);
                    String grade = c.getString(2);

                    Course f = getCourseInfo(crn);

                    CourseTaken d = new CourseTaken(f);

                    d.setGrade(grade);
                    courseTakenList.add(d);
                } while (c.moveToNext());
            }
            c.close();
            return courseTakenList;
        }
        return null;
    }

    public void dropCourse(String username, String crn) {
        user x = getUserInfo(username);
        if (x.getUsertype().equals("student")) {
            Student s = getStudentInfo(username);
            String sid = String.valueOf(s.getSid());
            String selection = "sid" + " = ?" + " AND " + "crn" + " = ?";
            String[] selectionArgs = {sid, crn};

            db.delete("takes", selection, selectionArgs);
        }
    }

    public void addCourse(String username,String crn){
        user x = getUserInfo(username);
        if (x.getUsertype().equals("student")) {
            Student s = getStudentInfo(username);
            String sid = String.valueOf(s.getSid());
            db.execSQL("insert into takes (sid, crn, grade)" + "values("+sid+","+crn+",null)");
        }
    }

    public void addGives(String username,String crn )    {
        user x = getUserInfo(username);
        if (x.getUsertype().equals("instructor")) {
            ContentValues val = new ContentValues();
            val.put("username", username);
            val.put("crn", crn);
            db.insert("gives", null, val);

            //db.execSQL("insert into gives (username,crn )" + "values("+username+","+crn+")");
        }

    }

    public ArrayList<String> checkTimeConflict(String username, String crn) {
        Student s = getStudentInfo(username);
        Course tobeRegistered = getCourseInfo(crn);
        String q = tobeRegistered.time;
        String x = tobeRegistered.time1;
        ArrayList<String> conflictedCourses = new ArrayList<String>();

        final HashMap<String, CourseTaken> myCourses = getCoursesTaken(username);
        if (q != null) {
            boolean addedToList;
            for (Map.Entry<String, CourseTaken> h : myCourses.entrySet()) {
                addedToList = false;
                String tmptime = h.getValue().time;
                String tmptime1 = h.getValue().time1;
                if (tmptime != null) {
                    if (timeConflict(tmptime, q)) {
                        conflictedCourses.add(h.getValue().ccode);
                        addedToList = true;
                    }
                }
                if (!addedToList && tmptime1 != null) {
                    if (timeConflict(tmptime1, q)) {
                        conflictedCourses.add(h.getValue().ccode);
                        addedToList = true;
                    }
                }
                if (!addedToList && x != null) {
                    if (tmptime != null) {
                        if (timeConflict(tmptime, x)) {
                            conflictedCourses.add(h.getValue().ccode);
                            addedToList = true;
                        }
                        if (!addedToList && tmptime1 != null) {
                            if (timeConflict(tmptime1, x)) {
                                conflictedCourses.add(h.getValue().ccode);
                                addedToList = true;
                            }
                        }
                    }
                }
            }

        }
        return conflictedCourses;
    }

    private boolean timeConflict(String tmptime, String q) {

        if(tmptime!=null && q != null)
        {
            String tmpday = tmptime.substring(q.length() - 1, q.length());
            String day = q.substring(q.length() - 1, q.length());
            if(tmpday.equals(day))
            {
                String tmpintervalstart = tmptime.substring(0,5);
                String tmpintervalend   = tmptime.substring(6,11);
                String qintervalstart   = q.substring(0,5);
                String qintervalend     = q.substring(6,11);
                try {
                    Date tmpstart   = parser.parse(tmpintervalstart);
                    Date tmpend     = parser.parse(tmpintervalend);
                    Date qstart     = parser.parse(qintervalstart);
                    Date qend       = parser.parse(qintervalend);
                    if(qstart.after(tmpstart) && qstart.before(tmpend))
                    {
                        return true;
                    }
                    else if(qend.after(tmpstart) && qend.before(tmpend))
                    {
                        return true;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
        return false;
    }

    public ArrayList<Student> getCourseStudents(String ccode) {

        String crn = getCourseCrn(ccode);

        String[] columns = {"sid", "crn", "grade"};
        String selection = "crn" + " = ?";
        String[] selectionArgs = {crn};
        ArrayList<Student> courseStudents = new ArrayList<>();

        Cursor c = db.query("takes", columns, selection, selectionArgs, null, null, null);
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                int sid2 = Integer.parseInt(c.getString(0));
                String crn2 = c.getString(1);
                String grade = c.getString(2);

                Student tmp = getStudentInfoWithSid(sid2);
                courseStudents.add(tmp);
            } while (c.moveToNext());
        }
        c.close();
        return courseStudents;
    }

    public void insertUser(user x) {
        ContentValues val = new ContentValues();
        val.put("username", x.getUsername());
        val.put("password", x.getPassword());
        val.put("usertype", x.getUsertype());
        val.put("name",     x.getName());
        val.put("lastname", x.getLastname());
        db.insert("users", null, val);
    }

    public void insertStudent(Student x) {
        ContentValues val = new ContentValues();
        val.put("sid", x.getSid());
        val.put("username", x.getUsername());
        val.put("major", x.getMajor());
        val.put("totalcredits", x.getTotalcredits());
        val.put("sname", x.getSname()); //sname=name +" "+lastname
        db.insert("students", null, val);
    }

    public void insertInstructor(String username , String fullname){
        ContentValues val = new ContentValues();
        val.put("username", username);
        val.put("fullname", fullname);
        db.insert("instructors", null, val);
    }

    public void insertCourse(Course x) {
        ContentValues val = new ContentValues();
        val.put("crn", x.getCrn());
        val.put("cname", x.getCname());
        val.put("ccode", x.getCcode());
        val.put("subject",  x.getSubject());
        val.put("semester", x.getSemester());
        val.put("time", x.getTime());
        val.put("room", x.getRoom());
        val.put("time1", x.getTime1());
        val.put("room1", x.getRoom1());
        val.put("credit", x.getCredit());
        val.put("creditreq", x.getCreditreq());
        val.put("faculty", x.getFaculty());
        val.put("slevel", x.getSlevel());
        val.put("instr", x.getInstr());
        val.put("capacity", x.getCapacity());
        val.put("reg", x.getReg());
        val.put("cinfo", x.getCinfo());
        db.insert("courses", null, val);

    }

    public void deleteUser(user c) {
        String username = c.getUsername();
        db.delete("users", "username=" + username, null);
    }

    public void SetGrade(String username,String ccode ,String grade){
        Student x = getStudentInfo(username);

        String sid = Integer.toString(x.getSid());
        String crn =  getCourseCrn(ccode);
        // selection criteria
        String whereClause = "sid" + " = ?" + " AND " + "crn" + " = ?";

        // where arguments
        String[] whereArgs = {sid, crn};

        ContentValues val = new ContentValues();
        val.put("grade",grade);
        db.update("takes",val,whereClause,whereArgs);

    }




}
