// TodoAdapter.java
package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private List<TodoModel> todoList;

    public TodoAdapter(List<TodoModel> todoList) {
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.bind(position + 1, todoList.get(position).getTodoText());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public void setTasks(List<TodoModel> newTasks) {
        this.todoList = newTasks;
        notifyDataSetChanged();
    }

    // New method to update the numbering of the items
    public void updateItemNumbers() {
        notifyDataSetChanged();
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView todoText;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            todoText = itemView.findViewById(R.id.textViewTodo);
        }

        public void bind(int number, String text) {
            todoText.setText(number + ". " + text);
        }
    }
}