package home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.onboardingscreen.PostAdapter;
import com.example.test.onboardingscreen.PostAdapter1;
import com.example.test.onboardingscreen.PostModel;
import com.example.test.onboardingscreen.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment1 extends Fragment {
    private static final String TAG= "HomeFragment1";
    RecyclerView rv;
    ArrayList<PostModel> pmodel;
    ProgressDialog pd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home1,container,false);
        pmodel = new ArrayList<>();
        rv = view.findViewById(R.id.rv3);
        pd = new ProgressDialog(getContext());
        pd.setMessage("Loading...");
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("GlobalPosts");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for(DataSnapshot snapshot1 : snapshot.getChildren())
                    {
                        String imgURL = snapshot1.child("ImageUrl").getValue().toString();
                        String caption = snapshot1.child("Caption").getValue().toString();
                        pmodel.add(new PostModel(imgURL,caption));
                    }
                    PostAdapter1 postAdapter = new PostAdapter1(getContext(),pmodel);
                    rv.setAdapter(postAdapter);
                    rv.setLayoutManager(new LinearLayoutManager(getContext()));
                    pd.dismiss();
                }
                else
                    {
                        pd.dismiss();
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

        return view;
    }
}
