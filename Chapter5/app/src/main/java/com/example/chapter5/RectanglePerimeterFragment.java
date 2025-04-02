package com.example.chapter5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RectanglePerimeterFragment extends Fragment {
    TextView perimeterTextView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rectangle_perimeter, container, false);
        perimeterTextView = view.findViewById(R.id.perimeterTextView);;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            double perimeter = getArguments().getDouble("perimeter", 0);
            perimeterTextView.setText("Perimeter: " + perimeter);
        }
    }
}