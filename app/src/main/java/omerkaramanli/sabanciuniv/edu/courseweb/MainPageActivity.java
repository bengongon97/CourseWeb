package omerkaramanli.sabanciuniv.edu.courseweb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainPageActivity extends AppCompatActivity {

    Button courseButton;
    Button profileButton;

    Database db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //these two lines for database communication
        db = new Database(this);
        db.open();
        //these two lines for getting the usernmae of the user who logged in
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName, MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);

        user x = db.getUserInfo(username); // you can access the user info from this object


        courseButton = (Button) findViewById(R.id.coursesButton);
        profileButton = (Button) findViewById(R.id.profileButton);

        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCourses();
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickProfile();
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
    public void clickCourses(){
        Intent courseIntent = new Intent(this, CoursesActivity.class);
        this.startActivity(courseIntent);
    }

    public void clickProfile(){
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        this.startActivity(profileIntent);
    }


}
