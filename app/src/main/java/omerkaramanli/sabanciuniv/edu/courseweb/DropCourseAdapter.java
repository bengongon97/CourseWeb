package omerkaramanli.sabanciuniv.edu.courseweb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DropCourseAdapter extends ArrayAdapter {

    TextView totalC;
    ArrayList<CourseTaken> dropC;

    public DropCourseAdapter(Context context, ArrayList<CourseTaken> takenCourses, TextView totalCred, ArrayList<CourseTaken> toBeDropped){
        super(context, 0, takenCourses);
        totalC=totalCred;
        dropC = toBeDropped;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final CourseTaken selectedCourse = (CourseTaken) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.droplist, parent, false);
        }

        // Lookup view for data population
        TextView courseName = convertView.findViewById(R.id.cnameDropTextView);
        final CheckBox checkIt = convertView.findViewById(R.id.dropCheckBox);

        checkIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkIt.isChecked()){
                    dropC.add(selectedCourse);
                    int tCr= Integer.parseInt(totalC.getText().toString());
                    int selectedCred= selectedCourse.credit;
                    tCr -= selectedCred;
                    totalC.setText(tCr+"");
                }
                else if(!checkIt.isChecked()) {
                    dropC.remove(selectedCourse);
                    int tCr= Integer.parseInt(totalC.getText().toString());
                    int selectedCred= selectedCourse.credit;
                    tCr += selectedCred;
                    totalC.setText(tCr+"");
                }
            }
        });

        courseName.setText(selectedCourse.ccode);
        notifyDataSetChanged();
        return convertView;
    }
}