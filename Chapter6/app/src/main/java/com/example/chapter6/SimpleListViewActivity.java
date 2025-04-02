package com.example.chapter6;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SimpleListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_simple_list_view);

        ListView simpleListView = findViewById(R.id.simpleListView);
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
        SimpleListAdapter adapter = new SimpleListAdapter(
                this,
                title,
                description,
                images
        );
        simpleListView.setAdapter(adapter);


// If simple_custom_list_item.xml and SimpleListAdapter.java not defined or made
//        String[] fruits = {"Apple","Mango","Bananna", "Cherry", "Orange", "Grapes"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                fruits
//        );
//        simpleListView.setAdapter(adapter);
//        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(SimpleListViewActivity.this,
//                        "You Clicked: " + fruits[position],
//                        Toast.LENGTH_LONG
//                ).show();
//            }
//        });


    }
}