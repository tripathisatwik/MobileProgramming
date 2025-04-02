package com.example.rectanglecalculator;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputFragment extends Fragment {

    private EditText lengthEditText;
    private EditText breadthEditText;
    private double length;
    private double breadth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        lengthEditText = view.findViewById(R.id.lengthEditText);
        breadthEditText = view.findViewById(R.id.breadthEditText);

        Button calculateAreaButton = view.findViewById(R.id.calculateArea);
        Button calculatePerimeterButton = view.findViewById(R.id.calculatePerimeter);

        calculateAreaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateArea();
            }
        });

        calculatePerimeterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePerimeter();
            }
        });

        return view;
    }


    private void calculateArea() {
        String lengthStr = lengthEditText.getText().toString();
        String breadthStr = breadthEditText.getText().toString();
        double area = Double.parseDouble(lengthStr) * Double.parseDouble(breadthStr);
        AreaFragment areaFragment = new AreaFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble("area", area);
        areaFragment.setArguments(bundle);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, areaFragment);
        transaction.commit();
    }

    private void calculatePerimeter() {
        String lengthStr = lengthEditText.getText().toString();
        String breadthStr = breadthEditText.getText().toString();
        double perimeter = 2 * (Double.parseDouble(lengthStr) + Double.parseDouble(breadthStr));
        PerimeterFragment perimeterFragment = new PerimeterFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble("perimeter", perimeter);
        perimeterFragment.setArguments(bundle);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, perimeterFragment);
        transaction.commit();
    }
}