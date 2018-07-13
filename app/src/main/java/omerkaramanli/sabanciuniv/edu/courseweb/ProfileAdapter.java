package omerkaramanli.sabanciuniv.edu.courseweb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileAdapter extends ArrayAdapter {

    public ProfileAdapter (Context context, ArrayList<CourseTaken> myCourses){
        super(context, 0, (List) myCourses);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Course selectedCourse = (Course) getItem(position);
        final CourseTaken takenCourse = (CourseTaken) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.profilelist, parent, false);
        }

        TextView courseName= convertView.findViewById(R.id.profileCourseTextView);
        TextView gradeView= convertView.findViewById(R.id.profileCourseGradeView);

        gradeView.setText(takenCourse.grade);
        courseName.setText(selectedCourse.cname);

        return  convertView;
    }



}
