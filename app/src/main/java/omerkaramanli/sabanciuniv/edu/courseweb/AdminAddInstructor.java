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

public class AdminAddInstructor extends AppCompatActivity {
    Database db ;
    public static String globalPreferenceName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_user);
        db = new Database(this);
        db.open();

        //SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName, MODE_PRIVATE);
        //final String username = sharedPreferences.getString("username", null);

        Button addUser = findViewById(R.id.addUserButton);
        final EditText Eusername = findViewById(R.id.usernameEditText);
        final EditText Ename = findViewById(R.id.nameEditText);
        final EditText Elastname = findViewById(R.id.lastnameEditText);
        final EditText Epassword = findViewById(R.id.passwordEditText);

        ArrayList<String> userTypeArray = new ArrayList<>();
        userTypeArray.add("admin");
        userTypeArray.add("student");
        userTypeArray.add("instructor");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AdminAddInstructor.this, android.R.layout.simple_spinner_item, userTypeArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inLastname = Elastname.getText().toString();
                String inName = Ename.getText().toString();
                String inPassword = Epassword.getText().toString();
                String inUsername = Eusername.getText().toString();
                final user tmp = new user(inUsername,inPassword,"student",inName,inLastname);

                if(inUsername.equals( "" ))
                    Toast.makeText(AdminAddInstructor.this, "Username cannot be left blank!", Toast.LENGTH_SHORT).show();
                else if (inName.equals( "" ) || inLastname.equals( "" ))
                    Toast.makeText(AdminAddInstructor.this, "Name or last name cannot be left blank!", Toast.LENGTH_SHORT).show();
                else if(inPassword.equals( "" ))
                    Toast.makeText(AdminAddInstructor.this, "Please provide a password", Toast.LENGTH_SHORT).show();
                else {
                    if (db.getUserInfo( inUsername ) == null) {
                        db.insertUser( tmp );
                        db.insertInstructor( inUsername, inName + " " + inLastname );
                        Toast.makeText( AdminAddInstructor.this, "User successfully added, check it!", Toast.LENGTH_SHORT ).show();
                    } else {
                        Toast.makeText( AdminAddInstructor.this, "User already exists, sorry! Try again!", Toast.LENGTH_SHORT ).show();
                    }
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
