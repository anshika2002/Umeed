package network;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.test.onboardingscreen.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import Utils.BottomNvaigationViewHelper;

public class network_social extends AppCompatActivity {
    private static final String TAG ="Network Activity";
    private static final int ACTIVITY_NUM= 1;

    private Context mcontext = network_social.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_social);
        Log.d(TAG, "onCreate: started");

    }
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView ");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottom_nav);
        BottomNvaigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);

        BottomNvaigationViewHelper.enableNavigation(mcontext,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}