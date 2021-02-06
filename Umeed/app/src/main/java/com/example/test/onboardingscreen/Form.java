package com.example.test.onboardingscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class Form extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5;
    TextInputEditText te1;
    RadioGroup radioGroup;
    RadioButton selectedRadioButton;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        e1 = findViewById(R.id.enter_name);
        e2 = findViewById(R.id.enter_city);
        e3 = findViewById(R.id.enter_phone);
        e4 = findViewById(R.id.enter_email);
        e5 = findViewById(R.id.enter_pin);
        te1 = findViewById(R.id.enter_message);

        radioGroup = findViewById(R.id.rg);

        b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedRbText = "abc";
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    selectedRadioButton = findViewById(selectedRadioButtonId);
                    selectedRbText = selectedRadioButton.getText().toString();
                } else {
                    Toast.makeText(Form.this, "Select", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(Form.this,ChatMainActivity.class);
                i.putExtra("name",e1.getText().toString());
                i.putExtra("city",e2.getText().toString());
                i.putExtra("phone",e3.getText().toString());
                i.putExtra("email",e4.getText().toString());
                i.putExtra("pin",e5.getText().toString());
                i.putExtra("message", Objects.requireNonNull(te1.getText()).toString());
                i.putExtra("gender",selectedRbText);
                startActivity(i);
            }
        });

    }
}