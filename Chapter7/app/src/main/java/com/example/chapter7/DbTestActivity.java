package com.example.chapter7;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DbTestActivity extends AppCompatActivity {

    EditText idEt, nameEt, addressEt;
    Button insertBtn, selectBtn, updateBtn, deleteBtn;
    RecyclerView recyclerView;
    RecordAdapter adapter;
    List<Record> recordList;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_db_test_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DbHelper(this);
        idEt = findViewById(R.id.idEt);
        nameEt = findViewById(R.id.nameEt);
        addressEt = findViewById(R.id.addressEt);
        insertBtn = findViewById(R.id.insertBtn);
        selectBtn = findViewById(R.id.selectBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        recyclerView = findViewById(R.id.resRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recordList = new ArrayList<>();
        adapter = new RecordAdapter(recordList);
        recyclerView.setAdapter(adapter);

        insertBtn.setOnClickListener(v -> {
            String idStr = idEt.getText().toString().trim();
            String name = nameEt.getText().toString().trim();
            String address = addressEt.getText().toString().trim();

            if (!idStr.isEmpty() && !name.isEmpty() && !address.isEmpty()) {
                int id = Integer.parseInt(idStr);
                long result = dbHelper.insertData(id, name, address);
                if (result != -1) {
                    clearInputFields();
                    loadRecords(); // Reload data after insertion
                }
            } else {
                Toast.makeText(DbTestActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        selectBtn.setOnClickListener(v -> {
            loadRecords();
        });

        updateBtn.setOnClickListener(v -> {
            String idStr = idEt.getText().toString().trim();
            String name = nameEt.getText().toString().trim();
            String address = addressEt.getText().toString().trim();

            if (!idStr.isEmpty() && !name.isEmpty() && !address.isEmpty()) {
                int rowsAffected = dbHelper.updateData(idStr, name, address);
                if (rowsAffected > 0) {
                    clearInputFields();
                    loadRecords();
                }
            } else {
                Toast.makeText(DbTestActivity.this, "Please fill ID, Name, and Address to update", Toast.LENGTH_SHORT).show();
            }
        });

        deleteBtn.setOnClickListener(v -> {
            String idStr = idEt.getText().toString().trim();

            if (!idStr.isEmpty()) {
                int rowsAffected = dbHelper.deleteData(idStr);
                if (rowsAffected > 0) {
                    clearInputFields();
                    loadRecords();
                }
            } else {
                Toast.makeText(DbTestActivity.this, "Please enter ID to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadRecords() {
        recordList.clear();
        Cursor cursor = dbHelper.selectData();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                recordList.add(new Record(id, name, address));
            }
            cursor.close();
        }
        adapter.setRecords(recordList);
    }

    private void clearInputFields() {
        idEt.setText("");
        nameEt.setText("");
        addressEt.setText("");
    }
}