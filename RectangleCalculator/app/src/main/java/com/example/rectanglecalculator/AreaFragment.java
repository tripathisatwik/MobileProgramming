package com.example.rectanglecalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AreaFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area, container, false);
        TextView resultText = view.findViewById(R.id.areaResult);

        if (getArguments() != null && getArguments().containsKey("area")) {
            double area = getArguments().getDouble("area");
            resultText.setText("Area: " + area);
        } else {
            resultText.setText("Area not calculated.");
        }

        return view;
    }
}
