package omerkaramanli.sabanciuniv.edu.courseweb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoursesActivity extends AppCompatActivity {

    Database db ;
    ListView addCourseList;
    TextView totalCredits;
    Button addCourseButton;
    static Integer myCredits;
    public static String globalPreferenceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        db = new Database(this);
        db.open();

        final SharedPreferences.Editor editor = getSharedPreferences(globalPreferenceName,MODE_PRIVATE).edit();
        final ArrayList<Course> temp= new ArrayList<>(); //courses to be added, determined in courselist adapter

        //these two lines for getting the usernmae of the user who logged in
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName,MODE_PRIVATE);
        final String username = sharedPreferences.getString("username",null);

        //IMPORTANT FUNCTION
        //it retunrs all the courses as a list. look Course CLASS also
        final ArrayList<Course> courseslist = db.getAllCourseslist();

       // final ArrayList<CourseTaken> myCourses= db.getCoursesTaken(username);

        final HashMap<String, CourseTaken> myCourses = db.getCoursesTaken(username);

        Integer crd=0;
        for (Map.Entry<String,CourseTaken> h: myCourses.entrySet()){
            crd+=h.getValue().credit;
        }
        myCredits=crd;

        totalCredits = (TextView) findViewById(R.id.creditsListView);
        String credits= crd.toString();
        totalCredits.setText(credits);


        addCourseList = (ListView) findViewById(R.id.addcoursesListView);
        addCourseButton = (Button) findViewById(R.id.addCoursesButton);


        addCourseList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Course item = (Course) addCourseList.getItemAtPosition(position);
                String infoOfTheCourse = item.getCinfo();
                String instructorOfIt = item.getInstr();
                Toast.makeText(CoursesActivity.this, "Course info: "+ infoOfTheCourse + " and instructor is " + instructorOfIt ,Toast.LENGTH_LONG).show();


                return true;
            }
        });

        /*addCourseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Course item = (Course) addCourseList.getItemAtPosition(position);
                String infoOfTheCourse = item.getCinfo();
                String instructorOfIt = item.getInstr();
                Toast.makeText(CoursesActivity.this, "Course info: "+ infoOfTheCourse + " and instructor is " + instructorOfIt ,Toast.LENGTH_LONG).show();

            }
        });*/

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                for (Course c: temp) {
                    CourseTaken x = new  CourseTaken(c);

                    if(! myCourses.containsKey(x.crn) ) { //does not exist in list
                        ArrayList<String> conflictlist = db.checkTimeConflict(username,x.crn);
                        if(conflictlist.isEmpty()) {
                            db.addCourse(username, x.crn);
                            myCourses.put(x.crn, x);
                            count++;
                        }
                        else
                        {
                            String conflictingcourses = "";
                            for (int i=0; i<conflictlist.size(); i++)
                            {
                                conflictingcourses = conflictingcourses + " " + conflictlist.get(i);
                            }
                            Toast.makeText(CoursesActivity.this, "The course "+ x.ccode + " conflicts with " + conflictingcourses + "!" ,Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(CoursesActivity.this, "You have already taken "+ x.cname + " !",Toast.LENGTH_SHORT).show();
                    }

                }
                if(count > 0)
                Toast.makeText(CoursesActivity.this, "Addition successful! You have added " + count + " course(s).",Toast.LENGTH_SHORT).show();

            }
        });

        CourseListAdapter adapter = new CourseListAdapter(this,courseslist,totalCredits, temp );

        addCourseList.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        //IMPORTANT FUNCTION
        //it adds course to takes table with entred username and crn
       // db.addCourse(username,"crn");//change crn accordingly

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
