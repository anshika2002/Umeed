package home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.test.onboardingscreen.PostAdapter1;
import com.example.test.onboardingscreen.PostModel;
import com.example.test.onboardingscreen.R;
import com.google.android.material.tabs.TabLayout;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import Utils.BottomNvaigationViewHelper;

public class social_media extends AppCompatActivity {
    private static final String TAG = "social_media";
    private static final int ACTIVITY_NUM= 0;
    private Context mcontext = social_media.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);
        Log.d(TAG, "onCreate: starting");
        setupBottomNavigationView();
        setupViewPager();



    }
    /**
     * for profile and message
     */
    private void setupViewPager(){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment1());
        adapter.addFragment(new sos());
        adapter.addFragment(new MessageFragment());
       ViewPager viewPager =(ViewPager) findViewById(R.id.container);
       viewPager.setAdapter(adapter);

        TabLayout tabLayout =(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_sos);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_notifications_active_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_send_24);


    }
    /**
     * bottomNavigationView setup
     */
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