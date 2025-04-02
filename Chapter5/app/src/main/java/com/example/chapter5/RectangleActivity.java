package com.example.chapter5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

public class RectangleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rectangle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText lengthEditText = findViewById(R.id.lengthEditText);
        EditText widthEditText = findViewById(R.id.widthEditText);
        Button calculateButton = findViewById(R.id.calculateResultBtn);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double length = Double.parseDouble(lengthEditText.getText().toString());
                double width = Double.parseDouble(widthEditText.getText().toString());

                double area = length * width;
                double perimeter = 2 * (length + width);

                RectangleAreaFragment areaFragment = new RectangleAreaFragment();
                Bundle areaBundle = new Bundle();
                areaBundle.putDouble("area", area);
                areaFragment.setArguments(areaBundle);

                RectanglePerimeterFragment perimeterFragment = new RectanglePerimeterFragment();
                Bundle perimeterBundle = new Bundle();
                perimeterBundle.putDouble("perimeter", perimeter);
                perimeterFragment.setArguments(perimeterBundle);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.area_container, areaFragment);
                transaction.replace(R.id.perimeter_container, perimeterFragment);
                transaction.commit();
            }
        });
    }
}
