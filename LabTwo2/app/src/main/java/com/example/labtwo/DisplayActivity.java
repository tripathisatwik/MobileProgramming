package com.example.labtwo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display);

        TextView nameView = findViewById(R.id.nameView);
        TextView emailView = findViewById(R.id.emailView);
        TextView ageView = findViewById(R.id.ageView);
        TextView genderView = findViewById(R.id.genderView);
        TextView courseView = findViewById(R.id.courseView);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String age = getIntent().getStringExtra("age");
        String gender = getIntent().getStringExtra("gender");
        String course = getIntent().getStringExtra("course");

        nameView.setText("Name: " + name);
        emailView.setText("Email: " + email);
        ageView.setText("Age: " + age);
        genderView.setText("Gender: " + gender);
        courseView.setText("Course: " + course);

    }
}