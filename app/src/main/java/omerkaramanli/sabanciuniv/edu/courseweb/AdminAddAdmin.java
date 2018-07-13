package omerkaramanli.sabanciuniv.edu.courseweb;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AdminAddAdmin extends AppCompatActivity {
    Database db ;
    public static String globalPreferenceName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_admin);
        db = new Database(this);
        db.open();

        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.globalPreferenceName, MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);

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
