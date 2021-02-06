package Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.test.onboardingscreen.AddPost;
import com.example.test.onboardingscreen.R;

import Profile.ProfileActivity;
import list_ngo.list_of_ngo;
import network.network_social;
import post.post_social;
import home.social_media;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNvaigationViewHelper {
    private static final String TAG = "bottomNavigationViewHel";
    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: settingup bottomNavigationView ");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(true);
    }

    public static  void enableNavigation(final Context context,BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_search1:
                        Intent intent2 =new Intent(context, network_social.class);
                        context.startActivity(intent2);

                        break;
                    case R.id.nav_add1:
                        Intent intent3 =new Intent(context, AddPost.class);
                        context.startActivity(intent3);

                        break;
                    case R.id.nav_list_of_ngo:
                        Intent intent4 =new Intent(context, list_of_ngo.class);
                        context.startActivity(intent4);

                        break;
                    case R.id.nav_profile:
                        Intent intent5 =new Intent(context, ProfileActivity.class);
                        context.startActivity(intent5);

                        break;
                }


                return false;
            }
        });
    }
}
