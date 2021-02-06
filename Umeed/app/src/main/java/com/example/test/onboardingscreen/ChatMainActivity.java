package com.example.test.onboardingscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.midi.MidiOutputPort;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatMainActivity extends AppCompatActivity {


    List<MessageChatModel> messageChatModelList =  new ArrayList<>();
    RecyclerView recyclerView;
    MessageChatAdapter adapter ;
    TextView t1,t2,t3,t4,t5,t6,t7;
    EditText messageET;
    ImageView sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        messageET = (EditText)findViewById(R.id.messageET);
        sendBtn = (ImageView) findViewById(R.id.sendBtn);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(ChatMainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        t1 = findViewById(R.id.name);
        t2 = findViewById(R.id.gender);
        t3 = findViewById(R.id.city);
        t4 = findViewById(R.id.pincode);
        t5 = findViewById(R.id.phone);
        t6 = findViewById(R.id.email);
        t7 = findViewById(R.id.message);

        t1.setText(getIntent().getStringExtra("name"));
        t2.setText(getIntent().getStringExtra("gender"));
        t3.setText(getIntent().getStringExtra("city"));
        t4.setText(getIntent().getStringExtra("pin"));
        t5.setText(getIntent().getStringExtra("phone"));
        t6.setText(getIntent().getStringExtra("email"));
        t7.setText(getIntent().getStringExtra("message"));

        MessageChatModel model1 = new MessageChatModel(
                "Hello.I am facing an issue that i wanted to share",
                "10:00 PM",
                0
        );
        MessageChatModel model2 = new MessageChatModel(
                "hello, we are Manas mental health ngo and we are always there to help u, tell us what's the issue.",
                "10:02 PM",
                1
        );
        MessageChatModel model3 = new MessageChatModel(
                "I live in this village named pokhar and my family members are torturing me physically and mentally everyday, i need help urgently, please help me.",
                "10:03 PM",
                0
        );
        MessageChatModel model4 = new MessageChatModel(
                "We will definitely help you please give us the following details needed for us to take any step officially.",
                "10:04 PM",
                1
        );


        messageChatModelList.add(model1);
        messageChatModelList.add(model2);
        messageChatModelList.add(model3);
        messageChatModelList.add(model4);
        messageChatModelList.add(model1);
        messageChatModelList.add(model2);
        messageChatModelList.add(model3);
        messageChatModelList.add(model4);
        messageChatModelList.add(model1);
        messageChatModelList.add(model2);
        messageChatModelList.add(model3);
        messageChatModelList.add(model4);

        recyclerView.smoothScrollToPosition(messageChatModelList.size());
        adapter = new MessageChatAdapter(messageChatModelList, ChatMainActivity.this );
        recyclerView.setAdapter(adapter);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = messageET.getText().toString();

                MessageChatModel model = new MessageChatModel(
                        msg,
                        "10:00 PM",
                        0
                );
                messageChatModelList.add(model);
                recyclerView.smoothScrollToPosition(messageChatModelList.size());
                adapter.notifyDataSetChanged();
                messageET.setText("");


            }
        });

    }
}
