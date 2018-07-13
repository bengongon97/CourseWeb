package omerkaramanli.sabanciuniv.edu.courseweb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class InstructorCourseAdapter extends ArrayAdapter {

    public InstructorCourseAdapter(Context context, ArrayList<CourseTaken> courseList){
        super(context, 0, courseList);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final Course selectedCourse = (Course) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.actual_profilelist, parent, false);
        }

        TextView courseName= convertView.findViewById(R.id.profileCourseTextView);
        courseName.setText(selectedCourse.cname);

        return convertView;
    }

}
