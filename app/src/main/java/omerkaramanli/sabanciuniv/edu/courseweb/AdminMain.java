package omerkaramanli.sabanciuniv.edu.courseweb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminMain extends AppCompatActivity {
    Button addCourseButton;
    Button addAdminButton;
    Button addInstructorButton;
    Button addStuButton;
    Button adminEditGradeButton;
    Database db ;
    public static String globalPreferenceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        db = new Database(this);
        db.open();

        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName, MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);

        addCourseButton = (Button) findViewById(R.id.addCourseButton);
        addInstructorButton = (Button) findViewById(R.id.adminAddInstructorButton );
        adminEditGradeButton = (Button) findViewById(R.id.editGradesButton);
        addStuButton = (Button) findViewById(R.id.adminAddStudentButton);

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAddCourse();
            }
        });

        addInstructorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAddInstructor();
            }
        });

        adminEditGradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickEditGrades();
            }
        });

        addStuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAddStudent();
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
    public void clickAddCourse(){
        Intent addCourseIntent = new Intent (this, AdminAddCourse.class);
        this.startActivity(addCourseIntent);
    }

    public void clickAddInstructor(){
        Intent addInstIntent = new Intent(this, AdminAddInstructor.class);
        this.startActivity(addInstIntent);
    }

    public void clickAddStudent(){
        Intent addStuIntent = new Intent(this, AdminAddStudent.class);
        this.startActivity(addStuIntent);
    }

    public void clickEditGrades(){
        Intent editGradesIntent = new Intent(this, AdminEditGrades.class);
        this.startActivity(editGradesIntent);
    }
}
