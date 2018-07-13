package omerkaramanli.sabanciuniv.edu.courseweb;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class AdminEditGrades extends AppCompatActivity {
    Database db ;
    Button gradeBttn;
    EditText grade;

    public static String globalPreferenceName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_grades);
        db = new Database(this);
        db.open();

        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName, MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);

        db= new Database(this);
        db.open();

        ArrayList<Course> courses = db.getAllCourseslist();
        ArrayList<String> courseList = new ArrayList<String>();

        for (Course c: courses) {
            courseList.add(c.ccode);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner cItems = (Spinner) findViewById(R.id.selectCourseSpinner);
        cItems.setAdapter(adapter);

        final String[] selectedCourse = new String[1];
        final String[] selectedStudent = new String[1];

        cItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                selectedCourse[0] =selectedItem;

                ArrayList<Student> students = db.getCourseStudents(selectedItem);

                ArrayList<String> studentList = new ArrayList<>();
                if(students.size()==1){
                    gradeBttn.setEnabled(false);
                }
                else {
                    gradeBttn.setEnabled(true);
                    for (Student s : students) {
                        studentList.add(s.username);
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AdminEditGrades.this, android.R.layout.simple_spinner_item, studentList);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner sItems = (Spinner) findViewById(R.id.studentSelectSpinner);
                sItems.setAdapter(adapter);

                sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selectedStu = parent.getItemAtPosition(position).toString();
                        selectedStudent[0] = selectedStu;
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                       // gradeBttn.setEnabled(false);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gradeBttn = findViewById(R.id.adminGradeButton);
        gradeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedStudent[0].equals( "" ))
                    Toast.makeText(AdminEditGrades.this, "You have to select a student!", Toast.LENGTH_SHORT).show();
                else if(selectedCourse[0].equals( "" ))
                    Toast.makeText(AdminEditGrades.this, "You have to select a course!", Toast.LENGTH_SHORT).show();
                else {
                    grade = findViewById( R.id.gradeEditText );
                    String letterGrade = grade.getText().toString();
                    letterGrade = letterGrade.toUpperCase();

                    if (letterGrade.equals( "A" ) || letterGrade.equals( "A-" ) || letterGrade.equals( "B+" ) || letterGrade.equals( "B" ) || letterGrade.equals( "B-" )
                            || letterGrade.equals( "C+" ) || letterGrade.equals( "C" ) || letterGrade.equals( "C-" ) || letterGrade.equals( "D+" ) || letterGrade.equals( "D" )
                            || letterGrade.equals( "F" )) {
                        db.SetGrade( selectedStudent[0], selectedCourse[0], letterGrade );
                        Toast.makeText( AdminEditGrades.this, "Grade change is successful.", Toast.LENGTH_SHORT ).show();
                    }
                    else
                        Toast.makeText( AdminEditGrades.this, "It is not a valid grade! Try again!", Toast.LENGTH_SHORT ).show();

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
