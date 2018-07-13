package omerkaramanli.sabanciuniv.edu.courseweb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import java.util.List;

class CourseListAdapter extends ArrayAdapter {

    TextView totalC;
    ArrayList<Course> tmp;

    public CourseListAdapter(Context context, ArrayList<Course> courseList, TextView totalCred, ArrayList<Course> courseL){
        super(context, 0, courseList);
        totalC=totalCred;
        tmp = courseL;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final Course selectedCourse = (Course) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.addlist, parent, false);
        }

        // Lookup view for data population

        TextView addName = convertView.findViewById(R.id.cnameAddTextView);
        TextView ccred = convertView.findViewById(R.id.ccredAddTextView);
        final CheckBox checkIt = convertView.findViewById(R.id.addCheckBox);
        ImageButton infoBtn = convertView.findViewById(R.id.infoButton);
        //Button addButton = convertView.findViewById(R.id.addCoursesButton);


        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedCourse.getTime1()==null)
                {
                    Toast.makeText(getContext(), "Time: "+selectedCourse.getTime()+" Room: "+selectedCourse.getRoom(),  Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(getContext(), "Time 1: "+selectedCourse.getTime()+" Room 1: "+selectedCourse.getRoom()
                            +"Time 2: "+selectedCourse.getTime1() + " Room 2: " + selectedCourse.getRoom1(),  Toast.LENGTH_LONG).show();
                }
            }
        });




        checkIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Course temp= (Course) getItem(position);
                if(checkIt.isChecked()) {
                    int tCr= Integer.parseInt(totalC.getText().toString()); //0 at first.
                    int selectedCred= selectedCourse.credit;
                    if(tCr+selectedCred>20){
                        Toast.makeText(getContext(), "Total credits cannot exceed 20!",  Toast.LENGTH_SHORT).show();
                        checkIt.setChecked(false);
                    }
                    else {
                        tCr += selectedCred;
                        totalC.setText(tCr + "");
                        tmp.add(selectedCourse);
                    }
                }
                else {
                    tmp.remove(selectedCourse);
                    int tCr= Integer.parseInt(totalC.getText().toString());
                    int selectedCred= selectedCourse.credit;
                    tCr -= selectedCred;
                    totalC.setText(tCr+"");
                }
            }
        });


        addName.setText(selectedCourse.ccode);
        ccred.setText(selectedCourse.getCreditStr());

        return convertView;
    }
}
