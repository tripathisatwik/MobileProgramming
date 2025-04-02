package com.example.rectanglecalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PerimeterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perimeter, container, false);
        TextView resultText = view.findViewById(R.id.perimeterResult);

        if (getArguments() != null && getArguments().containsKey("perimeter")) {
            double perimeter = getArguments().getDouble("perimeter");
            resultText.setText("Perimeter: " + perimeter);
        } else {
            resultText.setText("Perimeter not calculated.");
        }

        return view;
    }
}
