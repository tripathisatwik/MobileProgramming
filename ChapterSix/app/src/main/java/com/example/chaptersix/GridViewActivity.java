package com.example.chaptersix;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GridViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grid_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        GridView gridView = findViewById(R.id.gridView);
//        String[] names = {"Ram","Shayam","Hari","Sita"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                this,
//                R.layout.simple_girdview_item,
//                R.id.gridView,
//                names
//        );
//        gridView.setAdapter(adapter);

        String[] title = {"Title 1","Title 2","Title 3","Title 4","Title 5","Title 6","Title 7","Title 8"};
        String[] description = {"Description 1","Description 2","Description 3","Description 4","Description 5","Description 6","Description 7","Description 8"};
        int[] images = {
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background
        };
        CustomGridAdapter customGridAdapter = new CustomGridAdapter(this,title,description,images);
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(customGridAdapter);
    }
}