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
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {
    private CustomDialogListner listner;

    public interface CustomDialogListner{
        void onDialogResult(String input);
    }

    public void setDialogListner(CustomDialogListner customDialogListner){
        this.listner = customDialogListner;
    }

    @NonNull
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){
        View dialogView  = inflater.inflate(R.layout.custom_dialog,container,false);
        EditText editText1 = dialogView.findViewById(R.id.editTextNum1);
        EditText editText2 = dialogView.findViewById(R.id.editTextNum2);
        Button button = dialogView.findViewById(R.id.calculateResultBtn);
        TextView resultView  = dialogView.findViewById(R.id.calculateResultTv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Perform Calculations
                String num1 = editText1.getText().toString();
                String num2 = editText2.getText().toString();

                int result = Integer.parseInt(num1) + Integer.parseInt(num2);
                listner.onDialogResult("Result: " + result);
                //Set result in TextView
                resultView.setText("Result: " + result);
            }
        });
        return dialogView;
    }
}
