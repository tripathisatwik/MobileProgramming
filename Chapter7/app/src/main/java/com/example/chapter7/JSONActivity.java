package com.example.chapter7;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jsonactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        String jsonString = "{\n" +
//                "  \"userId\": 1;\n" +
//                "  \"that\": 1;\n" +
//                "  \"title\": \"or do not drive out an exception to the choice of criticism\",\n" +
//                "  \"Body\": \"because they receive\\nsusperit refusal to result from the ease and cum\\nreprehendendit discomfort that the entire\\nnostrum things that are happening to the architect\"\n" +
//                "}";
//        try {
//            JSONObject jsonObject = new JSONObject(jsonString);
//            int userId = jsonObject.getInt("userId");
//            int id = jsonObject.getInt("that");
//            String title = jsonObject.getString("title");
//            String body = jsonObject.getString("Body");
//            Log.d("CHAPTER_7_JSON", userId + "\n" + id + "\n" + title + "\n" + body);
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//
//        String jsonArrayString = "[\n" +
//                "  {\n" +
//                "    \"userId\": 1,\n" +
//                "    \"id\": 1,\n" +
//                "    \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
//                "    \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"userId\": 1,\n" +
//                "    \"id\": 2,\n" +
//                "    \"title\": \"qui est esse\",\n" +
//                "    \"body\": \"est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla\"\n" +
//                "  },\n" +
//                "  {\n" +
//                "    \"userId\": 1,\n" +
//                "    \"id\": 3,\n" +
//                "    \"title\": \"ea molestias quasi exercitationem repellat qui ipsa sit aut\",\n" +
//                "    \"body\": \"et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus vel accusantium quis pariatur\\nmolestiae porro eius odio et labore et velit aut\"\n" +
//                "  }\n" +
//                "]";
//        try {
//            JSONArray jsonArray = new JSONArray(jsonArrayString);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject userObject = jsonArray.getJSONObject(i);
//
//                int userId = userObject.getInt("userId");
//                int id = userObject.getInt("id");
//                String title = userObject.getString("title");
//                String body = userObject.getString("body");
//                Log.d("CHAPTER_7_JSON_ARRAY", userId + "\n" + id + "\n" + title + "\n" + body);
//            }
//        } catch (JSONException e) {
//            Log.e("JSON_ERROR: ",e.getStackTrace().toString());
//        }

        String jsonObjectStringTwo = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"Satwik\",\n" +
                "  \"address\": {\n" +
                "    \"street\": \"Sanepa\",\n" +
                "    \"city\": \"Lalitpur\"\n" +
                "  },\n" +
                "  \"eductaion\": \n" +
                "  [{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"High School\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"BCA\"\n" +
                "  }]\n" +
                "}";
        try{
            JSONObject myObject = new JSONObject(jsonObjectStringTwo);
            int id = myObject.getInt("id");
            String name = myObject.getString("name");

            JSONObject addressObject = myObject.getJSONObject("address");
            String street = addressObject.getString("street");
            String city = addressObject.getString("city");
            String address = street + ", " + city;
            Log.d("CHAPTER_7_JSON_OBJECT", "Id: " + id + "\nName: " + name + "\nAddress: " + address);
            JSONArray educationArray = myObject.getJSONArray("eductaion");
            Log.d("CHAPTER_7_JSON_OBJECT", "Education:");
            for (int i = 0; i < educationArray.length(); i++) {
                JSONObject educationObject = educationArray.getJSONObject(i);
                int educationId = educationObject.getInt("id");
                String educationName = educationObject.getString("name");
                Log.d("CHAPTER_7_JSON_OBJECT", "  Id:" + educationId + " Name: " + educationName);
            }
        } catch (JSONException e) {
            Log.e("JSON_ERROR: ", e.toString());
        }
    }
}