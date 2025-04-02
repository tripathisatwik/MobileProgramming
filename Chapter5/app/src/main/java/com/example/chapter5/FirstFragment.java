package com.example.chapter5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class FirstFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment,container,false);
        EditText firstEditText = view.findViewById(R.id.firstText);
        EditText secondEditText = view.findViewById(R.id.secondText);
        Button submitBtn  = view.findViewById(R.id.button);
        TextView resultTextView = view.findViewById(R.id.resultText);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstTxt = firstEditText.getText().toString();
                String secondTxt = secondEditText.getText().toString();
                resultTextView.setText("Result: " + firstTxt + " " + secondTxt);
                Snackbar.make(v, "Button Clicked!", Snackbar.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
