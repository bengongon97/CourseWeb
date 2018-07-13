package omerkaramanli.sabanciuniv.edu.courseweb;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DropCourseActivity extends AppCompatActivity {

    Database db ;
    static int whichChange = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_course);
        //these two lines for database communication
        db = new Database(this);
        db.open();
        //these two lines for getting the usernmae of the user who logged in
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName,MODE_PRIVATE);
        final String username = sharedPreferences.getString("username",null);

        //IMPORTANT FUNCTION TO DISLAY COUSES ENROLLED
        //it retuns the list of classes that are enrolled by this user
        // it returns as courseTaken object refer to course taken class it includes all cousse info plus grade
        HashMap<String, CourseTaken> tmp = db.getCoursesTaken(username);

        final TextView crdtview = findViewById(R.id.creditsListView2);
        final TextView displayMessage = findViewById(R.id.displayMessage);

        final ArrayList<CourseTaken> takenCourses = db.getArrayCoursesTaken(username);

        final ArrayList<CourseTaken> tobeDropped = new ArrayList<>();

        final ListView myCourses= findViewById(R.id.dropcoursesListView);
        DropCourseAdapter adapter= new DropCourseAdapter(this,takenCourses,crdtview,tobeDropped);
        myCourses.setAdapter(adapter);

        //To get how many credits
        Integer crd=0;
        for (CourseTaken h: takenCourses){
            crd+=h.credit;
        }

        String credits= crd.toString();
        crdtview.setText(credits);

        Button dropButton = findViewById(R.id.dropCourseButton);
        dropButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                for (CourseTaken c: tobeDropped) {
                    db.dropCourse(username,c.crn);
                    takenCourses.remove(c);
                    whichChange++;
                }

                if(whichChange == 0)
                    displayMessage.setText("You have not done any changes!");
                else {
                    displayMessage.setText("You have dropped " + whichChange + " course(s).");
                    //crdtview.setText();
                }
                whichChange = 0;
            }

        });

        //by entreing the crn into this funtion you drop class
        //IMPORTANT FUNTION FOR DROP COURSE->
        // db.dropCourse(username,"crn");//change crn value accordingly
        adapter.notifyDataSetChanged();
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
