package Profile;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.onboardingscreen.R;

import java.util.ArrayList;

public class AccountSettingsActivity  extends AppCompatActivity {
    private static final String TAG = "AccountSettingsActivity";
    private Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        mContext = AccountSettingsActivity.this;
        Log.d(TAG, "onCreate: started");
       setupSettingList();
}
 private void setupSettingList(){
     Log.d(TAG, "setupSettingList: initializing'Account Settings' list.");
     ListView listView =(ListView) findViewById(R.id.lvAccountSettings);
     ArrayList<String> options = new ArrayList<>();
     options.add(getString(R.string.edit_profile));
     options.add(getString(R.string.sign_out));

     ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1,options);
     listView.setAdapter(adapter);
 }
}
