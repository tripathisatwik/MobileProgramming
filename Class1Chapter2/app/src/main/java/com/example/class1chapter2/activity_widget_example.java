package com.example.class1chapter2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class activity_widget_example extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_widget_example);

        TextView textViewOne  = findViewById(R.id.testView1);
        Button button1 = findViewById(R.id.button1);
        EditText editText = findViewById(R.id.editText1);
        CheckBox checkBoxOne = findViewById(R.id.checkboxOne);
        RadioButton radioButtonMale = findViewById(R.id.radioBtnMale);
        RadioButton radioButtonFemale = findViewById(R.id.radioBtnFemale);
        Spinner spinner = findViewById(R.id.spinner);

        String[] courses = {"C","C#","Java","Kotlin","Swift"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                courses
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        String gender = "Not selected";

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //textViewOne.setText("Welcome! " + enteredValue);
                //Toast.makeText(activity_widget_example.this, "Welcome! " + enteredValue, Toast.LENGTH_SHORT).show();
                String gender = "Not Selected";
                String enteredValue = editText.getText().toString();// User inputted string
                gender = radioButtonMale.isChecked()?"Male":"Female";
                boolean isChecked = checkBoxOne.isChecked();// Either true or false
                if(isChecked){
                    textViewOne.setText("User Inputted Value: " + enteredValue
                            + "\n Gender: " + gender
                            + "\n Course: " + spinner.getSelectedItem().toString());
                }else {
                    Snackbar.make(v,
                                    "Please accpect the terms and conditions",
                                    Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });

        checkBoxOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "Checkbox State: " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
    }
}