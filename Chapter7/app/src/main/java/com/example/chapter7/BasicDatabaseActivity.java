package com.example.chapter7;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class BasicDatabaseActivity extends AppCompatActivity {
    BasicDatabaseHelper basicDatabaseHelper;
    Button insertBtn, showBtn;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_basic_database);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        basicDatabaseHelper = new BasicDatabaseHelper(this);
        listView = findViewById(R.id.listView);
        insertBtn = findViewById(R.id.insertBtn);
        showBtn = findViewById(R.id.showBtn);

        insertBtn.setOnClickListener(v->{
            basicDatabaseHelper.insertUser("User" + (int)(Math.random()*1000));
        });
        showBtn.setOnClickListener(v -> {
            ArrayList<String> users = basicDatabaseHelper.getALlUsers();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,users);
            listView.setAdapter(adapter);
        });
    }
}