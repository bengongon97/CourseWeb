package omerkaramanli.sabanciuniv.edu.courseweb;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Pop extends Activity{

    Database db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new Database(this);
        db.open();

        SharedPreferences sharedPreferences = getSharedPreferences(CoursesActivity.globalPreferenceName, MODE_PRIVATE);
        String crn = sharedPreferences.getString("crn", null);

        setContentView(R.layout.activity_course_info);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width * 4/5, height * 3/5);


        TextView courseName = findViewById(R.id.courseNameInfo);
        TextView coursePlace = findViewById(R.id.placeCourse);
        TextView courseTime = findViewById(R.id.TimeCourse);
        TextView instructorName = findViewById(R.id.instructorName);
        TextView courseInfo = findViewById(R.id.courseInfoDetailed);



    }
}
