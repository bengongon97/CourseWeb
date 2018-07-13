package omerkaramanli.sabanciuniv.edu.courseweb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    Button schedule;
    Button dropCourse;
    Database db ;
    ListView myCoursesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
      //these two lines for database communication
        db = new Database(this);
        db.open();
        //these two lines for getting the usernmae of the user who logged in
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName,MODE_PRIVATE);
        String username = sharedPreferences.getString("username",null);

        //IMPORTANT FUNCTION TO DISLAY COUSES ENROLLED
        //it retuns the list of classes that are enrolled by this user
        // it returns as courseTaken object refer to course taken class it includes all cousse info plus grade
        ArrayList<CourseTaken> tmp = db.getArrayCoursesTaken(username);

        //Populate the listview by using the courses taken
        myCoursesList = (ListView) findViewById(R.id.pcourseListView);
        ProfileAdapter adapter= new ProfileAdapter(this,tmp);
        myCoursesList.setAdapter(adapter);

        schedule=(Button)findViewById(R.id.scheduleButton);
        dropCourse=(Button)findViewById(R.id.dropButton);

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSchedule();
            }
        });

        dropCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickDrop();
            }
        });

        adapter.notifyDataSetChanged();
    }
    public void clickSchedule(){
        Intent scheduleIntent = new Intent(this, ScheduleActivity.class);
        this.startActivity(scheduleIntent);
    }

    public  void clickDrop(){
        Intent dropIntent = new Intent(this, DropCourseActivity.class);
        this.startActivity(dropIntent);
    }
    @Override
    protected void onResume() {
        db.open();
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName,MODE_PRIVATE);
        String username = sharedPreferences.getString("username",null);
        ArrayList<CourseTaken> tmp = db.getArrayCoursesTaken(username);

        //Populate the listview by using the courses taken
        myCoursesList = (ListView) findViewById(R.id.pcourseListView);
        ProfileAdapter adapter= new ProfileAdapter(this,tmp);
        myCoursesList.setAdapter(adapter);

        schedule=(Button)findViewById(R.id.scheduleButton);
        dropCourse=(Button)findViewById(R.id.dropButton);

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSchedule();
            }
        });

        dropCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickDrop();
            }
        });

        adapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    protected void onPause() {
        db.close();
        super.onPause();
    }

}
