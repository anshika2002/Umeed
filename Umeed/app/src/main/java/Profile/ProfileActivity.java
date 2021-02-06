package Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.onboardingscreen.PostAdapter;
import com.example.test.onboardingscreen.PostModel;
import com.example.test.onboardingscreen.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import Utils.BottomNvaigationViewHelper;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private static  final int ACTIVITY_NUM = 4;
    RecyclerView rv;
    ArrayList<PostModel> pm;
    FirebaseAuth auth;
    ProgressDialog pd;
    private  Context mContext = ProfileActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started");
        auth = FirebaseAuth.getInstance();
        rv = findViewById(R.id.recycle_view2);
        pd = new ProgressDialog(this);
        pm = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(auth.getUid())
                .child("Posts");
        pd.setMessage("Loading....");
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for(DataSnapshot snapshot1 : snapshot.getChildren())
                    {
                        String imgUrl = snapshot1.child("ImageUrl").getValue().toString();
                        String caption = snapshot1.child("Caption").getValue().toString();
                        pm.add(new PostModel(imgUrl,caption));
                    }
                    PostAdapter postAdapter = new PostAdapter(mContext,pm);
                    rv.setAdapter(postAdapter);
                    rv.setLayoutManager(new GridLayoutManager(mContext,3));
                    pd.dismiss();
                }
                else
                    {
                        pd.dismiss();
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
        setupBottomNavigationView();
        setupToolbar();
    }
        /**
         * Responsible for setting up the profile toolbar
         */
        private void setupToolbar(){
            Toolbar toolbar =(Toolbar) findViewById(R.id.profileToolBar);
            setSupportActionBar(toolbar);

            ImageView profileMenu =(ImageView) findViewById(R.id.profileMenu);
            profileMenu.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: navigating to account settings.");
                    Intent intent = new Intent(mContext, AccountSettingsActivity.class);
                    startActivity(intent);

                }
            });

        }




    /**
 * BottomNavigationView setup
 */
        private void setupBottomNavigationView(){
            Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView ");
            BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottom_nav);
            BottomNvaigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);

            BottomNvaigationViewHelper.enableNavigation(mContext,bottomNavigationViewEx);
            Menu menu = bottomNavigationViewEx.getMenu();
            MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
            menuItem.setChecked(true);


}


    }