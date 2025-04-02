package com.example.chapter5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class CustomDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custom_dialog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button customDialogBtn = findViewById(R.id.customDialogBtn);
        customDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogFragment customDialogFragment = new CustomDialogFragment();
                customDialogFragment.setDialogListner(new CustomDialogFragment.CustomDialogListner(){
                    @Override
                    public void onDialogResult(String input){
                        Toast.makeText(
                                CustomDialogActivity.this,
                                "Results: " + input,
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        Button customDialogBtn2 = findViewById(R.id.customDialogBtn2);
        customDialogBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogFragment customDialogFragment = new CustomDialogFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                customDialogFragment.show(fragmentManager, "Custon_dialog");
            }
        });
    }
    private void showCustomDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View customDialogView = inflater.inflate(R.layout.custom_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Calculate Sum");
        builder.setView(customDialogView); //Seting custom view into Alert Window

        EditText editText1 = customDialogView.findViewById(R.id.editTextNum1);
        EditText editText2 = customDialogView.findViewById(R.id.editTextNum2);
        Button button = customDialogView.findViewById(R.id.calculateResultBtn);
        TextView resultView  = customDialogView.findViewById(R.id.calculateResultTv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Perform Calculations
                    String num1 = editText1.getText().toString();
                    String num2 = editText2.getText().toString();
                    int result = Integer.parseInt(num1) + Integer.parseInt(num2);

                    //Set result in TextView
                    resultView.setText("Result: " + result);
                    resultView.setText(String.valueOf(result));
                } catch (NumberFormatException e) {
                    resultView.setText("Invalid input");
                }
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}