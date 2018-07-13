package omerkaramanli.sabanciuniv.edu.courseweb;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScheduleActivity extends AppCompatActivity {
    Button monday;
    Button tuesday;
    Button wednesday;
    Button thursday;
    Button friday;
    Database db ;
    String username ;
    public final static String BILGI = "BILGI";
    public final static String GUN   = "GUN" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        //these two lines for database communication
        db = new Database(this);
        db.open();
        //these two lines for getting the usernmae of the user who logged in
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName,MODE_PRIVATE);
        username = sharedPreferences.getString("username",null);
        monday=(Button)findViewById(R.id.mondayButton);
        tuesday=(Button)findViewById(R.id.tuesdayButton);
        wednesday=(Button)findViewById(R.id.wednesdayButton);
        thursday=(Button)findViewById(R.id.thursdayButton);
        friday=(Button)findViewById(R.id.fridayButton);

        //Since day infos are embedded in time variables of courses i didn't finish thi part yet but you will get a COURSE(class) List so you can implement according to that
        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    clickDayM();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    clickDayT();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

        wednesday.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                try {
                    clickDayW();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        thursday.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                try {
                    clickDayR();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        friday.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                try {
                    clickDayF();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.O)
    public void clickDayM() throws ParseException {
        Intent dayIntent = new Intent(this, DayActivity.class);
        //Intent dayIntent1 = new Intent(this, DayActivity.class);
        List<CourseTaken> a = db.getArrayCoursesTaken(username);
        String w1 = "Monday";
        dayIntent.putExtra(GUN,w1) ;
        //startActivity(dayIntent);
        ArrayList<String> array = new ArrayList<String>();
        for (int i = 0; i < a.size(); i++) // loop to store the time of the classes
        {
            CourseTaken b = a.get(i);
            String q = b.time;
            String x = b.time1;
            String Maddee = b.cname ;

            if (q != null) {
                if (q.substring(q.length() - 1, q.length()).equals("M")) // as the day of the class is defined to be the last charecter of the time
                {

                    String w;

                    w = b.cname;
                    w = w + "   " + b.time.substring(0, b.time.length() - 1);
                    w = w + "  " + b.room ;
                    array.add(w);
                    //dayIntent.putExtra(BILGI,w) ;
                    ///startActivity(dayIntent);

                }
            }
            if (x != null) {
                if (x.substring(x.length() - 1, x.length()).equals("M")) // as the day of the class is defined to be the last charecter of the time
                {
                    String w;

                    w = b.cname;
                    w = w + "   " + b.time1.substring(0, b.time1.length() - 1);
                    w = w +"  "+  b.room1 ;
                    array.add(w);
                }
            }
        }
        array = Sorter(array) ;
        dayIntent.putExtra(BILGI,array) ;
        startActivity(dayIntent);
    }

    public void clickDayT() throws ParseException {
        Intent dayIntent = new Intent(this, DayActivity.class);
        // Intent dayIntent1 = new Intent(this, DayActivity.class);
        List<CourseTaken> a = db.getArrayCoursesTaken(username);
        String w1 = "Tuesday";
        dayIntent.putExtra(GUN,w1) ;
        // startActivity(dayIntent);
        ArrayList array = new ArrayList<String>();
        for (int i = 0; i < a.size(); i++) // loop to store the time of the classes
        {
            CourseTaken b = a.get(i);
            String q = b.time;
            String x = b.time1;
            if (q != null) {
                if (q.substring(q.length() - 1, q.length()).equals("T")) // as the day of the class is defined to be the last charecter of the time
                {

                    String w;

                    w = b.cname;
                    w = w + "   " + b.time.substring(0, b.time.length() - 1);
                    w = w + "  " + b.room ;
                    array.add(w);
                    //dayIntent.putExtra(BILGI,w) ;
                    ///startActivity(dayIntent);

                }
            }
            if (x != null) {
                if (x.substring(x.length() - 1, x.length()).equals("T")) // as the day of the class is defined to be the last charecter of the time
                {
                    String w;

                    w = b.cname;
                    w = w + "   " + b.time1.substring(0, b.time1.length() - 1);
                    w = w +"  "+  b.room1 ;
                    array.add(w);
                }
            }
        }

        ArrayList array1 ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            array = Sorter(array) ;
        }
        dayIntent.putExtra(BILGI,array) ;
        startActivity(dayIntent);
        // finish();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void clickDayW() throws ParseException {
        Intent dayIntent = new Intent(this, DayActivity.class);
        //Intent dayIntent1 = new Intent(this, DayActivity.class);
        List<CourseTaken> a = db.getArrayCoursesTaken(username);
        String w1 = "Wedensday";
        dayIntent.putExtra(GUN,w1) ;
        //startActivity(dayIntent1);
        ArrayList<String> array = new ArrayList<String>();
        for (int i = 0; i < a.size(); i++) // loop to store the time of the classes
        {
            CourseTaken b = a.get(i);
            String q = b.time;
            String x = b.time1;
            if (q != null) {
                if (q.substring(q.length() - 1, q.length()).equals("W")) // as the day of the class is defined to be the last charecter of the time
                {

                    String w;

                    w = b.cname;
                    w = w + "   " + b.time.substring(0, b.time.length() - 1);
                    w = w + "  " + b.room ;
                    array.add(w);
                    //dayIntent.putExtra(BILGI,w) ;
                    ///startActivity(dayIntent);

                }
            }
            if (x != null) {
                if (x.substring(x.length() - 1, x.length()).equals("W")) // as the day of the class is defined to be the last charecter of the time
                {
                    String w;

                    w = b.cname;
                    w = w + "   " + b.time1.substring(0, b.time1.length() - 1);
                    w = w +"  "+  b.room1 ;
                    array.add(w);
                }
            }
        }
        array = Sorter(array) ;
        dayIntent.putExtra(BILGI,array) ;
        startActivity(dayIntent);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void clickDayR() throws ParseException {
        Intent dayIntent = new Intent(this, DayActivity.class);
        Intent dayIntent1 = new Intent(this, DayActivity.class);
        List<CourseTaken> a = db.getArrayCoursesTaken(username);
        String w1 = "Thursday";
        dayIntent.putExtra(GUN,w1) ;
        //startActivity(dayIntent1);
        ArrayList<String> array = new ArrayList<String>();
        for (int i = 0; i < a.size(); i++) // loop to store the time of the classes
        {
            CourseTaken b = a.get(i);
            String q = b.time;
            String x = b.time1;
            if (q != null) {
                if (q.substring(q.length() - 1, q.length()).equals("R")) // as the day of the class is defined to be the last charecter of the time
                {

                    String w;

                    w = b.cname;
                    w = w + "   " + b.time.substring(0, b.time.length() - 1);
                    w = w + "  " + b.room ;
                    array.add(w);
                    //dayIntent.putExtra(BILGI,w) ;
                    ///startActivity(dayIntent);

                }
            }
            if (x != null) {
                if (x.substring(x.length() - 1, x.length()).equals("R")) // as the day of the class is defined to be the last charecter of the time
                {
                    String w;

                    w = b.cname;
                    w = w + "   " + b.time1.substring(0, b.time1.length() - 1);
                    w = w +"  "+  b.room1 ;
                    array.add(w);
                }
            }
        }
        array = Sorter(array) ;
        dayIntent.putExtra(BILGI,array) ;
        startActivity(dayIntent);
    }
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void clickDayF() throws ParseException {
        Intent dayIntent = new Intent(this, DayActivity.class);
        //Intent dayIntent1 = new Intent(this, DayActivity.class);
        List<CourseTaken> a = db.getArrayCoursesTaken(username);
        String w1 = "Friday";
        dayIntent.putExtra(GUN,w1) ;
        //startActivity(dayIntent1);
        ArrayList<String> array = new ArrayList<String>();
        for (int i = 0; i < a.size(); i++) // loop to store the time of the classes
        {
            CourseTaken b = a.get(i);
            String q = b.time;
            String x = b.time1;
            if (q != null) {
                if (q.substring(q.length() - 1, q.length()).equals("F")) // as the day of the class is defined to be the last charecter of the time
                {

                    String w;

                    w = b.cname;
                    w = w + "   " + b.time.substring(0, b.time.length() - 1);
                    w = w + "  " + b.room ;
                    array.add(w);
                    //dayIntent.putExtra(BILGI,w) ;
                    ///startActivity(dayIntent);

                }
            }
            if (x != null) {
                if (x.substring(x.length() - 1, x.length()).equals("F")) // as the day of the class is defined to be the last charecter of the time
                {
                    String w;

                    w = b.cname;
                    w = w + "   " + b.time1.substring(0, b.time1.length() - 1);
                    w = w +"  "+  b.room1 ;
                    array.add(w);

                }
            }
        }
        array = Sorter(array) ;
        dayIntent.putExtra(BILGI,array) ;
        startActivity(dayIntent);
    }
    public boolean stringContainsNumber( String s )
    {
        Pattern p = Pattern.compile( "[0-9]" );
        Matcher m = p.matcher( s );

        return m.find();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList Sorter(ArrayList<String> a) throws ParseException {
        ArrayList<String> b = new ArrayList<String>() ;
        String[] splited = new String[0];
        for (int i = 0 ; i < a.size() ; i++)
        {
            splited = a.get(i).split("\\s+");
            String splite =  "";
            for (int j = 0 ; j < splited.length ; j++) {
                if (stringContainsNumber(splited[j]))
                {
                    splite = splited[j];
                    break;
                }
            }
            String [] v ;
            v = splite.split("-") ;
            b.add(v[0] + " " + i) ;

        }

        DateFormat formatter = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
        ArrayList<Date> dates = new ArrayList<Date>();

        Collections.sort(b);

        ArrayList<String> c = new ArrayList<String>() ;

        for(int i = 0 ; i<b.size();i++){

            String tahseen = b.get(i);
            char a1 = tahseen.charAt(tahseen.length()-1) ;
            int Index = a1 - '0' ;
            c.add(a.get(Index)) ;
        }
        return c ;
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
