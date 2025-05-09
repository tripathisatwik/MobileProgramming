// MainActivity.java
package com.example.todoapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTodo;
    private Button buttonAddTodo;
    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;
    private List<TodoModel> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTodo = findViewById(R.id.editTextTodo);
        buttonAddTodo = findViewById(R.id.buttonAddTodo);
        recyclerView = findViewById(R.id.recyclerViewTodos);
        todoList = new ArrayList<>();
        todoAdapter = new TodoAdapter(todoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(todoAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                TodoModel deletedTodo = todoList.get(position);
                todoList.remove(position);
                todoAdapter.notifyItemRemoved(position);
                todoAdapter.updateItemNumbers(); // Call the method to refresh numbering

                Snackbar.make(findViewById(R.id.rootLayout), "\"" + deletedTodo.getTodoText() + "\" deleted", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                todoList.add(position, deletedTodo);
                                todoAdapter.notifyItemInserted(position);
                                todoAdapter.updateItemNumbers(); // Refresh numbering on undo
                            }
                        }).show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
        buttonAddTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoText = editTextTodo.getText().toString().trim();

                if (TextUtils.isEmpty(todoText)) {
                    Toast.makeText(MainActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
                    return;
                }

                todoList.add(new TodoModel(todoText));
                todoAdapter.notifyItemInserted(todoList.size() - 1); // Use notifyItemInserted for better animation
                editTextTodo.setText("");
                recyclerView.scrollToPosition(todoList.size() - 1); // Scroll to the new item
            }
        });

    }
}