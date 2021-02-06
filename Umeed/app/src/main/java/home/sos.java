package home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test.onboardingscreen.R;

public class sos extends Fragment {
    private static final String TAG = "sos";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sos,container,false);
        return view;
        counter = view.findViewById(R.id.counter);
        start = view.findViewById(R.id.start_service);
        stop = view.findViewById(R.id.stop_service);
        phone = view.findViewById(R.id.phone_num);
        save = view.findViewById(R.id.save_phone);

        phone.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);

        if (getPreference(getApplicationContext(),"Pref_key") == null){
            phone.setVisibility(View.VISIBLE);
            save.setVisibility(View.VISIBLE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if (!((checkSelfPermission(Manifest.permission.SEND_SMS ) == PackageManager.PERMISSION_GRANTED)
            && (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION ) == PackageManager.PERMISSION_GRANTED)
            && (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED))){
                requestPermissions(new String[] {Manifest.permission.SEND_SMS,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.getText().toString() == null){
                    phone.setError("Please ");
                }else {
                    setPreference(getApplicationContext(),"Pref_key",phone.getText().toString());
                    Toast.makeText(MainActivity.this, "Number Saved", Toast.LENGTH_SHORT).show();
                    phone.setVisibility(View.INVISIBLE);
                    save.setVisibility(View.INVISIBLE);
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShakeService.class);
                //Start Service
                //startService(intent);
                intent.setAction("START_SERVICE");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    getApplicationContext().startForegroundService(intent);
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShakeService.class);
                //Start Service
                //startService(intent);
                intent.setAction("STOP_SERVICE");
                startService(intent);
            }
        });
    }

    public static void setPreference(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPreference(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, null);
    }
}
