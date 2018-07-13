package omerkaramanli.sabanciuniv.edu.courseweb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    Button loginButton;
    Database db ;
    public static String globalPreferenceName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new Database(this);
        db.open();

        loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences(globalPreferenceName,MODE_PRIVATE).edit();
                EditText Edit1,Edit2;
                Edit1 = (EditText)findViewById(R.id.usernameEditText);
                Edit2 = (EditText)findViewById(R.id.paswordEditText);
                String username = Edit1.getText().toString();
                String password = Edit2.getText().toString();
                List<Course> s = db.getAllCourseslist();

                if(db.loginCheck(username,password))
                {
                    editor.putString("username",username);
                    editor.commit();
                    user x = db.getUserInfo(username);

                    if(x.getUsertype().equals("student")) {
                        clickLoginStudent();
                    }
                    else if(x.getUsertype().equals("admin"))
                    {
                        clickLoginAdmin();
                    }
                    else if(x.getUsertype().equals("instructor")) {
                        ArrayList<CourseTaken> courses = db.getArrayCoursesGiven(username);
                        clickLoginInstructor();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        db.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        db.close();
        super.onPause();
    }

    public void clickLoginStudent(){
       // if(student)
        Intent loginIntent = new Intent(this, MainPageActivity.class);
        this.startActivity(loginIntent);
    }
    public void clickLoginAdmin(){
        // if(admin)
        Intent addAdminIntent = new Intent(this, AdminMain.class);
        this.startActivity(addAdminIntent);
    }

    public void clickLoginInstructor() {
        Intent addInstructorIntent = new Intent(this, InstructorActivity.class);
        this.startActivity(addInstructorIntent);
    }
}
