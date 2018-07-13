package omerkaramanli.sabanciuniv.edu.courseweb;

/**
 * Created by sev on 27.05.2018.
 */

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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdminAddStudent extends AppCompatActivity {
    Database db ;
    public static String globalPreferenceName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_student);
        db = new Database(this);
        db.open();

        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName, MODE_PRIVATE);
        final String username = sharedPreferences.getString("username", null);
        /*
        * USER TABLE FIELDS: username, password, usertype, name, lastname
        * STUDENT TABLE FIELDS: sid, username, major, totalcredits, sname
        * Totalcredist:0 initially. Major is taken as input.
        * Usertype= student.
        * */

        Button addUser = findViewById(R.id.adminAddStudentButton);
        final EditText Eusername = findViewById(R.id.studentUsernameEditText);
        final EditText Ename = findViewById(R.id.studentNameEditText);
        final EditText Elastname = findViewById(R.id.studentSurnameEditText);
        final EditText Epassword = findViewById(R.id.studentPasswordEditText);
        final EditText Esid= findViewById( R.id.studenIDEditText);
        final EditText Emajor = findViewById( R.id.studentMajor );


        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inLastname = Elastname.getText().toString();
                String inName = Ename.getText().toString();
                String inPassword = Epassword.getText().toString();
                String inUsername = Eusername.getText().toString();
                String inMajor= Emajor.getText().toString();
                String inSid = Esid.getText().toString();
                try {
                    if(inUsername.equals( "" )) {
                        Toast.makeText(AdminAddStudent.this, "Username cannot be empty!", Toast.LENGTH_SHORT).show();
                    }
                    if(inPassword.equals( "" ))
                    {
                        Toast.makeText(AdminAddStudent.this, "Password cannot be empty!", Toast.LENGTH_SHORT).show();
                    }
                    if(inName.equals( "" ) || inLastname.equals( "" ))
                    {
                        Toast.makeText(AdminAddStudent.this, "Name or Last Name cannot be empty!", Toast.LENGTH_SHORT).show();
                    }
                    if(inSid.equals( "" ))
                    {
                        Toast.makeText(AdminAddStudent.this, "Sid cannot be empty!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        int sid = Integer.parseInt( inSid );
                        final user tmp = new user( inUsername, inPassword, "student", inName, inLastname );
                        final Student stu = new Student( sid, inUsername, inMajor, 0, inName + " " + inLastname );

                        if (db.getUserInfo( inUsername ) == null) {
                            db.insertUser( tmp );
                            db.insertStudent(stu);
                            Toast.makeText( AdminAddStudent.this, "User successfully added, check it!", Toast.LENGTH_SHORT ).show();
                        }
                        // }
                        else {
                            Toast.makeText( AdminAddStudent.this, "User already exists, sorry! Try again!", Toast.LENGTH_SHORT ).show();
                        }
                    }
                }
                catch (Exception e) {
                    Toast.makeText(AdminAddStudent.this, "Student ID must be an Integer!", Toast.LENGTH_SHORT).show();
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
