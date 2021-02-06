package list_ngo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.test.onboardingscreen.ChatMainActivity;
import com.example.test.onboardingscreen.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


import Utils.BottomNvaigationViewHelper;

public class list_of_ngo extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListNombres;
    private HashMap<String, Contacto> listaContactos;
    private int lastExpandedPosition = -1;


    private static final String TAG ="list of NGOs";
    private static final int ACTIVITY_NUM= 3;

    private Context mcontext = list_of_ngo.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_ngo);
        Log.d(TAG, "onCreate: started");




        init();

        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastExpandedPosition != -1 && groupPosition != lastExpandedPosition){
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });


        /**
         expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
        @Override
        public void onGroupCollapse(int groupPosition) {
        Toast.makeText(getBaseContext(),"List Collapsed:" +
        expandableListNombres.get(groupPosition),Toast.LENGTH_SHORT).show();
        }
        });
         expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v,
        int groupPosition, int childPosition, long id) {
        Toast.makeText(getBaseContext(),
        expandableListNombres.get(groupPosition) +
        " ---> " + listaContactos.get(expandableListNombres.get(groupPosition))
        ,Toast.LENGTH_SHORT).show();
        return false;
        }
        });
         */

    }

    private void init() {
        this.expandableListView = findViewById(R.id.expandableListView);
        this.listaContactos = getContactos();
        this.expandableListNombres = new ArrayList<>(listaContactos.keySet());
        this.expandableListAdapter = new CustomExpandableListAdapter(this, expandableListNombres, listaContactos);

    }



    private HashMap<String, Contacto> getContactos() {
        HashMap<String, Contacto> listaC = new HashMap<>();

        listaC.put("SEWA :\nThey  work for women workers’ rights and try to launch demonstrations in a non-violent way. ", new Contacto("0512-36521",
                "ngo@gmail.com", "INDIA", R.drawable.img_11));
        listaC.put("Action Aid India :\nThe center caters to domestic and sexual violence victims.", new Contacto("0512-36521",
                "ngo@gmail.com", "INDIA", R.drawable.img_12));
        listaC.put("Majlis Manch :\nCenter provides socio-legal support to victims of sexual abuse.", new Contacto("0512-36521",
                "ngo@gmail.com", "INDIA", R.drawable.img_13));
        listaC.put("Sayodhya Home For Women In Need :\nThe shelter works closely with police and judicial official.", new Contacto("0512-36521",
                "ngo@gmail.com", "INDIA", R.drawable.img_11));
        listaC.put("Guria India :\nHelps women victims to get legal justice", new Contacto("0512-36521",
                "ngo@gmail.com", "INDIA", R.drawable.img_12));
        listaC.put("Snehalaya :\nSnehalaya focuses on empowering women, children as well as LGBT communities.", new Contacto("0512-36521",
                "ngo@gmail.com", "INDIA", R.drawable.img_13));
        listaC.put("Azad Foundation :\nThey provide women with a six months program that includes self- awareness, defense training, sexuality, and reproductive rights, etc", new Contacto("0512-36521",
                "ngo@gmail.com", "INDIA", R.drawable.img_11));
        listaC.put(" MAKAM :\nThe Mahila Kissan Adhikar Manch helps landless women farmers in raising the voice for their rights.", new Contacto("0512-36521",
                "ngo@gmail.com", "INDIA", R.drawable.img_12));
        listaC.put(" Aasra :\nOrganization for women who are lonely, distressed and have suicidal thoughts", new Contacto("0512-36521",
                "sewa@gmail.com", "INDIA", R.drawable.img_13));


        return listaC;



    }
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView ");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottom_nav);
        BottomNvaigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);

        BottomNvaigationViewHelper.enableNavigation(mcontext,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
}}