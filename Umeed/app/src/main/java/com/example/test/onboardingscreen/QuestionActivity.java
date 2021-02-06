package com.example.test.onboardingscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class QuestionActivity extends AppCompatActivity {

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();

    private TextView ques,qno;
    private LinearLayout options;
    private Button next;

    private int count=0,position=0,most=0;

    private List<QuestionModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ques=findViewById(R.id.ques);
        qno=findViewById(R.id.qno);
        options=findViewById(R.id.optll);
        next=findViewById(R.id.b5);

        list= new ArrayList<>();


        list= new ArrayList<>();
        list.add(new QuestionModel("Assess your self-worth and confidence on a scale of 10 ","8-10","1-3","3-5","5-8","1-3"));
        list.add(new QuestionModel("Assess your sleep quality on a scale of 10 ","8-10","3-5","1-3","5-8","1-3"));
        list.add(new QuestionModel("Assess your self-control impulsiveness on a scale of 10 ","8-10","3-5","5-8","1-3","1-3"));
        list.add(new QuestionModel("Assess your emotional resilience on a scale of 10 ","8-10","1-3","5-8","3-5","1-3"));
//        list.add(new QuestionModel("Question 5","A","B","C","D","B"));
//        list.add(new QuestionModel("Question 6","A","B","C","D","A"));
//        list.add(new QuestionModel("Question 7","A","B","C","D","D"));
//        list.add(new QuestionModel("Question 8","A","B","C","D","A"));
//        list.add(new QuestionModel("Question 9","A","B","C","D","C"));
//        list.add(new QuestionModel("Question 10","A","B","C","D","B"));

        for(int i=0;i<4;i++){
            options.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    measure((Button)v);
                }
            });
        }

        playAnim(ques,0,list.get(position).getQuestion());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.setEnabled(false);
                next.setAlpha(0.7f);
                enableOptn(true);
                position++;
                if(position==list.size()){
                    Intent assesmentResult=new Intent(QuestionActivity.this,CircularGauge.class);
                    startActivity(assesmentResult);
                    finish();
                    return;
                }
                count=0;
                playAnim(ques,0,list.get(position).getQuestion());
            }
        });

    }

    private  void playAnim(final View view, final int value, final String data){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(value==0&&count<4){
                    String optn="";
                    if(count==0){
                        optn=list.get(position).getOption1();
                    }
                    else if(count==1){
                        optn=list.get(position).getOption2();
                    }
                    else if(count==2){
                        optn=list.get(position).getOption3();
                    }
                    else if(count==3){
                        optn=list.get(position).getOption4();
                    }

                    playAnim(options.getChildAt(count),0,optn);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                if(value==0){
                    try{
                        ((TextView)view).setText(data);
                        qno.setText(position+1+"/"+list.size());
                    }catch (ClassCastException ex){
                        ((Button)view).setText(data);
                    }
                    view.setTag(data);
                    playAnim(view,1,data);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void measure(Button selectedOptn){
        enableOptn(false);
        next.setEnabled(true);
        next.setAlpha(1);
        selectedOptn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#084887")));
        if(selectedOptn.getText().toString().equals(list.get(position).getHighest())){
            //highest threat
            most++;
        }
//        else{
//            Button highestOptn= (Button) options.findViewWithTag(list.get(position).getHighest());
//            highestOptn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#084887")));
//        }

    }
    private void enableOptn(boolean enable){
        for(int i=0;i<4;i++){
            options.getChildAt(i).setEnabled(enable);
            if(enable){
                options.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#999999")));
            }
        }

    }
}