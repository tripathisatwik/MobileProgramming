package com.example.chapter7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LocalApiActivity extends AppCompatActivity {

    private TextView responseTextView;
    private EditText nameEditText;
    private EditText emailEditText;
    private Button addUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_local_api);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //responseTextView = findViewById(R.id.localApiTextView);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        addUserButton = findViewById(R.id.addUserButton);

        getSingleUser();
        // getMultipleUser();

        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewUser();
            }
        });
    }

    public void getSingleUser() {
        String url = "http://192.168.120.73:8080/get_user.php";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, //HTTP methods like get,post,put,delete,....
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            StringBuilder userInfo = new StringBuilder();
                            String id = response.getString("id");
                            String name = response.getString("name");
                            String email = response.getString("email");
                            //Extract other necessary fields from response object

                            userInfo.append("Id: ").append(id)
                                    .append("\nName: ").append(name)
                                    .append("\nEmail: ").append(email);
                            responseTextView.setText(userInfo.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LocalApiActivity.this, "Error parsing JSON response", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(LocalApiActivity.this, "Error in fetching data", Toast.LENGTH_LONG).show();

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public void addNewUser() {
        String url = "http://192.168.120.249/androidBackend/insert_user.php";
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(LocalApiActivity.this, "Please enter name and email", Toast.LENGTH_SHORT).show();
            return;
        }
    }

//    public void getMultipleUser(){
//        String url = "https://jsonplaceholder.typicode.com/users";
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
//                Request.Method.GET,
//                url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray jsonArrayResponse) {
//                        try {
//                            StringBuilder userInfo = new StringBuilder();
//
//                            for(int i=0;i<jsonArrayResponse.length();i++){
//                                JSONObject response = jsonArrayResponse.getJSONObject(i);
//                                String name = response.getString("name");
//                                String email = response.getString("email");
//                                String phone = response.getString("phone");
//                                //Extract other necessary fields from response object
//
//                                userInfo.append("Name: ").append(name)
//                                        .append("\nEmail: ").append(email)
//                                        .append("\nPhone: ").append(phone).append("\n\n");
//                            }
//                            responseTextView.setText(userInfo.toString());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            Toast.makeText(LocalApiActivity.this,"Error parsing JSON array",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                        Toast.makeText(LocalApiActivity.this,"Error fetching multiple users",Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
//        requestQueue.add(jsonArrayRequest);
//    }
}