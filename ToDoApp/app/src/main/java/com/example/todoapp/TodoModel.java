package com.example.todoapp;
public class TodoModel {
    private String todoText;
    public TodoModel(String todoText) {
        this.todoText = todoText;
    }
    public String getTodoText() {
        return todoText;
    }
    public void setTodoText(String todoText) {
        this.todoText = todoText;
    }
}
