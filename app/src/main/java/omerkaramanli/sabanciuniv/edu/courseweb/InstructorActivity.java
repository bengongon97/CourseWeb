package omerkaramanli.sabanciuniv.edu.courseweb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class InstructorActivity extends AppCompatActivity {
    Button insEditGrade;
    Database db;
    ListView myCoursesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_main);

        insEditGrade = (Button) findViewById(R.id.insEditGradesButton);
        insEditGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickEditGrade();
            }
        });

        db= new Database(this);
        db.open();
        //these two lines for getting the usernmae of the user who logged in
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName,MODE_PRIVATE);
        String username = sharedPreferences.getString("username",null);
        ArrayList<CourseTaken> tmp = db.getArrayCoursesGiven(username);


        //Populate the listview by using the courses taken
        myCoursesList = (ListView) findViewById(R.id.instructorCoursesListView);
        InstructorCourseAdapter adapter= new InstructorCourseAdapter(this,tmp);
        myCoursesList.setAdapter(adapter);

    }

    public void clickEditGrade(){
        Intent editGradeIntent = new Intent(this, InstructorEditGrade.class);
        this.startActivity(editGradeIntent);
    }
}