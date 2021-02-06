package com.example.test.onboardingscreen;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment implements View.OnClickListener {

     View v;
    private Button strt,start1;
    View background;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v =  inflater.inflate(R.layout.fragment_home, container, false);

//        background.findViewById(R.id.background);
//        if(savedInstanceState==null){
//            background.setVisibility(View.INVISIBLE);
//            final ViewTreeObserver viewTreeObserver=background.getViewTreeObserver();
//            if(viewTreeObserver.isAlive()){
//                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                    @Override
//                    public void onGlobalLayout() {
//                        CircularReveal();
//                        background.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                    }
//                });
//            }
//        }

        strt=v.findViewById(R.id.strt);
        strt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questIntent=new Intent(getActivity(),QuestionActivity.class);
                startActivity(questIntent);
            }
        });
        start1=v.findViewById(R.id.start1);
        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questIntent=new Intent(getActivity(),QuestionActivity.class);
                startActivity(questIntent);
            }
        });

        return v;

    }

    @Override
    public void onClick(View v) {

    }

//    private void CircularReveal(){
//        int cx=background.getRight();
//        int cy=background.getHeight();
//
//        float finalRadius=(float)Math.hypot(background.getWidth(),background.getHeight());
//
//        Animator anim= ViewAnimationUtils.createCircularReveal(
//                background,cx,cy,0,finalRadius
//        );
//
//        background.setVisibility(View.VISIBLE);
//        anim.setDuration(3000);
//
//        anim.start();
//
//    }



}
