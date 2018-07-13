package omerkaramanli.sabanciuniv.edu.courseweb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DayActivity extends AppCompatActivity {
    Database db;
    ArrayList<String> S ;
    String GUN ;
   private String[] lv_arr = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        //these two lines for database communication
        db = new Database(this);
        db.open();
        //these two lines for getting the usernmae of the user who logged in
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName,MODE_PRIVATE);
        String username = sharedPreferences.getString("username",null);
        Bundle dayIntent = getIntent().getExtras() ;
        Intent bilgiIntent = getIntent() ;

        List<CourseTaken> a =  db.getArrayCoursesTaken(username) ;
        GUN    = dayIntent.getString("GUN") ;
        S = new ArrayList<String>() ;
        S = (ArrayList<String>) getIntent().getSerializableExtra("BILGI");


        ListView listview = (ListView) findViewById(R.id.DayList) ;
        TextView textView = (TextView) findViewById(R.id.textView2);
        TextView textView1 = (TextView) findViewById(R.id.textView3);
        TextView textView2 = (TextView) findViewById(R.id.Gun);

        if (S != null ) {
            lv_arr = S.toArray(new String[0]);

            String x = GUN;
            textView2.setText(x);

            listview.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, lv_arr));
        }


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
}
