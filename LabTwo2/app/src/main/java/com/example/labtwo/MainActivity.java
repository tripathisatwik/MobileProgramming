package com.example.labtwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView nameText = findViewById(R.id.nameData);
        TextView mailText = findViewById(R.id.emailData);
        TextView ageText = findViewById(R.id.ageData);
        RadioButton radioButtonMale = findViewById(R.id.radioMaleData);
        RadioButton radioButtonFemale = findViewById(R.id.radioFemaleData);
        Spinner courseSpinner = findViewById(R.id.spinnerData);
        CheckBox termsCheckbox = findViewById(R.id.termsCheckboxData);
        Button submitButton = findViewById(R.id.submitButtonData);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString().trim();
                String email = mailText.getText().toString().trim();
                String age = ageText.getText().toString().trim();

                String gender = radioButtonMale.isChecked()?"Male":"Female";

                String course = courseSpinner.getSelectedItem().toString();

                if (!termsCheckbox.isChecked()) {
                    Toast.makeText(MainActivity.this, "Please accept the terms & conditions", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (name.isEmpty() || email.isEmpty() || age.isEmpty() || gender.equals("Not Selected")) {
                    Toast.makeText(MainActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(MainActivity.this, com.example.labtwo.DisplayActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("age", age);
                intent.putExtra("gender", gender);
                intent.putExtra("course", course);
                startActivity(intent);

            }
        });
    }
}