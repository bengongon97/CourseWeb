package omerkaramanli.sabanciuniv.edu.courseweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminAddCourse extends AppCompatActivity {
    Database db ;
    Button addCourse ;

    EditText Ecrn, Eccode, Ecname, Einstructor;

    EditText Eroom1,Eday1, Etime1;

    EditText Eroom2,Eday2, Etime2;

    EditText Ecredit,EcReq, Efaculty, Ecapacity,Einfo;

    public static String globalPreferenceName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_course);
        db = new Database(this);
        db.open();

        addCourse= findViewById(R.id.createCourseButton);

        Ecrn= findViewById(R.id.adminCRNEditExt);
        Ecname= findViewById(R.id.adminCNameDitText);
        Eccode=findViewById(R.id.adminCCodeEditText);
        Einstructor = findViewById( R.id.adminInstructor );

        Eroom1=findViewById(R.id.adminRoomEditText);
        Eday1=findViewById(R.id.adminDayEditText);
        Etime1=findViewById( R.id.adminTimeEditText );

        Eroom2=findViewById(R.id.adminRoom1EditText);
        Eday2=findViewById(R.id.adminDay1EditText);
        Etime2=findViewById( R.id.AdminTime1EditText );

        Ecredit = findViewById( R.id.adminCreditEditText );
        EcReq= findViewById( R.id.adminCreditReqEditText);
        Efaculty = findViewById( R.id.adminFacultyEditText);
        Ecapacity = findViewById( R.id.adminCapacityEditText);
        Einfo = findViewById( R.id.adminCInfoEditText);

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String crn = Ecrn.getText().toString();
                String cname = Ecname.getText().toString();
                String ccode = Eccode.getText().toString();
                String room1 = Eroom1.getText().toString();
                String day1 = Eday1.getText().toString();
                String time1 = Etime1.getText().toString();

                time1 += " " + day1;

                String room2 = Eroom2.getText().toString();
                String day2 = Eday2.getText().toString();
                String time2 = Etime2.getText().toString();

                time2 += " " + day2;

                String instr = Einstructor.getText().toString();
                String faculty = Efaculty.getText().toString();
                String info = Einfo.getText().toString();

                String instFullName;
                user instructor = db.getUserInfo(instr);
                if (instructor == null)
                    Toast.makeText(AdminAddCourse.this, "Instructor does not exist! Try adding" +
                            "an instructor first! Or correct your input lan!", Toast.LENGTH_SHORT).show();
                else {
                    instFullName = instructor.getName() + " " + instructor.getLastname();
                if(crn.equals( "" ))
                    Toast.makeText( AdminAddCourse.this, "Crn cannot be blank!", Toast.LENGTH_SHORT ).show();
                if(ccode.equals( "" ))
                    Toast.makeText( AdminAddCourse.this, "Course code cannot be blank!", Toast.LENGTH_SHORT ).show();
                if(cname.equals( "" ))
                    Toast.makeText( AdminAddCourse.this, "Course name cannot be blank!", Toast.LENGTH_SHORT ).show();
                if(room1.equals( "" ))
                    Toast.makeText( AdminAddCourse.this, "Room cannot be blank!", Toast.LENGTH_SHORT ).show();
                if(day1.equals( "" ))
                    Toast.makeText( AdminAddCourse.this, "Day code cannot be blank!", Toast.LENGTH_SHORT ).show();
                if(time1.equals( "" ))
                    Toast.makeText( AdminAddCourse.this, "Time code cannot be blank!", Toast.LENGTH_SHORT ).show();

                    int index = ccode.indexOf(" ");
                    String subject = ccode.substring(0, index);


                    try {
                        int credit = Integer.parseInt(Ecredit.getText().toString());
                        int creditReq = Integer.parseInt(EcReq.getText().toString());
                        int capacity = Integer.parseInt(Ecapacity.getText().toString());
                        //int crnInt = Integer.parseInt(crn);

                        try{
                            Course temp = new Course(crn, cname, ccode, subject, "2017-2018", time1, room1, time2, room2, credit, creditReq, faculty, "undergrad", instFullName, capacity, 0, info);
                            db.insertCourse(temp);
                            db.addGives(instr,crn);

                            Toast.makeText(AdminAddCourse.this, "Course " + temp.ccode +" added successfully!", Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception y) {
                            Toast.makeText(AdminAddCourse.this, "Somehow, it cannot be added to database!", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(AdminAddCourse.this, "Credit/Credit Req/Capacity must be integer!", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }
    @Override
    public void onResume()
    {
        db.open();
        super.onResume();
    }
    @Override
    protected void onPause() {
        db.close();
        super.onPause();
    }
}
