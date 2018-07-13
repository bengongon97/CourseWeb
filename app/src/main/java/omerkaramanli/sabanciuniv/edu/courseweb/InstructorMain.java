package omerkaramanli.sabanciuniv.edu.courseweb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InstructorMain extends AppCompatActivity {
    Button insEditGrade;
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
    }

    public void clickEditGrade(){
        Intent editGradeIntent = new Intent(this, InstructorEditGrade.class);
        this.startActivity(editGradeIntent);
    }
}
